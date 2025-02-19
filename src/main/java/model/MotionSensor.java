package model;

import javafx.scene.paint.Color;

public class MotionSensor extends Component {
	
	public static Color COLOR = Color.LIGHTGREEN;

	public static int ManufacturedTime = 3;

	
 	private final int price = 10;
    private final char ecoScore = 'B';
    
    private int scope;
    private String color;
    
	public MotionSensor(String type, int arrivalTime, int scope, String color, int defectRate) {
		super(type, arrivalTime, scope, color, defectRate);
		
		this.scope = scope;
		this.color = color;
	}

 
	
	public String getColor() {
		return this.color;
	}
	
	public int getScope() {
		return this.scope;
		
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "motionSensor";
	}
	
	
	@Override
	public String toString() {
		return "Le capteur de mouvements à une portée spécifiée en mètres et une couleure qui peut être soit noir, jaune ou rouge.";
	}
 

	@Override
	public String getDescription() {
		//return "Type : "+ getType() +" - arrival : "+ getArrivalTime()+" - scope : "+getParameter()+" - color : "+getColor()+" - defect : "+getDefectRate();
		return "Type : "+ getType() +" - scope : "+getScope()+" - color : "+getColor()+" - W - defect : "+getDefectRate()+"% - arrival : "+ getArrivalTime()+"s";
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

}
