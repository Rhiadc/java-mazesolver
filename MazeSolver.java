import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class MazeSolver{


	/** A seguinte função recebe como parâmetros o labirinto (uma matriz de caracteres) e as coordenadas x,y de onde se encontra.
	* o parametro d, pode ser um dos 4 casos: 0 = cima, 1 = direita, 2 = baixo, 3 = esquerda. Este parametro esta ali para garantir
	* que o algoritmo não fique num loop infinito como subir/descer, esquerda/direita, ou seja, se numa chamada n eu fui para a direita,
	* numa chamada n+1 não poderei ir para esquerda, evitando loops infinitos. A resolução do mapa é simples, é apenas feita uma verificação
	* de caminhos vazios ao redor do ponto de cordenada passado, caso exista um ponto maze[x][y] == '', o algoritmo se chama recursivamente
	* para este ponto */
	private static boolean solveRecursive(char[][] maze, int x, int y, int d){
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
					if (maze[y-1][x] == ' '){
					    solved = solveRecursive (maze, x, y - 1, 2);

					}
					break;
				    case 1:
					if (maze[y][x+1] == ' '){
					    solved = solveRecursive (maze, x + 1, y, 3);

					}
					break;
				    case 2:
					if (maze[y+1][x] == ' '){
					    solved = solveRecursive (maze, x, y + 1, 0);

					}
					break;
				    case 3:
					if (maze[y][x-1] == ' '){
					    solved = solveRecursive (maze, x - 1, y, 1);

					}
					break;	
					}
			}
		}

		/*A seguinte parte do código desenha o caminho a partir do momento em que as recurcões são fechadas, utilizando o parametro "d" para o mesmo. O caminho agora desenhado
			pode ter sua corretude afirmada graças à recursividade que excluiu os dead-ends da solução.
		*/
		if (maze[y-1][x] == 'f' ||  maze[y][x+1] == 'f' || maze[y+1][x] == 'f' || maze[y][x-1] == 'f' ){
          		solved = true;
		}
		if (solved){
				maze[y][x] = '*';
				switch (d){
					case 0:
						maze[y-1][x] = '*';
						break;
					case 1:
						maze[y][x+1] = '*';
						break;
					case 2:
						maze[y+1][x] = '*';
						break;
					case 3:
						maze[y][x-1] = '*';
						break;
					    }
			}

		return solved;

		}


	public static void imprime(char[][] maze){
		for (int i = 0; i<maze.length; i++){
			for(int j =0; j<maze.length; j++){
				System.out.print(maze[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

	public static List<String> leMapa(int value){
		String arquivo = "/mapa" + value + ".txt";
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

	public static void main(String[] args){
		char[][] maze = new char[][]{{'x','x','x','x','x','x','x','x','x','x'},
										{'g',' ','x','x',' ',' ',' ',' ',' ','f'},
										{'x',' ','x','x',' ','x','x','x',' ','x'},
										{'x',' ','x','x','x','x',' ',' ',' ','x'},
										{'x',' ',' ',' ',' ',' ','x','x',' ','x'},
										{'x',' ','x',' ','x','x','x','x',' ','x'},
										{'x',' ','x',' ','x',' ',' ',' ',' ','x'},
										{'x',' ','x',' ','x',' ','x','x',' ','x'},
										{'x',' ','x',' ',' ',' ',' ','x',' ','x'},
										{'x','x','x','x','x','x','x','x','x','x'},};
		//solveRecursive(maze, 1, 1, -1);
		//imprime(maze);
		List<String> mapa = leMapa(0);
		for (int i = 0; i < mapa.size(); i++) {
			String s = (String) mapa.get(i);
			System.out.println(s);								
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


}
