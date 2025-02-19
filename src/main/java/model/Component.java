package model;

	public abstract class Component extends Product {


		 private String type;
		 private int arrivalTime;
		 private int parameter; // puissance, pourcentage ou portée.
		 private int defectRate; // taux de défectuosité. On tiendra en compte qu'un produit (composant ou produit fabriqué) est défectueux si a>50%
		 
		 private String color; // que pour capteurs     
 
		 
		//contructeurs moteurs et batteries
		    public Component(String type, int arrivalTime, int parameter1, int defectRate) {
		        this.type = type;
		        this.arrivalTime = arrivalTime;
		        this.parameter = parameter1;
		        this.defectRate = defectRate;
		    }

		  //contructeurs capteurs
		    public Component(String type, int arrivalTime, int parameter1, String color, int defectRate) {
		        this.type = type;
		        this.arrivalTime = arrivalTime;
		        this.parameter = parameter1;
		        this.color = color;
		        this.defectRate = defectRate;
		    }
		    
		    
		    //Méthodes
		    
		    public String getType() {
		    	return this.type;
		    }
		    
		    public void setType(String type) {
		    	this.type = type;
		    }
		    
		    public int getArrivalTime() {
		    	return this.arrivalTime;
		    }
		    
		    public void setArrivalTime(int arrivalTime) {
		    	this.arrivalTime = arrivalTime;
		    }
		    
		    public int getDefectRate() {
		    	return this.defectRate;
		    }
		    
		    public void setDefectRate(int defectRate) {
		    	this.defectRate = defectRate;
		    }
		    
		    public int getParameter() {
		    	return this.defectRate;
		    }
		    
		    public void setParameter(int parameter) {
		    	this.parameter = parameter;
		    }
		    
		    public String getColor() {  
		    	return this.color;
		    }

		    
		    public abstract String getDescription();
		    
		    
		    
		    }
