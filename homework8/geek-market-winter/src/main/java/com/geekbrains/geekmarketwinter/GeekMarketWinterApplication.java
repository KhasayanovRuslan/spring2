package com.geekbrains.geekmarketwinter;

import com.geekbrains.geekmarketwinter.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class GeekMarketWinterApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(GeekMarketWinterApplication.class, args);
	}

//	@Autowired
//	private JavaMailSender javaMailSender;

	@Autowired
	private MailService mailService;

	@Override
	public void run(String... args) throws Exception {
	//	sendEmail();
//		sendEmailWithAttachment();
//		mailService.sendMail("artem_evdokimov_1992@mail.ru", "Testing magazine", "java spring");
//		mailService.sendMail("KhasayanovRuslan11@gmail.ru", "Testing magazine", "java spring");


//		ArrayList<Integer> al1 = new ArrayList<>();
//		al1.add(1);
//		al1.add(2);
//		al1.add(3);
//
//		ArrayList<Integer> al2 = new ArrayList<>();
//		al2.add(2);
//		al2.add(1);
//		al2.add(3);
//		al2.add(4);
//
//		al1.equals(al2)
	}



//	void sendEmail() {
//		SimpleMailMessage msg = new SimpleMailMessage();
//		msg.setTo("artem_evdokimov_1992@mail.ru");
//		msg.setSubject("Testing semf email");
//		msg.setText("Hello world!");
//		javaMailSender.send(msg);
//	}
//
//	void sendEmailWithAttachment() throws MessagingException, IOException {
//
//		MimeMessage msg = javaMailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//		helper.setTo("artem_evdokimov_1992@mail.ru");
//
//		helper.setSubject("Testing from Spring Boot");
//
//		helper.setText("<h1>Check attachment for image!</h1>", true);
//
//		FileSystemResource file = new FileSystemResource("E:/обучение/Java Spring part 2/Lesson_7/geek-market-winter/logo.png");
//		helper.addAttachment(file.getFilename(), file);
//
//		javaMailSender.send(msg);
//
//	}


}