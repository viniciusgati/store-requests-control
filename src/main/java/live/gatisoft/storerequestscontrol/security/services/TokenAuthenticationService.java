package live.gatisoft.storerequestscontrol.security.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Locale;

@Component
public class TokenAuthenticationService {

//    @Autowired
//    private JwtUtils jwtTokenUtil;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    static final String TOKEN_PREFIX = "Bearer";
//    static final String HEADER_STRING = "Authorization";
//
//    public void addAuthentication(HttpServletResponse response, String username) {
//        Optional<User> data = userRepository.findByLoginAndDeletedIsFalse(username);
//        if (data.isPresent()) {
//            User user = data.get();
//            String token = jwtTokenUtil.generateToken(username, user.getRoles());
//            response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + token);
//        } else {
//            String token = jwtTokenUtil.generateToken(username, new ArrayList<>());
//            response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + token);
//        }
//    }
//
//    public Authentication getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader(HEADER_STRING);
//        if (token == null|| token.trim().toLowerCase(Locale.ROOT).equals("bearer"))
//            return null;
//        else {
//            try {
//                String cleanToken = token.replace(TOKEN_PREFIX + " ", "");
//                final String username = jwtTokenUtil.getUsernameFromToken(token);
//                return new UsernamePasswordAuthenticationToken(username, null, jwtTokenUtil.getRoles(token));
//            } catch (Exception ex) { //invalid token gerenates exception
//                return null;
//            }
//        }
//    }

}