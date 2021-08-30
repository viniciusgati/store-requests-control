package live.gatisoft.storerequestscontrol.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDtoRequest {
    public String email;
    public String password;
}
