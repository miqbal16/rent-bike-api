package mandiri.apirentmotorcycle.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandiri.apirentmotorcycle.constant.BikeStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BikeResponse {
    private String id;
    private String brand;
    private String typeBike;
    private String color;
    private String licensePlate;
    private int productionYear;
    private String bikeStatus;
    private Long rentBikePrice;
}
