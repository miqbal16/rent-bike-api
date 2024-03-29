package mandiri.apirentmotorcycle.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RentTransactionRequest {
    private String bikeId;
    private int dailyRental;
}
