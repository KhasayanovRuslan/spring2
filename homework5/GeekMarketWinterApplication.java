package com.geekbrains.geekmarketwinter;

import com.geekbrains.geekmarketwinter.utils.RabbitProvider;
import com.geekbrains.geekmarketwinter.utils.RabbitProviderAuthErr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class GeekMarketWinterApplication implements CommandLineRunner {
//	private RabbitProvider rabbitProvider;
	private RabbitProviderAuthErr rabbitProviderAuthErr;

	@Autowired
	public void setRabbitProviderAuthErr(RabbitProviderAuthErr rabbitProviderAuthErr) {
		this.rabbitProviderAuthErr = rabbitProviderAuthErr;
	}

	public static void main(String[] args) {
		SpringApplication.run(GeekMarketWinterApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		rabbitProviderAuthErr.openConnect();
		rabbitProviderAuthErr.receiverMsgAndLogDB();
	}
}