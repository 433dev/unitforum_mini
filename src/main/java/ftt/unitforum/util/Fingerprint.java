package ftt.unitforum.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class Fingerprint {

	public static String getMD5(String str){
		String result;
		byte digestedData[];
		StringBuffer sb = new StringBuffer();
		
		try{
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(str.getBytes());
			digestedData = md.digest();

			for(int i = 0 ; i < digestedData.length ; i++){
				sb.append(Integer.toString( (digestedData[i] & 0xff) + 0x100, 16).substring(1) );
			}
			
			result = sb.toString();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
			result = null;
		}

		return result;
	}
}