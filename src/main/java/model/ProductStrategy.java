package model;

 
	
	public interface ProductStrategy {
	    String getStrategyDescription();
	    Product determineProduct();
		
	    
	    class AlwaysPriceStrategy implements ProductStrategy {
		    public String getStrategyDescription(){return "priceStrategy";}
		    @Override
		    public Product determineProduct() {
		        
		    	return new SecurityAlarm();
	}
	    }

		class AlwaysEcoScoreStrategy implements ProductStrategy {
		    public String getStrategyDescription(){return "ecoScoreStrategy";}
		    @Override
		    public Product determineProduct() {
		    
		    	return new RemoteControlledCar();
		        
		    }
	}
		
		
		
		

		class AlwaysTimeStrategy implements ProductStrategy {
		public String getStrategyDescription(){return "timeStrategy";}
		
		@Override
		public Product determineProduct() {
			// TODO Auto-generated method stub
			return new SecurityAlarm();
		}
	}

}
	