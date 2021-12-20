package es.jmmluna.tim.infrastructure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("es.jmmluna.tim")
public class Configurer {
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
