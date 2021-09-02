package live.gatisoft.storerequestscontrol.common.controllers;

import live.gatisoft.storerequestscontrol.common.repository.UserRepository;
import live.gatisoft.storerequestscontrol.common.schema.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Post to /authentication/authenticate should return 404 for invalid credentials")
    public void GetAuthLoginWithInvalidCredentials() throws Exception {
        assertTrue(true);
        mvc.perform(
                MockMvcRequestBuilders
                        .post("/authentication/authenticate")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"nonexistent\", \"password\": \"test\"}")
                        .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is(404));
    }


    @Test
    @DisplayName("Post to /authentication/authenticate should return tokens for valid user")
    public void GetAuthLogin() throws Exception {
        String samplePassword = "12341234";
        User user = new User();
        user.setEmail("valid@email.com");
        user.setPassword(samplePassword);
        userRepository.save(user);

        mvc.perform(
            MockMvcRequestBuilders
                .post("/authentication/authenticate")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \""+user.getEmail()+"\", \"password\": \""+samplePassword+"\"}")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(jsonPath("token").exists())
        .andExpect(jsonPath("refreshToken").exists())
        .andExpect(status().is(200));
    }

    @Test
    @DisplayName("Post to /authentication/authenticate should return 404 if password does not match")
    public void GetAuthLoginWithInvalidPasswordShouldFail() throws Exception {
        String samplePassword = "12341234";
        String wrongPassword = "asdf";
        User user = new User();
        user.setEmail("valid@email.com");
        user.setPassword(samplePassword);
        userRepository.save(user);

        mvc.perform(
                MockMvcRequestBuilders
                        .post("/authentication/authenticate")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \""+user.getEmail()+"\", \"password\": \""+wrongPassword+"\"}")
                        .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is(404));
    }

}