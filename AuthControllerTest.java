import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthControllerTest {

    private AuthController auth;

    @BeforeEach
    void setUp() {
        auth = new AuthController();
    }

    @Test
    void testLoginBerhasil() {
        assertTrue(auth.login("admin", "123456"));
    }

    @Test
    void testLoginPasswordSalah() {
        assertFalse(auth.login("admin", "salah"));
    }

    @Test
    void testLoginUsernameSalah() {
        assertFalse(auth.login("tidakada", "123456"));
    }

    @Test
    void testLoginUsernameNull() {
        assertFalse(auth.login(null, "123456"));
    }

    @Test
    void testLoginSudahLogin() {
        auth.login("admin", "123456");
        assertFalse(auth.login("admin", "123456"));
    }

    @Test
    void testLogoutBerhasil() {
        auth.login("admin", "123456");
        auth.logout();
        assertFalse(auth.isLoggedIn());
    }

    @Test
    void testIsLoggedInSebelumLogin() {
        assertFalse(auth.isLoggedIn());
    }
}