package in.ashutoshkrris.reddit.service.impl;

import in.ashutoshkrris.reddit.dto.NotificationEmail;
import in.ashutoshkrris.reddit.exceptions.RedditException;
import in.ashutoshkrris.reddit.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;

    @Override
    public void sendMail(NotificationEmail notificationEmail) throws RedditException {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("noreply@reddit.com");
            mimeMessageHelper.setTo(notificationEmail.getRecipient());
            mimeMessageHelper.setSubject(notificationEmail.getSubject());
            mimeMessageHelper.setText(notificationEmail.getBody());
        };
        try {
            javaMailSender.send(mimeMessagePreparator);
            log.info("Verification email sent to {}", notificationEmail.getRecipient());
        } catch (MailException e) {
            throw new RedditException("Unable to send verification email to " + notificationEmail.getRecipient(), e);
        }
    }

}
