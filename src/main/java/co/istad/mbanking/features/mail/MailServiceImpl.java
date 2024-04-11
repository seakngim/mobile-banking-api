package co.istad.mbanking.features.mail;
import co.istad.mbanking.features.mail.dto.MailRequest;
import co.istad.mbanking.features.mail.dto.MailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@RequiredArgsConstructor
//@Slf4j
public class MailServiceImpl implements MailService {
private final JavaMailSender javaMailSender;
private final TemplateEngine templateEngine;

public MailResponse send(MailRequest mailRequest){
    Context context = new Context();
    context.setVariable("Name", "ISTAD");
    String template = templateEngine.process("home", context);
    MimeMessage mimeMessageMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessageMessage);
    try{
        mimeMessageHelper.setTo(mailRequest.to());
        mimeMessageHelper.setSubject(mailRequest.subject());
        mimeMessageHelper.setText(template, true);
    }catch (MessagingException e){
        throw new RuntimeException(e);
    }
    javaMailSender.send(mimeMessageMessage);
    return new MailResponse("Success");
}
}
