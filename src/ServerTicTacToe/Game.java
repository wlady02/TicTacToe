package ServerTicTacToe;

public class Game implements IGame{
	int[] anArray = new int[9];
		
	Game(){
		this.anArray[0]=-1;this.anArray[1]=-1;this.anArray[2]=-1;
		this.anArray[3]=-1;this.anArray[4]=-1;this.anArray[5]=-1;
		this.anArray[6]=-1;this.anArray[7]=-1;this.anArray[8]=-1;
		
	}
	
	public int getMove() {
		//escoger posición aleatoria y actualizar el tablero y devolver ese numero
		
		
		
		return 0;
	}
	
	//hacer jugada
	public void setMove(int pos,int value) {
		this.anArray[pos]=value;
	}
	
	public void imprimirtabla(){
		for(int i=0;i<9;i++){
			System.out.println(anArray[i]+"  ");
		}
	}

}

