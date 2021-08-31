package live.gatisoft.storerequestscontrol.common;

import live.gatisoft.storerequestscontrol.common.dto.AuthDataDtoResponse;
import live.gatisoft.storerequestscontrol.common.dto.UserAuthDtoRequest;
import live.gatisoft.storerequestscontrol.common.repository.UserRepository;
import live.gatisoft.storerequestscontrol.common.schema.User;
import live.gatisoft.storerequestscontrol.security.services.BCryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/authentication")
@CrossOrigin
public class AuthenticationController {

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserAuthDtoRequest authData) throws Exception {
        Optional<User> userFound = userRepository.findFirstByEmail(authData.email);
        if (userFound.isPresent()) {
            User user = userFound.get();
            boolean passwordMatches = BCryptService.passwordMatches(authData.getPassword(), user.getEncryptedPassword());
//            authenticate(authData.email, authData.password); // internal checks, yet I don't know what this does
            if (passwordMatches)
                return ResponseEntity.ok(new AuthDataDtoResponse(user));
        }
        return ResponseEntity.notFound().build();
    }

//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }

}
