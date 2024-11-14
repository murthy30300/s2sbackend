package com.klu.ss.configs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cloudinary.Cloudinary;


@Configuration
public class CloudinaryConfig {
	

	@Bean
	public Cloudinary cloudinary() {
		final Map<String,String> config = new HashMap<>();
		config.put("cloud_name", "dovvc3hvb");
		config.put("api_key", "833958691547925");
		config.put("api_secret", "69rl2WuZGkZQ60tHN_UugJ0E030");
		return new Cloudinary(config);
		
	}

}
