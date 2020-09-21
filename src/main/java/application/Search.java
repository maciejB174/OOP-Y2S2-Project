package application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Search tab is used to search for players using their name.
 * @author Maciej Becmer
 *
 */
public class Search extends Tab {

	public Search(){
		GridPane management = new GridPane();
		management.setPadding(new Insets(20,5000,5000,10));
		management.setStyle("-fx-background-color: rgb(230,230,230);");
		management.setHgap(20);
		management.setVgap(15);
		
		final TextField firstName = new TextField();
		firstName.setPromptText("First Name ");
		firstName.getText();
		GridPane.setConstraints(firstName,1,0);
		management.getChildren().add(firstName);
		
		final Text fname = new Text();
		fname.setText("FirstName : ");
		GridPane.setConstraints(fname,0,0);
		management.getChildren().add(fname);
		
		final TextField middleName = new TextField();
		middleName.setPromptText("Middle Name ");
		middleName.getText();
		middleName.setMinWidth(160);
        GridPane.setConstraints(middleName,1,1);
        management.getChildren().add(middleName);
    	        
		final Text mname = new Text();
		mname.setText("middleName : ");
		GridPane.setConstraints(mname,0,1);
		management.getChildren().add(mname);
		
		final TextField lastName = new TextField();
		lastName.setPromptText("Last Name ");
		lastName.getText();
		GridPane.setConstraints(lastName,1,2);
		management.getChildren().add(lastName);
		
		final Text lname = new Text();
		lname.setText("LastName : ");
		GridPane.setConstraints(lname,0,2);
		management.getChildren().add(lname);
		
		final TextArea textArea = new TextArea();
		textArea.setStyle("-fx-control-inner-background: white;");
		textArea.setPromptText("Found Player");
		textArea.setMinHeight(50);
		textArea.setMinWidth(620);
		GridPane.setConstraints(textArea,0,5,4,1);
		management.getChildren().add(textArea);
		
		Button search = new Button("Search");
		GridPane.setConstraints(search,0,4);
		management.getChildren().add(search);
		
		search.setOnAction(e -> {
			String firName = firstName.getText();
			String midName = middleName.getText();
			String lasName = lastName.getText();
			firstName.setText("");
			middleName.setText("");
			lastName.setText("");
			int j = 1;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	Player newPlayer = em.find(Player.class, j);
        	while (newPlayer != null) {
        		if (firName.contentEquals(newPlayer.getName().getFname()) && midName.contentEquals(newPlayer.getName().getMname()) && lasName.contentEquals(newPlayer.getName().getLname())) {
        			textArea.clear();
        			Team newTeam = em.find(Team.class, newPlayer.getTeamID());
        			int m = 1;
        			Manager newManager = em.find(Manager.class, m);
        			boolean teamCheck = false;
        			while (newManager != null) {
        				if (newManager.getTeamID() == newPlayer.getTeamID()) {
        					teamCheck = true;
        					break;
        				}
        				m ++;
        				newManager = em.find(Manager.class, m);
        			}
        			if (teamCheck) {
        				textArea.appendText(newPlayer.getName().getFname() + " " + newPlayer.getName().getMname() + " " + newPlayer.getName().getLname() + " " + newPlayer.getPhone() + " " + newPlayer.getEmail() + " " + newPlayer.getGoals() + " Team : " + newTeam.getName() + " Manager: " + newManager.getName().getFname() + " " + newManager.getName().getMname() + " " + newManager.getName().getLname() + " " + newManager.getEmail() + " " + newManager.getPhone());
        			}
        			else {
        				textArea.appendText(newPlayer.getName().getFname() + " " + newPlayer.getName().getMname() + " " + newPlayer.getName().getLname() + " " + newPlayer.getPhone() + " " + newPlayer.getEmail() + " " + newPlayer.getGoals() + " This player does not have a team");
        			}
        			break;
        		}
        		else {
        			textArea.clear();
        			textArea.appendText("Could not find given player");
        		}
        		j ++;
        		newPlayer = em.find(Player.class, j);
        	}
		});
		management.setGridLinesVisible(false);
		this.setContent(management);
		this.setText("Search Player");
	}
}