import java.awt.Color;
import javax.swing.JProgressBar;

public class PmanProgressBar extends JProgressBar{
	
		int x;
		double y;
		
	PmanProgressBar(){

		this.setBounds(425, 50, 250, 25);
		this.setStringPainted(true);
		this.setForeground(Color.BLACK);
	}
	
	public void fillBar(int x, int y) {
		this.setValue(100*x/y);
	}

}
