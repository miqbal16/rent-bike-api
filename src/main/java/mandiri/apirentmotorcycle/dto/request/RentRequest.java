package mandiri.apirentmotorcycle.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandiri.apirentmotorcycle.constant.Guarantee;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RentRequest {
    private String id;
    private String customerId;
    private Guarantee guarantee;
    private int rentDaily;
    private String bikeId;
}
