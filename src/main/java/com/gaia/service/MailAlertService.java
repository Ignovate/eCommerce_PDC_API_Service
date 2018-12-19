package com.gaia.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailAlertService {

	@Autowired
	private JavaMailSender sender;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(MailAlertService.class);

	public void sendEmail(String toMail,String text,String subject) {
		try {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(toMail);
		helper.setText(text);
		helper.setSubject(subject);
		sender.send(message);
		}catch(Exception e)
		{
			LOGGER.error("unable to send mail alert error",e);
		}
	}

}
