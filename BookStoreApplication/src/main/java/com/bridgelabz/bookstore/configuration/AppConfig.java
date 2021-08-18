package com.bridgelabz.bookstore.configuration;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.OrderDetails;
import com.bridgelabz.bookstore.util.JwtToken;




@Configuration
public class AppConfig {
	
	private static MessageSourceAccessor messageSourceAccessor;
	
	@Bean
	public Book book()
	{
		return new Book();
	}
	@Bean
	public OrderDetails orderDetails() {
		return new OrderDetails();
	}
	@Bean
	public ModelMapper modelMapper() {
	ModelMapper modelMapper = new ModelMapper();
	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	return modelMapper;
	}
	
	@PostConstruct
	private void initMessageSourceAccessor() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages/errormessages", "classpath:messages/successmessages");
		messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.getDefault());

	}

	public static MessageSourceAccessor getMessageAccessor() {
		return messageSourceAccessor;
	}
	
	
	@Bean
	public JwtToken jwttoken()
	{
		return new JwtToken();
	}
	@Bean
    public RestTemplate restTemplate() {
		return new  RestTemplate();
	}
	

}
