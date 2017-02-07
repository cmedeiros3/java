package webservice;

import java.net.URL;



import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class CEPWebService {

	private String estado = "";
	private String cidade = "";
	private String bairro = "";
	private String logradouro = "";
	private int resultado = 0;

	@SuppressWarnings("rawtypes")
	public CEPWebService(String cep) {
		System.out.println("CEP Service");
		try {
			URL url = new URL(
					"http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep
							+ "&formato=xml");
			Document document = getDocument(url);
			Element root = document.getRootElement();
			System.out.println("url");
			for (Iterator i = (Iterator) root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				System.out.println("Iterator");
				if (element.getQualifiedName().equals("uf"))
					setEstado(element.getText());
				if (element.getQualifiedName().equals("cidade"))
					setCidade(element.getText());
				if (element.getQualifiedName().equals("bairro"))
					setBairro(element.getText());
				
				if (element.getQualifiedName().equals("logradouro"))
					setLogradouro(element.getText());
				
				if (element.getQualifiedName().equals("resultado"))
					setResultado(Integer.parseInt(element.getText()));
				
				System.out.println(getLogradouro());
				System.out.println(getEstado());
				System.out.println(getBairro());
				System.out.println(getCidade());
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	public Document getDocument(URL url) throws DocumentException{
		SAXReader reader =new SAXReader();
		Document document=reader.read(url);
		return document;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

}
