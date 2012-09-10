/**
 *
 * History:
 *   11-9-1 下午10:50 Created by ZGong
 */
package gz.aws.mail;


import javax.mail.internet.InternetAddress;

/**
 * Created by ZGong.
 *
 * @version 1.0 11-9-1 下午10:50
 */
public class MailSender {
    interface MailSenderInternal {
        public void sendMessage(String subject, String body, String... to) throws Exception;

        public void sendMessage(String subject, String body, InternetAddress... to) throws Exception;
    }

    private final static MailSenderInternal MAIL_SENDER = new MailSenderInternal() {
        @Override
        public void sendMessage(String subject, String body, String... to) throws Exception {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void sendMessage(String subject, String body, InternetAddress... to) throws Exception {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    };


    public static void sendMessage(String subject, String body, String... to) throws Exception {
        MAIL_SENDER.sendMessage(subject, body, to);
    }

    public static void sendMessage(String subject, String body, InternetAddress... to) throws Exception {
        MAIL_SENDER.sendMessage(subject, body, to);
    }
}
