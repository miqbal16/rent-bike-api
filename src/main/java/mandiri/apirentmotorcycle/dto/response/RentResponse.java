package mandiri.apirentmotorcycle.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandiri.apirentmotorcycle.constant.Guarantee;
import mandiri.apirentmotorcycle.entity.Bike;
import mandiri.apirentmotorcycle.entity.Customer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RentResponse {
    private String id;
    private CustomerResponse customer;
    private int dailyRental;
    private String guarantee;
    private Bike bike;
}
