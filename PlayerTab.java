package application;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Player tab of the application used to manage all options related to player.
 * @author Maciej Becmer
 *
 */
public class PlayerTab extends Tab {

	
	public PlayerTab(){
		GridPane management = new GridPane();
		management.setPadding(new Insets(20,5000,5000,10));
		management.setStyle("-fx-background-color: rgb(230,230,230);");
		management.setHgap(20);
		management.setVgap(15);
		
		//AddPlayerOption
		
		final Stage addPlayerStage = new Stage();
        addPlayerStage.initModality(Modality.APPLICATION_MODAL);
        
        GridPane addPlayerGrid = new GridPane();
		addPlayerGrid.setPadding(new Insets(20,5000,5000,10));
		addPlayerGrid.setStyle("-fx-background-color: rgb(230,230,230);");
		addPlayerGrid.setHgap(30);
		addPlayerGrid.setVgap(15);
		
		final TextField firstName = new TextField();
		firstName.setPromptText("First Name ");
		firstName.getText();
		firstName.setMinWidth(160);
		GridPane.setConstraints(firstName,1,0);
		addPlayerGrid.getChildren().add(firstName);
		
		final Text fname = new Text();
		fname.setText("FirstName : ");
		GridPane.setConstraints(fname,0,0);
		addPlayerGrid.getChildren().add(fname);
		
		final TextField middleName = new TextField();
		middleName.setPromptText("Middle Name ");
		middleName.getText();
		middleName.setMinWidth(160);
		GridPane.setConstraints(middleName,3,0);
		addPlayerGrid.getChildren().add(middleName);
		
		final Text mname = new Text();
		mname.setText("MiddleName : ");
		GridPane.setConstraints(mname,2,0);
		addPlayerGrid.getChildren().add(mname);
		
		final TextField lastName = new TextField();
		lastName.setPromptText("Last Name ");
		lastName.getText();
		lastName.setMinWidth(160);
		GridPane.setConstraints(lastName,1,1);
		addPlayerGrid.getChildren().add(lastName);
		
		final Text lname = new Text();
		lname.setText("LastName : ");
		GridPane.setConstraints(lname,0,1);
		addPlayerGrid.getChildren().add(lname);
		
		final TextField phoneText = new TextField();
		phoneText.setPromptText("Phone No : ");
		phoneText.getText();
		phoneText.setMinWidth(160);
		GridPane.setConstraints(phoneText,3,1);
		addPlayerGrid.getChildren().add(phoneText);
		
		final Text phone = new Text();
		phone.setText("Phone No : ");
		GridPane.setConstraints(phone,2,1);
		addPlayerGrid.getChildren().add(phone);
		
		final TextField emailField = new TextField();
		emailField.setPromptText("email ");
		emailField.getText();
		emailField.setMinWidth(160);
		GridPane.setConstraints(emailField,1,2);
		addPlayerGrid.getChildren().add(emailField);
		
		final Text email = new Text();
		email.setText("Email : ");
		GridPane.setConstraints(email,0,2);
		addPlayerGrid.getChildren().add(email);
		
		final TextField goals = new TextField();
		goals.setPromptText("goals ");
		goals.getText();
		goals.setMinWidth(160);
		GridPane.setConstraints(goals,3,2);
		addPlayerGrid.getChildren().add(goals);
		
		final Text goalsText = new Text();
		goalsText.setText("goals : ");
		GridPane.setConstraints(goalsText,2,2);
		addPlayerGrid.getChildren().add(goalsText);
		
		Button submit = new Button("Submit");
		submit.setMinWidth(70);
		GridPane.setConstraints(submit, 0, 10);
		addPlayerGrid.getChildren().add(submit);
		
		Button back = new Button("Back");
		back.setMinWidth(70);
		back.setTranslateX(120);
		GridPane.setConstraints(back, 3, 10);
		addPlayerGrid.getChildren().add(back);
		
		final Text isGoalieText = new Text();
		isGoalieText.setText("Is this player a goalie : ");
		GridPane.setConstraints(isGoalieText,0,3,2,2);
		addPlayerGrid.getChildren().add(isGoalieText);
		
		final ComboBox<String> isGoalieBox = new ComboBox<String>();
		isGoalieBox.setMinWidth(100);
		isGoalieBox.setTranslateX(-140);
		isGoalieBox.getItems().add("True");
		isGoalieBox.getItems().add("False");
		isGoalieBox.setValue("False");
		GridPane.setConstraints(isGoalieBox, 2, 3,2,2);
		addPlayerGrid.getChildren().add(isGoalieBox);
		
		final Text teamText = new Text();
		teamText.setText("Select a team ");
		GridPane.setConstraints(teamText,2,3,2,2);
		addPlayerGrid.getChildren().add(teamText);
		
		final ComboBox<String> teamBox = new ComboBox<String>();
		teamBox.setMinWidth(100);
		teamBox.setTranslateX(-140);
		teamBox.getItems().add("None");
		GridPane.setConstraints(teamBox,4,3,2,2);
		addPlayerGrid.getChildren().add(teamBox);
		
		final Text Warning = new Text();
		Warning.setText("You did not enter a correct value");
		Warning.setFill(Color.RED);
		Warning.setVisible(false);
		GridPane.setConstraints(Warning, 0, 5, 2, 1);
		addPlayerGrid.getChildren().add(Warning);
		
		Scene addPlayerScene = new Scene(addPlayerGrid,630,310);
        addPlayerStage.setScene(addPlayerScene);
        addPlayerStage.initModality(Modality.NONE);
        addPlayerStage.setResizable(false);
        
        back.setOnAction(e -> {
        	teamBox.getItems().clear();
        	addPlayerStage.hide();
        });
        
        submit.setOnAction(e -> {
        	boolean valueCheck = true;
        	String first = firstName.getText();
        	String middle = middleName.getText();
        	String last = lastName.getText();
        	String phoneNo = phoneText.getText();
        	String emailAdd = emailField.getText();
        	int goalsNo = 0;
        	try {
	        	String goalsString = goals.getText();
	        	goalsNo = Integer.parseInt(goalsString);
        	}
        	catch (java.lang.NumberFormatException err) {
        		Warning.setVisible(true);
        		valueCheck = false;
        	}
        	if (first.contentEquals("") || middle.contentEquals("") || last.contentEquals("") || phoneNo.contentEquals("") || emailAdd.contentEquals("")) {
        		valueCheck = false;
        	}
        	String isGoalieString = isGoalieBox.getValue();
        	boolean isGoalie = Boolean.parseBoolean(isGoalieString);
        	String teamSelect = teamBox.getValue();
        	int teamNo = 1;
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	Team newTeam = em.find(Team.class,teamNo);
        	boolean teamCheck = false;
        	while (newTeam != null) {
        		if (teamSelect.contentEquals(newTeam.getName())) {
        			teamCheck = true;
        			break;
        		}
        		teamNo ++;
        		newTeam = em.find(Team.class, teamNo);
        	}
        	if (!teamCheck) {
        		teamNo = -1;
        	}
        	if (valueCheck) {
	        	Name newName = new Name(first,middle,last);
	        	Player newPlayer = new Player(newName,phoneNo,emailAdd,goalsNo,isGoalie,teamNo);
	        	newPlayer.persist();
	        	teamBox.getItems().clear();
	        	addPlayerStage.hide();
        	}
        	
        });
		
		//Remove Player Options
        final Stage removePlayerStage = new Stage();
        removePlayerStage.initModality(Modality.APPLICATION_MODAL);
        
        GridPane removePlayerGrid = new GridPane();
		removePlayerGrid.setPadding(new Insets(20,5000,5000,10));
		removePlayerGrid.setStyle("-fx-background-color: rgb(230,230,230);");
		removePlayerGrid.setHgap(30);
		removePlayerGrid.setVgap(15);
		
		Button submitRemove = new Button("Submit");
		submitRemove.setMinWidth(70);
		GridPane.setConstraints(submitRemove, 0, 3);
		removePlayerGrid.getChildren().add(submitRemove);
		
		Button backRemove = new Button("Back");
		backRemove.setMinWidth(70);
		backRemove.setTranslateX(120);
		GridPane.setConstraints(backRemove, 8, 3);
		removePlayerGrid.getChildren().add(backRemove);
		
        final ComboBox<String> playerBox = new ComboBox<String>();
		playerBox.setMinWidth(100);
		playerBox.setMinWidth(400);
		GridPane.setConstraints(playerBox,1,0,2,1);
		removePlayerGrid.getChildren().add(playerBox);
		
		final Text playerBoxText = new Text();
		playerBoxText.setText("Select player to remove");
		GridPane.setConstraints(playerBoxText,0,0);
		removePlayerGrid.getChildren().add(playerBoxText);
		
		final Text removeText = new Text();
		removeText.setText("Select team to assign to");
		GridPane.setConstraints(removeText,0,1,2,1);
		removePlayerGrid.getChildren().add(removeText);
		
        final ComboBox<String> teamRemoveBox = new ComboBox<String>();
		teamRemoveBox.setMinWidth(400);
		GridPane.setConstraints(teamRemoveBox,1,1,13,1);
		removePlayerGrid.getChildren().add(teamRemoveBox);
		
		Scene removePlayerScene = new Scene(removePlayerGrid,600,160);
        removePlayerStage.setScene(removePlayerScene);
        removePlayerStage.initModality(Modality.NONE);
        removePlayerStage.setResizable(false);
        
        backRemove.setOnAction(e-> {
        	playerBox.getItems().clear();
        	removePlayerStage.hide();
    	});
        
        submitRemove.setOnAction(e -> {
        	
        	String playerInfo = playerBox.getValue();
        	int j = 1;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	Player newPlayer = em.find(Player.class,j);
			while (newPlayer != null) {
				Team newTeam = em.find(Team.class, newPlayer.getTeamID());
				String entity = (newPlayer.getName().getFname() + " " + newPlayer.getName().getMname() + " " + newPlayer.getName().getLname() + " " + newPlayer.getEmail() + " " + newPlayer.getPhone() + " " + newPlayer.getGoals() + " " + newTeam.getName() + " " + newTeam.getJersey());
				if (entity.contentEquals(playerInfo)) {
					break;
				}
				j ++;
				newPlayer = em.find(Player.class, j);
			}
			String teamSelect = teamRemoveBox.getValue();
        	int teamNo = 1;
        	boolean teamCheck = false;
        	Team newTeam = em.find(Team.class,teamNo);
        	while (newTeam != null) {
        		if (teamSelect.contentEquals(newTeam.getName())) {
        			teamCheck = true;
        			break;
        		}
        		teamNo ++;
        		newTeam = em.find(Team.class, teamNo);
        	}
        	if (!teamCheck) {
        		teamNo = -1;
        	}
			newPlayer.transferPlayer(teamNo);
        	
        	teamRemoveBox.getItems().clear();
        	playerBox.getItems().clear();
        	removePlayerStage.hide();
        });
        
        
        //End of remove player options
       
        //Edit Player options
        final Stage editPlayerStage = new Stage();
        editPlayerStage.initModality(Modality.APPLICATION_MODAL);
        
        GridPane editPlayerGrid = new GridPane();
		editPlayerGrid.setPadding(new Insets(20,5000,5000,10));
		editPlayerGrid.setStyle("-fx-background-color: rgb(230,230,230);");
		editPlayerGrid.setHgap(20);
		editPlayerGrid.setVgap(15);
		
		Button submitEdit = new Button("Submit");
		submitEdit.setMinWidth(70);
		GridPane.setConstraints(submitEdit, 0, 8);
		editPlayerGrid.getChildren().add(submitEdit);
		
		Button backEdit = new Button("Back");
		backEdit.setMinWidth(70);
		backEdit.setTranslateX(90);
		GridPane.setConstraints(backEdit, 3, 8);
		editPlayerGrid.getChildren().add(backEdit);
		
		final ComboBox<String> editBox = new ComboBox<String>();
		editBox.setMinWidth(460);
		editBox.setMaxWidth(460);
		GridPane.setConstraints(editBox,1,0,5,1);
		editPlayerGrid.getChildren().add(editBox);
		
		final Text editBoxText = new Text();
		editBoxText.setText("Select player to edit");
		GridPane.setConstraints(editBoxText,0,0);
		editPlayerGrid.getChildren().add(editBoxText);
		
		final TextField firstNameEdit = new TextField();
		firstNameEdit.setPromptText("First Name ");
		firstNameEdit.getText();
		firstNameEdit.setMinWidth(160);
		GridPane.setConstraints(firstNameEdit,1,1);
		editPlayerGrid.getChildren().add(firstNameEdit);
		
		final Text fnameEdit = new Text();
		fnameEdit.setText("FirstName : ");
		GridPane.setConstraints(fnameEdit,0,1);
		editPlayerGrid.getChildren().add(fnameEdit);
		
		final TextField middleNameEdit = new TextField();
		middleNameEdit.setPromptText("Middle Name ");
		middleNameEdit.getText();
		middleNameEdit.setMinWidth(160);
		GridPane.setConstraints(middleNameEdit,3,1);
		editPlayerGrid.getChildren().add(middleNameEdit);
		
		final Text mnameEdit = new Text();
		mnameEdit.setText("MiddleName : ");
		GridPane.setConstraints(mnameEdit,2,1);
		editPlayerGrid.getChildren().add(mnameEdit);
		
		final TextField lastNameEdit = new TextField();
		lastNameEdit.setPromptText("Last Name ");
		lastNameEdit.getText();
		lastNameEdit.setMinWidth(160);
		GridPane.setConstraints(lastNameEdit,1,2);
		editPlayerGrid.getChildren().add(lastNameEdit);
		
		final Text lnameEdit = new Text();
		lnameEdit.setText("LastName : ");
		GridPane.setConstraints(lnameEdit,0,2);
		editPlayerGrid.getChildren().add(lnameEdit);
		
		final TextField phoneTextEdit = new TextField();
		phoneTextEdit.setPromptText("Phone No : ");
		phoneTextEdit.getText();
		phoneTextEdit.setMinWidth(160);
		GridPane.setConstraints(phoneTextEdit,3,2);
		editPlayerGrid.getChildren().add(phoneTextEdit);
		
		final Text phoneEdit = new Text();
		phoneEdit.setText("Phone No : ");
		GridPane.setConstraints(phoneEdit,2,2);
		editPlayerGrid.getChildren().add(phoneEdit);
		
		final TextField emailFieldEdit = new TextField();
		emailFieldEdit.setPromptText("email ");
		emailFieldEdit.getText();
		emailFieldEdit.setMinWidth(160);
		GridPane.setConstraints(emailFieldEdit,1,3);
		editPlayerGrid.getChildren().add(emailFieldEdit);
		
		final Text emailEdit = new Text();
		emailEdit.setText("Email : ");
		GridPane.setConstraints(emailEdit,0,3);
		editPlayerGrid.getChildren().add(emailEdit);
		
		final TextField goalsEdit = new TextField();
		goalsEdit.setPromptText("goals ");
		goalsEdit.getText();
		goalsEdit.setMinWidth(160);
		GridPane.setConstraints(goalsEdit,3,3);
		editPlayerGrid.getChildren().add(goalsEdit);
		
		final Text goalsTextEdit = new Text();
		goalsTextEdit.setText("goals : ");
		GridPane.setConstraints(goalsTextEdit,2,3);
		editPlayerGrid.getChildren().add(goalsTextEdit);
		
		final Text isGoalieTextEdit = new Text();
		isGoalieTextEdit.setText("Is this player a goalie : ");
		GridPane.setConstraints(isGoalieTextEdit,0,4,2,2);
		editPlayerGrid.getChildren().add(isGoalieTextEdit);
		
		final ComboBox<String> isGoalieBoxEdit = new ComboBox<String>();
		isGoalieBoxEdit.setMinWidth(100);
		isGoalieBoxEdit.setTranslateX(-140);
		isGoalieBoxEdit.getItems().add("True");
		isGoalieBoxEdit.getItems().add("False");
		isGoalieBoxEdit.setValue("False");
		GridPane.setConstraints(isGoalieBoxEdit, 2,4,2,2);
		editPlayerGrid.getChildren().add(isGoalieBoxEdit);
		
		final Text teamTextEdit = new Text();
		teamTextEdit.setText("Select a team ");
		GridPane.setConstraints(teamTextEdit,2,4,2,2);
		editPlayerGrid.getChildren().add(teamTextEdit);
		
		final ComboBox<String> teamBoxEdit = new ComboBox<String>();
		teamBoxEdit.setMinWidth(100);
		teamBoxEdit.setTranslateX(-140);
		GridPane.setConstraints(teamBoxEdit,4,4,2,2);
		editPlayerGrid.getChildren().add(teamBoxEdit);
		
		final Text editWarning = new Text();
		editWarning.setText("You did not enter a correct value");
		editWarning.setFill(Color.RED);
		editWarning.setVisible(false);
		GridPane.setConstraints(editWarning, 0, 6, 2, 1);
		editPlayerGrid.getChildren().add(editWarning);
		
		Scene editPlayerScene = new Scene(editPlayerGrid,630,310);
        editPlayerStage.setScene(editPlayerScene);
        editPlayerStage.initModality(Modality.NONE);
        editPlayerStage.setResizable(false);
        
        backEdit.setOnAction(e-> {
        	
        		
        	editBox.getItems().clear();
        	teamBoxEdit.getItems().clear();
        	editPlayerStage.hide();
        });
        
        submitEdit.setOnAction(e -> {
        	boolean valueCheck = true;
        	String playerInfo = editBox.getValue();
        	String first = firstNameEdit.getText();
        	String middle = middleNameEdit.getText();
        	String last = lastNameEdit.getText();
        	String phoneNo = phoneTextEdit.getText();
        	String emailAdd = emailFieldEdit.getText();
        	int goalsNo = 0;
        	try {
	        	String goalsString = goalsEdit.getText();
	        	goalsNo = Integer.parseInt(goalsString);
        	}
        	catch (java.lang.NumberFormatException err) {
        		editWarning.setVisible(true);
        		valueCheck = false;
        	}
        	if (first.contentEquals("") || middle.contentEquals("") || last.contentEquals("") || phoneNo.contentEquals("") || emailAdd.contentEquals("")) {
        		valueCheck = false;
        	}
        	String isGoalieString = isGoalieBoxEdit.getValue();
        	boolean isGoalie = Boolean.parseBoolean(isGoalieString);
        	int j = 1;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	Player newPlayer = em.find(Player.class,j);
			while (newPlayer != null) {
				Team newTeam = em.find(Team.class, newPlayer.getTeamID());
				String entity = (newPlayer.getName().getFname() + " " + newPlayer.getName().getMname() + " " + newPlayer.getName().getLname() + " " + newPlayer.getEmail() + " " + newPlayer.getPhone() + " " + newPlayer.getGoals() + " " + newTeam.getName() + " " + newTeam.getJersey());
				if (entity.contentEquals(playerInfo)) {
					break;
				}
				j ++;
				newPlayer = em.find(Player.class, j);
			}
			String teamSelect = teamBoxEdit.getValue();
			boolean teamCheck = false;
			int teamNo = 1;
        	Team newTeam = em.find(Team.class,teamNo);
        	while (newTeam != null) {
        		if (teamSelect.contentEquals(newTeam.getName())) {
        			teamCheck = true;
        			break;
        		}
        		teamNo ++;
        		newTeam = em.find(Team.class, teamNo);
        	}
        	if (!teamCheck) {
        		teamNo = -1;
        	}
        	if (valueCheck) {
            	Name newName = new Name(first,middle,last);
            	newPlayer.editPlayer(newName, phoneNo, emailAdd, goalsNo, isGoalie, teamNo);
	        	editBox.getItems().clear();
	        	teamBoxEdit.getItems().clear();
	        	editPlayerStage.hide();
        	}
        });
        
        //End of Edit options
        
        //Update options
        
        final Stage updatePlayerStage = new Stage();
        updatePlayerStage.initModality(Modality.APPLICATION_MODAL);
        
        GridPane updatePlayerGrid = new GridPane();
        updatePlayerGrid.setPadding(new Insets(20,5000,5000,10));
        updatePlayerGrid.setStyle("-fx-background-color: rgb(230,230,230);");
        updatePlayerGrid.setHgap(20);
        updatePlayerGrid.setVgap(15);
		
		Button submitUpdate = new Button("Submit");
		submitUpdate.setMinWidth(70);
		GridPane.setConstraints(submitUpdate, 0, 3);
		updatePlayerGrid.getChildren().add(submitUpdate);
		
		Button backUpdate = new Button("Back");
		backUpdate.setMinWidth(70);
		backUpdate.setTranslateX(90);
		GridPane.setConstraints(backUpdate, 11, 3);
		updatePlayerGrid.getChildren().add(backUpdate);
		
		final ComboBox<String> updateBox = new ComboBox<String>();
		updateBox.setMinWidth(440);
		updateBox.setMaxWidth(440);
		GridPane.setConstraints(updateBox,1,0,5,1);
		updatePlayerGrid.getChildren().add(updateBox);
		
		final Text updateBoxText = new Text();
		updateBoxText.setText("Select player to update");
		GridPane.setConstraints(updateBoxText,0,0);
		updatePlayerGrid.getChildren().add(updateBoxText);
		
		final TextField goalsUpdate = new TextField();
		goalsUpdate.setPromptText("goals ");
		goalsUpdate.getText();
		goalsEdit.setMinWidth(160);
		GridPane.setConstraints(goalsUpdate,1,1);
		updatePlayerGrid.getChildren().add(goalsUpdate);
		
		final Text goalsTextUpdate = new Text();
		goalsTextUpdate.setText("goals : ");
		GridPane.setConstraints(goalsTextUpdate,0,1);
		updatePlayerGrid.getChildren().add(goalsTextUpdate);
		
		final Text goalsWarning = new Text();
		goalsWarning.setText("You did not enter a valid number");
		goalsWarning.setFill(Color.RED);
		goalsWarning.setVisible(false);
		GridPane.setConstraints(goalsWarning, 2, 1, 2, 1);
		updatePlayerGrid.getChildren().add(goalsWarning);
		
		Scene updatePlayerScene = new Scene(updatePlayerGrid,630,160);
        updatePlayerStage.setScene(updatePlayerScene);
        updatePlayerStage.initModality(Modality.NONE);
        updatePlayerStage.setResizable(false);
        
        backUpdate.setOnAction(e-> {
        	updateBox.getItems().clear();
        	updatePlayerStage.hide();
        });
       
        submitUpdate.setOnAction(e -> {
        	String playerInfo = updateBox.getValue();
        	int j = 1;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	Player newPlayer = em.find(Player.class,j);
			while (newPlayer != null) {
				Team newTeam = em.find(Team.class, newPlayer.getTeamID());
				String entity = (newPlayer.getName().getFname() + " " + newPlayer.getName().getMname() + " " + newPlayer.getName().getLname() + " " + newPlayer.getEmail() + " " + newPlayer.getPhone() + " " + newPlayer.getGoals() + " " + newTeam.getName() + " " + newTeam.getJersey());
				if (entity.contentEquals(playerInfo)) {
					break;
				}
				j ++;
				newPlayer = em.find(Player.class, j);
			}
			int goalsNo = 0;
			boolean goalsCheck = true;
			try {
				String goalsString = goalsUpdate.getText();
				goalsNo = Integer.parseInt(goalsString);
			}
			catch (java.lang.NumberFormatException err){
				goalsCheck = false;
				goalsWarning.setVisible(true);
			}
			if (goalsCheck) {
	        	newPlayer.updateGoals(goalsNo);
	        	updateBox.getItems().clear();
	        	updatePlayerStage.hide();
			}
        });
        
        //End of update options     
        
		final Text addLabel = new Text();
		addLabel.setText("Add a new Player to an existing team");
		GridPane.setConstraints(addLabel, 0, 0);
		management.getChildren().add(addLabel);
		
		Button addPlayer = new Button("Add Player");
		addPlayer.setMinSize(300, 80);
		GridPane.setConstraints(addPlayer,0,1);
		management.getChildren().add(addPlayer);
		
		addPlayer.setOnAction(e -> {
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
    		teamBox.getItems().clear();
    		teamBox.setValue("None");
        	List<Team> teamList = em.createQuery("from Team").getResultList();
        	for (int p = 0; p < teamList.size(); p ++) {
        		teamBox.getItems().add(teamList.get(p).getName());
        	}
			
			addPlayerStage.show();
		});
		
		final Text removeLabel = new Text();
		removeLabel.setText("Transfer player from a team");
		GridPane.setConstraints(removeLabel, 1, 0);
		management.getChildren().add(removeLabel);
		
		Button removePlayer = new Button("Transfer Player");
		removePlayer.setMinSize(300, 80);
		GridPane.setConstraints(removePlayer,1,1);
		management.getChildren().add(removePlayer);
		
		removePlayer.setOnAction(e -> {
			teamRemoveBox.getItems().clear();
			int j = 1;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	Player newPlayer = em.find(Player.class,j);
        	Team newTeam = em.find(Team.class,newPlayer.getTeamID());
        	String string = (newPlayer.getName().getFname() + " " + newPlayer.getName().getMname() + " " + newPlayer.getName().getLname() + " " + newPlayer.getEmail() + " " + newPlayer.getPhone() + " " + newPlayer.getGoals() + " " + newTeam.getName() + " " + newTeam.getJersey());
        	playerBox.setValue(string);
			while (newPlayer != null) {
				newTeam = em.find(Team.class, newPlayer.getTeamID());
				String entity = (newPlayer.getName().getFname() + " " + newPlayer.getName().getMname() + " " + newPlayer.getName().getLname() + " " + newPlayer.getEmail() + " " + newPlayer.getPhone() + " " + newPlayer.getGoals() + " " + newTeam.getName() + " " + newTeam.getJersey());
				playerBox.getItems().add(entity);
				j ++;
				newPlayer = em.find(Player.class, j);
			}
        	teamRemoveBox.setValue("None");
        	List<Team> teamList = em.createQuery("from Team").getResultList();
        	for (int p = 0; p < teamList.size(); p ++) {
        		teamRemoveBox.getItems().add(teamList.get(p).getName());
        	}
			
			removePlayerStage.show();
		});
		
		final Text editPlayerText = new Text();
		editPlayerText.setText("Edit an existing player");
		GridPane.setConstraints(editPlayerText, 0, 2);
		management.getChildren().add(editPlayerText);
		
		Button editPlayer = new Button("Edit Player");
		editPlayer.setMinSize(300, 80);
		GridPane.setConstraints(editPlayer,0,3);
		management.getChildren().add(editPlayer);
		
		editPlayer.setOnAction(e -> {
			editWarning.setVisible(false);
			int j = 1;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	Player newPlayer = em.find(Player.class,j);
        	Team newTeam = em.find(Team.class,newPlayer.getTeamID());
        	String string = (newPlayer.getName().getFname() + " " + newPlayer.getName().getMname() + " " + newPlayer.getName().getLname() + " " + newPlayer.getEmail() + " " + newPlayer.getPhone() + " " + newPlayer.getGoals() + " " + newTeam.getName() + " " + newTeam.getJersey());
        	editBox.setValue(string);
			while (newPlayer != null) {
				newTeam = em.find(Team.class, newPlayer.getTeamID());
				String entity = (newPlayer.getName().getFname() + " " + newPlayer.getName().getMname() + " " + newPlayer.getName().getLname() + " " + newPlayer.getEmail() + " " + newPlayer.getPhone() + " " + newPlayer.getGoals() + " " + newTeam.getName() + " " + newTeam.getJersey());
				editBox.getItems().add(entity);
				j ++;
				newPlayer = em.find(Player.class, j);
			}
			teamBoxEdit.getItems().clear();
			teamBoxEdit.setValue("None");
        	List<Team> teamList = em.createQuery("from Team").getResultList();
        	for (int p = 0; p < teamList.size(); p ++) {
        		teamBoxEdit.getItems().add(teamList.get(p).getName());
        	}
			
			editPlayerStage.show();
		});
		
		final Text updateGoalsText = new Text();
		updateGoalsText.setText("Update the goals of a player");
		GridPane.setConstraints(updateGoalsText, 1, 2);
		management.getChildren().add(updateGoalsText);
		
		Button updateGoals = new Button("update Goals");
		updateGoals.setMinSize(300, 80);
		GridPane.setConstraints(updateGoals,1,3);
		management.getChildren().add(updateGoals);
		updateGoals.setOnAction(e -> {
			goalsWarning.setVisible(false);
			int j = 1;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	Player newPlayer = em.find(Player.class,j);
        	Team newTeam = em.find(Team.class,newPlayer.getTeamID());
        	String string = (newPlayer.getName().getFname() + " " + newPlayer.getName().getMname() + " " + newPlayer.getName().getLname() + " " + newPlayer.getEmail() + " " + newPlayer.getPhone() + " " + newPlayer.getGoals() + " " + newTeam.getName() + " " + newTeam.getJersey());
        	updateBox.setValue(string);
			while (newPlayer != null) {
				newTeam = em.find(Team.class, newPlayer.getTeamID());
				String entity = (newPlayer.getName().getFname() + " " + newPlayer.getName().getMname() + " " + newPlayer.getName().getLname() + " " + newPlayer.getEmail() + " " + newPlayer.getPhone() + " " + newPlayer.getGoals() + " " + newTeam.getName() + " " + newTeam.getJersey());
				updateBox.getItems().add(entity);
				j ++;
				newPlayer = em.find(Player.class, j);
			}
			
			updatePlayerStage.show();
		});
		
		this.setContent(management);
		this.setText("Player Management");
		
	}
}