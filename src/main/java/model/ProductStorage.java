package model;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductStorage {
	
	public static int MAX_PRODUCT_STOCK_SIZE = 50;
	private static ProductStorage instance;
    private ArrayList<Product> productStorageList;

    private ArrayList<ProductObserver> observers = new ArrayList<>();

    private ProductStorage() {
    	productStorageList = new ArrayList<>();
    
    }

    public static ProductStorage getInstance() {
        if (instance == null) {
            instance = new ProductStorage();
        }
        return instance;
    }


    public ArrayList<Product> getProductStorageList() {
        return productStorageList;
    }
    
    
    
    public void addObserver(ProductObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ProductObserver observer) {
        observers.remove(observer);
    }

    private void notifyProductAdded(Product product) {
        for (ProductObserver observer : observers) {
            observer.onProductAdded(product);
        }
    }

    private void notifyProductRemoved(Product product) {
        for (ProductObserver observer : observers) {
            observer.onProductRemoved(product);
        }
    }

    public void addProduct(Product product) {
        	
    	productStorageList.add(product);
    	notifyProductAdded(product);
        
    	
    }
    
    public void removeProduct(Product product) {
    	productStorageList.remove(product);
    	notifyProductRemoved(product);
    }
    

}
