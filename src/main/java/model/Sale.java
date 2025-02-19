package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Sale {
    private Product product;
     
    private LocalDateTime localDateTime;	
     
    private String ticketName; 
    private String ticketDescription;
    
    public Sale(Product product) {
        this.product = product;
        this.localDateTime = LocalDateTime.now();	// heure et date actuelle
 
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss"); // je défini le nom du fichier(ticket)
        String formattedTime = localDateTime.format(formatter);
        this.ticketName =  formattedTime;
        
       
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);

        ticketDescription= "Date : "+formattedDateTime;								//---> description du ticket
        ticketDescription+="\nTitle : "+this.getProduct().getType();
        ticketDescription+="\nPrice : "+this.getProduct().getPrice()+" euros";
        ticketDescription+="\nEco-Score : "+this.getProduct().getEcoScore();
        ticketDescription+="\nDefect : "+this.getProduct().getDefectRate()+" %";
        ticketDescription+="\nDetails : "+this.getProduct().toString();
        
    }

    public Product getProduct() {
        return this.product;
    }

 
    
    public void recordSale() {  //enregistrer une vente.
    	 try {
        	 
             // crée un fichier dans src/main/java/sales/
             FileWriter fw = new FileWriter("src/main/java/sales/t_"+this.getTicketName()+".txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw);
             
             // Écrire la date, l'heure et le nom du produit dans le fichier
             out.println(this.toString());
             
             out.close();
             bw.close();
             fw.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
  
        
    	 ProductStorage store = ProductStorage.getInstance();		// retrait du stock
     	 
    	 store.removeProduct(this.getProduct());
      	 
    	
    }

	public String getTicketName() {
		return ticketName;
	}

	public String toString() {	
		return this.ticketDescription;
	}
	

}
