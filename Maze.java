import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Maze{

	//declarar tipos
	Parede[] paredes;
	Livre[] livre;
	char[][] mapaConvertido = new char[10][10];
	char[][] mapaConvertidoImpressao;
	List<String> listaConversao; 
	//arquivoMapa é um passado no construtor numero de 0 à 4
	int arquivoMapa;

	Public maze(int arquivoMapa){
		this.arquivoMapa = arquivoMapa;
	}

	//retorna o numero do mapa
	private int getArquivoMapa(){
		return this.arquivoMapa;
	}

	public void soluciona(){
		listaConversao = leMapa(getArquivoMapa());
		mapaConvertido = converteMapa(listaConversao);
	}


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

	//acabar esse metodo
	private char[][] converteMapaSaida(char[][] maze){
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
