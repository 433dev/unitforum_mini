package ftt.unitforum.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.stereotype.Component;

@Component
public class Urldecoding {
	
	public static String urlDecoding(String str){
		
		String result;
		
		try {
			result = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			result = null;
		}
		return result;
		
	}
}
