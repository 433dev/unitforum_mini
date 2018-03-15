package ftt.unitforum.controller.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		
		long startTime = System.currentTimeMillis();
		Date now = new Date(startTime);
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		
		//System.out.println("---called preHandle---");
		System.out.println("--- "+dformat.format(now)+" [Requested Params] "+request.getRequestURL().toString()+" ---");
		
		Enumeration<String> aAll = request.getParameterNames();
		while (aAll.hasMoreElements()) {
		    String aName = aAll.nextElement();
		    String aValue = request.getParameter(aName);
		    System.out.println(aName + " : " + aValue);
		}
		System.out.println("--------------------");
		
        request.setAttribute("_startTime", startTime);
		
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
		long startTime = (Long) request.getAttribute("_startTime");
		long endTime = System.currentTimeMillis();
		
		Date now = new Date(endTime);
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		System.out.println("--- "+dformat.format(now)+" [Elapsed Time] "+(endTime-startTime)+" ms ---\n");
	}
}
