package model;

import java.util.ArrayList;
import java.util.List;

public class ComponentStorage {
	
	public static int MAX_COMPONENT_STOCK_SIZE = 200; //taille max du stock

     
    private static ComponentStorage instance;   // instance statique pour implémenter le pattern singleton
    private ArrayList<Component> components;	// liste de stock de composants
    private List<ComponentObserver> observers = new ArrayList<>();	// liste pour stocker les observateurs qui seront informés lors des modifications de la liste des composantes

    private ComponentStorage() {// contructeur privé, la classe ne peut etre que instanciée depuis la méthode getInstance() ci dessou
        components = new ArrayList<>();
    }

    public static ComponentStorage getInstance() {	
        if (instance == null) {
            instance = new ComponentStorage();
        }
        return instance;
    }

    public void addObserver(ComponentObserver observer) {	// ajoute observateur
        observers.add(observer);
    }

    public void removeObserver(ComponentObserver observer) {		// retire
        observers.remove(observer);
    }

    public void addComponent(Component component) {
        components.add(component);
        notifyComponentAdded(component);
    }

    private void notifyComponentAdded(Component component) {	//informe observ nouveau composent ajouté
        for (ComponentObserver observer : observers) {
            observer.onComponentAdded(component);
        }
    }
    
    private void notifyComponentRemoved(Component component) {    ///informe observ nouveau composent supprimé.
        for (ComponentObserver observer : observers) {
            observer.onComponentRemoved(component);
        }
    }

    public ArrayList<Component> getComponents() {
        return components;
    }
    
    public void removeComponent(Component component) {	
        components.remove(component);	// supprime composant du stock
        notifyComponentRemoved(component); // informe les observateurs.
        }
}
