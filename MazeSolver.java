public class MazeSolver{
	
	/** A seguinte função recebe como parâmetros o labirinto (uma matriz de caracteres) e as coordenadas x,y de onde se encontra.
	* o parametro d, pode ser um dos 4 casos: 0 = cima, 1 = direita, 2 = baixo, 3 = esquerda. Este parametro esta ali para garantir
	* que o algoritmo não fique num loop infinito como subir/descer, esquerda/direita, ou seja, se numa chamada n eu fui para a direita,
	* numa chamada n+1 não poderei ir para esquerda, evitando loops infinitos. A resolução do mapa é simples, é apenas feita uma verificação
	* de caminhos vazios ao redor do ponto de cordenada passado, caso exista um ponto maze[x][y] == '', o algoritmo se chama recursivamente
	* para este ponto */
	
	
	private static boolean solveRecursive(char[][] maze, int x, int y, int d){
		boolean solved = false;
		
		
		/*o booleano serve para caso o algoritmo chegue num dead-end, ele consiga sair, note que sem o booleano, o algoritmo
		ficaria preso em um dead-end, caso encontrasse um*/
		
		for (int i=0; i<4; i++){
			if(x!=0 && y!=0 ){
				if(maze[y-1][x] == 'f' ||  maze[y][x+1] == 'f' || maze[y+1][x] == 'f' || maze[y][x-1] == 'f' ){
						System.out.println("Achei o final, coordenadas: " + x + "," + y);
						break;
				}
			}
			if (i != d){
				switch (i){
					// 0 = up, 1 = right, 2 = down, 3 = left
				    case 0:
					if (maze[y-1][x] == ' ')
					    solved = solveRecursive (maze, x, y - 1, 2);
					break;
				    case 1:
					if (maze[y][x+1] == ' ')
					    solved = solveRecursive (maze, x + 1, y, 3);
					break;
				    case 2:
					if (maze[y+1][x] == ' ')
					    solved = solveRecursive (maze, x, y + 1, 0);
					break;
				    case 3:
					if (maze[y][x-1] == ' ')
					    solved = solveRecursive (maze, x - 1, y, 1);
					break;	
						}
			}
		}
		
		
		/*Esta segunda parte do codigo funciona como um escape de uma dead-end. Caso o algoritmo se encontra em uma, ele 
		a marcará com um 'x' e saira dela.*/
		/*if(!solved){
			maze[y][x] = 'x';
			switch(d){
					//up
					case 0:
						if (maze[y-1][x] == ' '){
							solved = solveRecursive(maze, y-1, x, 2);
							break;
						}
					//right	
					case 1:
						if(maze[y][x+1] == ' '){
							solved = solveRecursive(maze, y, x+1, 3);
							break;
						}
					case 2:
						if(maze[y+1][x] == ' '){
							solved = solveRecursive(maze, y+1, x, 0);
							break;
						}
					case 3:
						if(maze[y][x-1] == ' '){
							solved = solveRecursive(maze, y, x-1, 1);
							break;
						}	
				}
			System.out.println("dead-end"); 
		}*/
		
		

		return solved;
	}

	public static void main(String[] args){
		char[][] maze = new char[][]{{'x','x','x','x','x','x'},{'g',' ',' ',' ',' ','x'},{'x',' ','x','x','x','x'},{'x',' ',' ',' ',' ','x'},{'x','x','x','x',' ','x'},{'x',' ',' ',' ',' ','x'},{'x','x','f','x','x','x'}};
		solveRecursive(maze, 1, 1, -1);
		/**
				Exemplo de mapa utilizado:
				x x x x x x 
				g         x
				x   x x x x
				x         x
				x x x x   x
				x         x
				x x f x x x
		
		*/
	}
}

