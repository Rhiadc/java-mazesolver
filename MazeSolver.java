public class MazeSolver{

	private void solveRecursive(char[][] maze, int x, int y, int d){
		boolean solved = false;
		//0=up, 1 = right, 2 = down, 3 = left
		//a direção passada no 4 parametro serve para o algoritmo não ficar num loop infinito
		for (int i=0; i<4 && !solved; i++){
			if(i!=d){	
				switch(i){
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
			}	
		}
	}
}