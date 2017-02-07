package dao;

import java.math.BigInteger;

public class ClientDao extends DAO {

	
	    public boolean clientValidator(String cpf) {
	        boolean result = false;
	        try {
	            int count = ((BigInteger) getSession().createSQLQuery(
	                    "select count(*) from cliente where cpf='" + cpf + "'").uniqueResult()).intValue();
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
	                    "select count(*) from cliente where  email='" + email + "'").uniqueResult()).intValue();
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
