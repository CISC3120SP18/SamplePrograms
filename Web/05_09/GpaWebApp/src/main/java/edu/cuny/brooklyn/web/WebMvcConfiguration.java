package edu.cuny.brooklyn.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration {

	@Configuration
	@EnableWebMvc
	public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer {
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			if (!registry.hasMappingForPattern("/webjars/**")) {
				registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");

			}
		}
	}
}
