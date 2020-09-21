package application;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Intro tab used to greet the user
 * @author Maciej Becmer
 *
 */
public class IntroTab extends Tab {

	public IntroTab(){
		GridPane intro = new GridPane();
		
		intro.setPadding(new Insets(0,5000,5000,10));
		intro.setStyle("-fx-background-color: rgb(230,230,230);");
		
		intro.setHgap(10);
		intro.setVgap(10);
		
		final Text introText = new Text();
		introText.setText("\t     Welcome to the application \n\t Here you can manage the league \n\t by adding and removing players, \n\t\t teams and managers \n\t  Click the tabs above to begin");
		GridPane.setConstraints(introText,0,6);
		introText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 26));
		intro.getChildren().add(introText);

		this.setContent(intro);
		this.setText("Welcome");
		
	}
}