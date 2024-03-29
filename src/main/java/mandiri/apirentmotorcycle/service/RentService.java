package mandiri.apirentmotorcycle.service;

import mandiri.apirentmotorcycle.dto.request.RentRequest;
import mandiri.apirentmotorcycle.dto.response.FinishRentResponse;
import mandiri.apirentmotorcycle.dto.response.RentResponse;

import java.util.List;

public interface RentService {
    List<RentResponse> getAllRents();
    RentResponse createRental(RentRequest request);
    FinishRentResponse finishRent(String rentId);
    RentResponse findRentById(String id);
}
