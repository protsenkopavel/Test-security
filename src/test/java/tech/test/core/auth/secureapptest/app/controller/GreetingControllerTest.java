package tech.test.core.auth.secureapptest.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tech.test.core.auth.secureapptest.app.dto.UserDetailsDto;
import tech.test.core.auth.secureapptest.app.service.AuthenticationService;
import tech.test.core.auth.secureapptest.config.SecurityConfig;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetingController.class)
@Import(SecurityConfig.class)
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthenticationService authenticationService;

    @Test
    void greetUserWithoutAuthenticationShouldReturnUnauthorized() throws Exception {
        // Act
        mockMvc.perform(get("/api/v1/greeting/hello"))
                // Assertion
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "test-user")
    void greetUserWithAuthenticationShouldReturnGreeting() throws Exception {
        // Arrange
        UserDetailsDto userDetails = new UserDetailsDto("test-user");
        when(authenticationService.getAuthenticatedUser()).thenReturn(userDetails);

        // Act
        mockMvc.perform(get("/api/v1/greeting/hello"))
                // Assertion
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain"))
                .andExpect(content().string("Hello, test-user!"));
    }

}