package steps.mainframe;

import common.utils.mainframe.Mainframe;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;


public class mainframeSteps {
	
	@Given("^Eu me conecto no mainframe$")
	public void Eu_me_conecto_no_mainframe() throws Exception{
		Mainframe.mfConnect("pw3270:A", "192.168.101.1:1025", 10);
	}
	
	@Given("^Acesso o ambiente \"([^\"]*)\"$")
	public void Acesso_o_ambiente(String mainframe) throws Exception{
		Mainframe.setText(23, 7, "AC");
		Mainframe.Enter();
	}
	
	@Given("^Uso o login \"([^\"]*)\" e senha \"([^\"]*)\"$")
	public void Uso_o_login_senha(String login, String senha) throws Exception{
		Mainframe.setText(17, 19, login);
		Mainframe.setText(19, 19, senha);
		Mainframe.Enter();
	}
	
	@When("^Acesso a transação \"([^\"]*)\"$")
	public void Acesso_a_transacao(String transacao) throws Exception{
		Mainframe.setText(1, 1, transacao);
		Mainframe.Enter();
	}

	@When("^Informo os dados da minha consulta: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\"$")
	public void Acesso_a_transacao(String cnpjConsultante, String dtConsult, String logon, String transacao, String docConsultado, String horainicio, String horaFim) throws Exception{
		
		String data = dtConsult.replaceAll("/", "");
		String horaini = horainicio.replaceAll(":", "");
		String horafi = horaFim.replaceAll(":", "");
		Mainframe.setText(4, 16, cnpjConsultante);
		Mainframe.setText(4, 28, "1");
		Mainframe.setText(4, 36, data);
		Mainframe.setText(5, 16, logon);
		Mainframe.setText(5, 43, transacao);
		Mainframe.setText(5, 65, docConsultado);
		Mainframe.setText(6, 37, horaini);
		Mainframe.setText(6, 56, horafi);
		Mainframe.Enter();
		System.out.println(Mainframe.getScreen());
		Mainframe.waitMF(2);
	}
	
	@Then("^Exibe a contabilização da minha transação \"([^\"]*)\"$")
	public void Exibe_a_contabilizacao_da_minha_transacao(String transacao) throws Exception{
		Assert.assertEquals(transacao, Mainframe.getText(9, 32, 4));
	}
	
	@Then("^A quantidade cobrada será de: \"([^\"]*)\"$")
	public void A_quantidade_cobrada_sera_de(String qtcob) throws Exception{
		Assert.assertEquals(qtcob, Mainframe.getText(9, 70, 1));
	}
	
	@Then("^A quantidade não cobrada será de: \"([^\"]*)\"$")
	public void A_quantidade_nao_cobrada_sera_de(String qtncob) throws Exception{
		System.out.println(Mainframe.getText(9, 80, 1));
		Assert.assertEquals(qtncob, Mainframe.getText(9, 80, 1));
	}
	
	@After("@logoutMF")
	public void takeDown() {
		Mainframe.disconnect();
	}
}