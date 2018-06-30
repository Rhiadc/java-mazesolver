public class MazeSolver{
	/** A seguinte função recebe como parâmetros o labirinto (uma matriz de caracteres) e as coordenadas x,y de onde se encontra.
	* o parametro d, pode ser um dos 4 casos: 0 = cima, 1 = direita, 2 = baixo, 3 = esquerda. Este parametro esta ali para garantir
	* que o algoritmo não fique num loop infinito como subir/descer, esquerda/direita, ou seja, se numa chamada n eu fui para a direita,
	* numa chamada n+1 não poderei ir para esquerda, evitando loops infinitos. A resolução do mapa é simples, é apenas feita uma verificação
	* de caminhos vazios ao redor do ponto de cordenada passado, caso exista um ponto maze[x][y] == '', o algoritmo se chama recursivamente
	* para este ponto */
	private void solveRecursive(char[][] maze, int x, int y, int d){
		boolean solved = false;
		/*o booleano serve para caso o algoritmo chegue num dead-end, ele consiga sair, note que sem o booleano, o algoritmo
		ficaria preso em um dead-end, caso encontrasse um*/
		for (int i=0; i<4 && !solved; i++){
			if(i!=d){	
				switch(i){
					//up
					case 0:
						if (maze[y-1][x] == ' '){
							solved = true; 
							solveRecursive(maze, y-1, x, 2);
							break;
						}
					//right	
					case 1:
						if(maze[y][x+1] == ' '){
							solved = true;
							solveRecursive(maze, y, x+1, 3);
							break;
						}
					case 2:
						if(maze[y+1][x] == ' '){
							solved = true;
							solveRecursive(maze, y+1, x, 0);
							break;
						}
					case 3:
						if(maze[y][x-1] == ' '){
							solved = true;
							solveRecursive(maze, y, x-1, 1);
							break;
						}	
				}
			}	
		}
		/*Esta segunda parte do codigo funciona como um escape de uma dead-zone. Caso o algoritmo se encontra em uma, ele 
		a marcará com um 'x' e saira dela.*/
		if(!solved){
			maze[y][x] = 'x';
			switch(d){
					//up
					case 0:
						if (maze[y-1][x] == ' '){
							solved = true;
							solveRecursive(maze, y-1, x, 2);
							break;
						}
					//right	
					case 1:
						if(maze[y][x+1] == ' '){
							solved = true;
							solveRecursive(maze, y, x+1, 3);
							break;
						}
					case 2:
						if(maze[y+1][x] == ' '){
							solved = true;
							solveRecursive(maze, y+1, x, 0);
							break;
						}
					case 3:
						if(maze[y][x-1] == ' '){
							solved = true;
							solveRecursive(maze, y, x-1, 1);
							break;
						}	
				}
			 
		}
	}
}
