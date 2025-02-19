
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ComponentView;
import model.ProductController;
import model.ProductView;
import model.WareHouse;
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	 	ComponentView componentView = new ComponentView();
    	    ProductView productView = new ProductView();

    	    WareHouse wareHouse = new WareHouse(componentView,productView); 

    	    ProductController productController = new ProductController(productView,wareHouse);

    	    BorderPane root = new BorderPane();
    	    root.setRight(componentView.getScene().getRoot()); 
    	    root.setLeft(productView.getScrollPane());

    	    Scene scene = new Scene(root);
    	    primaryStage.setTitle("Liste des composants et produits");
    	    primaryStage.setScene(scene);

    	    // Set the window to be full screen
    	    primaryStage.setFullScreen(true);
    	    
    	    primaryStage.show();
    }
}
