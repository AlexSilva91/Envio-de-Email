package enviarEmail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Envio {

	public static void main(String[] args) {
		final String usuario = "Email-De-Origem";
		final String dest = "Email-De-Destino";
		final String senha = "Senha";
		
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable","true");
		
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuario, senha);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(dest));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario+","+dest));
			message.setSubject("Assunto");
			message.setText("Mensagem!");
			Transport.send(message);
			System.out.println("Enviando....");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
