package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import javafx.animation.PauseTransition;
import javafx.application.Platform;

public class WareHouse implements ComponentObserver, ProductObserver {
    
	
    private ComponentController componentController;
    private ComponentView componentView;
    private ComponentStorage componentStorage;
    private ProductStorage productStorage;
    private ProductView productView;
    
    private ProductFactory productFactory;  // ajout d'une instance de ProductFactory

    
    
    public WareHouse(ComponentView componentView, ProductView productView) {
        this.componentView = componentView;
        this.componentController = new ComponentController(componentView);
        this.productView = productView;

        componentStorage = ComponentStorage.getInstance();
        productStorage = ProductStorage.getInstance();

        componentStorage.addObserver(this);		// ajoute le wareHouse comme observateur des compos
        productStorage.addObserver(this);		// ajoute le wareHouse comme observateur des produits

        this.productFactory = new ProductFactory("priceStrategy"); // initialisé a pricestrategy par defaut
        
    }
    
    public void updateCurrentStrategy(String strategy) {	// met a jour la strategy
        this.productFactory.setCurrentStrategy(strategy);
    }

    
    @Override
    public void onComponentAdded(Component component) {			// methode d'ajout d'un produit
        System.out.println("Composant ajouté de type: " + component.getType());
        
        if(productStorage.getProductStorageList().size()<ProductStorage.MAX_PRODUCT_STOCK_SIZE) {	//verifie si le stock produit nes pas plein
        	List<Callable<Boolean>> productCreationMethods;
            switch (productFactory.getCurrentStrategy().getStrategyDescription()) {
                case "priceStrategy":
                    productCreationMethods = Arrays.asList(
                        this::createSecurityAlarmIfPossible,
                        this::createCarIfPossible,
                        this::createRobotFollowerIfPossible,
                        this::createSurveillanceDroneIfPossible
     
                    );
                    break;
                case "ecoScoreStrategy":
                    productCreationMethods = Arrays.asList(
                        this::createCarIfPossible,
                        this::createRobotFollowerIfPossible,
                        this::createSecurityAlarmIfPossible,
                        this::createSurveillanceDroneIfPossible
     

                    );
                    break;
                case "timeStrategy":
                    productCreationMethods = Arrays.asList(
                        this::createSecurityAlarmIfPossible,
                        this::createCarIfPossible,
                    	this::createRobotFollowerIfPossible,
                    	this::createSurveillanceDroneIfPossible
                     
                    );
                    break;
                default:
                    productCreationMethods = Collections.emptyList();
                    break;
            }

            for (Callable<Boolean> method : productCreationMethods) {
            	try {
                    if (method.call()) {
                        break;
                    }
                } catch (Exception e) {
                     e.printStackTrace();
                }
            }
        }
        
    }

        
  
    @Override
    public void onComponentRemoved(Component component) {		//met a jour laffichage apres retrait d'un compos
        Platform.runLater(() -> {
            componentView.getAllComponentsListView().getItems().clear();
            for (Component comp : componentStorage.getComponents()) {
                componentView.getAllComponentsListView().getItems().add(comp.getDescription());
            }
        });
    }
    
    
    
    public boolean createRobotFollowerIfPossible() {				// cree un robot si possible
    	ElectricEngine electricEngine = findElectricEngineInComponents();
        MotionSensor motionSensor = findMotionSensorInComponents();

        System.out.println("Electric engine found: " + electricEngine);
        System.out.println("MotionSensor found: " + motionSensor);

        if (electricEngine != null && motionSensor != null) {
        	
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                   
                	int defect = (motionSensor.getDefectRate()+electricEngine.getDefectRate())/2;

                    Platform.runLater(() -> {
                        componentStorage.removeComponent(motionSensor);
                        componentStorage.removeComponent(electricEngine);

                        RobotFollower robotFollower = new RobotFollower(electricEngine, motionSensor, defect);  
                        productStorage.addProduct(robotFollower);
                        System.out.println("nouveaux robot follower");
                        System.out.println("taille product : "+productStorage.getProductStorageList().size());
                        System.out.println("taille liste composants : "+componentStorage.getComponents().size());
                    });
                }
            }, RobotFollower.ManufacturedTime*1000); // 5000 milliseconds = 5 seconds
            return true;
        } else {
            System.out.println("Failed to create robot follower. Missing components.");
        }
        return false;
    }
    
    
  
    
    public boolean createSurveillanceDroneIfPossible() {
        Battery battery = findBatteryInComponents();
        MotionSensor motionSensor = findMotionSensorInComponents();
    	ElectricEngine electricEngine = findElectricEngineInComponents();

        System.out.println("Battery found: " + battery);
        System.out.println("MotionSensor found: " + motionSensor);
        System.out.println("Electric engine found: " + electricEngine);

        if (battery != null && motionSensor != null && electricEngine != null) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {


                	int defect = (battery.getDefectRate()+motionSensor.getDefectRate()+electricEngine.getDefectRate())/3;
                    Platform.runLater(() -> {
                        componentStorage.removeComponent(motionSensor);
                        componentStorage.removeComponent(battery);
                        componentStorage.removeComponent(electricEngine);
                        
                        SurveillanceDrone surveillanceDrone = new SurveillanceDrone(electricEngine, motionSensor, battery, defect);  
                        productStorage.addProduct(surveillanceDrone);
                        System.out.println("nouveau drone de surveillance");
                        System.out.println("taille product : "+productStorage.getProductStorageList().size());
                        System.out.println("taille liste composants : "+componentStorage.getComponents().size());
                    });
                }
            }, SurveillanceDrone.ManufacturedTime*1000);  
            return true;
        } else {
            System.out.println("Failed to create drone de surveillance. Missing components.");
        }
        return false;
    }
 
   
    public boolean createSecurityAlarmIfPossible() {
        Battery battery = findBatteryInComponents();
        MotionSensor motionSensor = findMotionSensorInComponents();

        System.out.println("Battery found: " + battery);
        System.out.println("MotionSensor found: " + motionSensor);

        if (battery != null && motionSensor != null) {
            Timer timer = new Timer();
            //int manufacturedTime=0;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    
                	int defect = (battery.getDefectRate()+motionSensor.getDefectRate())/2;
                    Platform.runLater(() -> {
                        componentStorage.removeComponent(motionSensor);
                        componentStorage.removeComponent(battery);

                        SecurityAlarm alarm = new SecurityAlarm(battery, motionSensor, defect);  
                        productStorage.addProduct(alarm);
                        System.out.println("nouvelle alarme de secu créee");
                        System.out.println("taille product : "+productStorage.getProductStorageList().size());
                        System.out.println("taille liste composants : "+componentStorage.getComponents().size());
                    });
                }
            }, SecurityAlarm.ManufacturedTime*1000); // 5000 milliseconds = 5 seconds
            return true;
        } else {
            System.out.println("Failed to create alarm. Missing components.");
        }
        return false;
    }
    
    
    public boolean createCarIfPossible() {
        Battery battery = findBatteryInComponents();
       // MotionSensor motionSensor = findMotionSensorInComponents();
        ElectricEngine electricEngine = findElectricEngineInComponents();
        System.out.println("Battery found: " + battery);
        System.out.println("MotionSensor found: " + electricEngine);

        if (battery != null && electricEngine != null) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    
                	int defect = (battery.getDefectRate()+electricEngine.getDefectRate())/2;
                    Platform.runLater(() -> {
                        componentStorage.removeComponent(electricEngine);
                        componentStorage.removeComponent(battery);

                        RemoteControlledCar remoteControlledCar = new RemoteControlledCar(electricEngine, battery, defect);  
                        productStorage.addProduct(remoteControlledCar);
                        System.out.println("nouvelle voiture telecommandee créee");
                        System.out.println("taille liste products : "+productStorage.getProductStorageList().size());
                        System.out.println("taille liste composants : "+componentStorage.getComponents().size());
                    });
                }
            }, RemoteControlledCar.ManufacturedTime*1000); // 5000 milliseconds = 5 seconds
        return true;
        } else {
            System.out.println("Failed to create voiture telecommandee, Missing components.");
        }
        return false;
    }

 


    private Battery findBatteryInComponents() {			// trouve si une batterie est dispo en stock
        for (Component component : componentStorage.getComponents()) {
            if ("battery".equals(component.getType())) {
                return (Battery) component;
            }
        }
        return null;
    }

    private MotionSensor findMotionSensorInComponents() {    // trouve si un capteur de mouv est dispo en stock
        for (Component component : componentStorage.getComponents()) {
            if ("motionSensor".equals(component.getType())) {
                return (MotionSensor) component;
            }
        }
        return null;
    }

    private ElectricEngine findElectricEngineInComponents() {
        for (Component component : componentStorage.getComponents()) {
            if ("electricEngine".equals(component.getType())) {
                return (ElectricEngine) component;
            }
        }
        return null;
    }

    
     
    public ComponentView getComponentView() {
        return componentView;
    }



    @Override
    public void onProductAdded(Product product) {			
        Platform.runLater(() -> {
            int totalProducts = productStorage.getProductStorageList().size();
            // l'index en coordonnées
            int row = (totalProducts - 1) / 4;
            int col = (totalProducts - 1) % 4;
            productView.addProduct(product, row, col);
        });
    }

    @Override
    public void onProductRemoved(Product product) {
        Platform.runLater(() -> {
            // reconstruit l'affichage des produits après suppression
            
            productView.getGridPane().getChildren().clear();// retire tous les produit de la grid.
            
            // affichage des coordonnées
            productView.displayCoordinates(5, 4);
            
            // remet les produis restants dans la grid
            int index = 0;
            for (Product p : productStorage.getProductStorageList()) {
                int row = index / 4;
                int col = index % 4;
                productView.addProduct(p, row, col);
                index++;
            }
        });
    }

    

    
    
}
