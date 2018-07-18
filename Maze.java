import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Maze{

	//declarar tipos
	private Parede parede;
	private Livre livre;
	private Saida saida;
	public Object[][] objeto;
	private char[][] mapaConvertido;
	private char[][] mapaConvertidoImpressao;
	private List<String> listaConversao; 
	//arquivoMapa é um passado no construtor numero de 0 à 4
	private int arquivoMapa;

	public Maze(int arquivoMapa){
		this.arquivoMapa = arquivoMapa;
		this.objeto = new Object[10][10];
		this.mapaConvertido = new char[10][10];
	}

	//retorna o numero do mapa
	private int getArquivoMapa(){
		return this.arquivoMapa;
	}

	public void soluciona(){
		listaConversao = leMapa(getArquivoMapa());
		mapaConvertido = converteMapa(listaConversao);
	}

	//Este metodo le recebe como um parâmetro um valor de 0 a 4, lê o txt do mapa associado ao valor, cria uma lista de strings, e armazena cada
	//linha como uma unica string
	private List<String> leMapa(int value){
		String arquivo = "mapa" + value + ".txt";
		//falta criar um excp
		String linha;  
		File mapa = new File(arquivo);
		List<String> lista = new ArrayList<String>();
		try {
		  BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(mapa)));
		  while (br.ready()) {
		    linha = br.readLine();
		    lista.add(linha);
		  }
		  br.close(); 
		}
		catch (Exception e) {
		  System.out.println("Erro: " + e.getMessage());
		}
		return lista;
	}

	//Este método recebe a lista de strings, lida do txt de mapa, e a converte para uma matriz de char, ignorando os espaços adicionais (retirados da
	// posição impar)
	private char[][] converteMapa(List mapa){
		int cont = 0;
		for (int i = 0; i <mapa.size(); i++) {
			String s = (String) mapa.get(i);
			for(int j=0; j<s.length(); j++){
				if(j%2==0){
					mapaConvertido[i][cont] = s.charAt(j); 
					cont+=1;
				}
			}
			cont=0;								
		}
		return mapaConvertido;
	}

	//Este metodo recebe a matriz em char e cria uma nova matriz de objetos espaço/caminho/final 
	public Object[][] insere(Livre livre, Parede parede, Saida saida){
		for(int i=0; i<mapaConvertido.length; i++){
			for(int j=0; j<mapaConvertido[0].length; j++){
				if(mapaConvertido[i][j]== ' '){
					objeto[i][j] = (Livre) livre;
				}else if(mapaConvertido[i][j] == 'x'){
					objeto[i][j] = (Parede) parede;
				}else{
					objeto[i][j] = (Saida) saida;
				}
				
			}
		}
		return objeto;
	}

	public void imprimeTeste(){
		System.out.println(objeto.length);
		System.out.println(objeto[0].length);
	}

	//acabar esse metodo
	/*private char[][] converteMapaSaida(char[][] maze){
		int totalCelulas = 2*maze.length-1;
		mapaConvertidoImpressao = new char[totalCelulas][totalCelulas];
		for (int i = 0; i<maze.length; i++){
			for(int j =0; j<maze.length; j++){
				mapaConvertidoImpressao[i]
			}
			System.out.println(" ");
		}
	}

	private invoca
	*/

	public void imprimeSolucao(char[][] maze){
		for (int i = 0; i<maze.length; i++){
			for(int j =0; j<maze.length; j++){
				System.out.print(maze[i][j] + " ");
			}
			System.out.println(" ");
		}
	}


	 // metodo que recebe o mapa resolvido e devolve uma matriz char com o mapa resolvido agora espaçado
	 // metodo que recebe matriz de char e cria uma matriz de objetos espaços/caminhos*/
}
