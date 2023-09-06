package game;

import javax.swing.*;
//awt to add pictures
import java.awt.event.*;
import java.util.Objects;

public class MyFrame extends JFrame implements ActionListener{
	
	String[] photos = new String[] {"Kingler.jpg", "Alakazam.png", "Houndoom.jpg", "Lapras.jpg", "Heracross.png",
									"Flareon.jpg", "Charmeleon.jpg", "Diglett.png", "Ponyta.jpg", "Aerodactyl.jpg"};
	
	char[] answers = new char[] {'A', 'B', 'D', 'A', 'B', 'C', 'B', 'C', 'D', 'A'};
	
	String[][] choices = new String[][] {
		{"Kingler", "Krabby", "Ninetales", "Charizard"},
		{"Wartortle", "Alakazam", "Pidgeotto", "Kadabra"},
		{"Magikarp", "Houndour", "Goldeen", "Houndoom"},
		{"Lapras", "Abra", "Magnemite", "Weedle"},
		{"Omanyte", "Heracross", "Caterpie", "Voltorb"},
		{"Eevee", "Jolteon", "Flareon", "Vaporeon"},
		{"Charmander", "Charmeleon", "Kabutops", "Vulpix"},
		{"Dodrio", "Dugtrio", "Diglett", "Doduo"},
		{"Horsea", "Farfetch'd", "Rapidash", "Ponyta"},
		{"Aerodactyl", "Articuno", "Moltres", "Zapdos"}
	};
	
	/*Bunch of variables. Most important note is that index is used to count up for each question asked,
	while randomIndex is used it select a question and answers from array to use, then discard them*/
	int index = 0;
	int randomIndex = (int)(answers.length*Math.random());
	int totalCorrect = 0;
	int totalQuestions = answers.length;
	char response;
	JButton button;
	JLabel question;
	JLabel pokeLabel;
	JLabel progressLabel;
	ImageIcon pokeImage;
	JRadioButton radioA;
	JRadioButton radioB;
	JRadioButton radioC;
	JRadioButton radioD;
	ButtonGroup group;
	
	//creating objects of classes.
	PmanProgressBar progress = new PmanProgressBar();
	ShrinkArray shrink = new ShrinkArray();
	
	MyFrame(){
		//Progress bar added to see how far along in quiz we are.
		//progress = new game.PmanProgressBar();
		progressLabel = new JLabel("Percent completed: ");
		progressLabel.setBounds(490,20,125,25);
		//button to go to next question. Action listener added to do stuff
		button = new JButton("Submit");
		button.setFocusable(false);
		button.setBounds(500, 275, 100, 30);
		button.setToolTipText("Click me");
		button.addActionListener(this);
		//Asks who's that pokeyman?
		question = new JLabel("Who's that Pokeyman?");
		question.setBounds(50, 20, 200, 30);
		//Label to hold the pokemon image. filled in more in nextQuestion();
		pokeLabel = new JLabel();
		//Add radio buttons
		radioA = new JRadioButton();
		radioA.setFocusable(false);
		radioA.setBounds(425, 125, 100, 30);
		radioB = new JRadioButton();
		radioB.setFocusable(false);
		radioB.setBounds(600, 125, 100, 30);
		radioC = new JRadioButton();
		radioC.setFocusable(false);
		radioC.setBounds(425, 200, 100, 30);
		radioD = new JRadioButton();
		radioD.setFocusable(false);
		radioD.setBounds(600, 200, 100, 30);
		//So only one radio button can be selected
		group = new ButtonGroup();
		group.add(radioA);
		group.add(radioB);
		group.add(radioC);
		group.add(radioD);
		//Setting the frame and adding everything to it.
		this.setSize(750, 450);
		this.setLocation(500, 200);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(progressLabel);
		this.add(pokeLabel);
		this.add(progress);
		this.add(button);
		this.add(question);
		this.add(radioA);
		this.add(radioB);
		this.add(radioC);
		this.add(radioD);
		
		//Method called to get the ball rolling
		nextQuestion();
	}
	//this method checks to see if there are questions left to ask
	public void nextQuestion() {
		//sets the text for radio buttons and sets pokemon image.
		if(index < totalQuestions) {
			radioA.setText(choices[randomIndex][0]);
			radioB.setText(choices[randomIndex][1]);
			radioC.setText(choices[randomIndex][2]);
			radioD.setText(choices[randomIndex][3]);
			
			pokeImage = new ImageIcon(getClass().getClassLoader().getResource(photos[randomIndex]));
			pokeLabel.setIcon(pokeImage);
			pokeLabel.setBounds(50, 50, 300, 300);
			//JFrame gets set visible, finally
			this.setVisible(true);
		} else {
			 displayResults();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			//Doesn't do anything if a radio isn't selected.
			if(radioA.isSelected() || radioB.isSelected() || radioC.isSelected() || radioD.isSelected()){
				if(radioA.isSelected()) {
					response = 'A';
				}
				if(radioB.isSelected()) {
					response = 'B';
				}
				if(radioC.isSelected()) {
					response = 'C';
				}
				if(radioD.isSelected()) {
					response = 'D';
				}
				if(response == answers[randomIndex]) {
					System.out.println("Question #" + (index+1) + " is Right!");
					totalCorrect++;
				} else {
					System.out.println("Question #" + (index+1) + " is Wrong!");
				}
				/*increment counter index, fill progress bar, remove button selection, 
				remove element from arrays, generate new random index and go to next question.*/
				index++;
				progress.fillBar(index, totalQuestions);
				group.clearSelection();
				photos = shrink.removePhoto(photos, randomIndex);
				answers = shrink.removeAnswers(answers, randomIndex);
				choices = shrink.removeChoices(choices, randomIndex);
				randomIndex = (int)(answers.length * Math.random());
				nextQuestion();
			}
		}
	}
	
	//This method clears the frame and displays the results of the quiz.
	public void displayResults(){
		 this.getContentPane().removeAll();
		 this.repaint();
		 double result = (double)totalCorrect/(double)totalQuestions;
		 //should probably use different JLabels below to make more sense.
		 question.setText("You got " + totalCorrect + " out of " + totalQuestions + " right.");
		 question.setBounds(320, 170, 300, 30);
		 pokeLabel.setIcon(null);
		 pokeLabel.setText("That's " + (int)(100*result) + "%.");
		 pokeLabel.setBounds(320, 200, 300, 30);
		 this.add(question);
		 this.add(pokeLabel);
	}

}