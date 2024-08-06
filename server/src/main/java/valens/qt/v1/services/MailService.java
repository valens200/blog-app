package valens.qt.v1.services;
import org.springframework.stereotype.Component;
import valens.qt.v1.utils.Mail;

@Component
public interface MailService {


    public void sendMail(Mail mail);
}
