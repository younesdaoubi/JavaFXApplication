/**
 * 
 */
/**
 * @author youyo
 *
 */
module myprojectLastVersion {
	requires javafx.graphics;
	//requires java.desktop;
	requires javafx.controls;
	//requires javafx.fxml;
	
	opens main to javafx.fxml;
	exports main;
}