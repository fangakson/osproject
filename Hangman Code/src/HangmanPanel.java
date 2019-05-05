import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class HangmanPanel extends JPanel implements MouseListener{
	
	
	private static final long serialVersionUID = 1L;
	public Image img;
	public ImageIcon icon; 
	
	public Image img2;
	public ImageIcon icon2; 
	public int wrong;

	HangmanCheckString chString;
	public HangmanPanel(){

		chString = new HangmanCheckString();
		chString.chooseWord();
		this.setSize(100,500);
		this.setVisible(true);
		this.setFocusable(true);
		icon = new ImageIcon("image/Hangman1.jpg");
		img = icon.getImage();
		
		icon2 = new ImageIcon("image/alphabet12.png");
		img2 = icon2.getImage();
		
		addMouseListener(this);	
		wrong = 0;
		}
	public void paint(Graphics g){ 
		//check my videos on graphics for paint and repaint
		g.drawImage(img,0,0,700,500,null);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(375, 95, 308, 308);
		g.drawImage(img2,380,100,300,300, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.ITALIC,68));
		g.drawChars(chString.hangmanLetters, 0, chString.charArray.length, 180, 460);

		if(chString.wrongGuess == 1){
			g.drawOval(162, 150, 50, 50);  //head
		}
		if(chString.wrongGuess == 2){		
			g.drawOval(162, 150, 50, 50);  //head
			g.drawLine(188, 200, 188, 275);//body
		}
		if(chString.wrongGuess == 3){		
			g.drawOval(162, 150, 50, 50);  //head
			g.drawLine(188, 200, 188, 275);//body
			g.drawLine(148, 235, 188, 215);// left arm
		}
		if(chString.wrongGuess == 4){		
			g.drawOval(162, 150, 50, 50);  //head
			g.drawLine(188, 200, 188, 275);//body
			g.drawLine(148, 235, 188, 215);// left arm
			g.drawLine(188, 215, 228, 235);// right arm
		}
		if(chString.wrongGuess == 5){	
			g.drawOval(162, 150, 50, 50);  //head
			g.drawLine(188, 200, 188, 275);//body
			g.drawLine(148, 235, 188, 215);// left arm
			g.drawLine(188, 215, 228, 235);// right arm
			g.drawLine(188, 275, 228, 335);// right leg
		}
		if(chString.wrongGuess == 6){	
			g.drawOval(162, 150, 50, 50);  //head
			g.drawLine(188, 200, 188, 275);//body
			g.drawLine(148, 235, 188, 215);// left arm
			g.drawLine(188, 215, 228, 235);// right arm
			g.drawLine(188, 275, 228, 335);// right leg
			g.drawLine(148, 335, 188, 275); // left leg
		}
		if(chString.winOrLoose == true){
			g.setColor(Color.red);
			g.drawString("you win", 80, 250);
			g.setColor(Color.white);
			g.fillRect(40, 418, 100, 50);
			g.setColor(Color.red);
			g.setFont(new Font("Arial",Font.ITALIC,18));
			g.drawString("New Game", 45, 450);
		
		}
		if(chString.wrongGuess == 6){
			g.setColor(Color.red);
			g.drawString("Hanged", 80, 250);
			g.setColor(Color.white);
			g.fillRect(40, 418, 100, 50);
			g.setColor(Color.red);
			g.setFont(new Font("Arial",Font.ITALIC,18));
			g.drawString("New Game", 45, 450);
		}
		

		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		int mX = e.getX(); 
		int mY = e.getY();
	 ///the If statement below will activated when man is hanged or user wins 
		if(mX > 40 && mX < 140 && mY > 418 && mY < 468 && (chString.wrongGuess == 6 || chString.winOrLoose == true)){
			chString.wrongGuess = 0;
			chString.chooseWord();
			chString.winOrLoose = false;
			for(int i = 0; i < 26; i++){ 
				chString.usedLetterList[i] = '-';
			}
		}
		
		 // the if statements below is the areas of the letters to be clicked.
		
		// Top line A to G
		if(mX > 386 && mX < 430 && mY > 95 && mY < 170){
		char ch = 'a';
		chString.checkWord(ch);
		}
		if(mX > 432 && mX < 468 && mY > 95 && mY < 170){
			char ch = 'b';
			chString.checkWord(ch);
			}
		if(mX > 470 && mX < 515 && mY > 95 && mY < 170){
			char ch = 'c';
			chString.checkWord(ch);
			}
		if(mX > 515 && mX < 556 && mY > 95 && mY < 170){
			char ch = 'd';
			chString.checkWord(ch);
			}
		if(mX > 555 && mX < 593 && mY > 95 && mY < 170){
			char ch = 'e';
			chString.checkWord(ch);
			}
		if(mX > 593 && mX < 624 && mY > 95 && mY < 170){
			char ch = 'f';  // f being click
			chString.checkWord(ch);
			}
		if(mX > 625 && mX < 670 && mY > 95 && mY < 170){
			char ch = 'g';
			chString.checkWord(ch);
			}
		
		// Second line from top J to N ( the alphabet is wrong I down loaded the image from google images )
		
		if(mX > 385 && mX < 409 && mY > 172 && mY < 245){
			char ch = 'j';
			chString.checkWord(ch);
			}
		if(mX > 422 && mX < 460 && mY > 172 && mY < 245){
			char ch = 'h';
			chString.checkWord(ch);
			}
		if(mX > 463 && mX < 490 && mY > 172 && mY < 245){
			char ch = 'i';
			chString.checkWord(ch);
			}
		if(mX > 492 && mX < 531 && mY > 172 && mY < 245){
			char ch = 'k';
			chString.checkWord(ch);
			}
		if(mX > 533 && mX < 570 && mY > 172 && mY < 245){
			char ch = 'l';
			chString.checkWord(ch);
			}
		if(mX > 573 && mX < 626 && mY > 172 && mY < 245){
			char ch = 'm';
			chString.checkWord(ch);
			}
		if(mX > 628 && mX < 673 && mY > 172 && mY < 245){
			char ch = 'n';
			chString.checkWord(ch);
			}
		
		// Third line of letters O to U
		
		if(mX > 380 && mX < 425 && mY > 250 && mY < 325){
			char ch = 'o';
			chString.checkWord(ch);
			}
		if(mX > 427 && mX < 458 && mY > 250 && mY < 325){
			char ch = 'p';
			chString.checkWord(ch);
			}
		if(mX > 460 && mX < 510 && mY > 250 && mY < 325){
			char ch = 'q';
			chString.checkWord(ch);
			}
		if(mX > 513 && mX < 552 && mY > 250 && mY < 325){
			char ch = 'r';
			chString.checkWord(ch);
			}
		if(mX > 555 && mX < 592 && mY > 250 && mY < 325){
			char ch = 's';
			chString.checkWord(ch);
			}
		if(mX > 594 && mX < 634 && mY > 250 && mY < 325){
			char ch = 't';
			chString.checkWord(ch);
			}
		if(mX > 637 && mX < 682 && mY > 250 && mY < 325){
			char ch = 'u';
			chString.checkWord(ch);
			}
		
		// Bottom line V to Z
				
		if(mX > 401 && mX < 446 && mY > 330 && mY < 403){
			char ch = 'v';
			chString.checkWord(ch);
			}
		if(mX > 447 && mX < 512 && mY > 330 && mY < 403){
			char ch = 'w';
			chString.checkWord(ch);
			}
		if(mX > 515 && mX < 556 && mY > 330 && mY < 403){
			char ch = 'x';
			chString.checkWord(ch);
			}
		if(mX > 559 && mX < 602 && mY > 330 && mY < 403){
			char ch = 'y';
			chString.checkWord(ch);
			}
		if(mX > 605 && mX < 650 && mY > 330 && mY < 403){
			char ch = 'z';
			chString.checkWord(ch);
			
			
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
				
	}
	@Override
	public void mouseExited(MouseEvent e) {
			
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
			
	}
	@Override
	public void mouseReleased(MouseEvent e) {
				
	}

}
