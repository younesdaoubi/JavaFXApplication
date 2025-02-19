package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ProductView {
    private static final int CELL_SIZE = 100; // taille d'une cellule de ma grid produits

    private GridPane gridPane;	// contient les produits
    private ScrollPane scrollPane; // permet le defilement si trop de produits 

    private boolean isAlphaCoordinates = false;
    private Button toggleButton;
    
    private ProductController controller;
    private ProductStorage productStorage = ProductStorage.getInstance();
    // choix de la strategie
     private ChoiceBox<String> productTypeChoiceBox;

    public ProductView() {
    	
    	//initialtion du gridpane :
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setPrefWidth(200 * 5);

        scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxHeight(750);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        displayCoordinates(5, 4);

        toggleButton = new Button("0 - 9 / A - Z");	// bouton pour basculer chiffre/lettre (stock emplacement)
        toggleButton.setOnAction(e -> toggleCoordinates());
        gridPane.add(toggleButton, 0, 0, 4, 1);
        
        
        
        //strategie
        productTypeChoiceBox = new ChoiceBox<>();
        
        productTypeChoiceBox.getItems().addAll("priceStrategy", "ecoScoreStrategy", "timeStrategy");
        productTypeChoiceBox.setValue("priceStrategy"); // valeur par défaut
        
        VBox creationBox = new VBox(productTypeChoiceBox);
        creationBox.setSpacing(10);

        gridPane.add(creationBox, 0, 0, 4, 1);
        
        
        
        VBox controlsBox = new VBox(toggleButton, creationBox);
        controlsBox.setSpacing(10);
        gridPane.add(controlsBox, 0, 0, 4, 1);
        
    
        
        
        productTypeChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                notifyProductTypeChanged(newValue);
            }
        });

    }
    
    
    
    public void setController(ProductController controller) {
        this.controller = controller;
    }

    private void notifyProductTypeChanged(String newType) {
        if (controller != null) {
            controller.changeProductCreationStrategy(newType);
        }
    }
    
    
    
    public void addProduct(Product product, int row, int col) {
        Rectangle rectangle = new Rectangle(200, 100);
        rectangle.setFill(getColorByProductType(product.getType()));

        Label productNameLabel = new Label(product.getType());
        productNameLabel.setWrapText(true);

        StackPane productContainer = new StackPane(rectangle, productNameLabel);

        Tooltip tooltip = new Tooltip(product.getDescription());
        Tooltip.install(productContainer, tooltip);

        productContainer.setOnMouseClicked(e -> showProductDetails(product));

        int totalItems = row * 4 + col;
        int realRow = (totalItems / 4) + 1;
        int realCol = totalItems % 4 + 1;

        gridPane.add(productContainer, col + 1, row + 1);

        if (row > 4) {
            displayCoordinates(row + 1, 4);
        }
    }

 
    public ChoiceBox<String> getProductTypeChoiceBox() {
        return productTypeChoiceBox;
    }

    



    public GridPane getGridPane() {
        return gridPane;
    }

    
    public void displayCoordinates(int rows, int cols) {			// méthode pour gérer l'affichage des coordonnées.
        removeCoordinates(); 

        int maxRows = Math.max(rows, gridPane.getRowCount());
        int maxCols = Math.max(cols, gridPane.getColumnCount());

        for (int i = 0; i < maxRows; i++) {
            Label rowLabel = new Label(isAlphaCoordinates ? String.valueOf((char) (i + 65)) : String.valueOf(i));
            StackPane rowContainer = new StackPane(rowLabel);
            rowContainer.setAlignment(Pos.CENTER);
            rowContainer.setMinSize(CELL_SIZE, CELL_SIZE);
            gridPane.add(rowContainer, 0, i + 1);
        }

        for (int j = 0; j < maxCols; j++) {
            Label colLabel = new Label(getColumnLabel(j));
            StackPane colContainer = new StackPane(colLabel);
            colContainer.setAlignment(Pos.CENTER);
            colContainer.setMinSize(CELL_SIZE, CELL_SIZE);
            gridPane.add(colContainer, j + 1, 0);
        }
    }


    private void removeCoordinates() {
        for (Node child : new ArrayList<>(gridPane.getChildren())) {
            if (child instanceof StackPane) {
                StackPane stack = (StackPane) child;
                if (stack.getChildren().get(0) instanceof Label) {
                    gridPane.getChildren().remove(child);
                }
            }
        }
    }
    
    private String getColumnLabel(int col) { // aide a gerer les postions (letter a-z puis aa,ab )
        if (!isAlphaCoordinates) {
            return String.valueOf(col);
        }

        StringBuilder label = new StringBuilder();
        while (col >= 0) {
            int remainder = col % 26;
            label.insert(0, (char) (remainder + 65));
            col = (col / 26) - 1;
        }
        return label.toString();
    }


    private void toggleCoordinates() {
        isAlphaCoordinates = !isAlphaCoordinates;
        displayCoordinates(5, 4);
        
        
    }


    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }
 
    private Color getColorByProductType(String type) {
         switch (type.toLowerCase()) {
            case "securityalarm":
                return SecurityAlarm.COLOR;
             case "remotecontrolledcar":
                return RemoteControlledCar.COLOR;
             case "robotfollower":
            	 return RobotFollower.COLOR;
             case "surveillancedrone":
            	 return SurveillanceDrone.COLOR;
             
            default:
                return Color.WHITE;
        }
    }

 
    
    
    
    
    
    private void showProductDetails(Product product) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Détails du produit");

        final String REGULAR_STYLE = "-fx-font-size: 14;";    // Constante pour le style


        VBox content = new VBox(10);  
        content.setPadding(new Insets(10, 10, 10, 10));
        content.setSpacing(15); 

       
        Label title = new Label(product.getType());
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");  
        content.getChildren().add(title);

        // Description
        Label description = new Label("Description: " + product.getDescription());
        description.setStyle("-fx-font-size: 12;");
        content.getChildren().add(description);

        
        String productType = product.getType();

        if ("securityAlarm".equals(productType)) {
            Label pourcentage = new Label("Pourcentage: " +((SecurityAlarm) product).getPourcentage());
            pourcentage.setStyle(REGULAR_STYLE);
            content.getChildren().add(pourcentage);

            Label scope = new Label("Scope: " + ((SecurityAlarm) product).getScope());
            scope.setStyle(REGULAR_STYLE);
            content.getChildren().add(scope);
            
            Label defect = new Label("defect : " + ((SecurityAlarm) product).getDefectRate());
            defect.setStyle(REGULAR_STYLE);
            content.getChildren().add(defect);
        } else if ("remoteControlledCar".equals(productType)) {
            Label power = new Label("Power: " + ((RemoteControlledCar) product).getPower());
            power.setStyle(REGULAR_STYLE);
            content.getChildren().add(power);

            Label pourcentage = new Label("Pourcentage: " + ((RemoteControlledCar) product).getPourcentage());
            pourcentage.setStyle(REGULAR_STYLE);
            content.getChildren().add(pourcentage);
            
            Label defect = new Label("defect : " + ((RemoteControlledCar) product).getDefectRate());
            defect.setStyle(REGULAR_STYLE);
            content.getChildren().add(defect);
        } else if ("robotFollower".equals(productType)) {
            Label scope = new Label("Scope: " + ((RobotFollower) product).getScope());
            scope.setStyle(REGULAR_STYLE);
            content.getChildren().add(scope);

            Label power = new Label("Power: " + ((RobotFollower) product).getPower());
            power.setStyle(REGULAR_STYLE);
            content.getChildren().add(power);
            
            Label defect = new Label("defect : " + ((RobotFollower) product).getDefectRate());
            defect.setStyle(REGULAR_STYLE);
            content.getChildren().add(defect);
        } else if ("surveillanceDrone".equals(productType)) {
            Label power = new Label("Power: " + ((SurveillanceDrone) product).getPower());
            power.setStyle(REGULAR_STYLE);
            content.getChildren().add(power);

            Label pourcentage = new Label("Pourcentage: " + ((SurveillanceDrone) product).getPourcentage());
            pourcentage.setStyle(REGULAR_STYLE);
            content.getChildren().add(pourcentage);

            Label scope = new Label("Scope: " + ((SurveillanceDrone) product).getScope());
            scope.setStyle(REGULAR_STYLE);
            content.getChildren().add(scope);
            
            Label defect = new Label("defect : " + ((SurveillanceDrone) product).getDefectRate());
            defect.setStyle(REGULAR_STYLE);
            content.getChildren().add(defect);
        }

        // prix
        Label price = new Label("Prix: " + product.getPrice() + " €");
        price.setStyle(REGULAR_STYLE);
        content.getChildren().add(price);

        // EcoScore
        Label ecoScore = new Label("EcoScore: " + product.getEcoScore());
        ecoScore.setStyle(REGULAR_STYLE);
        content.getChildren().add(ecoScore);

     // boutton vendre 
        Button sellButton = new Button("Vendre");
        sellButton.setStyle(
            "-fx-background-color: #ffffff; " +
            "-fx-border-color: #aaaaaa; " +  
            "-fx-border-radius: 5; " + 
            "-fx-padding: 5 15 5 15;" 
        );
        sellButton.setOnAction(e -> {
            if(product.getDefectRate() < 50) {
                Sale sale = new Sale(product);
                sale.recordSale();

                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                confirmationAlert.setTitle("Confirmation");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("The sale was successfully registered. !\nTicket available in the sales file.");
                confirmationAlert.showAndWait();

                dialog.close();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Sale is not possible due to a high default rate.");
                errorAlert.showAndWait();
            }
        });
        content.getChildren().add(sellButton);

        
        content.setStyle(
            "-fx-background-color: #ffffff;" + 
            "-fx-border-color: " + getColorByProductType(product.getType()).toString().replace("0x", "#") + ";" +
            "-fx-border-width: 5;"
        );
        
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.showAndWait();
    }

    
    
 
     

}
