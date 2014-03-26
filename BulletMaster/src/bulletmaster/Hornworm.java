package bulletmaster;

public class Hornworm extends Enemy {

	public Hornworm(int centX, int centY) {    
		setCenterX(centX);
		setCenterY(centY);
	}
	
	//default constructor, no parameters given
	public Hornworm(){
		setCenterX(300);
		setCenterY(300);
	}	
	
	
}