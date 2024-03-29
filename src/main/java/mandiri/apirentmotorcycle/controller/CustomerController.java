package mandiri.apirentmotorcycle.controller;

import lombok.RequiredArgsConstructor;
import mandiri.apirentmotorcycle.constant.AppPath;
import mandiri.apirentmotorcycle.dto.request.CustomerRequest;
import mandiri.apirentmotorcycle.dto.response.CustomerResponse;
import mandiri.apirentmotorcycle.service.CustomerService;
import mandiri.apirentmotorcycle.util.map.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.CUSTOMER)
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<CustomerResponse> customers = customerService.findAll();

        return ResponseMapper.commonResponse(customers,
                "Successfully get all data customers",
                HttpStatus.OK);
    }

    @GetMapping(AppPath.BY_ID)
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {
        CustomerResponse customer = customerService.findById(id);

        return ResponseMapper.commonResponse(customer,
                "Successfully get customer",
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customer = customerService.update(customerRequest);

        return ResponseMapper.commonResponse(customer,
                "Successfully update customer",
                HttpStatus.OK);
    }
}
