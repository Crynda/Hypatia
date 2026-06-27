package gui.controladores;

import java.io.IOException;

import gui.config.GestorTemas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	Image icon = new Image (getClass().getResourceAsStream("/recursos/Banner4.png"));

	@FXML
	private ImageView Banner;
	
	public void CRegresionLineal(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/escenas/RegresionLineal.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		GestorTemas.preparar(scene);

		stage.setScene(scene);
		stage.show();
		
		

	}
}
