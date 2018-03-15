package ftt.unitforum.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import ftt.unitforum.exception.ForumException;
import ftt.unitforum.types.JsonResult;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = RestController.class)
public class CommonRestExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LocaleResolver localeResolver;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ResponseBody
	@ExceptionHandler(ForumException.class)
	public JsonResult forumExceptionHandler(ForumException e, HttpServletRequest request){
		
		String errorMessage = e.getErrorMessage(messageSource, localeResolver.resolveLocale(request));
		logger.error("[defaultExceptionHandler] code:{}, msg:{}, addmsg:{}, debmsg:{}", e.getErrorCode(), errorMessage, e.getAddMessage(), e.getDebugMessage());
		
		return JsonResult.fail(-100, errorMessage);
		//return JsonResult.fail(-100, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public JsonResult defaultExceptionHandler(Exception e, HttpServletRequest request){
		
		ForumException fe;
		if (e instanceof SQLException){
			fe = new ForumException("system.error.db");
		} else {
			fe = new ForumException("system.error.unknown");
		}
		
		String errorMessage = fe.getErrorMessage(messageSource, localeResolver.resolveLocale(request));
		logger.error("[defaultExceptionHandler] class:{}, msg:{}", e.getClass(), e.getMessage());
		
		return JsonResult.fail(-100, errorMessage);
	}
}
