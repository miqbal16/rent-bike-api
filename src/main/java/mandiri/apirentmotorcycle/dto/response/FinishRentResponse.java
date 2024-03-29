package mandiri.apirentmotorcycle.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FinishRentResponse {
    private String customerId;
    private Long penaltyPayment;
}
