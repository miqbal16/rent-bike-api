package mandiri.apirentmotorcycle.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoginRequest {
    @NotBlank(message = "You must input username")
    private String username;
    @NotBlank(message = "You must input password")
    private String password;
}
