package mandiri.apirentmotorcycle.service;

import mandiri.apirentmotorcycle.dto.request.BikeRequest;
import mandiri.apirentmotorcycle.dto.response.BikeResponse;

import java.util.List;

public interface BikeService {
    BikeResponse create(BikeRequest bikeRequest);
    List<BikeResponse> findAll();
    BikeResponse getById(String id);
    BikeResponse update(BikeRequest bikeRequest);
    void delete(String id);
}
