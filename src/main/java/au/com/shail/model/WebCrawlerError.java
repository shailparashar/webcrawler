package au.com.shail.model;

import org.springframework.http.HttpStatus;

public class WebCrawlerError {
	
	HttpStatus code;
	String message;
	
	public WebCrawlerError(HttpStatus code, String message) {
		this.code = code;
		this.message = message;
	}

	public HttpStatus getCode() {
		return code;
	}
	public void setCode(HttpStatus code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
	

}
