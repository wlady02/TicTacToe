package ServerTicTacToe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;

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
	public int getIid() {
		// TODO Auto-generated method stub
		return iid;
	}

	public void process(DataInputStream canalEntrada, DataOutputStream canalSalida) {
				System.out.println("entro al process");
				try {
					Game objetoGame;
					int numMethod = canalEntrada.readInt();
					System.out.println(numMethod);
		            switch (numMethod) {
	                case 1:	{
	                	//Crear instancia del juego
	                	System.out.println("case 1");
	                    objetoGame = new Game();
	                    objectHash.put(objid,objetoGame);
	                    canalSalida.writeBoolean(true);
	                    break;
	                    }	                    
	                case 2: {
	                	int move = canalEntrada.readInt();
	                	System.out.println("case 2 "+move);
	                	//objetoGame.setMove(move,1);
	                	objetoGame = (Game)objectHash.get(objid);
	                	System.out.println(objetoGame);
	                	objetoGame.setMove(move,1);
	                	objetoGame.imprimirtabla();
	                	break;
	                	}
		            }
				} catch (IOException exception) {
					System.out.println("Error on playing: " + exception);
				} 				
			}		
}


