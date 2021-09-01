package live.gatisoft.storerequestscontrol.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtService {
    public static final long JWT_TOKEN_VALIDITY = 2 * 60 * 60;
    public static final long JWT_TOKEN_CHANGE_EMAIL_VALIDITY = 15 * 60;

    @Value("${jwt.secret}")
    private String secret;
}
