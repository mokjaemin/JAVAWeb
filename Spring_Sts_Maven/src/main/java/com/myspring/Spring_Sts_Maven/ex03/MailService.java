package com.myspring.Spring_Sts_Maven.ex03;

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
	
	// xml에서 제작한 빈 주입, 메일 전송을 위한 빈
	@Autowired
	 private JavaMailSender mailSender;
	
	// xml에서 제작한 빈 주입
    @Autowired
    private SimpleMailMessage preConfiguredMessage;
 
    
    
    // MailController를 통해 메일을 보내는 경우
    @Async // 비동기로 수행 - 메일보내는 게 많을수록 비동기로 수행함
	public void sendMail(String to, String subject, String body) {
      // 메일 전송을 위한 연결 정보 객체
      MimeMessage message = mailSender.createMimeMessage();
      try {
		MimeMessageHelper messageHelper = 
		new MimeMessageHelper(message, true, "UTF-8");
		//messageHelper.setCc("zzzzzz@naver.com"); // 참조자
		messageHelper.setFrom("bamer@naver.com", "목재민"); 
		// 메일 보내는 사람 주소, 이름
		messageHelper.setSubject(subject);
		messageHelper.setTo(to); 
		messageHelper.setText(body);
		mailSender.send(message);  
      }catch(Exception e){
		e.printStackTrace();
	  }
	}
 
    
    // xml에서 설정한 제목 주소, Controller에서 지정한 내용을 메일로 보내는 경우
	@Async
	public void sendPreConfiguredMail(String message) {
	  SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
	  mailMessage.setText(message);
	  mailSender.send(mailMessage);
	}
}

