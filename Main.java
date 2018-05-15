package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {
	private FlowPane imagepaneTop;
	private FlowPane imagepaneBottom;
	private Deck deck;

	@Override
	public void start(Stage primaryStage) {

		try {
			primaryStage.setTitle("BlackJack");
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			FlowPane flowpane = new FlowPane();
			
			imagepaneTop = new FlowPane();
			imagepaneBottom = new FlowPane();

			Button startButton = new Button("Load");
			Button hitButton = new Button("Hit");
			Button standButton = new Button("Stand");
			Button resetButton = new Button("Reset");

			flowpane.getChildren().add(startButton);
			flowpane.getChildren().add(hitButton);
			flowpane.getChildren().add(standButton);
			flowpane.getChildren().add(resetButton);

			root.setTop(imagepaneTop);
			root.setCenter(imagepaneBottom);
			root.setBottom(flowpane);

			flowpane.setAlignment(Pos.CENTER);
			imagepaneBottom.setAlignment(Pos.BOTTOM_LEFT);
			imagepaneTop.setAlignment(Pos.TOP_LEFT);

			deck = new Deck();
			deck.init();
			//deck.print();
			deck.Shuffle();

			startButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					dealToPlayer();
					dealToDealer();
					dealToPlayer();
					dealToDealer();
				}
			});
			hitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					if (deck.playerSum < 21) {
						dealToPlayer();
					} else {
						dailogBox("Can't hit again", "You Busted");
					}
				}
			});

			standButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					int dealersum = deck.dealerSum;
					if (dealersum < 17) {
						dealToDealer();
						dealersum = deck.dealerSum;
					} else if (dealersum >= 17) {

						if ( deck.dealerSum<21) {
							System.out.println("You wins");
							deck.dealerSum = 0;
							deck.playerSum = 0;
							dailogBox("You Wins ,dealer Busted!!!", "Winner Declared");

						} else if (deck.playerSum < deck.dealerSum) {
							System.out.println("Dealer wins");
							deck.dealerSum = 0;
							deck.playerSum = 0;
							dailogBox("Dealer Wins", "Winner Declared");
						} else {
							System.out.println("Draw");
							deck.dealerSum = 0;
							deck.playerSum = 0;
							dailogBox("Dealer Wins", "Winner Declared");
						}

					} else {
						try {
							throw new Exception("bad number passed");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			});
			resetButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					deck.dealerSum = 0;
					deck.playerSum = 0;
					imagepaneBottom.getChildren().clear();
					imagepaneTop.getChildren().clear();
					for (int i = 0; i < 2; i++) {
						dealToPlayer();
					}
					for (int i = 0; i < 2; i++) {
						dealToDealer();
					}
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dealToDealer() {
		Card c = deck.getNextCard();
		String filename1 = c.getName().toLowerCase();
		System.out.println(filename1);
		Image img = new Image("PlayingCards/" + filename1, 80, 80, true, true);
		ImageView iv1 = new ImageView(img);
		imagepaneTop.getChildren().add(iv1);
		deck.dealerSum += c.getValue();
		System.out.println("Dealer scores " + deck.dealerSum);
	}

	private void dealToPlayer() {
		Card c = deck.getNextCard();
		String filename1 = c.getName().toLowerCase();
		System.out.println(filename1);
		Image img = new Image("PlayingCards/" + filename1, 80, 80, true, true);
		ImageView iv = new ImageView(img);
		imagepaneBottom.getChildren().add(iv);
		deck.playerSum += c.getValue();
		System.out.println("Player scores " + deck.playerSum);
	}

	private void dailogBox(String message, String message1) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("BlackJack");
		alert.setHeaderText(message1);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);

	}
}
