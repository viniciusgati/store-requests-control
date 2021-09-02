package live.gatisoft.storerequestscontrol.common.dto;

import lombok.Data;

@Data
public class AuthDataDtoResponse {
    public String token;
    public String refreshToken;

    public AuthDataDtoResponse(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
