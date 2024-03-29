package mandiri.apirentmotorcycle.controller;

import lombok.RequiredArgsConstructor;
import mandiri.apirentmotorcycle.constant.AppPath;
import mandiri.apirentmotorcycle.dto.request.RentRequest;
import mandiri.apirentmotorcycle.dto.response.FinishRentResponse;
import mandiri.apirentmotorcycle.dto.response.RentResponse;
import mandiri.apirentmotorcycle.service.RentService;
import mandiri.apirentmotorcycle.util.map.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.RENT)
public class RentController {
    private final RentService rentService;

    @GetMapping
    public ResponseEntity<?> getAllRents() {
        List<RentResponse> rents = rentService.getAllRents();

        return ResponseMapper.commonResponse(rents,
                "Successfully get all rents",
                HttpStatus.OK);
    }

    @GetMapping(AppPath.BY_ID)
    public ResponseEntity<?> getRentById(@PathVariable String id) {
        RentResponse rent = rentService.findRentById(id);

        return ResponseMapper.commonResponse(rent,
                "Successfully get rent",
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createRent(@RequestBody RentRequest rentRequest) {
        RentResponse rent = rentService.createRental(rentRequest);

        return ResponseMapper.commonResponse(rent,
                "Successfully create rent",
                HttpStatus.CREATED);
    }

    @PutMapping(AppPath.BY_ORDER_ID)
    public ResponseEntity<?> finishRent(@PathVariable String orderId) {
        FinishRentResponse rent = rentService.finishRent(orderId);

        return ResponseMapper.commonResponse(rent,
                "Successfully finishing rent transaction",
                HttpStatus.OK);
    }

}
