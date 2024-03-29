package mandiri.apirentmotorcycle.controller;

import lombok.RequiredArgsConstructor;
import mandiri.apirentmotorcycle.constant.AppPath;
import mandiri.apirentmotorcycle.dto.request.BikeRequest;
import mandiri.apirentmotorcycle.dto.response.BikeResponse;
import mandiri.apirentmotorcycle.service.BikeService;
import mandiri.apirentmotorcycle.util.map.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.BIKE)
public class BikeController {
    private final BikeService  bikeService;

    @GetMapping
    public ResponseEntity<?> getAllBikes() {
        List<BikeResponse> bikes = bikeService.findAll();

        return ResponseMapper.commonResponse(bikes,
                "Successfully get all bikes",
                HttpStatus.OK);
    }

    @GetMapping(AppPath.BY_ID)
    public ResponseEntity<?> getBikeById(@PathVariable String id) {
        BikeResponse bike = bikeService.getById(id);

        return ResponseMapper.commonResponse(bike,
                "Successfully get bike",
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createBike(@RequestBody BikeRequest bikeRequest) {
        BikeResponse bike = bikeService.create(bikeRequest);

        return ResponseMapper.commonResponse(bike,
                "Successfully create bike",
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateBike(@RequestBody BikeRequest bikeRequest) {
        BikeResponse bike = bikeService.update(bikeRequest);

        return ResponseMapper.commonResponse(bike,
                "Successfully update bike",
                HttpStatus.OK);
    }

    @DeleteMapping(AppPath.BY_ID)
    public ResponseEntity<?> removeBike(@PathVariable String id) {
        bikeService.delete(id);

        return ResponseMapper.commonResponse(null,
                "Successfully delete bike",
                HttpStatus.OK);
    }

}
