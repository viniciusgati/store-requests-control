package live.gatisoft.storerequestscontrol.common.schema;

import live.gatisoft.storerequestscontrol.security.services.BCryptService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String document;

    private String passwordResetToken;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PRIVATE)
    private String encryptedPassword;

    public void setPassword(String plainPassword) {
        String encryptedPassword = BCryptService.encodeStringForPassword(plainPassword);
        setEncryptedPassword(encryptedPassword);
    }

}
