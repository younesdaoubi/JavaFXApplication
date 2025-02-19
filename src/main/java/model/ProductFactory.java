package model;

import model.ProductStrategy.AlwaysEcoScoreStrategy;
import model.ProductStrategy.AlwaysPriceStrategy;
import model.ProductStrategy.AlwaysTimeStrategy;
 
public class ProductFactory {
 
	ProductStrategy currentStrategy ;
    
    public ProductFactory(String initialStrategyChoice){
        setCurrentStrategy(initialStrategyChoice);
    }

    
    public Product createProduct() {
        
    	return (currentStrategy).determineProduct();
    	
    }
    
    public ProductStrategy getCurrentStrategy() {			//get la strategie 
        return this.currentStrategy;		
    }

    
    public void setCurrentStrategy(String strategyChoice){
        switch (strategyChoice) {
            case "priceStrategy":
                currentStrategy = new AlwaysPriceStrategy();
                break;
                
            case "ecoScoreStrategy":
            	currentStrategy = new AlwaysEcoScoreStrategy();
                break;
                
            case "timeStrategy":
                currentStrategy = new AlwaysTimeStrategy();
                break;  
        }
            
        }
    }