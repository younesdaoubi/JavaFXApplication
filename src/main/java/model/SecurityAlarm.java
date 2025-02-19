package model;

import javafx.scene.paint.Color;

public class SecurityAlarm extends Product {
	
	public final static Color COLOR = Battery.COLOR.interpolate(MotionSensor.COLOR, 0.5);
 	public final static int ManufacturedTime = 4;
 	
 	private final int price = 20;
    private final char ecoScore = 'C';
    
	private Battery battery;
    private MotionSensor motionSensor;
    
    private int pourcentage;
    private int scope;
 
    private int defectRate;
    
    
    public SecurityAlarm(Battery battery, MotionSensor motionSensor, int defect) {
        this.battery = battery;
        this.motionSensor = motionSensor;
        this.defectRate = defect;
        this.scope = motionSensor.getScope();
        this.pourcentage = battery.getPourcentage();
        
    }
    
    
    public SecurityAlarm() {
        
    }
 
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "securityAlarm";
	}
	
	
	
 

    public Battery getBattery() {
        return this.battery;
    }

    public MotionSensor getMotionSensor() {
        return this.motionSensor;
    }

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "voici mon alarme de securite";
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
	
	public int getPourcentage() {
		// TODO Auto-generated method stub
		return this.pourcentage;
	}
	
	@Override
	public String toString() {
		return this.battery.toString()+"\n"+this.motionSensor.toString();
	}

}
