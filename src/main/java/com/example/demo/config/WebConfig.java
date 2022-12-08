package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.example.demo.interceptors.CustomInterceptor;



@SuppressWarnings("deprecation")
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

   
   @Override
   public void addInterceptors(final InterceptorRegistry registry) {
	   registry.addInterceptor(new CustomInterceptor())
	   .excludePathPatterns("/favicon.ico")
	   .excludePathPatterns("/images/*");
   }

}
