package ClientTicTacToe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Board extends javax.swing.JFrame {   
	private static final long serialVersionUID = 1L;    
	private JLabel label;
    private Cell[] cells;
    private Game game;
    
	public Board(String name){
		super(name);
		label = new JLabel();
		cells = new Cell[Game.NUMBER_OF_CELLLS];
		game = new Game();
		initBoard();
	}
	public void initBoard(){
		GridLayout layout = new GridLayout(3, 3);
		JPanel headerPanel = new JPanel();
		JPanel boardPanel = new JPanel();

		headerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		headerPanel.add(label, CENTER_ALIGNMENT);
		layout.setHgap(10);
		layout.setVgap(10);
		boardPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		boardPanel.setLayout(layout);
		boardPanel.setBackground(Color.WHITE);

		for(int i = 0; i < Game.NUMBER_OF_CELLLS; i++){
			cells[i] = new Cell(i);
			cells[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					onClick(event);					
				}
			});
			boardPanel.add(cells[i]);
		}		
		setLayout(new BorderLayout());
		add(headerPanel, BorderLayout.NORTH);
		add(boardPanel, BorderLayout.CENTER);
	}
	
	private void onClick(ActionEvent event) {
		Cell cell = (Cell) event.getSource();		
		Movement movement = game.move(cell.getCellNumber());
		if(movement == Movement.NOT_ALLOWED){
			label.setText("Movimiento no válido.");
		}
		else{
			String[] cellValues = game.getBoard();
			for(int i = 0; i < Game.NUMBER_OF_CELLLS; i++)
				cells[i].setText(cellValues[i]);
			
			if(movement != Movement.ALLOWED)
				finishGame();
			if(movement == Movement.WINNING_PLAYER1)
				label.setText("Ha ganado el jugador 1.");
			else if(movement == Movement.WINNING_PLAYER2)
				label.setText("Ha ganado el jugador 2.");
			else if(movement == Movement.FINAL)
				label.setText("Se ha terminado la partida. El juego ha quedado en tablas.");
		}
	}
	
	private void finishGame(){
		for(int i = 0; i < Game.NUMBER_OF_CELLLS; i++)
			cells[i].setEnabled(false);
	}
}