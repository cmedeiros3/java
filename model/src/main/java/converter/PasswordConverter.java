package converter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordConverter {
	
	public static String md5(String password){
		System.out.println("PasswordConverter "+password);
		String pw =null;
		MessageDigest md=null;
		try{
			md= MessageDigest.getInstance("MD5");
			
		}catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
		BigInteger hash =new BigInteger(1, md.digest(password.getBytes()));
		pw=hash.toString(16);
		return pw;
	}

}
