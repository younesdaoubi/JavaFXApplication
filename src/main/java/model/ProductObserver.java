package model;

public interface ProductObserver {
	
	void onProductAdded(Product product);
    void onProductRemoved(Product product);

}
