package model;

import javafx.scene.paint.Color;

public class ElectricEngine extends Component{
	
	public static Color COLOR = Color.LIGHTPINK;
	public static int ManufacturedTime = 3;

	private final int price = 15;
    private final char ecoScore = 'A';
     
	private int power;

    
	public ElectricEngine(String type, int arrivalTime, int power, int defectRate) {
		super(type, arrivalTime, power, defectRate);
		// TODO Auto-generated constructor stub
		this.power = power;
		
	}

	
	public int getPower() {
		return this.power;	}
	
	@Override
	public String getType() {
		return "electricEngine";
	}
	
	@Override
	public String getDescription() {
		return "Type : "+ getType() +" - power : "+getPower()+"W - defect : "+getDefectRate()+"% - arrival : "+ getArrivalTime()+"s";
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
		return "Le moteur éléctrique à une puissance, spécifiée en Watts.";
	}
	
}
