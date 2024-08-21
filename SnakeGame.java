package pkg2;

import java.awt.Color;

import javax.swing.JFrame;

public class SnakeGame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Snake Game");
		frame.setBounds(10,10,900,700);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePannel p1 = new GamePannel() ;
		p1.setBackground(Color.DARK_GRAY);
		frame.add(p1);
		
			
		
		frame.setVisible(true);

	}

}
