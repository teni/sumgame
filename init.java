import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;


public class init extends JFrame implements ActionListener{
private JPanel panel;
JRadioButtonMenuItem beginner;
JRadioButtonMenuItem intermediate;
JRadioButtonMenuItem advanced;
int diff = 1;

	init(){
		setTitle("Summer 1.0  Unfinished... ");
		
		// menu
		JMenuBar m =   new JMenuBar();
		JMenu game = new JMenu("Game");
			JMenuItem newG = new JMenuItem("New Game");
			JMenuItem exit = new JMenuItem("Exit");
			exit.addActionListener(this);
		newG.addActionListener(this);
		game.add(newG);game.add(exit);
		JMenu options = new JMenu("Options");
		JMenu level = new JMenu("Level");
		
		beginner = new JRadioButtonMenuItem("Beginner");
		intermediate = new JRadioButtonMenuItem("Intermediate");
		advanced = new JRadioButtonMenuItem("Advanced");
		beginner.addActionListener(this); intermediate.addActionListener(this); 
		advanced.addActionListener(this);
			level.add(beginner);
			level.add(intermediate);
			level.add(advanced);
			
			options.add(level);
			
		
		JMenu help = new JMenu("Help");
		      JMenuItem playInstructions = new JMenuItem("How to play");
		       JMenuItem about = new JMenuItem("About");
		       
		       help.add(playInstructions);
		       help.add(about);
		m.add(game);
		m.add(options);
		m.add(help);
		
	this.setJMenuBar(m);
		// end of menu
	
	newGame();
	
	}
	public void actionPerformed(ActionEvent ee){
		if(ee.getActionCommand().equalsIgnoreCase("New Game")){
			this.remove(panel);
			this.newGame();
			this.validate();
		}
		if(ee.getActionCommand().equalsIgnoreCase("Exit")){
			System.exit(0);
		}
		if(ee.getActionCommand().equalsIgnoreCase("Beginner")){
			beginner.setSelected(true);
			
		    intermediate.setSelected(false);
			 advanced.setSelected(false);
			 diff = 1;
		}
		if(ee.getActionCommand().equalsIgnoreCase("Intermediate")){
			beginner.setSelected(false);
			
		    intermediate.setSelected(true);
			 advanced.setSelected(false);
			 diff = 2;
		}
		if(ee.getActionCommand().equalsIgnoreCase("Advanced")){
			beginner.setSelected(false);
			
		    intermediate.setSelected(false);
			 advanced.setSelected(true);
			 diff = 3;
		}
		
	}
	public void newGame(){
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		main_view b = new main_view();
		
		panel.add(b,BorderLayout.CENTER);
		this.setSize(500,510);
		this.validate();
		this.add(panel);
		this.setVisible(true);
	
		
	}
public static void main(String args[]){
	init thisForm = new init();
	
	
	thisForm.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
}
}
