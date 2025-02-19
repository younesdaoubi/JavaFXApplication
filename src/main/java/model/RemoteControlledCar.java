package model;

import javafx.scene.paint.Color;

public class RemoteControlledCar extends Product {

	public final static Color COLOR = Battery.COLOR.interpolate(ElectricEngine.COLOR, 0.5);
	public final static int ManufacturedTime = 5;

	private final int price = 30;
    private final char ecoScore = 'B';
    
	private ElectricEngine electricEngine;
    private Battery battery;

    private int power;
    private int pourcentage;
    
     
    private int defectRate;
    
    public RemoteControlledCar(ElectricEngine engine, Battery battery, int defect) {
        this.electricEngine = engine;
        this.battery = battery;
        this.defectRate= defect;
        this.pourcentage = battery.getPourcentage();
    }
    
    public RemoteControlledCar() {}


    public ElectricEngine getElectricEngine() {
        return electricEngine;
    }

    public Battery getBattery() {
        return battery;
    }
    
    
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "remoteControlledCar";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "voici ma voiture telecommandee";
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
	public int getDefectRate() {
		// TODO Auto-generated method stub
		return this.defectRate;
	}
	
	public int getPourcentage() {
		// TODO Auto-generated method stub
		return this.pourcentage;
	}


	
	public int getPower() {
		// TODO Auto-generated method stub
		return this.power;
	}
	
	@Override
	public String toString() {
		return this.battery.toString()+"\n"+this.electricEngine.toString();
	}
}
