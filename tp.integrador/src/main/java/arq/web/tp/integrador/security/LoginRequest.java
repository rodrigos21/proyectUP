package arq.web.tp.integrador.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
     @JsonProperty("username")
     String username;
     @JsonProperty("password")
     String password;
}
