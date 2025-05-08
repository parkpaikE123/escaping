package com.ryu.escaping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ryu.escaping.common.FileManager;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/branch/image/**")
		.addResourceLocations("file://" + FileManager.FILE_UPLOAD_PATH + "/");
		registry.addResourceHandler("/theme/image/**")
		.addResourceLocations("file://" + FileManager.FILE_UPLOAD_PATH_THEME + "/");
	}
	
}
