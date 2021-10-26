package live.gatisoft.storerequestscontrol.common.schema;

import live.gatisoft.storerequestscontrol.security.services.BCryptService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String document;

    private String passwordResetToken;

    private String encryptedPassword;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    private void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public void setPassword(String plainPassword) {
        String encryptedPassword = BCryptService.encodeStringForPassword(plainPassword);
        setEncryptedPassword(encryptedPassword);
    }

}
