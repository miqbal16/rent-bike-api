package mandiri.apirentmotorcycle.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mandiri.apirentmotorcycle.constant.Status;
import mandiri.apirentmotorcycle.entity.UserCredential;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private String id;
    private String customerName;
    private String address;
    private String phone;
    private String email;
    private String identityNumber;
    private Status userStatus;
}