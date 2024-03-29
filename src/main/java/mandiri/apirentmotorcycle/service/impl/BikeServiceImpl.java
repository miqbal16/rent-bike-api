package mandiri.apirentmotorcycle.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mandiri.apirentmotorcycle.constant.BikeStatus;
import mandiri.apirentmotorcycle.dto.request.BikeRequest;
import mandiri.apirentmotorcycle.dto.response.BikeResponse;
import mandiri.apirentmotorcycle.entity.Bike;
import mandiri.apirentmotorcycle.repository.BikeRepository;
import mandiri.apirentmotorcycle.service.BikeService;
import mandiri.apirentmotorcycle.util.map.ResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BikeServiceImpl implements BikeService {


    private final BikeRepository bikeRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public BikeResponse create(BikeRequest bikeRequest) {

        UUID userId = UUID.randomUUID();

        Bike bike = Bike.builder()
                .id(userId.toString())
                .brand(bikeRequest.getBrandName())
                .type(bikeRequest.getTypeBike())
                .color(bikeRequest.getColor())
                .licensePlate(bikeRequest.getLicensePlate())
                .productionYear(bikeRequest.getProductionYear())
                .bikeStatus(BikeStatus.AVAILABLE)
                .rentBikePrice(bikeRequest.getPricePerDay())
                .build();


        bikeRepository.createBike(
                bike.getId(),
                bike.getBrand(),
                bike.getType(),
                bike.getColor(),
                bike.getProductionYear(),
                bike.getLicensePlate(),
                bike.getBikeStatus(),
                bike.getRentBikePrice()
        );


        return ResponseMapper.bikeResponse(bike);
    }

    public List<BikeResponse> findAll() {
        List<Bike> bikes = bikeRepository.findAllBikes()
                .orElseThrow();

        return bikes.stream().map(
                ResponseMapper::bikeResponse
        ).toList();
    }

    @Override
    public BikeResponse getById(String id) {
        Bike bike = bikeRepository.findBikeById(id)
                .orElseThrow(
                        () -> new RuntimeException("Bike with id "
                        + id + " is not found"));

        return ResponseMapper.bikeResponse(bike);
    }

    @Override
    public BikeResponse update(BikeRequest bikeRequest) {
        Bike bike = bikeRepository.findBikeById(bikeRequest.getId())
                .orElseThrow(() -> new RuntimeException("Bike with id "
                                + bikeRequest.getTypeBike() + " is not found"));

        if (bike.getBikeStatus() == BikeStatus.BOOKED) {
            throw new RuntimeException("Bike Booked can not update data bike");
        }

        bike.setBrand(bikeRequest.getBrandName());
        bike.setType(bikeRequest.getTypeBike());
        bike.setColor(bikeRequest.getColor());
        bike.setLicensePlate(bikeRequest.getLicensePlate());
        bike.setProductionYear(bikeRequest.getProductionYear());
        bike.setBikeStatus(BikeStatus.AVAILABLE);
        bike.setRentBikePrice(bikeRequest.getPricePerDay());

        bikeRepository.updateBike(
                bike.getId(),
                bike.getBrand(),
                bike.getType(),
                bike.getColor(),
                bike.getProductionYear(),
                bike.getLicensePlate(),
                bike.getBikeStatus(),
                bike.getRentBikePrice()
        );

        return ResponseMapper.bikeResponse(bike);
    }

    public void delete(String id) {
        Bike bike = bikeRepository.findBikeById(id)
                .orElseThrow(() -> new RuntimeException("Bike with id "
                        + id + " is not found"));

        bikeRepository.removeBike(bike.getId());
    }
}
