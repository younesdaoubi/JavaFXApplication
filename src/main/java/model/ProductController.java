package model;

public class ProductController {

    private ProductView view;  //vue 
    private ProductStorage productStorage;  // stock produit (singleton)

    private ProductFactory productFactory = new ProductFactory("priceStrategy");  // creer une instance factory avec priceStrategy par defaut.
    private WareHouse wareHouse;  

    
    public ProductController(ProductView view, WareHouse wareHouse) {  
        this.view = view;
        this.wareHouse = wareHouse;   
        productStorage = ProductStorage.getInstance();
        populateGrid();   // remplissage de la grid avec des produits existants

        
        // ecoute le type de choix afin de mettre a jour la strategie de l'entrepot
         view.getProductTypeChoiceBox().valueProperty().addListener((observable, oldValue, newValue) -> {
            this.wareHouse.updateCurrentStrategy(newValue);
        });

    }

    
    
    
    public void changeProductCreationStrategy(String productType) { // change la strategy du produit
        productFactory.setCurrentStrategy(productType);
    }



    private void populateGrid() {  		// affiche la grille de produits en colonnes ( la met a jour)
        int col = 0;
        int row = 0;
        for (Product product : productStorage.getProductStorageList()) {
            view.addProduct(product, row, col);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }
    }
    
 
}
