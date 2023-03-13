package com.myspring.Spring_Sts_Maven.ex04;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

//@Service("mailService")
public class MailService {
	@Autowired
	 private JavaMailSender mailSender;
    @Autowired
    private SimpleMailMessage preConfiguredMessage;
 
    @Async
    public void sendMail(String to, String subject, String body)
    {
    	MimeMessage message = mailSender.createMimeMessage();
    	 
    	try {
    	   MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    	   messageHelper.setSubject(subject);
    	   messageHelper.setTo(to);
    	   messageHelper.setFrom("ahrwoals@pusan.ca.kr", "목재민");
    	   // 반드시 true 설정
    	   // Controller에서 html을 텍스트로 저장해서 전달했음
    	   // 따라서 true를 통해 메일 받았을때, html 형식으로 출력 됨.
    	   messageHelper.setText(body,true);
    	   mailSender.send(message);
    	}catch(Exception e){
    		  e.printStackTrace();
    	}
    	
    }
 
    @Async
    public void sendPreConfiguredMail(String message) {
            SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
            mailMessage.setText(message);
            mailSender.send(mailMessage);
    }
}

