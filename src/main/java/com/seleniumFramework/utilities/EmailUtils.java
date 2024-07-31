package com.seleniumFramework.utilities;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailUtils {

	private static final Logger logger = LogManager.getLogger(EmailUtils.class);
	private static final String SMTP_HOST = "smtp.example.com";
	private static final String SMTP_PORT = "587";
	private static final String SMTP_USERNAME = "your-email@example.com";
	private static final String SMTP_PASSWORD = "your-email-password";

	/********************************************************************************************
	 * Sends a plain text email.
	 *
	 * @param to      The recipient's email address.
	 * @param subject The subject of the email.
	 * @param body    The body of the email.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/

	public static void sendPlainTextEmail(String to, String subject, String body) {
		sendEmail(to, subject, body, false, null);
	}

	/********************************************************************************************
	 * Sends an HTML email.
	 *
	 * @param to      The recipient's email address.
	 * @param subject The subject of the email.
	 * @param body    The body of the email.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static void sendHtmlEmail(String to, String subject, String body) {
		sendEmail(to, subject, body, true, null);
	}

	/********************************************************************************************
	 * Sends an email with an attachment.
	 *
	 * @param to         The recipient's email address.
	 * @param subject    The subject of the email.
	 * @param body       The body of the email.
	 * @param attachment The file to be attached.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static void sendEmailWithAttachment(String to, String subject, String body, File attachment) {
		sendEmail(to, subject, body, true, attachment);
	}

	/********************************************************************************************
	 * Sends an email.
	 *
	 * @param to         The recipient's email address.
	 * @param subject    The subject of the email.
	 * @param body       The body of the email.
	 * @param isHtml     Indicates if the email body is HTML.
	 * @param attachment The file to be attached (optional).
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	private static void sendEmail(String to, String subject, String body, boolean isHtml, File attachment) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", SMTP_HOST);
		properties.put("mail.smtp.port", SMTP_PORT);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SMTP_USERNAME));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);

			if (attachment == null) {
				if (isHtml) {
					message.setContent(body, "text/html");
				} else {
					message.setText(body);
				}
			} else {
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(body, "text/html");

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				MimeBodyPart attachmentBodyPart = new MimeBodyPart();
				attachmentBodyPart.attachFile(attachment);
				multipart.addBodyPart(attachmentBodyPart);

				message.setContent(multipart);
			}

			Transport.send(message);
			logger.info("Email sent successfully to {}", to);

		} catch (MessagingException | IOException e) {
			logger.error("Failed to send email to {}: {}", to, e.getMessage(), e);
		}
	}

	/********************************************************************************************
	 * Loads email configuration from a properties file.
	 *
	 * @param filePath The path to the properties file.
	 * @return Properties object containing the configuration.
	 * @throws IOException If an error occurs while reading the properties file.
	 * 
	 * @author Abhijeet Maske Created June 27,2023
	 * @version 1.0 June 27,2023
	 ********************************************************************************************/
	public static Properties loadEmailConfig(String filePath) throws IOException {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(filePath)) {
			properties.load(fis);
		} catch (IOException e) {
			logger.error("Failed to load email configuration from {}: {}", filePath, e.getMessage(), e);
			throw e;
		}
		logger.info("Email configuration loaded from {}", filePath);
		return properties;
	}
}