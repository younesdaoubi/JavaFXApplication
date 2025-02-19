package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComponentArrivalTest {

    private ComponentArrival componentArrival;
    private ComponentStorage componentStorage; 
    private ComponentView view; 

    @BeforeEach
    public void setUp() {

    	//je recupere mon stock
        this.componentStorage = ComponentStorage.getInstance();

        
        this.view = new ComponentView();  
        componentArrival = new ComponentArrival(componentStorage, view);
    }

    @Test
    public void testAberrantValues() throws IOException {
        File tempFile = File.createTempFile("tempFile", ".tmp");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        
        writer.write("7,Batterie,-31%,d30\n");  // valeure aberrante
        writer.write("5,Moteur,145W,d0\n");
        writer.write("4,Moteur,2249W,d100\n");
        writer.write("7,Capteur,536m,jaune,d50\n");
        writer.write("8,Moteur,236W,d25\n");
        writer.write("3,Capteur,808m,bleu,d58\n");  // valeure aberrante
        writer.write("8,Moteur,78W,d25\n");
     

        writer.close();

        componentArrival.setSimulationFilePath(tempFile.getAbsolutePath());  
        componentArrival.loadComponents();

        assertEquals(13, ComponentStorage.getInstance().getComponents().size(), " 5 composents sont passés");
    }
}
