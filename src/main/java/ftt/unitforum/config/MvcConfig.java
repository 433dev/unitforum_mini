package ftt.unitforum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ftt.unitforum.controller.interceptor.FingerprintInterceptor;
import ftt.unitforum.controller.interceptor.LanguageInterceptor;
import ftt.unitforum.controller.interceptor.LogInterceptor;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
    private AppConfig appConfig;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		
		// for checking fingerprint 
		HandlerInterceptor fingerprintInterceptor = new FingerprintInterceptor();
		registry.addInterceptor(fingerprintInterceptor);
		
		// LocaleChangeInterceptor
		LanguageInterceptor languageInterceptor = new LanguageInterceptor();
		languageInterceptor.setParamName(appConfig.getLocaleParam());
		registry.addInterceptor(languageInterceptor);
		
		// for DEBUG
		if (appConfig.getEnvironment().equals("Dev")) {
			HandlerInterceptor logIntercepter = new LogInterceptor();
			registry.addInterceptor(logIntercepter);
		}
    }
	
	@Bean
    public MessageSource messageSource() {
        
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setCacheSeconds(600);
        messageSource.setBasename(appConfig.getMessageResource());
		// System.out.println("message resource path : "+appConfig.getMessageResource());
        messageSource.setDefaultEncoding("UTF-8");
        
        return messageSource;
    }
	
	@Bean
	public LocaleResolver localeResolver() {
		
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("ko"));
		
		return sessionLocaleResolver;
	}
	
	@Bean
    public ViewResolver getViewResolver() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
	
//	@Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
	
//	@Bean
//	public FilterRegistrationBean filterRegistration() {
//	
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(httpFilter());
//		registration.addUrlPatterns("/forum/*");
//		//registration.setOrder(0);
//		//registration.setName("httpFilter");
//		return registration;
//	}
//	
//	@Bean
//	public Filter httpFilter() {
//		return new HttpFilter();
//	}
}