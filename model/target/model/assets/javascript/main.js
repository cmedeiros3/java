$(document).ready(function() {
	var pessoa = document.getElementById("pessoa");
	var cpf= document.getElementById("cpf");
	var cnpj= document.getElementById("cnpj");
	
		if(pessoa=="F"){
			cnpj.style.display="none";
			cpf.style.display="block";
			
		}else if(pessoa=="J"){
			cpf.style.display="none";
			rg.style.display="none";
			cnpj.style.display="block";
			
			
		}else{
			alert("Selecione pessoa física ou jurídica");
		}

});
