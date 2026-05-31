import java.util.ArrayList;

public class AuthController {

    private ArrayList<user> daftarUser;
    private user userAktif;

    public AuthController() {
        daftarUser = new ArrayList<>();
        userAktif  = null;
        daftarUser.add(new user("admin", "123456"));
    }

    public boolean login(String username, String password) {
        if (userAktif != null) {
            System.out.println("Sudah login sebagai : " + userAktif.getUsername());
            return false;
        }
        if (username == null || password == null) {
            System.out.println("Username dan password tidak boleh kosong.");
            return false;
        }

        for (user u : daftarUser) {
            if (u.validasiLogin(username, password)) {
                userAktif = u;
                System.out.println("Login berhasil! Selamat datang, " + userAktif.getUsername());
                return true;
            }
        }

        System.out.println("Username atau password salah.");
        return false;
    }

    public void logout() {
        if (userAktif == null) {
            System.out.println("Tidak ada sesi aktif.");
            return;
        }
        System.out.println(userAktif.getUsername() + " berhasil logout.");
        userAktif = null;
    }

    public boolean isLoggedIn() {
        return userAktif != null;
    }
}