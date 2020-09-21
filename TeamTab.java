package application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Team tab used to control all options of the team
 * @author HP
 *
 */
public class TeamTab extends Tab {
	@SuppressWarnings("null")
	public TeamTab(){
		GridPane management = new GridPane();
		management.setPadding(new Insets(20,5000,5000,10));
		management.setStyle("-fx-background-color: rgb(230,230,230);");
		management.setHgap(10);
		management.setVgap(15);
		
		final TextArea textArea = new TextArea();
		textArea.setStyle("-fx-control-inner-background: white;");
		textArea.setPromptText("Display");
		textArea.setMinHeight(160);
		textArea.setMinWidth(620);
		GridPane.setConstraints(textArea,0,2,4,1);
		management.getChildren().add(textArea);
		
		//AddTeamOption

		final Stage addTeamStage = new Stage();
        addTeamStage.initModality(Modality.APPLICATION_MODAL);
        
        GridPane addTeamGrid = new GridPane();
		addTeamGrid.setPadding(new Insets(20,5000,5000,10));
		addTeamGrid.setStyle("-fx-background-color: rgb(230,230,230);");
		addTeamGrid.setHgap(30);
		addTeamGrid.setVgap(15);
		
		final TextField name = new TextField();
		name.setPromptText("Team Name ");
		name.getText();
		name.setMinWidth(160);
		GridPane.setConstraints(name,1,0);
		addTeamGrid.getChildren().add(name);
		
		final Text Tname = new Text();
		Tname.setText("Team Name : ");
		GridPane.setConstraints(Tname,0,0);
		addTeamGrid.getChildren().add(Tname);
		
		final TextField jersey = new TextField();
		jersey.setPromptText("Jersey");
		jersey.getText();
		jersey.setMinWidth(160);
		GridPane.setConstraints(jersey,3,0);
		addTeamGrid.getChildren().add(jersey);
		
		final Text Jtext = new Text();
		Jtext.setText("Jersey : ");
		GridPane.setConstraints(Jtext,2,0);
		addTeamGrid.getChildren().add(Jtext);
		
		Button submit = new Button("Submit");
		submit.setMinWidth(70);
		GridPane.setConstraints(submit, 0, 2);
		addTeamGrid.getChildren().add(submit);
		
		Button back = new Button("Back");
		back.setMinWidth(70);
		back.setTranslateX(100);
		GridPane.setConstraints(back, 3, 2);
		addTeamGrid.getChildren().add(back);
		
		Scene addTeamScene = new Scene(addTeamGrid,580,110);
        addTeamStage.setScene(addTeamScene);
        addTeamStage.initModality(Modality.NONE);
        addTeamStage.setResizable(false);
		
		back.setOnAction(e -> {
        	addTeamStage.hide();
        });
		
		submit.setOnAction( e -> {
			int j = 1;
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
			String teamName = name.getText();
        	String teamJersey = jersey.getText();
        	Team newTeam = em.find(Team.class, j);
        	while (newTeam != null) {
        		j ++;
        		newTeam = em.find(Team.class,j);
        	}
        	Team team = new Team(j, teamName, teamJersey);
        	team.persist();

			addTeamStage.hide();
		});
		
		//End of add team options
		
		//Remove team options
        final Stage removeTeamStage = new Stage();
        removeTeamStage.initModality(Modality.APPLICATION_MODAL);
        
        GridPane removeTeamGrid = new GridPane();
		removeTeamGrid.setPadding(new Insets(20,5000,5000,10));
		removeTeamGrid.setStyle("-fx-background-color: rgb(230,230,230);");
		removeTeamGrid.setHgap(30);
		removeTeamGrid.setVgap(15);
		
		Button submitRemove = new Button("Submit");
		submitRemove.setMinWidth(70);
		GridPane.setConstraints(submitRemove, 0, 3);
		removeTeamGrid.getChildren().add(submitRemove);
		
		Button backRemove = new Button("Back");
		backRemove.setMinWidth(70);
		backRemove.setTranslateX(120);
		GridPane.setConstraints(backRemove, 8, 3);
		removeTeamGrid.getChildren().add(backRemove);
		
        final ComboBox<String> teamBox = new ComboBox<String>();
		teamBox.setMinWidth(100);
		teamBox.setMinWidth(400);
		GridPane.setConstraints(teamBox,1,0,2,1);
		removeTeamGrid.getChildren().add(teamBox);
		
		final Text teamBoxText = new Text();
		teamBoxText.setText("Select team to remove");
		GridPane.setConstraints(teamBoxText,0,0);
		removeTeamGrid.getChildren().add(teamBoxText);
		
		Scene removeTeamScene = new Scene(removeTeamGrid,590,125);
        removeTeamStage.setScene(removeTeamScene);
        removeTeamStage.initModality(Modality.NONE);
        removeTeamStage.setResizable(false);
        
        backRemove.setOnAction(e-> {
        	teamBox.getItems().clear();
        	removeTeamStage.hide();
    	});
        
        submitRemove.setOnAction(e -> {
        	String teamSelect = teamBox.getValue();
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	int teamNo = 1;
        	Team newTeam = em.find(Team.class,teamNo);
        	while (newTeam != null) {
        		if (teamSelect.contentEquals(newTeam.getName())) {
        			break;
        		}
        		teamNo ++;
        		newTeam = em.find(Team.class, teamNo);
        	}
        	
        	newTeam.remove();
        	
        	teamBox.getItems().clear();
        	removeTeamStage.hide();
        });
		
		//End of remove options
        
        //Details options
        final Stage detailsStage = new Stage();
        detailsStage.initModality(Modality.APPLICATION_MODAL);
        
        GridPane detailsGrid = new GridPane();
        detailsGrid.setPadding(new Insets(20,5000,5000,10));
        detailsGrid.setStyle("-fx-background-color: rgb(230,230,230);");
        detailsGrid.setHgap(30);
        detailsGrid.setVgap(15);
		
		Button submitDetails = new Button("Submit");
		submitDetails.setMinWidth(70);
		GridPane.setConstraints(submitDetails, 0, 3);
		detailsGrid.getChildren().add(submitDetails);
		
		Button backDetails = new Button("Back");
		backDetails.setMinWidth(70);
		backDetails.setTranslateX(120);
		GridPane.setConstraints(backDetails, 8, 3);
		detailsGrid.getChildren().add(backDetails);
		
        final ComboBox<String> teamDetailsBox = new ComboBox<String>();
		teamDetailsBox.setMinWidth(100);
		teamDetailsBox.setMinWidth(400);
		GridPane.setConstraints(teamDetailsBox,1,0,2,1);
		detailsGrid.getChildren().add(teamDetailsBox);
		
		final Text detailsText = new Text();
		detailsText.setText("Select team to display");
		GridPane.setConstraints(detailsText,0,0);
		detailsGrid.getChildren().add(detailsText);
		
		Scene detailsScene = new Scene(detailsGrid,590,125);
        detailsStage.setScene(detailsScene);
        detailsStage.initModality(Modality.NONE);
        detailsStage.setResizable(false);
        
        backDetails.setOnAction(e-> {
        	teamDetailsBox.getItems().clear();
        	detailsStage.hide();
    	});
        
        submitDetails.setOnAction(e -> {
        	textArea.clear();
        	String teamName = teamDetailsBox.getValue();
        	int j = 1;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	List<Team> teamList = em.createQuery("from Team").getResultList();
        	
        	int i = 0;
        	for (i = 0; i < teamList.size(); i ++) {
        		if (teamName.contentEquals(teamList.get(i).getName())) {
        			break;
        		}
        	}
        	int p = 1;
        	Player newPlayer = em.find(Player.class, p);
        	ArrayList<Player> playerList = new ArrayList<Player>();
        	while (newPlayer != null) {
        		if (newPlayer.getTeamID() == i + 1) {
        			playerList.add(newPlayer);
        		}
        		p ++;
        		newPlayer = em.find(Player.class, p);
        	}
        	for (int k = 0; k < playerList.size(); k ++) {
        		String display = (playerList.get(k).getName().getFname() + " " + playerList.get(k).getName().getMname() + " " + playerList.get(k).getName().getLname() + " " + playerList.get(k).getPhone() + " " + playerList.get(k).getEmail() + " " + playerList.get(k).getGoals() + "\n");
        		textArea.appendText(display);
        	}
        	
        	teamDetailsBox.getItems().clear();
        	detailsStage.hide();
        });
        
		
		
		final Text addLabel = new Text();
		addLabel.setText("Add a new team");
		GridPane.setConstraints(addLabel, 0, 0);
		management.getChildren().add(addLabel);
		
		Button addTeam = new Button("Add Team");
		addTeam.setMinSize(120, 30);
		GridPane.setConstraints(addTeam,1,0);
		management.getChildren().add(addTeam);
		
		addTeam.setOnAction(e -> {
			
			addTeamStage.show();
		});
		
		final Text removeLabel = new Text();
		removeLabel.setText("Remove an existing team");
		GridPane.setConstraints(removeLabel, 2, 0);
		management.getChildren().add(removeLabel);
		
		Button removeTeam = new Button("Remove Team");
		removeTeam.setMinSize(140, 30);
		GridPane.setConstraints(removeTeam,3,0);
		management.getChildren().add(removeTeam);
		
		removeTeam.setOnAction(e -> {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	List<Team> teamList = em.createQuery("from Team").getResultList();
        	teamBox.setValue("None");
        	for (int j = 1; j < teamList.size(); j ++) {
        		teamBox.getItems().add(teamList.get(j).getName());
        	}
        	removeTeamStage.show();
        	
		});
		
		final Text listTeams = new Text();
		listTeams.setText("List teams in the league");
		GridPane.setConstraints(listTeams, 0, 1);
		management.getChildren().add(listTeams);
		
		Button listButton = new Button("List Teams");
		listButton.setMinSize(120, 30);
		GridPane.setConstraints(listButton,1,1);
		management.getChildren().add(listButton);
		
		listButton.setOnAction(e -> {
			textArea.clear();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	List<Team> teamList = em.createQuery("from Team").getResultList();
        	for (int j = 1; j < teamList.size(); j ++) {
        		String content = (teamList.get(j).getName() + " " + teamList.get(j).getJersey() + "\n");
        		textArea.appendText(content);
        	}
			
		});
		
		final Text teamDetails = new Text();
		teamDetails.setText("Display Team details");
		GridPane.setConstraints(teamDetails, 2, 1);
		management.getChildren().add(teamDetails);
		
		Button detailsButton = new Button("Team Details");
		detailsButton.setMinSize(140, 30);
		GridPane.setConstraints(detailsButton,3,1);
		management.getChildren().add(detailsButton);
		
		detailsButton.setOnAction(e -> {
			teamDetailsBox.getItems().clear();
			teamDetailsBox.setValue("None");
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	List<Team> teamList = em.createQuery("from Team").getResultList();
        	for (int j = 0; j < teamList.size(); j ++) {
        		teamDetailsBox.getItems().add(teamList.get(j).getName());
        	}
        	
			detailsStage.show();
		});

		this.setContent(management);
		this.setText("Team Management");
	}
}