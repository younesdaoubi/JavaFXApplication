package model;

import model.ComponentStorage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import model.Battery;
import model.ElectricEngine;
import model.MotionSensor;

public class ComponentController {

    private ComponentStorage componentStorage; 				//stock de composants (singleton)
    private ComponentView view;								//vue composants affichage
    private ComponentArrival componentArrival; 				//arrivée des composants


    //Constructeur
    public ComponentController(ComponentView view) {
        this.view = view;	// init vue
        componentStorage = ComponentStorage.getInstance();
        componentArrival = new ComponentArrival(componentStorage, view); // je récupère l'instance de storage composants

        initHandlers();	// init évenements
        
        
        componentArrival.loadComponents(); 

        populateListView();
    }

    private void initHandlers() {
        view.getCountButton().setOnAction(e -> {
            System.out.println("Total: " + componentStorage.getComponents().size());
            for (Component component : componentStorage.getComponents()) {
                System.out.println(component.getDescription());
            }
            System.out.println("size of components list : " + componentStorage.getComponents().size());
        });
    }

 
    
    private void populateListView() {	//affiche a la vue la liste du stock de composants
        List<String> allComponents = new ArrayList<>();
        for (Component component : componentStorage.getComponents()) {
            allComponents.add(component.getDescription());
        }
        view.getAllComponentsListView().getItems().addAll(allComponents);
    }
}
