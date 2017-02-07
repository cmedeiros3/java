package validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("CPFValidator")
public class CPFValidator implements Validator {

	public void validate(FacesContext arg0, UIComponent arg1, Object field)
			throws ValidatorException {
		//System.out.println("validate void");
		if (validateCPF(String.valueOf(field))==false) {
		//	System.out.println("message error");
			FacesMessage message = new FacesMessage();
			FacesContext context = FacesContext.getCurrentInstance();
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"CPF Inválido", "");
			//context.addMessage(null, message);
			throw new ValidatorException(message);
			
		}

	}

	public static boolean validateCPF(String cpf) {
		//System.out.println("validateCPF" +cpf);
		if (cpf == null || cpf.length() != 11 || isPadrao(cpf)){
		//	System.out.println("validateCPF false");
			return false;
		}	
		try {
			Long.parseLong(cpf);
		} catch (NumberFormatException e) {
			return false;
		}

		return DigitVerification(cpf.substring(0, 9)).equals(
				cpf.substring(9, 11));
	}

	private static boolean isPadrao(String cpf) {
		if (cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444")
				|| cpf.equals("55555555555") || cpf.equals("66666666666")
				|| cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || cpf.equals("00000000000")){
			return true;
		}
			
			return false;
	}
	
	private static String DigitVerification(String num){
		//System.out.println("DigitVerification");
		Integer first, second;
		int soma=0, peso=10;
		for(int i=0;i<num.length();i++){
			soma +=Integer.parseInt(num.substring(i,i+1))* peso--;
		}
		if(soma %11==0 | soma %11==1){
			first = new Integer(0);
		}else{
			first= new Integer(11-(soma%11));
		}
		soma=0;
		peso=11;
		for(int i=0;i<num.length();i++){
			soma +=Integer.parseInt(num.substring(i,i+1))* peso--;
		}
		soma += first.intValue()*2;
		if(soma %11==0 | soma %11==1){
			second = new Integer(0);
		}else{
			second= new Integer(11-(soma%11));
		}
		
		return first.toString() +second.toString();
	}

}
