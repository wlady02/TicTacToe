package ClientTicTacToe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Cell extends JButton {
	private int number;
	private static final long serialVersionUID = 1L;	
	public Cell(int number){
		this.number = number;
		setFont(new Font("Ubuntu", 1, 36));
		setBackground(Color.CYAN);
		setBorder(BorderFactory.createEmptyBorder());
	}
	
	public int getCellNumber(){
		return number;
	}
	

}
