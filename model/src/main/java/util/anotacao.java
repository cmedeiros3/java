/*
 * Para fazer a chamada do validator de CPF em um campo do formulário:
 

<h:inputText value="#{cliente.cpf}" required="true" maxlength="11">
     <f:validator validatorId="validator.CpfValidator" />
</h:inputText>
Por fim, precisamos colocar no bundle a mensagem de erro de validação de CPF:
1
erro.validacao.cpf=CPF inválido.

*
*/