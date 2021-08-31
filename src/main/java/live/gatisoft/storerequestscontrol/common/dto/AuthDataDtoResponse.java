package live.gatisoft.storerequestscontrol.common.dto;

import live.gatisoft.storerequestscontrol.common.schema.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDataDtoResponse {
    public String token;
    public String refreshToken;

    public AuthDataDtoResponse(User user) {
        this.token = "test";
        this.refreshToken = "refresh";
    }
}
