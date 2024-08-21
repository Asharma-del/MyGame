package pkg2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javax.swing.Timer;
public class GamePannel extends JPanel implements ActionListener , KeyListener {
	
	private int [] snakexlength = new int [750];
	private int [] snakeylength = new int [750];
	private int lengthofsanke = 3;
	
	private int[] xpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] ypos= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	
	private Random random = new Random();
	private int foodx,foody;
	
	private boolean left= false;
	private boolean Right= true;
	private boolean up= false;
	private boolean down= false;
	
	private int moves = 0;
	private int score=0;
    private boolean gameover=false;
    private boolean pause= false;
	
//	ImageIcon snakeTitle = new ImageIcon(getClass().getResource("Game/Title img.jpg"));
	private ImageIcon snake = new ImageIcon(getClass().getResource("Game/snake.jpg"));
	private ImageIcon food= new ImageIcon(getClass().getResource("Game/food.jpg"));
 
	
	private Timer Timer;
	private int delay=100;
		public GamePannel() {
			this.addKeyListener(this);
			this.setFocusable(true);
			this.requestFocusInWindow();
			setFocusTraversalKeysEnabled(true);
			Timer = new Timer(delay,this);
			Timer.start();
			
			newfood();
			
		}
		private void newfood() {
			// TODO Auto-generated method stub
			foodx= xpos[random.nextInt(34)];
			foody = ypos[random.nextInt(23)];
			for(int i = lengthofsanke-1;i>=0; i--) {
				if(snakexlength[i]== foodx && snakeylength[0]== foody) {
					newfood();
				}
			}
			
		}
		public void paint(Graphics g) {
			super.paint(g);
			
			g.setColor(Color.WHITE);
			g.drawRect(25, 10, 851, 55);
			g.drawRect(25,74,851,576);
			
			g.setColor(Color.BLACK);
         //  snakeTitle.paintIcon(this, g, 25, 11);
			g.fillRect(25, 70, 851, 576);
			if(moves == 0) {

				snakexlength[0]=100;
				snakexlength[1]=75;
				snakexlength[2]=50;
				
				snakeylength[0]=100;
				snakeylength[1]=100;
				snakeylength[2]=100;
				
			}
			
			if(left) {
				snake.paintIcon(this, g,snakexlength[0], snakeylength[0]);
			}
			if(Right) {
				snake.paintIcon(this, g,snakexlength[0], snakeylength[0]);
			}
			if(up) {
				snake.paintIcon(this, g,snakexlength[0], snakeylength[0]);
			}
			if(down) {
				snake.paintIcon(this, g,snakexlength[0], snakeylength[0]);
			}
			for(int i=1; i<lengthofsanke; i++) {
				snake.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			 }
			food.paintIcon(this, g, foodx, foody);
			
			if(gameover) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", Font.BOLD,50));
				g.drawString("Game Over", 300, 300);
				g.setFont(new Font("Arial", Font.PLAIN,20));
				g.drawString("press to SPACE to Restart", 320, 350);
				
			}
			if(pause) {
				g.setColor(Color.WHITE);
				g.drawString("press 1 to resume", 300, 300);
				g.setFont(new Font("Arial", Font.PLAIN,20));
				
			}
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.PLAIN,14));
			g.drawString("Score :"+score, 750, 30);
			g.drawString("length :"+lengthofsanke, 750, 50);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial",Font.BOLD,50));
			g.drawString("Snake Game", 300, 50);
			
			
			
			
			g.dispose();
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i= lengthofsanke-1; i>0;i--) {
				snakexlength[i]=snakexlength[i-1];
				snakeylength[i]=snakeylength[i-1];
			}

			if(left) {
				snakexlength[0]=snakexlength[0]-25;
			}
			if(Right) {
				snakexlength[0]=snakexlength[0]+25;
			}
			if(up) {
				snakeylength[0]=snakeylength[0]-25;
			}
			if(down) {
				snakeylength[0]=snakeylength[0]+25;
			}
			if(snakexlength[0]>850)snakexlength[0]=25;
			if(snakexlength[0]<25)snakexlength[0]=850;
			
			if(snakeylength[0]>625)snakeylength[0]=75;
			if(snakeylength[0]<75)snakeylength[0]=625;
			
			colliesWithfood();
			colliesWithbody();
			repaint();
			
		}
	
			
		
		private void colliesWithfood() {
		  if(snakexlength[0]== foodx  && snakeylength[0]==foody) {
			  newfood();
			  lengthofsanke++;
			 
			score++;
		  }
			}
			
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode()==KeyEvent.VK_1) {
				Resume();
			}
			 
			if(e.getKeyCode()==KeyEvent.VK_0) {
				pause();
			}
			
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				Restart();
				
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT && (!Right)) {
				left=true;
				Right=false;
				up=false;
				down=false;
				moves++;
				}
			
						
		   if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left)){
			left=false;
			Right=true;
			up=false;
			down=false;
			moves++;
		   }
		   if(e.getKeyCode()==KeyEvent.VK_UP && (!down)) {
				left=false;
				Right=false;
				up=true;
				down=false;
				moves++;
				}
			
			if(e.getKeyCode()==KeyEvent.VK_DOWN && (!up)) {
			left=false;
			Right=false;
			up=false;
			down=true;
			moves++;
			}
			
		}
		
		private void Resume() {
			moves++;
			Timer.start();
			
		}
		private void pause() {
		     moves=0;
		     Timer.stop();
		     if(pause) {
		    	
		     }
		    
		}
		
		private void Restart() {
			gameover=false;
			moves=0;
			score=0;
			lengthofsanke=3;
			left=false;
			Right=true;
			up=false;
			down=false;
			Timer.start();
			
		}
		private void colliesWithbody() {
			for(int i= lengthofsanke-1; i>0; i--) {
				
				if(snakexlength[i]==snakexlength[0] && snakeylength[i]==snakeylength[0]) {
					Timer.stop();
					gameover=true;
				}
			}
		}
	//	private void collisonWithwall() {
	//		for(int i= lengthofsanke-1;i>0; i--) {
	//			if() {
		//			Timer.stop();
			//		gameover=true;
				//}
			//}
		//}
	        
	        
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		
		
}
