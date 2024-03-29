package mandiri.apirentmotorcycle.util.map;

import mandiri.apirentmotorcycle.dto.response.*;
import mandiri.apirentmotorcycle.entity.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseMapper {
    public static ResponseEntity<?> commonResponse(
            Object data, String message, HttpStatus statusCode) {
        CommonResponse<Object> response = CommonResponse.builder()
                .statusCode(statusCode.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(statusCode)
                .body(response);
    }

    public static CustomerResponse customerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .identityNumber(customer.getIdentityNumber())
                .userStatus(customer.getCustomerStatus())
                .build();
    }

    public static BikeResponse bikeResponse(Bike bike) {
        return BikeResponse.builder()
                .id(bike.getId())
                .brand(bike.getBrand())
                .typeBike(bike.getType().name())
                .color(bike.getColor())
                .productionYear(bike.getProductionYear())
                .licensePlate(bike.getLicensePlate())
                .bikeStatus(bike.getBikeStatus().name())
                .rentBikePrice(bike.getRentBikePrice())
                .build();
    }

    public static RentResponse rentResponse(Rent rent, Bike bike, CustomerResponse customer) {
        return RentResponse.builder()
                .id(rent.getId())
                .bike(bike)
                .dailyRental(rent.getDailyRental())
                .customer(customer)
                .guarantee(rent.getGuarantee().name())
                .build();
    }

    public static FinishRentResponse finishRentResponse(String customerId, long penaltyPayment) {
        return FinishRentResponse.builder()
                .customerId(customerId)
                .penaltyPayment(penaltyPayment)
                .build();
    }

    public static RegisterResponse registerResponse(UserCredential userCredential) {
        return RegisterResponse.builder()
                .username(userCredential.getUserName())
                .role(userCredential.getRole().getName().toString())
                .build();
    }

    public static LoginResponse loginResponse(String token, AppUser appUser) {
        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .build();
    }
}
