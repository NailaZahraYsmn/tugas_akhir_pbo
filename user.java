public class user implements Tampilan {
    private String username;
    private String password;

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong.");
        }

        this.password = password;
    }

    public boolean validasiLogin(String username, String password) {
        if (username == null || password == null) return false;
        return this.username.equals(username) && this.password.equals(password);
    }

    
    @Override
    public String tampilkanInfo() {
        return "User: " + username;
    }
}

