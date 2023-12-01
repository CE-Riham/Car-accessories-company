package classes;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class Mail {

    Session newSession = null;
    MimeMessage mimeMessage = null;

    public void rasheedEmail(String names) throws MessagingException {
        Mail mail = new Mail();
        mail.setupServerProperties();
        mail.draftEmail(names);
        System.out.println("Befor mail.send"+"     ; ;;;;;;;;;;;;;;");

        mail.sendEmail();
    }

    private void sendEmail() throws NoSuchProviderException {
        String fromUser = "s12028064@stu.najah.edu";
        String fromUserP = "vqo@954719";
        String emailHost = "smtp.gmail.com";

        Transport transport = newSession.getTransport("smtp");

        try {
            transport.connect(emailHost, fromUser, fromUserP);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                transport.close();
            } catch (MessagingException e) {
                System.err.println("Error closing transport: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    private MimeMessage draftEmail(String names) throws MessagingException {
        String emailSubject = "Your product is completed";
        String emailBody = "Come to the store and take the product as soon as possible.";

        mimeMessage = new MimeMessage(newSession);

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(names));
        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "text/html"); // Corrected content type
        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);
        mimeMessage.setContent(multiPart);

        return mimeMessage;
    }

    private void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");


        newSession = Session.getDefaultInstance(properties, null);
    }

}
