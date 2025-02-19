package model;

public interface ComponentObserver {	
	public abstract void onComponentAdded(Component component); // methode public et abstraite qui doit etre redéfinie
	public abstract void onComponentRemoved(Component component);

}
