package com.system.virus;

import com.system.virus.repositories.VirusFamilyRepository;
import com.system.virus.repositories.VirusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

/**
 * @author Mark Anthony Ortiz - ortizmark905@gmail.com
 */
@SpringBootApplication
public class StockMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMgmtApplication.class, args);
	}

	@Bean
	ServletRegistrationBean h2servletRegistration(){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}

	@Bean
	public CommandLineRunner executeQuery(VirusFamilyRepository virusFamilyRepository,
										  VirusRepository virusRepository) {
		return (args) -> {

			virusFamilyRepository.findAll().forEach(System.out::println);
			virusRepository.findAll().forEach(System.out::println);
		};
	}

}
