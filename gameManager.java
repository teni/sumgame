import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;



public class gameManager implements ActionListener {
	Timer t;
	LinkedList<Integer> numList = new LinkedList<Integer>();
	LinkedList<JButton> buttonList = new LinkedList<JButton>();
	int pauseHolder = 0;
	boolean gameOn = true;
	
	public void stopGame(){
		gameOn = false;
	}
	/**
	 * We need an array of ints to hold all the generated numbers
	 * 
	 * these will be selected in twos or threes or  fours
	 * depending on the level of the game
	 * summed and that will be the asking value.
	 * 
	 * 
	 * We need to be able to tell when the game is over
	 * either because the player won or because time ran out.
	 * 
	 * The stopwatch is reset each  time a sum is gotten.
	 * 
	 * 
	 * 
	 * 
	 */
	int numArray[];
	int currentCumulative = 0;
	int currentAskingValue = 0;
	main_view gameView;
	public gameManager(int num_of_elements, int difficulty, main_view obj){
		numArray = new int[num_of_elements];
		gameView  = obj;
		setDifficulty(difficulty);
		populateArray();
		t = new Timer(1000,this);
		t.start();
		
	}
	
	int difficulty = 1;
	public void setDifficulty(int i){
		difficulty = i;
	}
	public void resetTime(){
		
		
	}
	int hh= 0;
	public void actionPerformed(ActionEvent B){
 if(numList.isEmpty() && currentCumulative==0 && gameView.boardNum.getText().equalsIgnoreCase("0")){
			 
			 // go to the next level
			 gameView.boardNum.setText("Y O U  W I N !");
			 t.stop();
		 }
		if(hh>=0 && hh<=15){
		 gameView .timeBar.setValue(hh++);
		 
		 gameView.timeLabel.setText(""+ (16-hh));
		 gameView.timeBar.setString(""+hh);
		}
		 if(hh>15){
			 gameView.remove(gameView.nums);
		
				
				 
			  
				  
				  gameView.gameDisplay = new JLabel("    GAME OVER");
				  gameView.gameDisplay.setFont(new Font(Font.SERIF,Font.PLAIN, 62));
				  gameView.gameDisplay.setForeground(Color.white);
				gameView.add(gameView.gameDisplay);
					
			  
			  
			 gameView.repaint();
			// gameView.boardNum.setText("GAME OVER");
			 //gameView.gameDisplay.setText(" Try Again");
			 t.stop();
		 }
		
		
	}
	public void stackSum(int number, JButton which,Object r){
		// this is where we add our selections and check if
		// 
		currentCumulative += number;
		buttonList.add(which);
		if(currentCumulative>currentAskingValue){
			while(!buttonList.isEmpty()){
				JButton thisButton = buttonList.pop();
				
				int num = Integer.parseInt(thisButton.getText());
				thisButton.setBackground(new Color(num*12,num*14,num*2));
				
				((main_view)r).validate();
			}	
			currentCumulative = 0;
			
		
		}
		if(checkSum(currentCumulative)){
			//you are in here if the player is correct
			//blacken the buttons in the list
			//empty the list
			//zero the sum
			// Is the game still on?
			// yes:
			// reset the timer
			//  get a new number
			
			//no:
			//game over.
			
			
			while(!buttonList.isEmpty()){
				JButton thisButton = buttonList.pop();
				thisButton.setBackground(Color.BLACK);
				thisButton.setForeground(Color.BLACK);
				thisButton.setText("0");
				
				
				
				
			}
			
		
			gameView.score.setText(""+ ((Integer.parseInt(gameView.score.getText())+  (16-hh))));
			if(gameOn){
				currentAskingValue = getAskingValue();
				hh= 0;
				gameView .timeBar.setValue(hh);
				
			}
			currentCumulative = 0;
		}
		
	}
	public   void  populateArray(){
		/** Randomize the array before you make the linked list **/
		
		
		
		
		for(int i= 0 ; i< numArray.length ; i++){
			numArray[i] = generateNum();
			numList.push(numArray[i]);
		}
		shuffle();
	}
	
	
	private void shuffle(){
		for(int i = 0 ; i < numArray.length ; i++){
			
			int theNUM = numArray.length;
			int a = (int)(Math.random() * theNUM);
			int b = (int)(Math.random() * theNUM);
			int c = numArray[a];
			
			numArray[a] = numArray[b];
			numArray[b] = c ;
			
		}
	}
	public int getAskingValue(){
		int n = 0;
		currentAskingValue = 0;
		/** just randomize the array, make it a linked list and pop **/
		while(!numList.isEmpty() && (n<difficulty+1) ){
			int thepop = numList.pop();
			
			currentAskingValue += thepop;
			n++;
		}
		
		
		return currentAskingValue;
	}
	public boolean checkSum(int  cum){
		if(currentAskingValue>0 && cum==currentAskingValue ){
			return true;
		}
		return false;
	}
	public void  pauseGame(){
		t.stop();
	}
	public void resumeGame(){
		t.start();
	}
	private int generateNum(){
		int n = (int)(Math.random() * 10);
		return  n==0? n + 1 : n;
	}
	
	

}
