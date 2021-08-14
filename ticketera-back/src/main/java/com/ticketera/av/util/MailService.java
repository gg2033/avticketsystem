package com.ticketera.av.util;


import org.springframework.stereotype.Component;
@Component
public class MailService {

	public void sendMail(String content, String email) {
		  MailThread mailThread = new MailThread();
          mailThread.setUsername("fleiva@americavirtualsa.com");
          mailThread.setPassword("fer.123456");
          mailThread.setTo(email);
          mailThread.setContent(content);
          mailThread.run();
	}

}
