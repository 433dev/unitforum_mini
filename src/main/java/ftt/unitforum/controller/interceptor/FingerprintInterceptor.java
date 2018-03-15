package ftt.unitforum.controller.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ftt.unitforum.annotation.CheckFingerprint;
import ftt.unitforum.exception.ForumException;
import ftt.unitforum.util.Fingerprint;
import ftt.unitforum.util.Urldecoding;

public class FingerprintInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		//System.out.println("---called preHandle---");
		
		HandlerMethod hm = (HandlerMethod)handler; 
		Method method= hm.getMethod();
		
		// CheckFingerprint cf = hm.getMethodAnnotation(CheckFingerprint.class); // for test

		// Class 선언 또는 Method 선언이 있을 경우
		if( method.getDeclaringClass().isAnnotationPresent(CheckFingerprint.class)
				|| hm.getMethodAnnotation(CheckFingerprint.class) != null){
			
			StringBuffer sb = new StringBuffer();
			
			sb.append(Urldecoding.urlDecoding(request.getParameter("ssn")));
			sb.append(Urldecoding.urlDecoding(request.getParameter("gusn")));
			sb.append(Urldecoding.urlDecoding(request.getParameter("nick")));
			sb.append(Urldecoding.urlDecoding(request.getParameter("level")));
			sb.append(Urldecoding.urlDecoding(request.getParameter("unitstat")));
			String fttFp = request.getParameter("ftt_fp");
			
			if (fttFp == null || !fttFp.equals(Fingerprint.getMD5(sb.toString()))) {
				 //System.out.println("Vaild Fingerprint : " + Fingerprint.getMD5(sb.toString()));
				throw new ForumException("data.invalid.fingerprint").setDebugMessage( Fingerprint.getMD5(sb.toString()) );
			}
			else {
				// System.out.println("Checking Fingerprint is passed!");
			}
		}
		
		return true;
	}

	@Override
	public void postHandle(	HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		//System.out.println("---called postHandle---");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
		//System.out.println("---called afterCompletion---");
	}
}
