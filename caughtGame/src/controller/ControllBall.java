package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Game;
import thread.CircleThread;

public class ControllBall {
	@FXML private AnchorPane anchor;
	private Game game;
	private int score;

	public void mover() {
		
		for (int i = 0;i<game.getBalls().size();i++) {
			
			Circle circle = new Circle(game.getBalls().get(i).getRadius());
			circle.setCenterX(game.getBalls().get(i).getX());
			circle.setFill(Color.BLACK);
			anchor.getChildren().add(circle);
			CircleThread hilo = new CircleThread(circle,game.getBalls().get(i));
			hilo.start();
			
			
		}

	}
	
	public void clicBall(MouseEvent e ) {
	
		for (int i = 0;i<game.getBalls().size();i++) {
			double x = e.getSceneX()-(game.getBalls().get(i).getX());
				x*=x;
			double y = e.getSceneY()-(game.getBalls().get(i).getY());
				y*=y;
			double suma = x+y;
			
			
			double radio = game.getBalls().get(i).getRadius();
			  radio *=radio;
			  
			  if (suma <= radio) {
			
				game.getBalls().get(i).setStopped(true);;
				
			}
		}
		boolean n = true;
		for (int i = 0; i < game.getBalls().size(); i++) {
		if	(game.getBalls().get(i).isStopped()==false) {
			n =false;
		}
		
		
		}
	}
	

	
	
	public void hall() {
		
		Stage n = new Stage();
		AnchorPane ap = new AnchorPane();
		Label label = new Label (game.theBest());
		ap.getChildren().add(label);
		Scene scene = new Scene(ap , 300,300);
		n.setScene(scene);
		n.show();
		
	}
	
	
	public void loadGame(ActionEvent ee) {
		Stage n = new Stage();
		BorderPane ap = new BorderPane();
	
		TextField texF = new TextField();
		
		HBox hb = new HBox();
		Label lb =new Label();
		Button bt = new Button("Load");
		
		hb.getChildren().add(bt);
		ap.setTop(texF);
		ap.setCenter(hb);
		lb.setText("Enter file name");
		ap.setBottom(lb);
		Scene scene = new Scene(ap , 300,300);
		n.setScene(scene);
		n.show();
		bt.setOnAction(e-> {
			try {
			 game = new Game(texF.getText());
			 mover();
			 
				 lb.setText("The File has loaded successfully");
				 ap.setBottom(lb);
				 Stage s = (Stage)ap.getScene().getWindow();
				 s.close();
			}catch (Exception ew) {
				lb.setText("The File doesn't exist");
			}
				
				 
		
			 
		});
		
	}
	
	public void exit(ActionEvent e) {
		Stage s = (Stage)anchor.getScene().getWindow();
		s.close();
	}
	
	public void aboutGame(ActionEvent e) {
		
		Stage s = new Stage();
		AnchorPane anchor = new AnchorPane();
		String msg = "El juego consta de unas esferas Moviendose Vertical y Horizontalmente "+"\n"+"El Ganador sera el que para todas las esferas"+"\n"+"Haciendole clic sobre la esfera y tengas menos cantidad de rebotes";
		Label label = new Label(msg);
		anchor.getChildren().add(label);
		Scene scene = new Scene(anchor, 300,300);
		s.setScene(scene);
		s.setTitle("About The Game");
		s.show();
	}
}
