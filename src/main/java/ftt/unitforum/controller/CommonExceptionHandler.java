package ftt.unitforum.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import ftt.unitforum.config.AppConfig;
import ftt.unitforum.exception.ForumException;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice(annotations = Controller.class)
public class CommonExceptionHandler {

	@Autowired
    private AppConfig appConfig;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LocaleResolver localeResolver;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(ForumException.class)
	public ModelAndView forumExceptionHandler(ForumException e, HttpServletRequest request){
		
		String errorMessage = e.getErrorMessage(messageSource, localeResolver.resolveLocale(request));
		e.setErrorMessage(errorMessage);
		
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("appConfig", appConfig);
		mnv.addObject("forumException", e);
		mnv.setViewName("error/common_error");
		
		logger.error("[forumExceptionHandler] code:{}, msg:{}, addmsg:{}, debmsg:{}", e.getErrorCode(), errorMessage, e.getAddMessage(), e.getDebugMessage());
	
		return mnv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView defaultExceptionHandler(Exception e, HttpServletRequest request){
		
		ForumException fe;
		if (e instanceof SQLException){
			fe = new ForumException("system.error.db");
		} else {
			fe = new ForumException("system.error.unknown");
		}
		
		String errorMessage = fe.getErrorMessage(messageSource, localeResolver.resolveLocale(request));
		fe.setErrorMessage(errorMessage);
		
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("appConfig", appConfig);
		mnv.addObject("forumException", fe);
		mnv.setViewName("error/common_error");
		logger.error("[defaultExceptionHandler] class:{}, msg:{}", e.getClass(), e.getMessage());
		
		return mnv;
	}
}
