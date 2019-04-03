package cn.tedu.store.config;

import cn.tedu.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoginInterceptorConfigurer
	implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 创建拦截器对象
		HandlerInterceptor interceptor = new LoginInterceptor();



		// 白名单：在黑名单范围内，却不需要登录就可以访问
		List<String> excludePatterns = new ArrayList<>();
		excludePatterns.add("/bootstrap3/**");
		excludePatterns.add("/css/**");
		excludePatterns.add("/js/**");
		excludePatterns.add("/images/**");

		excludePatterns.add("/web/register.html");
		excludePatterns.add("/users/reg");
		excludePatterns.add("/web/login.html");
		excludePatterns.add("/users/login");

		// 注册拦截器
		registry
			.addInterceptor(interceptor)
			.addPathPatterns("/**")
			.excludePathPatterns(excludePatterns);
	}
	
}





