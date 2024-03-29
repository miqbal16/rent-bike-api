package mandiri.apirentmotorcycle.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandiri.apirentmotorcycle.constant.BikeStatus;
import mandiri.apirentmotorcycle.constant.BikeType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BikeRequest {
    private String id;
    private String brandName;
    private BikeType typeBike;
    private String color;
    private String licensePlate;
    private int productionYear;
    private long pricePerDay;
    private BikeStatus bikeStatus;
}
