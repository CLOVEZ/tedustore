package cn.tedu.store.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class MultipartConfig {
	@Bean
	public MultipartConfigElement getMultipartConfig() {
		MultipartConfigFactory factory
			= new MultipartConfigFactory();
		
		DataSize maxSize = DataSize.ofMegabytes(100L);
		factory.setMaxFileSize(maxSize);
		factory.setMaxRequestSize(maxSize);
		
		MultipartConfigElement element
			= factory.createMultipartConfig();
		
		return element;
	}
}
