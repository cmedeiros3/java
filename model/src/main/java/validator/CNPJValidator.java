package validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("CNPJValidator")
public class CNPJValidator implements Validator {

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (!validaCNPJ(String.valueOf(value))) {
			FacesMessage message = new FacesMessage();
			FacesContext facescontext = FacesContext.getCurrentInstance();
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"CNPJ Inválido", "");
			throw new ValidatorException(message);
		}

	}

	public static boolean validaCNPJ(String cnpj) {
		if (cnpj == null | cnpj.length() != 14) {
			return false;
		}
		try {
			Long.parseLong(cnpj);
		} catch (NumberFormatException e) {
			return false;
		}

		int soma = 0;
		String calculate = cnpj.substring(0, 12);
		char cnpj_char[] = cnpj.toCharArray();
		for (int i = 0; i < 4; i++)
			if (cnpj_char[i] - 48 >= 0 && cnpj_char[i] - 48 <= 9)
				soma += (cnpj_char[i] - 48) * (6 - (i + 1));

		for (int i = 0; i < 8; i++)
			if (cnpj_char[i + 4] - 48 >= 0 && cnpj_char[i + 4] - 48 <= 9)
				soma += (cnpj_char[i + 4] - 48) * (10 - (i + 1));

		int dig = 11 - soma % 11;
		calculate = (new StringBuilder(String.valueOf(calculate))).append(
				dig != 10 && dig != 11 ? Integer.toString(dig) : "0")
				.toString();
		soma = 0;
		for (int i = 0; i < 5; i++)
			if (cnpj_char[i] - 48 >= 0 && cnpj_char[i] - 48 <= 9)
				soma += (cnpj_char[i] - 48) * (7 - (i + 1));

		for (int i = 0; i < 8; i++)
			if (cnpj_char[i + 5] - 48 >= 0 && cnpj_char[i + 5] - 48 <= 9)
				soma += (cnpj_char[i + 5] - 48) * (10 - (i + 1));

		dig = 11 - soma % 11;
		calculate = (new StringBuilder(String.valueOf(calculate))).append(
				dig != 10 && dig != 11 ? Integer.toString(dig) : "0")
				.toString();

		return cnpj.equals(calculate);

	}
}
