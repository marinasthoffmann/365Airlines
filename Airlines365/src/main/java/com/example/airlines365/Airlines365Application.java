package com.example.airlines365;

import com.example.airlines365.dto.PassengerConfirmationResponse;
import com.example.airlines365.model.Passenger;
import com.example.airlines365.utils.StringToSeatConverter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class Airlines365Application {

	public static void main(String[] args) {
		SpringApplication.run(Airlines365Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(new StringToSeatConverter());
		modelMapper.typeMap(Passenger.class, PassengerConfirmationResponse.class).addMappings(mapper -> {
			mapper.map(src -> src.getConfirmacao().getEticket(),
					PassengerConfirmationResponse::setEticket);
			mapper.map(src -> src.getConfirmacao().getAssento(),
					PassengerConfirmationResponse::setAssento);
			mapper.map(src -> src.getConfirmacao().getDataHoraConfirmacao(),
					PassengerConfirmationResponse::setDataHoraConfirmacao);
		});
		return modelMapper;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

}
