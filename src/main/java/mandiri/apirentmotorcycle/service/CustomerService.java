package mandiri.apirentmotorcycle.service;

import mandiri.apirentmotorcycle.dto.request.CustomerRequest;
import mandiri.apirentmotorcycle.dto.response.CustomerResponse;
import mandiri.apirentmotorcycle.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer findByCredentialId(String credentialId);
    List<CustomerResponse> findAll();
    CustomerResponse findById(String id);
    CustomerResponse update(CustomerRequest customerRequest);
    void delete(String id);
}
