package mandiri.apirentmotorcycle.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mandiri.apirentmotorcycle.constant.BikeStatus;
import mandiri.apirentmotorcycle.constant.Status;
import mandiri.apirentmotorcycle.dto.request.RentRequest;
import mandiri.apirentmotorcycle.dto.response.CustomerResponse;
import mandiri.apirentmotorcycle.dto.response.FinishRentResponse;
import mandiri.apirentmotorcycle.dto.response.RentResponse;
import mandiri.apirentmotorcycle.entity.Bike;
import mandiri.apirentmotorcycle.entity.Customer;
import mandiri.apirentmotorcycle.entity.Rent;
import mandiri.apirentmotorcycle.entity.RentTransaction;
import mandiri.apirentmotorcycle.repository.BikeRepository;
import mandiri.apirentmotorcycle.repository.RentRepository;
import mandiri.apirentmotorcycle.repository.RentTransactionRepository;
import mandiri.apirentmotorcycle.service.CustomerService;
import mandiri.apirentmotorcycle.service.RentService;
import mandiri.apirentmotorcycle.util.map.ResponseMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final BikeRepository bikeRepository;
    private final CustomerService customerService;
    private final RentTransactionRepository rentTransactionRepository;

    @Override
    public List<RentResponse> getAllRents() {
        List<Rent> rents = rentRepository.findAllRents().orElseThrow();

        return rents.stream().map(
                rent -> {
                    CustomerResponse customer = customerService.findById(rent.getCustomer().getId());

                    RentTransaction rentTransaction = rentTransactionRepository
                            .findRentTransactionByRentId(rent.getId())
                            .orElseThrow();

                    Bike bike = bikeRepository.findBikeById(rentTransaction.getBike().getId())
                            .orElseThrow();

                    return ResponseMapper.rentResponse(rent, bike, customer);
                }
        ).toList();
    }

    @Override
    public RentResponse findRentById(String id) {

        Rent rent = rentRepository.getRentById(id)
                .orElseThrow(() -> new RuntimeException("Rent with id "
                        + id + " is not found"));

        CustomerResponse customer = customerService.findById(rent.getCustomer().getId());

        RentTransaction rentTransaction = rentTransactionRepository
                .findRentTransactionByRentId(rent.getId())
                .orElseThrow();

        Bike bike = bikeRepository.findBikeById(rentTransaction.getBike().getId())
                .orElseThrow();

        return ResponseMapper.rentResponse(rent, bike, customer);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RentResponse createRental(RentRequest rentRequest) {

        Bike bike = bikeRepository.findBikeById(rentRequest.getBikeId()).orElseThrow();
        CustomerResponse customerResponse = customerService.findById(rentRequest.getCustomerId());

        if (customerResponse.getUserStatus() == Status.NOT_ACTIVE) {
            throw new RuntimeException("Rent only customer active");
        }

        if (bike.getBikeStatus() == BikeStatus.BOOKED) {
            throw new RuntimeException("Motorbikes that have been booked cannot be rented");
        }

        Customer customer = Customer.builder()
                .id(customerResponse.getId())
                .customerName(customerResponse.getCustomerName())
                .email(customerResponse.getEmail())
                .phone(customerResponse.getPhone())
                .address(customerResponse.getAddress())
                .identityNumber(customerResponse.getIdentityNumber())
                .build();

        bike.setBikeStatus(BikeStatus.BOOKED);

        bikeRepository.updateBike(bike.getId(),
                bike.getBrand(),
                bike.getType(),
                bike.getColor(),
                bike.getProductionYear(),
                bike.getLicensePlate(),
                bike.getBikeStatus(),
                bike.getRentBikePrice());

        LocalDateTime startRent = LocalDateTime.now();
        Rent rent = Rent.builder()
                .dateRent(startRent)
                .dailyRental(rentRequest.getRentDaily())
                .guarantee(rentRequest.getGuarantee())
                .customer(customer)
                .build();

        rentRepository.saveAndFlush(rent);

        RentTransaction rentTransaction = RentTransaction.builder()
                .rent(rent)
                .totalPrice(bike.getRentBikePrice() * rentRequest.getRentDaily())
                .lateReturnFines(0L)
                .hasReturnGuarantee(false)
                .endDateRent(startRent.plusDays(rentRequest.getRentDaily()))
                .bike(bike)
                .build();

        rentTransactionRepository.save(rentTransaction);

        return ResponseMapper.rentResponse(rent, bike, customerResponse);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public FinishRentResponse finishRent(String rentId) {

        RentTransaction rentTransaction = rentTransactionRepository
                .findRentTransactionByRentId(rentId)
                .orElseThrow(() -> new RuntimeException("Rent transaction with rent id "
                        + rentId + " is not found"));

        Rent rent = rentRepository.getRentById(rentId)
                .orElseThrow(() -> new RuntimeException("Rent with id "
                        + rentId + " is not found"));

        Bike bike = bikeRepository.findBikeById(rentTransaction.getBike().getId()).orElseThrow();
        CustomerResponse customer = customerService.findById(rent.getCustomer().getId());

        LocalDateTime dateNow = LocalDateTime.now();
        long penaltyPayment = 0L;

        if (rentTransaction.isHasReturnGuarantee()) {
            throw new RuntimeException("Rent transaction already finished");
        }

        if (dateNow.isAfter(rentTransaction.getEndDateRent())) {
            long daysBetween = ChronoUnit.DAYS.between(rentTransaction.getEndDateRent(), dateNow);
            penaltyPayment += (daysBetween * 50_000L);
        }

        rentTransaction.setLateReturnFines(penaltyPayment);
        rentTransaction.setHasReturnGuarantee(true);

        bike.setBikeStatus(BikeStatus.AVAILABLE);

        bikeRepository.saveAndFlush(bike);
        rentTransactionRepository.save(rentTransaction);

        return ResponseMapper.finishRentResponse(customer.getId(), penaltyPayment);
    }
}
