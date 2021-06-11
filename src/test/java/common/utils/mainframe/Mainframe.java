package common.utils.mainframe;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;

import io.cucumber.java.Scenario;



public class Mainframe {
	
private static int TIMEOUT = 10;
static Scenario scenario;
	
	public interface jna extends Library {
		@SuppressWarnings("deprecation")
		jna INSTANCE = (jna) Native.loadLibrary("libhllapi", jna.class);
		
		public long hllapi_init(String tp);
		public long hllapi_deinit();
		public long hllapi_get_revision();
		public long hllapi_connect(String uri, int wait);
		public long hllapi_disconnect();
		public long hllapi_get_screen_at(int row, int col, byte[] lpstr);
		public long hllapi_set_text_at(int row, int col, String text);
		public long hllapi_wait_for_ready(int timeout);
		public long hllapi_wait(int timeout);
		public NativeLong hllapi_get_message_id();
		public long hllapi_enter();
		public long hllapi_pfkey(int keyCode);
		public long hllapi_pakey(int keyCode);
	}
	
	/**
	 * Realiza a conexão à uma sessão do mainframe
	 * @param session Nome da sessão aberta do pw3270. Se informada string vazia, o teste roda em background. Geralmente o valor é: pw3270:A
	 * @param uri Endereço ip a se conectar já com a porta no formato: 000.000.000.0:0000
	 * @param timeout O tempo de timeout para a conexão em segundos
	 */
	public static void mfConnect(String session, String uri, int timeout) {
		if(!(jna.INSTANCE.hllapi_init(session) == 0))
			throw new Error("Not able to initiate session. Session not found.");
		
		if(!(jna.INSTANCE.hllapi_connect(uri, timeout) == 0))
			throw new Error("Not able to connect to the informed URI: " + uri);
	}
	
	/**
	 * Obtém a revisão do pw3270 que está sendo usada.
	 * @return Retorna a revisão que está sendo utilizada
	 */
	public static long getRevision() { 
		waitMFReady(TIMEOUT);
		
		return jna.INSTANCE.hllapi_get_revision();
	}
	
	/**
	 * Aguarda que o MainFrame esteja pronto para ser manipulado.
	 * @param timeout Tempo de espera para que a tela esteja pronta pra receber informação
	 */
	public static void waitMFReady(int timeout) {
		if(!(jna.INSTANCE.hllapi_wait_for_ready(timeout) == 0))
			throw new Error("Not able to wait for ready.");
	}
	
	/**
	 * Aguarda no mainframe pelo tempo estipulado.
	 * @param timeout Tempo de espera em segundos
	 */
	public static void waitMF(int timeout) {
		if(!(jna.INSTANCE.hllapi_wait(timeout) == 0))
			throw new Error("Not able to wait.");
	}
	
	/**
	 * Digita o texto na linha e coluna determinada.
	 * @param row A linha onde será digitada
	 * @param col A coluna onde será digitada
	 * @param text O texto que será digitado
	 */
	public static void setText(int row, int col, String text) {
		waitMFReady(TIMEOUT);
		
		if(!(jna.INSTANCE.hllapi_set_text_at(row, col, text) == 0))
			throw new Error("Not able to set the text in row " + row + ", col " + col + " value: " + text);
	}
	
	/**
	 * Retorna o valor do texto encontrado na tela na posição e no tamanho especificado.
	 * @param row A linha onde está o texto
	 * @param col A coluna onde está o texto
	 * @param size O tamanho do texto
	 * @return Retorna a string encontrada na tela
	 */	
	public static String getText(int row, int col, int size) {
		waitMFReady(TIMEOUT);
		
		return getStringInScreen(row, col).substring(0, size);
	}
	
	/**
	 * Retorna a String da tela completa
	 * @return Retorna a tela completa como String
	 */
	public static String getScreen() {
		waitMFReady(TIMEOUT);
		
		return getStringInScreen(1, 1);
	}
	
	private static String getStringInScreen(int row, int col) {
		waitMFReady(TIMEOUT);
		
		String text = "";
		
		for(int i = 0; i<2000; i++)
			text += " ";
		byte[] lpstr = new byte[2001];
		lpstr = text.getBytes();
		
		if(!(jna.INSTANCE.hllapi_get_screen_at(row, col, lpstr) == 0))
			throw new Error("Not able to get the text in the screen");
		
		return Native.toString(lpstr);
	}
	
	/**
	 * Pressiona Enter
	 */
	public static void Enter() {
		waitMFReady(TIMEOUT);
		
		if(!(jna.INSTANCE.hllapi_enter() == 0))
			throw new Error("Not able to press enter");
	}
	
	/**
	 * Pressiona uma tecla PF
	 * @param keyCode A tecla PF que deseja digitar. Ex: Se keyCode = 3, será pressionada a tecla PF3
	 */
	public static void pfKey(int keyCode) {
		waitMFReady(TIMEOUT);
		
		if(!(jna.INSTANCE.hllapi_pfkey(keyCode) == 0))
			throw new Error("Not able to press PF" + keyCode);
	}
	
	/**
	 * Pressiona uma tecla PA
	 * @param keyCode A tecla PA que deseja digitar. Ex: Se keyCode = 3, será pressionada a tecla PA3
	 */
	public static void paKey(int keyCode) {
		waitMFReady(TIMEOUT);
		
		if(!(jna.INSTANCE.hllapi_pakey(keyCode) == 0))
			throw new Error("Not able to press PA" + keyCode);
	}
	
	/**
	 * Disconecta do mainframe
	 */
	public static void disconnect() {
		waitMFReady(TIMEOUT);
		
		if(!(jna.INSTANCE.hllapi_disconnect() == 0))
			throw new Error("Not able to disconnect");
		
		if(!(jna.INSTANCE.hllapi_deinit() == 0))
			throw new Error("Not able to deinit");
	}
	
}
