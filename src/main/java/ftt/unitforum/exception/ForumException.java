package ftt.unitforum.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;


@SuppressWarnings("serial")
public class ForumException extends Exception {
	private String errorCode;
	private String errorMessage;
	private String addMessage = "";
	private String debugMessage = "";
	private Object[] args;
	
	public ForumException(){
		super();
	}
	
	public ForumException(String errorCode){
		this.setErrorCode(errorCode);
	}
	
	public ForumException setErrorCode(String errorCode){
		this.errorCode = errorCode;
		return this;
	}

	public String getErrorCode(){
		return errorCode;
	}

	public ForumException setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public String getErrorMessage(MessageSource messageSource, Locale locale) {
		if (errorMessage == null || errorMessage.equals(""))
			this.errorMessage = messageSource.getMessage(this.errorCode, this.args, locale);
		
		return errorMessage;
	}
	
	public ForumException setAddMessage(String addMessage){
		this.addMessage = addMessage;
		return this;
	}
	
	public String getAddMessage(){
		return addMessage;
	}

	public ForumException setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
		return this;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
}