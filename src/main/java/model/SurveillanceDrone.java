package model;

import javafx.scene.paint.Color;

public class SurveillanceDrone extends Product{

	public static Color COLOR ;
	public final static int ManufacturedTime = 12;

	private final int price = 60;
    private final char ecoScore = 'E';    
    private int defectRate;
    
	private ElectricEngine electricEngine;
    private MotionSensor motionSensor;
    private Battery battery;
	
	private int pourcentage;
	private int power;
	private int scope;
    
    
    
    public SurveillanceDrone(ElectricEngine electricEngine, MotionSensor motionSensor, Battery battery, int defect) {
        this.electricEngine = electricEngine;
        this.motionSensor = motionSensor;
        this.battery = battery;
        this.defectRate = defect;       
        
        this.scope = motionSensor.getScope();
        this.pourcentage = battery.getPourcentage();
        this.power = electricEngine.getPower();
        
        
        Color color1 = ElectricEngine.COLOR;
        Color color2 = Battery.COLOR;
        Color color3 = MotionSensor.COLOR;

        double red = (color1.getRed() + color2.getRed() + color3.getRed()) / 3.0;
        double green = (color1.getGreen() + color2.getGreen() + color3.getGreen()) / 3.0;
        double blue = (color1.getBlue() + color2.getBlue() + color3.getBlue()) / 3.0;
        double opacity = (color1.getOpacity() + color2.getOpacity() + color3.getOpacity()) / 3.0;

        Color mixedColor = new Color(red, green, blue, opacity);
        
        this.COLOR = mixedColor;
    
    }


	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "surveillanceDrone";
	}
	
	
	
 

    public ElectricEngine getBattery() {
        return this.electricEngine;
    }

    public MotionSensor getMotionSensor() {
        return this.motionSensor;
    }
    
    public ElectricEngine getElectricEngine() {
        return this.electricEngine;
    }

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "voici mon drone de surveillance";
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
	
	public int getPower() {
		return this.power;
	}
	
	public int getPourcentage() {
		return this.pourcentage;
	}
	
	public int getScope() {
		return this.scope;
	}
	
	
	@Override
	public String toString() {
		return this.battery.toString()+"\n"+this.motionSensor.toString()+" "+this.electricEngine.toString();
	}


}


	
    
     
 
    
    
     