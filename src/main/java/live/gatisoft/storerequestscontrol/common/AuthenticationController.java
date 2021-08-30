package live.gatisoft.storerequestscontrol.common;

import live.gatisoft.storerequestscontrol.common.dto.AuthDataDtoResponse;
import live.gatisoft.storerequestscontrol.common.dto.UserAuthDtoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/authentication")
@CrossOrigin
public class AuthenticationController {

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserAuthDtoRequest authData) throws Exception {
        return ResponseEntity.ok(new AuthDataDtoResponse("12341234", "1234512345"));
//        authenticate(authData.login, authData.password);
//        Optional<User> response = userRepository.findByLoginAndDeletedAndActiveIsTrue(authData.login, false);
//        if (response.isPresent()) {
//            User user = response.get();
//            final String token = jwtTokenUtil.generateToken(user.getLogin(), user.getRoles());
//            final String refreshToken = jwtTokenUtil.generateRefreshToken(user.getLogin());
//            return ResponseEntity.ok(new AuthDataDtoResponse(token, refreshToken, new SecureUserDataDtoResponse(user)));
//        }
//        return ResponseEntity.notFound().build();
    }

}
