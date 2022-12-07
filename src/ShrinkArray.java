
public class ShrinkArray {

	public String[] removePhoto (String[] photos, int index) {
		String[] temp = new String[photos.length - 1];
		for(int i = 0, j = 0; i < photos.length; i++) {
			if (i == index){
				continue;
			}
			temp[j] = photos[i];
			j++;	
		}
		return temp;
	}
	
	public char[] removeAnswers (char[] answers, int index) {
		char[] temp = new char[answers.length - 1];
		for(int i = 0, j = 0; i < answers.length; i++) {
			if (i == index){
				continue;
			}
			temp[j] = answers[i];
			j++;	
		}
		return temp;
	}
	
	public String[][] removeChoices (String[][] choices, int index) {
		String[][] temp = new String[choices.length - 1][4];
		for(int i = 0, j = 0; i < choices.length; i++) {
			if (i == index){
				continue;
			}
			temp[j][0] = choices[i][0];
			temp[j][1] = choices[i][1];
			temp[j][2] = choices[i][2];
			temp[j][3] = choices[i][3];
			j++;	
		}
		return temp;
	}
	
}
