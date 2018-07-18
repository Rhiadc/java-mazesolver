import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class MazeSolver{

	public Parede parede;
	public Livre livre;
	public Saida saida;
	public Entrada entrada;
	public Asterisco asterisco;
	public Object[][] objeto;


	public MazeSolver(Object[][] objeto, Parede parede, Entrada entrada, Livre livre, Saida saida, Asterisco asterisco){
		this.objeto = objeto;
		this.parede = parede;
		this.entrada = entrada;
		this.livre = livre;
		this.saida = saida;
		this.asterisco = asterisco;
	}
	/** A seguinte função recebe como parâmetros o labirinto (uma matriz de caracteres) e as coordenadas x,y de onde se encontra.
	* o parametro d, pode ser um dos 4 casos: 0 = cima, 1 = direita, 2 = baixo, 3 = esquerda. Este parametro esta ali para garantir
	* que o algoritmo não fique num loop infinito como subir/descer, esquerda/direita, ou seja, se numa chamada n eu fui para a direita,
	* numa chamada n+1 não poderei ir para esquerda, evitando loops infinitos. A resolução do mapa é simples, é apenas feita uma verificação
	* de caminhos vazios ao redor do ponto de cordenada passado, caso exista um ponto objeto[x][y] == '', o algoritmo se chama recursivamente
	* para este ponto */
	public  boolean mazeRecursive(int x, int y, int d){
		boolean solved = false;
		/** O ultimo movimento é descartado caso não encontre um caso dentro do swith, assim, os dead-ends são excluidos da solução final, por exemplo, caso o algoritmo encontre um 
		* dead end com dois caminhos vazios à frente, ele andara os dois caminhos e a ultima instancia (chamaremos de "n", para exemplificar) será perdida, visto que a mesma não
		* contem mais nenhum caso no switch. A instância n-1 que deu origem a recursão n, também sera perdida, visto que o ultimo caso possível para a mesma ja foi verificado na chamada
		* da recursão n, assim, nos sobrando a recursão n-2, que por sua vez evitara entrar no dead-end novamente (visto que a coordenada ja foi verificada no swith.)
		*/

		
		for (int i=0; i<4 && !solved; i++){
			if (i != d){
				switch (i){
					// 0 = cima, 1 = direita, 2 = baixo, 3 = esquerda
				    case 0:
					if (objeto[y-1][x] instanceof Livre){
					    solved = mazeRecursive ( x, y - 1, 2);

					}
					break;
				    case 1:
					if (objeto[y][x+1] instanceof Livre){
					    solved = mazeRecursive ( x + 1, y, 3);

					}
					break;
				    case 2:
					if (objeto[y+1][x] instanceof Livre){
					    solved = mazeRecursive ( x, y + 1, 0);

					}
					break;
				    case 3:
					if (objeto[y][x-1] instanceof Livre){
					    solved = mazeRecursive ( x - 1, y, 1);

					}
					break;	
					}
			}
		}

		//A seguinte parte do código desenha o caminho a partir do momento em que as recurcões são fechadas, utilizando o parametro "d" para o mesmo. O caminho agora desenhado
			//pode ter sua corretude afirmada graças à recursividade que excluiu os dead-ends da solução.
		
		if (objeto[y-1][x] instanceof Saida ||  objeto[y][x+1] instanceof Saida || objeto[y+1][x] instanceof Saida || objeto[y][x-1] instanceof Saida ){
          		solved = true;
		}
		if (solved){
				objeto[y][x] = (Asterisco) asterisco;
				switch (d){
					case 0:
						objeto[y-1][x] = (Asterisco) asterisco;
						break;
					case 1:
						objeto[y][x+1] = (Asterisco) asterisco;
						break;
					case 2:
						objeto[y+1][x] = (Asterisco) asterisco;
						break;
					case 3:
						objeto[y][x-1] = (Asterisco) asterisco;
						break;
					    }
			}

		return solved;

		}

	public void imprime(){
		char valor;
		for(int i=0; i<objeto.length; i++){
			for(int j=0; j<objeto[0].length; j++){
				if(objeto[i][j]!=null){
					if(objeto[i][j] instanceof Parede){
						Parede parede = (Parede) objeto[i][j];
						valor = parede.getValor();
						System.out.print(valor + " ");
					}else if(objeto[i][j] instanceof Entrada){
						Entrada entrada = (Entrada) objeto[i][j];
						valor = entrada.getValor();
						System.out.print(valor + " ");
					} else if(objeto[i][j] instanceof Asterisco){
						Asterisco asterisco = (Asterisco) objeto[i][j];
						valor =  asterisco.getValor();
						System.out.print(valor + " ");
					}else if(objeto[i][j] instanceof Saida){
						Saida saida = (Saida) objeto[i][j];
						valor =  saida.getValor();
						System.out.print(valor + " ");
					}else{
						Caminho caminho = (Caminho) objeto[i][j];
						valor = caminho.getValor();
						System.out.println(valor + " ");
					}
				}else{
					System.out.println("nao funfou");
				}	
			}
			System.out.println(" ");
		}
	}


		/**
				Exemplo de mapa utilizado:
						x x x x x x x x x x
						g   x x           f
						x   x x   x x x   x
						x   x x x x       x
						x  	    x x   x
						x   x   x x x x   x
						x   x   x         x
						x   x   x   x x   x
						x   x         x   f
						x x x x x x x x x x		

		*/
	


}
