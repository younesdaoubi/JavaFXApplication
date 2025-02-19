package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;

public class ComponentArrival {
																	
	private static final String DEFAULT_SIMULATION_FILE_PATH = "helbelectro_seconde_sess.data"; // chemin d'arrivée par défaut
    private String simulationFilePath = DEFAULT_SIMULATION_FILE_PATH;

	private ComponentStorage componentStorage;
    private ComponentView view;
    
    public ComponentArrival(ComponentStorage componentStorage, ComponentView view) {
        this.componentStorage = componentStorage;
        this.view = view;
    }
    
    public void loadComponents() {	// méthhode pour lire le fichier
        new Thread(() -> {/// creation d'un nouveau thread pour executer la tâche.
            List<String> allComponents = new ArrayList<>();	// liste qui va stocker tous mes composants
            try (BufferedReader br = new BufferedReader(new FileReader(DEFAULT_SIMULATION_FILE_PATH))) {	//essaie de lire fichier avec BufferReader
                String line;
                 while ((line = br.readLine()) != null ) {	// lis le fichier jusque quand plus de lignes
                	if(componentStorage.getComponents().size() < ComponentStorage.MAX_COMPONENT_STOCK_SIZE) {
                		String[] components = line.split(",");	// split sert a découper avec , comme separateur
                        int arrival = Integer.parseInt(components[0]);
                        String type = components[1];

                        try {
                            Thread.sleep(arrival * 1000);// va simuler l'arrivée du compoants : arrival *1000 mls.
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (type.equals("Moteur")) {	// traitre moteur
                            int power = Integer.parseInt(components[2].replaceAll("[^0-9]", ""));
                            int defect = Integer.parseInt(components[3].replaceAll("[^0-9]", ""));
                            ElectricEngine engine = new ElectricEngine(type, arrival, power, defect);
                            componentStorage.addComponent(engine);
                            Platform.runLater(() -> view.getAllComponentsListView().getItems().add(engine.getDescription()));
                        } else if (type.equals("Capteur")) {// traitre capteur
                            int scope = Integer.parseInt(components[2].replaceAll("[^0-9]", ""));	// je remplace tous les caracteres non numériques par une chaine vide.
                            String color = components[3];
                            int defect = Integer.parseInt(components[4].replaceAll("[^0-9]", ""));
                            MotionSensor sensor = new MotionSensor(type, arrival, scope, color, defect);
                            componentStorage.addComponent(sensor);
                            Platform.runLater(() -> view.getAllComponentsListView().getItems().add(sensor.getDescription()));
                        } else if (type.equals("Batterie")) {// traitre batterie
                            int pourcentage = Integer.parseInt(components[2].replaceAll("[^0-9]", ""));
                            int defect = Integer.parseInt(components[3].replaceAll("[^0-9]", ""));
                            Battery battery = new Battery(type, arrival, pourcentage, defect);
                            componentStorage.addComponent(battery);
                            Platform.runLater(() -> view.getAllComponentsListView().getItems().add(battery.getDescription()));
                        }
                 	}
                }
            } catch (IOException e) { // gère les exception.
                e.printStackTrace();
            }
        }).start();
    }
    
    public void setSimulationFilePath(String path) {
        this.simulationFilePath = path;
    }
    
    public String getSimulationFilePath() {	// chemin de mon file d'arrivée de composants.
    	return this.simulationFilePath;
    }
}
