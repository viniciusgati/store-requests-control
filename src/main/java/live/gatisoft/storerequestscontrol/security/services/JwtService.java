package live.gatisoft.storerequestscontrol.security.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import live.gatisoft.storerequestscontrol.common.dto.AuthDataDtoResponse;
import live.gatisoft.storerequestscontrol.common.schema.User;
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

    //returns the username of the jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //returns expiration date of the jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //returns all information inside the token, to do that we need the secret key
    private Claims getAllClaimsFromToken(String token) {
        token = token.replace("Bearer ", "");
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generates the token for the user
    public String generateToken(String userLogin, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLES", roles);
        return doGenerateToken(claims, userLogin);
    }

    public String generateRefreshToken(String userLogin) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateRefreshToken(claims, userLogin);
    }

    //creates the token and defines his expiration date
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(getSigningKey()).compact();
    }

    private String doGenerateRefreshToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ((JWT_TOKEN_VALIDITY * 1000) * 5)))
                .signWith(getSigningKey()).compact();
    }

    private String doGenerateResetPasswordToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_CHANGE_EMAIL_VALIDITY * 1000))
                .signWith(getSigningKey()).compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateResetPasswordToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateResetPasswordToken(claims, user.getEmail());
    }

    public String generateExpiredToken(String userLogin) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateExpiredToken(claims, userLogin);
    }

    private String doGenerateExpiredToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() - 1000))
                .signWith(getSigningKey()).compact();
    }

    public Collection<? extends GrantedAuthority> getRoles(String token) {
        Claims claims = getAllClaimsFromToken(token);
        List<String> roles = (List<String>) claims.get("ROLES");
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new ArrayList<>(authorities);
    }

    public AuthDataDtoResponse GetTokensFor(User user) {
        final String token = generateToken(user.getEmail(), new ArrayList<>());
        final String refreshToken = generateRefreshToken(user.getEmail());
        return new AuthDataDtoResponse(token, refreshToken);
    }
}
