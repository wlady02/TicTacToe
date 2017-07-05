package ServerTicTacToe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import ClientTicTacToe.Cell;
import ClientTicTacToe.Player;

public class SkeletonGame implements ISkeleton {	
	private final int iid = 1;
    private static int objid = 0;
    Game objetoGame = null;
    Hashtable<Integer, Object> objectHash = new Hashtable<Integer, Object>();
    public void addObject(Object obj) {
        objectHash.put(objid, obj);
        objid++;
    }
    public Object getObject(int objid) {
        return objectHash.get(objid);
    }

	@Override
	public int getIid() {
		// TODO Auto-generated method stub
		return iid;
	}

	@Override
	public void process(DataInputStream canalEntrada,
			DataOutputStream canalSalida) {
				//String message;
				System.out.println("entro al process");
				//int movement;
				int player;
				
				try {
					
					//objid=canalEntrada.readInt();
					//System.out.println(objid);
					int numMethod = canalEntrada.readInt();
					System.out.println("recibo del cliente");
					System.out.println(numMethod);
		            switch (numMethod) {
	                case 1:	                	
	                	//Crear instancia del juego
	                	System.out.println("entro al primer case");
	                    objetoGame = new Game();
	                    addObject(objetoGame);
	                    canalSalida.writeBoolean(true);
	                    
	                    //objetoGame.imprimirtabla();
	                    break;	                    
	                /*case 2:	                	
	                	objid =canalEntrada.readInt();
	                    player = canalEntrada.readInt();
						//movement = Integer.parseInt(message);	          
	                    objetoGame = (Game) getObject(objid);	                    
	                    if (player == Player.PLAYER1.ordinal()){
							canalSalida.writeUTF("Es tu turno");
	                    }
						else
							canalSalida.writeUTF("Es el turno del jugador 1");
	                    break;*/
	                case 2:
	                	int move = canalEntrada.readInt();
	                	System.out.println("Entrando al case 3"+move);
	                	//objetoGame.setMove(move,1);
	                	objetoGame = (Game)getObject(objid);
	                	objetoGame.setMove(move, 1);
	                	//objetoGame.imprimirtabla();
	                	break;
		            }
				} catch (IOException exception) {
					System.out.println("Error on playing: " + exception);
				} 				
			}		
}


