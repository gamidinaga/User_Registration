package com.nt.common.utils;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.nt.entity.UserRegistration;

@Component
public class EmailUtility {
	private static JavaMailSender mailSender;

	@Autowired(required = true)
	public void setMailSender(JavaMailSender mailSender) {
		EmailUtility.mailSender = mailSender;
	}

	public static void sendEmailToUser(UserRegistration userRegistration) {
		System.out.println(" Implementation started for sending mail.....................");

		// SimpleMailMessage message = new SimpleMailMessage();
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
               System.out.println("userid  "+userRegistration.getId());
			String html = "\n<p>Hi!</p><a href='http://localhost:2626/User_Registration/update_status.htm?uId="+userRegistration.getId()+"'>click here to update status</a>";
			message.setContent(html, "text/html");
			helper.setFrom("gamidi.nagaraju1995@gmail.com");
			helper.setSubject("Sending for Activate User Account");
			helper.setText("Dear \n    Mr/Mrs....."+userRegistration.getFirstName()+" "+userRegistration.getLastName()+"   please update your Status By clicking Below link.");
			helper.setSentDate(new Date());
			helper.setTo(userRegistration.getEmail());
			helper.setText("Dear  \n Mr/Mrs..." + userRegistration.getFirstName() + "" + userRegistration.getLastName(),
					true);
			helper.setSubject("<b>our team is informing to you please update your status by clicking below link<b>");
			mailSender.send(message);

		} catch (MessagingException e1) {
			e1.printStackTrace();
		}

		System.out.println(" Mail Sent...To User  " +userRegistration.getFirstName()+" "+userRegistration.getLastName());
	}

	@Override
	public String toString() {
		return "EmailUtility [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
