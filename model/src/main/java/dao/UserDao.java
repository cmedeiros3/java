package dao;

import java.math.BigInteger;

public class UserDao extends DAO {

	
	  public boolean searchLogin(String username, String password) {
	        boolean result = false;
	        try {
	            int count = ((BigInteger) getSession().createSQLQuery(
	                    "select count(*) from login where username='" + username + "' and password='" + password + "'").uniqueResult()).intValue();
	            System.out.println("count " + count);
	            if (count > 0) {
	                result = true;
	            }
	        } catch (Exception e) {
	            result = false;
	        }

	        return result;

	    }

	    public boolean usernameValidator(String username) {
	        boolean result = false;
	        try {
	            int count = ((BigInteger) getSession().createSQLQuery(
	                    "select count(*) from login where username='" + username + "'").uniqueResult()).intValue();
	            System.out.println("count " + count);
	            if (count > 0) {
	                result = true;
	            }
	        } catch (Exception e) {
	            result = false;
	        }

	        return result;

	    }
	    
	    public boolean emailValidator(String email) {
	        boolean result = false;
	        try {
	            int count = ((BigInteger) getSession().createSQLQuery(
	                    "select count(*) from login where  email='" + email + "'").uniqueResult()).intValue();
	            System.out.println("count " + count);
	            if (count > 0) {
	                result = true;
	            }
	        } catch (Exception e) {
	            result = false;
	        }

	        return result;

	    }
	    
	    

}
