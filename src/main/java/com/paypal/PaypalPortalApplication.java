package com.paypal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PaypalPortalApplication {
	public static void main(String[] args) {
		SpringApplication.run(PaypalPortalApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		List<MediaType> mediaTypes = new ArrayList<>();
		mediaTypes.add(MediaType.APPLICATION_JSON);
		mediaTypes.add(MediaType.TEXT_HTML);
		messageConverter.setSupportedMediaTypes(mediaTypes);
		list.add(messageConverter);
		restTemplate.setMessageConverters(list);
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}
}
