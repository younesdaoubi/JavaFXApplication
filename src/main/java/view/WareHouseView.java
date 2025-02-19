//package view;
//
//import java.util.ArrayList;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import javafx.application.Platform;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import model.Component;
//import model.ComponentStorage;
//import model.ManufacturedProduct;
//import others.ManufacturedProductStorage;
//import others.WareHouse;
//
//public class WareHouseView {
//	
//	private VBox root;
//    private ScrollPane scrollPane;
//    private static WareHouseView instance;
//
//    public WareHouseView() {
//        root = new VBox();
//        scrollPane = new ScrollPane(root);
//        scrollPane.setFitToWidth(true);
//    }
//
//    public static WareHouseView getInstance() {
//        if (instance == null) {
//            instance = new WareHouseView();
//        }
//        return instance;
//    }
//
//    public VBox getRoot() {
//        return root;
//    }
//
//    public ScrollPane getScrollPane() {
//        return scrollPane;
//    }
//
//    public void afficherComposants(ArrayList<Component> composants) {
//        for (Component component : composants) {
//            HBox composantBox = new HBox();
//            composantBox.getChildren().add(new Label(component.getType()));
//
//            root.getChildren().add(composantBox);
//        }
//    }
//
//    public void afficherTailleComposants(int taille) {
//        HBox tailleBox = new HBox();
//        tailleBox.getChildren().add(new Label("Taille des composants: " + taille));
//
//        root.getChildren().add(tailleBox);
//    }
//	
//	
////	private VBox root;
////    private ScrollPane scrollPane;
////
////    public WareHouseView() {
////        root = new VBox();
////        scrollPane = new ScrollPane(root);
////        scrollPane.setFitToWidth(true);
////    }
//
////    public void afficherComposants(WareHouse model) {
////        root.getChildren().clear();
////
////        // Affichage des composants dans la zone de stockage des composants
////        HBox composantsBox = new HBox();
////        for (Component composant : model.getZoneStockageComposants()) {
////            composantsBox.getChildren().add(new Label(composant.getType()));
////        }
////
////        root.getChildren().add(composantsBox);
////    }
////
////    public VBox getRoot() {
////        return root;
////    }
////
////    public ScrollPane getScrollPane() {
////        return scrollPane;
////    }
//	
//	
//    
//
//    
////	private VBox root;
////    private ScrollPane scrollPane;
////
////    public WareHouseView() {
////        root = new VBox();
////        scrollPane = new ScrollPane(root);
////        scrollPane.setFitToWidth(true);
////    }
//  
//	
//	
//	
//	
////	 private VBox root;
////	    private ScrollPane scrollPane;
////
////	    public WareHouseView(WareHouse entrepot) {
////	        root = new VBox();
////	        scrollPane = new ScrollPane(root);
////	        scrollPane.setFitToWidth(true);
////
////	        ComponentStorage componentStorage = entrepot.getComponentStorage();
////	        ZoneStockageProduitsFini zoneStockageProduitsFini = entrepot.getZoneStockageProduitsFini();
////
////	        // Affichage des composants dans la zone de stockage des composants
////	        HBox composantsBox = new HBox();
////	        for (Component composant : componentStorage.getComposants()) {
////	            composantsBox.getChildren().add(new Label(composant.getType()));
////	        }
////
////	        // Affichage des produits finis dans la zone de stockage des produits finis
////	        HBox produitsFiniBox = new HBox();
////	        for (ManufacturedProduct produitFini : zoneStockageProduitsFini.getProduitsFini()) {
////	            produitsFiniBox.getChildren().add(new Label(produitFini.getName()));
////	        }
////
////	        root.getChildren().addAll(composantsBox, produitsFiniBox);
////	    }
////
////	    public VBox getRoot() {
////	        return root;
////	    }
////
////	    public ScrollPane getScrollPane() {
////	        return scrollPane;
////	    }
//
//	
//	
////	private VBox root;
////    private ScrollPane scrollPane;
////
////    public WareHouseView() {
////        root = new VBox();
////        scrollPane = new ScrollPane(root);
////        scrollPane.setFitToWidth(true);
////
////        // Création de l'entrepôt et des zones de stockage
////        WareHouse entrepot = new WareHouse();
////        ComponentStorage zoneStockageComposants = ComponentStorage.getInstance();
////        ManufacturedProductStorage zoneStockageProduitsFini = ManufacturedProductStorage.getInstance();
////
////        // Affichage des composants dans la zone de stockage des composants
////        HBox composantsBox = new HBox();
////        for (Component composant : zoneStockageComposants.getComposants()) {
////            composantsBox.getChildren().add(new Label(composant.getType()));
////        }
////
////        // Affichage des produits finis dans la zone de stockage des produits finis
////        HBox produitsFiniBox = new HBox();
////        for (ManufacturedProduct produitFini : zoneStockageProduitsFini.getProduitsFini()) {
////            produitsFiniBox.getChildren().add(new Label(produitFini.getName()));
////        }
////
////        root.getChildren().addAll(composantsBox, produitsFiniBox);
////    }
////
////    public VBox getRoot() {
////        return root;
////    }
////
////    public ScrollPane getScrollPane() {
////        return scrollPane;
////    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
////  public VBox getRoot() {
////  return root;
////}
////
////public ScrollPane getScrollPane() {
////  return scrollPane;
////}
////
////public void afficherComposants(ComponentStorage componentStorage) {
////  root.getChildren().clear();
////
////  // Affichage des composants dans la zone de stockage des composants
////  HBox composantsBox = new HBox();
////  for (Component composant : componentStorage.getComposants()) {
////      composantsBox.getChildren().add(new Label(composant.getType()));
////  }
////
////  root.getChildren().add(composantsBox);
////}
////
////public void afficherTailleComposants(ComponentStorage componentStorage) {
////  Label tailleLabel = new Label();
////  TimerTask task = new TimerTask() {
////      @Override
////      public void run() {
////          Platform.runLater(() -> {
////              tailleLabel.setText("Taille composants : " + componentStorage.getComposants().size());
////          });
////      }
////  };
////
////  Timer timer = new Timer();
////  timer.schedule(task, 0, 1000); // Mise à jour toutes les 1000 millisecondes (1 seconde)
////
////  root.getChildren().add(tailleLabel);
////}
//
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
////    public VBox getRoot() {
////        return root;
////    }
////
////    public ScrollPane getScrollPane() {
////        return scrollPane;
////    }
//
////    public void afficherComposant(Component component) {
////        HBox composantBox = new HBox();
////        composantBox.getChildren().add(new Label(component.getType()));
////
////        root.getChildren().add(composantBox);
////    }
//  
//    
//    
//    
//    
//    
//    
////    public void afficherComposants(ArrayList<Component> composants) {
////        for (Component component : composants) {
////            HBox composantBox = new HBox();
////            composantBox.getChildren().add(new Label(component.getType()));
////
////            root.getChildren().add(composantBox);
////        }
////    }
////
////
////    public void afficherTailleComposants(int taille) {
////        HBox tailleBox = new HBox();
////        tailleBox.getChildren().add(new Label("Taille des composants: " + taille));
////
////        root.getChildren().add(tailleBox);
////    }
//}
