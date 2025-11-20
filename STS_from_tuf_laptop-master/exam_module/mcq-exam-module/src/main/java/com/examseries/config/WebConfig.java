package com.examseries.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload.dir:uploads}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve static resources from classpath:/static/ (Spring Boot does this by default)
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        // Serve CSS/JS under /css, /js, /images from static folder
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");

        // Expose upload directory so uploaded files can be served (if you want)
        // For security, in production ensure proper checks.
        String uploadsPath = "file:" + System.getProperty("user.dir") + "/" + uploadDir + "/";
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadsPath);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // Default Thymeleaf auto-config handles view resolution, no change needed.
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Simple redirects
        registry.addViewController("/").setViewName("index");
    }
}

