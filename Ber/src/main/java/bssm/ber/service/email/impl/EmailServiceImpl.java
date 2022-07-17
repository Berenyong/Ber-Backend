package bssm.ber.service.email.impl;

import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bssm.ber.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    public static final String ePw = createKey();

    private MimeMessage createMessage(String email) throws Exception {

        System.out.println("보내는 대상 : " + email);
        System.out.println("인증 번호 : " + ePw);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, email); // 보내는 대상
        message.setSubject("[Ber] Confirm " + email + " to sign up"); // 제목

        String msgg = "";
        msgg += "<h2 style='color:#0068ff;'><strong> Ber 회원가입을 위한 이메일 주소를 확인해주세요. </strong></h2>";
        msgg += "<span style='font-size:17px ;'>반가워요! " + email
                + "</span><span style='font-size:17px';>을 통한"
                + " <strong>Ber</strong></span>"
                + "<span style='font-size:17px';>의 가입 신청을 확인 중입니다.</span>" +
                "<p style='font-size:17px ;'>인증 절차가 성공적으로 이루어지면, 회원가입을 빠르게 도와드릴게요.</p>";
        msgg += "<p style='font-size:17px ;'><strong>인증 코드를 웹사이트에 동일하게 작성해주세요: </strong></p>";
        msgg += "<div align='center' font-family:verdana';>";
        msgg += "<div style='font-size:200%'><strong>";
        msgg += ePw + "</strong><div><br/> ";
        msgg += "</div>";
        msgg += "<div align='left'><p style='font-size:17px';><strong>인증을 진행한 적이 없으시다면?</strong></p>";
        msgg += "<p style='font-size:17px';>걱정하지 마세요! 이메일 주소가 타인에 의해 잘못 기입된 것으로 보입니다.</p>";
        msgg += "<p style='font-size:17px';>타인에게 인증 코드를 알려주지 않도록, 해당 이메일을 무시하거나 삭제하셔도 됩니다.</p></div>";
        message.setText(msgg, "utf-8", "html"); // 내용
        message.setFrom(new InternetAddress("rltgjqmduftlagl@gmail.com", "Ber"));// 보내는 사람

        msgg += "<div style='margin:100px;'>";
        return message;
    }

    // 인증코드 만들기
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }

        return key.toString();
    }

    @Override
    public void sendSimpleMessage(String email) throws Exception {
        MimeMessage message = createMessage(email);
        try { // 예외처리
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }

    }

}