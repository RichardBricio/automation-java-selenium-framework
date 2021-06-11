@Contabilizacao
Feature: Verificacao e amostragem de status do jogador

	@test
	Scenario: Pré Requisito - Acessar site ----
		Given Que eu esteja usando o browser CHROME
		Given Que eu acesso a pagina de login no ambiente de HI
#		When Eu acesso o menu ORDENS

#	Scenario Outline: Contabilizacao das Ordens
#		Examples:
#			|  |
#		And Acesso o ambiente "AC"
#		And Uso o login "User" e senha "Senha"
#		When Acesso a transação "GR6A"
#		And Informo os dados da minha consulta: "<CnpjConsultante>", "<DataConsulta>", "<Logon>", "<Transacao>", "<DocConsultado>", "<HoraInicial>" e "<HoraFinal>"
#		Then Exibe a contabilização da minha transação "<Transacao>"
#		And A quantidade cobrada será de: "<QtCob>"
#		And A quantidade não cobrada será de: " <QtNcob>"
#
#	Examples:
#	| Descrição                    	| CnpjConsultante	| DataConsulta  | Logon		| Transacao	| DocConsultado	|HoraInicial	| HoraFinal	|	QtCob	|	QtNcob	|
#	| Gasto calculado - Contabiliza	|	062173620				| 07/12/2018		| STJ2772	| NRGW			| 							| 15:27:40		| 15:27:59	|	1			|   			|
##	| Gasto não calculado - Loga		|	062173620				| 07/12/2018		| STJ2772	| NRGW			| 							| 15:27:40		| 15:27:59	|				|					|