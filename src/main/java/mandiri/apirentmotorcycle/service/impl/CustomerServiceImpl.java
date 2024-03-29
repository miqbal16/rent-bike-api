package mandiri.apirentmotorcycle.service.impl;

import lombok.RequiredArgsConstructor;
import mandiri.apirentmotorcycle.dto.request.CustomerRequest;
import mandiri.apirentmotorcycle.dto.response.CustomerResponse;
import mandiri.apirentmotorcycle.entity.Customer;
import mandiri.apirentmotorcycle.repository.CustomerRepository;
import mandiri.apirentmotorcycle.service.CustomerService;
import mandiri.apirentmotorcycle.util.map.ResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customerList = customerRepository.findAllCustomers()
                .orElseThrow();

        return customerList.stream().map(
                ResponseMapper::customerResponse
        ).toList();
    }

    @Override
    public CustomerResponse findById(String id) {
        Customer customer = customerRepository.findCustomerById(id)
                .orElseThrow(() -> new RuntimeException(""));
        return ResponseMapper.customerResponse(customer);
    }

    @Override
    public Customer findByCredentialId(String credentialId) {
        return customerRepository.findByCredentialId(credentialId)
                .orElseThrow();
    }

    @Override
    public CustomerResponse update(CustomerRequest customerRequest) {

        Customer customer = customerRepository.findCustomerById(customerRequest.getId())
                .orElseThrow(() -> new RuntimeException("Customer with id "
                        + customerRequest.getId() + " is not found"));

        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setAddress(customerRequest.getAddress());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        customer.setIdentityNumber(customer.getIdentityNumber());

        customerRepository.updateCustomer(
                customer.getId(),
                customer.getCustomerName(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getIdentityNumber()
        );

        return ResponseMapper.customerResponse(customer);
    }

    @Override
    public void delete(String id) {

    }
}
