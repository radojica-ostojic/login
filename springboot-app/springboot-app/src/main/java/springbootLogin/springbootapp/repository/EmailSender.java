package springbootLogin.springbootapp.repository;

public interface EmailSender {
    void send(String to, String email);
}
