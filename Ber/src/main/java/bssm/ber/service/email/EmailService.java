package bssm.ber.service.email;


public interface EmailService {
    void sendSimpleMessage(String email) throws Exception;

    boolean verifyCode(String code);
}