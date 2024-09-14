package in.ashutoshkrris.reddit.service;

import in.ashutoshkrris.reddit.dto.NotificationEmail;
import in.ashutoshkrris.reddit.exceptions.RedditException;

public interface MailService {

    void sendMail(NotificationEmail notificationEmail) throws RedditException;

}
