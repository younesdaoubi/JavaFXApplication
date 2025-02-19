package model;

import javafx.scene.paint.Color;

public class Battery extends Component {

	
	public static Color COLOR = Color.LIGHTBLUE;		// couleure définie. 
	public static int ManufacturedTime = 3;		//temps de fabrique
	
	private final int price = 5;
    private final char ecoScore = 'C';
    
    private int pourcentage;	
	
    
    //contructeur
	public Battery(String type, int arrivalTime, int pourcentage, int defectRate) {
		super(type, arrivalTime, pourcentage, defectRate);
		// TODO Auto-generated constructor stub
		this.pourcentage = pourcentage;
	}

	
	//méthodes.

	public int getPourcentage() {
		return this.pourcentage;
	}
	
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "battery";
	}



	@Override
	public String getDescription() {
		return "Type : "+ getType() +" - pourcentage : "+getPourcentage()+"% - defect : "+getDefectRate()+"% - arrival : "+ getArrivalTime()+"s";

	}
	
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}

	@Override
	public char getEcoScore() {
		// TODO Auto-generated method stub
		return this.ecoScore;
	}
	
	@Override
	public String toString() {
		return "Les batteries ont un pourcentage de charge qui est compris entre 0 et 100.";
	}
 

}
