package live.gatisoft.storerequestscontrol.common.controllers;

import live.gatisoft.storerequestscontrol.common.dto.UserAuthDtoRequest;
import live.gatisoft.storerequestscontrol.common.repository.UserRepository;
import live.gatisoft.storerequestscontrol.common.schema.User;
import live.gatisoft.storerequestscontrol.security.services.BCryptService;
import live.gatisoft.storerequestscontrol.security.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/authentication")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserAuthDtoRequest authData) throws Exception {
        Optional<User> userFound = userRepository.findFirstByEmail(authData.getEmail());
        if (userFound.isPresent()) {
            User user = userFound.get();
            boolean passwordMatches = BCryptService.passwordMatches(authData.getPassword(), user.getEncryptedPassword());
            if (passwordMatches)
                return ResponseEntity.ok(jwtService.GetTokensFor(user));
        }
        return ResponseEntity.notFound().build();
    }

}
