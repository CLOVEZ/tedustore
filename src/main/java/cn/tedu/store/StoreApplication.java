package cn.tedu.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
@SpringBootApplication
@MapperScan("cn.tedu.store.mapper")
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	@SuppressWarnings("deprecation")
	public MultipartConfigElement
	multipartConfiguration() {
		MultipartConfigFactory factory
				= new MultipartConfigFactory();
		factory.setMaxRequestSize(50 * 1024 * 1024);
		factory.setMaxFileSize(50 * 1024 * 1024);
		return factory.createMultipartConfig();
	}

}

