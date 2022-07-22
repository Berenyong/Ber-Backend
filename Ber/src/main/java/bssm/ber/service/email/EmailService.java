package bssm.ber.service.email;


import javax.mail.internet.MimeMessage;

public interface EmailService {
    void sendSimpleMessage(String email) throws Exception;

    boolean verifyCode(String code);

    void sendWithdrawalMessage(String email) throws Exception;
}