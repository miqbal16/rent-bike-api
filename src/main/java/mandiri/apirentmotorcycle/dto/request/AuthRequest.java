package mandiri.apirentmotorcycle.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AuthRequest {
    @NotBlank(message = "You must input username")
    private String username;

    @NotBlank(message = "You must input password")
    private String password;

    @NotBlank(message = "You must input customer name")
    private String customerName;

    @NotBlank(message = "You must input address")
    private String address;

    @NotBlank(message = "You must input email")
    @Email(message = "Email must valid")
    private String email;

    @NotBlank(message = "You must input phone")
    private String phone;

    @NotBlank(message = "You must input identity number")
    private String identityNumber;
}
