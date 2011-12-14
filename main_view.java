import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class main_view extends JPanel implements ActionListener {
	JButton pauseButton;
	static gameManager theMgr;
	public JLabel boardNum = new JLabel("");
	public JProgressBar timeBar;
	public JLabel timeLabel ;
	public JPanel nums;
	public Dimension pauseButtonDim;
	JLabel gameDisplay;
	Box scoreBox;
	URL snd1;
	URL snd;
	URL snd3;
	public JLabel score = new JLabel("0");
	public JLabel level =  new JLabel("0");
	
	
	Box level_life_box;
	main_view() {
		
		theMgr = new gameManager(120,2,this);
		this.setLayout(new BorderLayout());
		setSize(500,500);
		
		
		gameDisplay = new JLabel("");
		
		
		setPreferredSize(new Dimension(500,500));
		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		// Bottom stuff
		Box b4_bottom =Box.createHorizontalBox();
		
		b4_bottom.setSize(498,60);
		Box bottom = Box.createHorizontalBox();
		
		bottom.setSize(498,40);
		 boardNum.setFont(new Font(Font.SERIF, Font.BOLD, 30));
		 boardNum.setForeground(Color.WHITE);
		 this.setBackground(new Color(35,35,35));
		timeLabel = new JLabel("0");
		timeLabel.setSize(35,90);
		timeLabel.setForeground(Color.YELLOW);
		timeBar = new JProgressBar(0,15);
		timeBar.setBorder(null);
		timeBar.setBorderPainted(false);
		timeBar.setForeground(new Color(14*14,14+7,4));
		pauseButton = new JButton(" Pause ");
		pauseButton.setBackground(new Color(82,82,82));
		pauseButton.setBorder(null);
		pauseButton.setForeground((Color.white));
		pauseButtonDim = pauseButton.getSize();
		
		scoreBox = Box.createVerticalBox();
		scoreBox.setSize(200,150);
		scoreBox.add(new JLabel("Score"));
		scoreBox.add(score);
		score.setForeground(Color.white);
		level_life_box = Box.createVerticalBox();
		level_life_box.add(new JLabel("Level"));
		level_life_box.add(level);
		level.setForeground(Color.white);
		scoreBox.setSize(200,150);
		bottom.add(Box.createHorizontalStrut(10));
		bottom.add(timeLabel);
		bottom.add(Box.createHorizontalStrut(20));
		
		bottom.add(timeBar);
		bottom.add(Box.createHorizontalStrut(20));
		bottom.add(pauseButton);
		bottom.add(Box.createHorizontalStrut(10));
		b4_bottom.add(Box.createHorizontalStrut(10));
		b4_bottom.add(scoreBox);
		b4_bottom.add(Box.createHorizontalStrut(165));
		Box boardNumBox = Box.createVerticalBox();
		boardNumBox.add(boardNum);
		boardNumBox.setSize(100,50);
		b4_bottom.add(Box.createHorizontalGlue());
		b4_bottom.add(boardNumBox);
		b4_bottom.add(Box.createHorizontalStrut(145));
		b4_bottom.add(level_life_box);
		b4_bottom.add(Box.createHorizontalGlue());
		Box vertBox = Box.createVerticalBox();
		vertBox.add(b4_bottom);
		vertBox.add(bottom);
		vertBox.add(Box.createVerticalStrut(8));
		this.add(vertBox, BorderLayout.SOUTH);
		
		// end of bottom stuff
		
		
	
		// numbers panel
		nums = new JPanel();
		for(int i = 0; i<theMgr.numArray.length; i++){
			nums.add(getNum(theMgr.numArray[i]));
			
		}
		
		
		boardNum.setText(""+theMgr.getAskingValue());
		nums.setSize(350,350);
		nums.setBackground(new Color(2,5,6));
		
		this.add(nums,BorderLayout.CENTER);
		setVisible(true);
		pauseButton.addActionListener(this);
		timeLabel.requestFocus();
		
	}

	public main_view startGame(){
	return new main_view();
	}
	
	public JButton getNum(int num){
		JButton it = new JButton(""+num);
		it.setSize(105,50);
		it.setBackground(new Color(num*12,num*14,num*2));
		it.setForeground(Color.WHITE);
		it.addActionListener(this);
		return it;
	}
	public void actionPerformed(ActionEvent e){
		JButton theButtonPressed = ((JButton)(e.getSource()));
	
		if(theButtonPressed.getBackground()== Color.red){
		
			return;
		}
		if(!(theButtonPressed.getBackground()== Color.red || theButtonPressed.getBackground()== Color.BLACK) && e.getActionCommand().equalsIgnoreCase("1") ||
		   e.getActionCommand().equalsIgnoreCase("2") ||
		   e.getActionCommand().equalsIgnoreCase("3") ||
		   e.getActionCommand().equalsIgnoreCase("4") ||
		   e.getActionCommand().equalsIgnoreCase("5") ||
		   e.getActionCommand().equalsIgnoreCase("6") ||
		   e.getActionCommand().equalsIgnoreCase("7") ||
		   e.getActionCommand().equalsIgnoreCase("8") ||
		   e.getActionCommand().equalsIgnoreCase("9") ){
			
			
			
			theButtonPressed.setBackground(Color.red);
		
			theMgr.stackSum(Integer.parseInt(theButtonPressed.getText()), theButtonPressed,this);
			boardNum.setText(""+theMgr.currentAskingValue);
			timeLabel.requestFocus(); // dont want the button focused after a press.
		}
		
		if(e.getActionCommand().equalsIgnoreCase(" Pause ")){
			JButton  it = ((JButton)e.getSource());
			it.setText("Continue");
			it.setBackground(new Color(82,82,82));
			nums.setVisible(false);
			
			this.add(gameDisplay);
			gameDisplay.setText("  GAME PAUSED");
			gameDisplay.setFont(new Font(Font.SERIF,Font.PLAIN, 62));
			
			this.validate();
			theMgr.pauseGame();
			timeLabel.requestFocus();
		
			
		}
		if(e.getActionCommand().equalsIgnoreCase("Continue")){
			JButton  it = ((JButton)e.getSource());
			it.setText(" Pause ");
			it.setBackground(new Color(82,82,82));
			this.remove(gameDisplay);
			nums.setVisible(true);
			this.validate();
			theMgr.resumeGame();
			timeLabel.requestFocus();
		}
	}
	
}
