package model;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class ComponentView {

    private ListView<String> allComponentsListView;	// liste composants
    private Button countButton;					// boutton pour afficher compteur (aditionnel)
    private Scene scene;						// scene principale

    
    //constructeur
    public ComponentView() {
    	
    	//initialisations
        allComponentsListView = new ListView<>();

        allComponentsListView.setCellFactory(new ComponentCellFactory());

        countButton = new Button("show count componentsList's counter");

        HBox buttonContainer = new HBox(countButton);
        buttonContainer.setAlignment(Pos.BOTTOM_LEFT);

        BorderPane root = new BorderPane();
        HBox listViewContainer = new HBox();
        allComponentsListView.prefHeightProperty().bind(listViewContainer.heightProperty());

        root.setRight(listViewContainer);		// ajout des composants au conteneurs
        listViewContainer.getChildren().addAll(allComponentsListView);
        root.setBottom(buttonContainer);

        scene = new Scene(root, 1200, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        
        allComponentsListView.setOnMouseClicked(event -> {	// sert a deselectionner (grid.clearselection()).
            allComponentsListView.getSelectionModel().clearSelection();
        });
    }

    public Scene getScene() {
        return scene;
    }

    public Button getCountButton() {
        return countButton;
    }

    public ListView<String> getAllComponentsListView() {
        return allComponentsListView;
    }

    private class ComponentCellFactory implements Callback<ListView<String>, ListCell<String>> { // factory pour creer une cellule personnalisée de la liste.
        @Override
        public ListCell<String> call(ListView<String> param) {
            return new ComponentCell();
        }
    }
    
    public static String toHexString(Color color) {	//  converti mes color en string pour css ci dessous.
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        return String.format("#%02X%02X%02X", r, g, b);
    }


    private class ComponentCell extends ListCell<String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {
                setText(item);
                if (item.contains("battery")) {
                    setStyle("-fx-background-color: " + toHexString(Battery.COLOR) + ";"); 
                } else if (item.contains("electricEngine")) {
                    setStyle("-fx-background-color: " + toHexString(ElectricEngine.COLOR) + ";"); 
                } else if (item.contains("motionSensor")) {
                    setStyle("-fx-background-color: " + toHexString(MotionSensor.COLOR) + ";"); 
                } else {
                    setStyle("");
                }
            } else {
                setText("");
                setStyle("");
            }
        }
    }
}
