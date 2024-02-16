package com.acts;

import java.time.LocalDate;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Application
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
				.setPropertyCondition(Conditions.isNotNull());
		modelMapper.addConverter(new StringToDateConverter());
		return modelMapper;
	}

	public class StringToDateConverter extends AbstractConverter<String, LocalDate> {
		@Override
		protected LocalDate convert(String source) {
			return LocalDate.parse(source);
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}