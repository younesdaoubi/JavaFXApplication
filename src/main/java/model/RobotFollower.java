package model;

import javafx.scene.paint.Color;

public class RobotFollower extends Product{

	public final static Color COLOR = ElectricEngine.COLOR.interpolate(MotionSensor.COLOR, 0.5);
	public final static int ManufacturedTime = 6;

	private final int price = 40;
    private final char ecoScore = 'B';
    
	private ElectricEngine electricEngine;
    private MotionSensor motionSensor;
    
    private int scope;
    private int power;
	
 
    private int defectRate;
 
    public RobotFollower(ElectricEngine electricEngine, MotionSensor motionSensor, int defect) {
        this.electricEngine = electricEngine;
        this.motionSensor = motionSensor;
        this.defectRate = defect;
        this.scope = motionSensor.getScope();
        this.power = electricEngine.getPower();
    }
    
     
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "robotFollower";
	}
	
	
	
 

    public ElectricEngine getBattery() {
        return this.electricEngine;
    }

    public MotionSensor getMotionSensor() {
        return this.motionSensor;
    }

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "voici mon robot suiveur";
	}
	
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return this.price;//return this.price;
	}

	@Override
	public char getEcoScore() {
		// TODO Auto-generated method stub
		return this.ecoScore;//return this.ecoScore;
	}


	@Override
	public int getDefectRate() {
		// TODO Auto-generated method stub
		return this.defectRate;
	}
	
	
	public int getScope() {
		// TODO Auto-generated method stub
		return this.scope;
	}


	
	public int getPower() {
		// TODO Auto-generated method stub
		return this.power;
	}
	
	@Override
	public String toString() {
		return this.electricEngine.toString()+"\n"+this.motionSensor.toString();
	}
	
	

}
