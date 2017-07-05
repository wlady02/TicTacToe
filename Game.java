package ClientTicTacToe;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFrame;

//import javax.swing.JFrame;



import ServerTicTacToe.IGame;

public class Game implements IGame{	
	
	static String host="localhost";
	static int port =9999;
	static int iid=1;
	int oid=0;
    public static final int NUMBER_OF_CELLLS = 9;
	
	private final int COM1 = 0x3f;						// Combinacion 1 - primera fila.
    private final int COM2 = 0xfc0;						// Combinacion 2 - segunda fila.
    private final int COM3 = 0x3f000;					// Combinacion 3 - tercera fila.
    private final int COM4 = 0x30c3;					// Combinacion 4 - primera columna.
    private final int COM5 = 0xc30c;					// Combinacion 5 - segunda columna.
    private final int COM6 = 0x30c30;					// Combinacion 6 - tercera columna.
    private final int COM7 = 0x30303;					// Combinacion 7 - diagonal de izquierda a derecha.
    private final int COM8 = 0x3330;					// Combinacion 8 - diagonal de derecha a izquierda.
    
    private final int PLAYER1_VALUE = 1;
    private final int PLAYER2_VALUE = 2;				//CPU
    private final int MASK = 3;
	
	private int board;
	
    private int movements;
	private Player player;	
	public Game(){
		board = 0;
		movements = 0;
		player = Player.PLAYER1;
		System.out.println("entro al constructor Game cliente");
		try{
		Socket s = new Socket(host, port);
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        DataInputStream dis = new DataInputStream(is);
        dos.writeInt(iid);
        dos.writeInt(1);
        
        if(dis.readBoolean()){
        	//setBoard();
        	System.out.println("recibida confirmacion de server, case 1");
        	s.close();
        }             
		}
		catch(Exception e)
		{			
		}
	}
	
	
	public Game(Player player){
		System.out.println("entro constructor Game cliente player");
		board = 0;
		movements = 0;
		this.player = player;
	}
	
	public Movement move(int cell) {
		
		try{
			Socket s = new Socket(host, port);
			InputStream is = s.getInputStream();
	        OutputStream os = s.getOutputStream();
	        DataOutputStream dos = new DataOutputStream(os);
	        DataInputStream dis = new DataInputStream(is);
	        dos.writeInt(iid);
	        dos.writeInt(2);
	        dos.writeInt(cell);//envio el indice cuando hago click
	        s.close();
			}catch(Exception e){e.printStackTrace();}
		
		if(!isValid(cell))
			return Movement.NOT_ALLOWED;
		
		int movement;
		if(player == Player.PLAYER1)
			movement = PLAYER1_VALUE << (2 * cell);
		else
			movement = PLAYER2_VALUE << (2 * cell);
		
		board |= movement;
		movements++;
		
		if(isWinner())
			return player == Player.PLAYER1 ? Movement.WINNING_PLAYER1 : Movement.WINNING_PLAYER2;
		
		if(movements == NUMBER_OF_CELLLS)
			return Movement.FINAL;

		//player = player == Player.PLAYER1 ? Player.PLAYER2 : Player.PLAYER1; //se cambia el valor de player de player1 a player2
		return Movement.ALLOWED;
	}
	
	private boolean isValid(int cell) {
		int value = board & (MASK << (2 * cell));
		return value == 0;
	}
	
	private boolean isWinner(){
		if(player == Player.PLAYER1)
			return ((board & COM1) == 0x15) || ((board & COM2) == 0x540) || ((board & COM3) == 0x15000) ||
                    ((board & COM4) == 0x1041) || ((board & COM5) == 0x4104) || ((board & COM6) == 0x10410) ||
                    ((board & COM7) == 0x10101) || ((board & COM8) == 0x1110);
		else
			return ((board & COM1) == 0x2a) || ((board & COM2) == 0xa80) || ((board & COM3) == 0x2a000) ||
                    ((board & COM4) == 0x2082) || ((board & COM5) == 0x8208) || ((board & COM6) == 0x20820) ||
                    ((board & COM7) == 0x20202) || ((board & COM8) == 0x2220);
	}
	
	public String[] getBoard(){
		String[] cells = new String[NUMBER_OF_CELLLS];
		int value;
		
		for(int i = 0; i < NUMBER_OF_CELLLS; i++){
			value = board & (MASK << (2 * i));
			value >>= (2 * i);
			if(value == PLAYER1_VALUE)
				cells[i] = "X";
			else if(value == PLAYER2_VALUE)
				cells[i] = "O";
			else
				cells[i] = "";
		}
		
		return cells;
	}

	public void setBoard(){
		Board board = new Board("Tablero");
		board.setSize(300, 350);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
	}
	
	public int getMove() {
		// TODO Auto-generated method stub
		
		//cliente enviar conexion por socket
		return 0;
	}
	
	public void setMove(int pos, int value) {
		// TODO Auto-generated method stub
		
		//recibir conexion del sever
		
	}
	public void setPlayer(String p){
		
	}
	public Player getPlayer(Player p) {
		return p.PLAYER1;
	}
	
}
