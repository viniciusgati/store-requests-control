package live.gatisoft.storerequestscontrol.common;

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

    @Test
    @DisplayName("Post to /authentication/authenticate should return tokens for valid user")
    public void GetAuthLogin() throws Exception {
        assertTrue(true);
        mvc.perform(
            MockMvcRequestBuilders
                .post("/authentication/authenticate")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"test\", \"password\": \"test\"}")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("token").exists())
                .andExpect(jsonPath("refreshToken").exists())
                .andExpect(status().is(200));
    }

}