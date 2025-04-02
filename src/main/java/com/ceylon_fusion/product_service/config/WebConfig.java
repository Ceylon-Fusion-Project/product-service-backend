package com.ceylon_fusion.product_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map /images/** to D:/product_images/
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/D:/Final-Year-Project/Images/product_images/");

        //Serve uploaded files (certificates, etc.)
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:/D:/Final-Year-Project/Images/product_files/");
    }
}
