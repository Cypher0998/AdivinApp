package dad.maven.adivinApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaAdivina extends Application {
	private Label textLabel;
	private Button checkButton;
	private TextField inputText;
	private VBox root;
	
	private int contador=0;
	private int numeroIntroducido;
	private int numeroAleatorio;
	public void start(Stage primaryStage)throws Exception{
		numeroAleatorio=(int) (Math.random()*100)+ 1;
		startInitialTextfield();
		startInitialLabel();
		startInitialButton();
		startInitialVBox();
		Scene escena=new Scene(root,320,200);
		primaryStage.setScene(escena);
		primaryStage.setTitle("AdivinApp");
		primaryStage.show();
		
	}
	private void startInitialTextfield() {
		inputText= new TextField();
		inputText.setMaxWidth(150);
		inputText.setAlignment(Pos.CENTER);
		inputText.setPromptText("Introduzca un número");
	}
	private void startInitialLabel() {
		textLabel= new Label();
		textLabel.setText("Inroduce un número del 1 al 100");
	}
	private void startInitialButton() {
		checkButton=new Button();
		checkButton.setText("Comprobar");
		checkButton.setOnAction(e -> onCheckButtonAction(e));
		checkButton.setDefaultButton(true);
	}
	
	private void startInitialVBox() {
		root=new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(textLabel,inputText,checkButton);
	}
	
	private void onCheckButtonAction(ActionEvent e) {
		try {
			numeroIntroducido=Integer.parseInt(inputText.getText());
		}catch(Exception error) {
			alertaNaN();
		}
		contador =contador+1;
		if(numeroIntroducido==numeroAleatorio) {
			alertaAcierto();
		}
		if(numeroIntroducido>numeroAleatorio & numeroIntroducido<=100||numeroIntroducido<numeroAleatorio& numeroIntroducido>=1)
			alertaFallo();
		if(numeroIntroducido<1||numeroIntroducido>100)
			alertaError();
	}
	private void alertaFallo() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("¡Has fallado!");
		if(numeroIntroducido>numeroAleatorio)
			alert.setContentText("El número a adivinar es menor que "+numeroIntroducido+". \nVuelve a intentarlo.");
		else
			alert.setContentText("El número a adivinar es mayor que "+numeroIntroducido+". \nVuelve a intentarlo.");

		alert.showAndWait();
	}
	private void alertaAcierto() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("¡Has ganado!");
		alert.setContentText("Sólo has necesitados "+contador+" intentos.\nVuelve a intentarlo." );
		alert.showAndWait();
		numeroAleatorio=(int) (Math.random()*100)+ 1;
	}
	private void alertaError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("Error");
		alert.setContentText("El número introducido no es válido");

		alert.showAndWait();
	}
	private void alertaNaN () {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("Error");
		alert.setContentText("No has introducido un número");

		alert.showAndWait();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
