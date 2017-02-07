package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

@FacesConverter("CPFConverter")
public class CPFConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) throws ConverterException{
		
		String cpf=value;
		if(value!=null && value.equals(" ")==false){
			cpf=value.replaceAll("\\.", "").replaceAll("\\-", "");
			
		}	
		
		System.out.println("CPF getAsObject" + cpf);
		return cpf;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) throws ConverterException {
		System.out.println("CPF getAsString");
		String cpf=(String) value;
		if(cpf!=null && cpf.length()==11){
			cpf=cpf.substring(0,3)+"." +cpf.substring(3,6) +"." + cpf.substring(6,9)+"-"+cpf.substring(9,11);
		}
		return cpf;
	}
	

}
