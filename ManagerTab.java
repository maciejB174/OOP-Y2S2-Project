package application;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Manager tab is used to manage all options regarding the manager.
 * @author Maciej Becmer
 *
 */
public class ManagerTab extends Tab {

	public ManagerTab(){
		GridPane management = new GridPane();
		management.setPadding(new Insets(20,5000,5000,10));
		management.setStyle("-fx-background-color: rgb(230,230,230);");
		management.setHgap(10);
		management.setVgap(15);
		
		//Add manager options
		
		final Stage addManagerStage = new Stage();
		addManagerStage.initModality(Modality.APPLICATION_MODAL);
        
        GridPane addManagerGrid = new GridPane();
        addManagerGrid.setPadding(new Insets(20,5000,5000,10));
        addManagerGrid.setStyle("-fx-background-color: rgb(230,230,230);");
        addManagerGrid.setHgap(30);
        addManagerGrid.setVgap(15);
		
		final TextField firstName = new TextField();
		firstName.setPromptText("First Name ");
		firstName.getText();
		firstName.setMinWidth(160);
		GridPane.setConstraints(firstName,1,0);
		addManagerGrid.getChildren().add(firstName);
		
		final Text fname = new Text();
		fname.setText("FirstName : ");
		GridPane.setConstraints(fname,0,0);
		addManagerGrid.getChildren().add(fname);
		
		final TextField middleName = new TextField();
		middleName.setPromptText("Middle Name ");
		middleName.getText();
		middleName.setMinWidth(160);
		GridPane.setConstraints(middleName,3,0);
		addManagerGrid.getChildren().add(middleName);
		
		final Text mname = new Text();
		mname.setText("MiddleName : ");
		GridPane.setConstraints(mname,2,0);
		addManagerGrid.getChildren().add(mname);
		
		final TextField lastName = new TextField();
		lastName.setPromptText("Last Name ");
		lastName.getText();
		lastName.setMinWidth(160);
		GridPane.setConstraints(lastName,1,1);
		addManagerGrid.getChildren().add(lastName);
		
		final Text lname = new Text();
		lname.setText("LastName : ");
		GridPane.setConstraints(lname,0,1);
		addManagerGrid.getChildren().add(lname);
		
		final TextField phoneText = new TextField();
		phoneText.setPromptText("Phone No : ");
		phoneText.getText();
		phoneText.setMinWidth(160);
		GridPane.setConstraints(phoneText,3,1);
		addManagerGrid.getChildren().add(phoneText);
		
		final Text phone = new Text();
		phone.setText("Phone No : ");
		GridPane.setConstraints(phone,2,1);
		addManagerGrid.getChildren().add(phone);
		
		final TextField emailField = new TextField();
		emailField.setPromptText("email ");
		emailField.getText();
		emailField.setMinWidth(160);
		GridPane.setConstraints(emailField,1,2);
		addManagerGrid.getChildren().add(emailField);
		
		final Text email = new Text();
		email.setText("Email : ");
		GridPane.setConstraints(email,0,2);
		addManagerGrid.getChildren().add(email);
		
		final ComboBox<Integer> rating = new ComboBox<Integer>();
		rating.setValue(0);
		rating.setMinWidth(160);
		for (int m = 0; m <= 10; m ++) {
			rating.getItems().add(m);
		}
		GridPane.setConstraints(rating,3,2);
		addManagerGrid.getChildren().add(rating);
		
		final Text ratingText = new Text();
		ratingText.setText("rating : ");
		GridPane.setConstraints(ratingText,2,2);
		addManagerGrid.getChildren().add(ratingText);
		
		final DatePicker DOB  = new DatePicker();
		DOB.setMinWidth(160);
		GridPane.setConstraints(DOB,1,3);
		addManagerGrid.getChildren().add(DOB);
		
		final Text DOBtext = new Text();
		DOBtext.setText("Date of Birth :");
		GridPane.setConstraints(DOBtext,0,3);
		addManagerGrid.getChildren().add(DOBtext);
		
		Button submit = new Button("Submit");
		submit.setMinWidth(70);
		GridPane.setConstraints(submit, 0, 9);
		addManagerGrid.getChildren().add(submit);
		
		Button back = new Button("Back");
		back.setMinWidth(70);
		back.setTranslateX(110);
		GridPane.setConstraints(back, 3, 9);
		addManagerGrid.getChildren().add(back);
		
		final Text teamText = new Text();
		teamText.setText("Select a team ");
		GridPane.setConstraints(teamText,2,3,1,1);
		addManagerGrid.getChildren().add(teamText);
		
		final ComboBox<String> teamBox = new ComboBox<String>();
		teamBox.setMinWidth(160);
		GridPane.setConstraints(teamBox,3,3,1,1);
		addManagerGrid.getChildren().add(teamBox);
		
		Scene addManagerScene = new Scene(addManagerGrid,630,310);
		addManagerStage.setScene(addManagerScene);
        addManagerStage.initModality(Modality.NONE);
        addManagerStage.setResizable(false);
        
        back.setOnAction(e -> {
        	teamBox.getItems().clear();
        	addManagerStage.hide();
        });
        
        submit.setOnAction(e -> {
        	String first = firstName.getText();
        	String middle = middleName.getText();
        	String last = lastName.getText();
        	String phoneNo = phoneText.getText();
        	String emailAdd = emailField.getText();
        	int ratingNo = rating.getValue();
        	String teamSelect = teamBox.getValue();
        	String date = DOB.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	List<Team> teamList = em.createQuery("from Team").getResultList();
        	List<Manager> managerList = em.createQuery("from Manager").getResultList();
        	int teamNo = -1;
        	for (int i = 1; i < teamList.size(); i ++) {
        		if (teamSelect.contentEquals(teamList.get(i).getName())) {
        			teamNo = i;
        		}
        	}
        	Team targetTeam = em.find(Team.class, teamNo);
        	System.out.println(targetTeam.getName());
        	for (int j = 0; j < managerList.size(); j ++) {
        		Team newTeam = em.find(Team.class, managerList.get(j).getTeamID());
        		if (targetTeam.getName().contentEquals(newTeam.getName())) {
        			managerList.get(j).transferManager(-1);
        		}
        	}
        	Name newName = new Name(first,middle,last);
        	Manager newManager = new Manager(newName,phoneNo,emailAdd,date,ratingNo,teamNo);
        	newManager.persist();
        	
        	teamBox.getItems().clear();
        	addManagerStage.hide();
        });
		
		//End of add manager options
        
        //Remove manager options
        
        final Stage removeManagerStage = new Stage();
		removeManagerStage.initModality(Modality.APPLICATION_MODAL);
        
        GridPane removeManagerGrid = new GridPane();
        removeManagerGrid.setPadding(new Insets(20,5000,5000,10));
        removeManagerGrid.setStyle("-fx-background-color: rgb(230,230,230);");
        removeManagerGrid.setHgap(30);
        removeManagerGrid.setVgap(15);
        
		final Text managerText = new Text();
		managerText.setText("Select a Manager ");
		GridPane.setConstraints(managerText,0,0,1,1);
		removeManagerGrid.getChildren().add(managerText);
        
        final ComboBox<String> managerBox = new ComboBox<String>();
		managerBox.setMinWidth(160);
		GridPane.setConstraints(managerBox,1,0,14,1);
		removeManagerGrid.getChildren().add(managerBox);
		
		final Text removeText = new Text();
		removeText.setText("Select team to assign to");
		GridPane.setConstraints(removeText,0,1,2,1);
		removeManagerGrid.getChildren().add(removeText);
		
        final ComboBox<String> teamRemoveBox = new ComboBox<String>();
		teamRemoveBox.setMinWidth(430);
		GridPane.setConstraints(teamRemoveBox,2,1,13,1);
		removeManagerGrid.getChildren().add(teamRemoveBox);
		
		Button submitRemove = new Button("Submit");
		submitRemove.setMinWidth(70);
		GridPane.setConstraints(submitRemove, 0, 4);
		removeManagerGrid.getChildren().add(submitRemove);
		
		Button backRemove = new Button("Back");
		backRemove.setMinWidth(70);
		backRemove.setTranslateX(10);
		GridPane.setConstraints(backRemove, 14, 4);
		removeManagerGrid.getChildren().add(backRemove);
		
		Scene removeManagerScene = new Scene(removeManagerGrid,630,170);
		removeManagerStage.setScene(removeManagerScene);
        removeManagerStage.initModality(Modality.NONE);
        removeManagerStage.setResizable(false);
        
        backRemove.setOnAction(e -> {
        	managerBox.getItems().clear();
        	teamRemoveBox.getItems().clear();
        	removeManagerStage.hide();
        });
        
        submitRemove.setOnAction(e -> {
        	String managerInfo = managerBox.getValue();
			String teamSelect = teamRemoveBox.getValue();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	List<Team> teamList = em.createQuery("from Team").getResultList();
        	List<Manager> managerList = em.createQuery("from Manager").getResultList();
        	int teamNo = -1;
        	for (int i = 1; i < teamList.size(); i ++) {
        		if (teamSelect.contentEquals(teamList.get(i).getName())) {
        			teamNo = i;
        		}
        	}
        	Team targetTeam = em.find(Team.class, teamNo);
        	System.out.println(targetTeam.getName());
        	for (int j = 0; j < managerList.size(); j ++) {
        		Team newTeam = em.find(Team.class, managerList.get(j).getTeamID());
        		String string = (managerList.get(j).getName().getFname() + " " + managerList.get(j).getName().getMname() + " " + managerList.get(j).getName().getLname() + " " + managerList.get(j).getPhone() + " " + managerList.get(j).getEmail() + " " + managerList.get(j).getDOB() + " " + managerList.get(j).getRating() + " " + newTeam.getName());
        		if (targetTeam.getName().contentEquals(newTeam.getName())) {
        			managerList.get(j).transferManager(-1);
        		}
        		if (string.contentEquals(managerInfo)) {
        			managerList.get(j).transferManager(teamNo);
        		}
        	}
        	managerBox.getItems().clear();
        	teamRemoveBox.getItems().clear();
        	removeManagerStage.hide();
        });
        
        //End of remove manager options
        
		
		final Text addLabel = new Text();
		addLabel.setText("Add a new manager");
		GridPane.setConstraints(addLabel, 0, 0);
		management.getChildren().add(addLabel);
		
		Button addManager = new Button("Add Manager");
		addManager.setMinSize(120, 30);
		GridPane.setConstraints(addManager,1,0);
		management.getChildren().add(addManager);
		
		addManager.setOnAction(e ->  {
			
        	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
    		teamBox.setValue("None");
        	List<Team> teamList = em.createQuery("from Team").getResultList();
        	for (int p = 0; p < teamList.size(); p ++) {
        		teamBox.getItems().add(teamList.get(p).getName());
        	}
			
            addManagerStage.show();
		});
		
		final Text removeLabel = new Text();
		removeLabel.setText("Transfer manager from team");
		GridPane.setConstraints(removeLabel, 2, 0);
		management.getChildren().add(removeLabel);
		
		Button removeManager = new Button("Transfer Manager");
		removeManager.setMinSize(100, 30);
		GridPane.setConstraints(removeManager,3,0);
		management.getChildren().add(removeManager);
		
		removeManager.setOnAction(e -> {
			teamRemoveBox.getItems().clear();
			int j = 1;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	Manager newManager = em.find(Manager.class, j);
        	Team newTeam = em.find(Team.class,newManager.getTeamID());
        	String string = (newManager.getName().getFname() + " " + newManager.getName().getMname() + " " + newManager.getName().getLname() + " " + newManager.getPhone() + " " + newManager.getEmail() + " " + newManager.getDOB() + " " + newManager.getRating() + " " + newTeam.getName());
        	managerBox.setValue(string);
        	while (newManager != null) {
        		newTeam = em.find(Team.class, newManager.getTeamID());
        		String managerString = (newManager.getName().getFname() + " " + newManager.getName().getMname() + " " + newManager.getName().getLname() + " " + newManager.getPhone() + " " + newManager.getEmail() + " " + newManager.getDOB() + " " + newManager.getRating() + " " + newTeam.getName());
        		managerBox.getItems().add(managerString);
        		j ++;
        		newManager = em.find(Manager.class, j);
        	}
        	teamRemoveBox.setValue("None");
        	List<Team> teamList = em.createQuery("from Team").getResultList();
        	for (int p = 0; p < teamList.size(); p ++) {
        		teamRemoveBox.getItems().add(teamList.get(p).getName());
        	}
			removeManagerStage.show();
		});
		
		final TextArea textArea = new TextArea();
		textArea.setStyle("-fx-control-inner-background: white;");
		textArea.setPromptText("Display");
		textArea.setMinHeight(160);
		textArea.setMinWidth(620);
		GridPane.setConstraints(textArea,0,2,4,1);
		management.getChildren().add(textArea);
		
		final Text orderByAlpha = new Text();
		orderByAlpha.setText("List managers by alphabet");
		GridPane.setConstraints(orderByAlpha, 0, 1);
		management.getChildren().add(orderByAlpha);
		
		Button orderByAlphaButton = new Button("List by Alpha");
		orderByAlphaButton.setMinSize(120, 30);
		GridPane.setConstraints(orderByAlphaButton,1,1);
		management.getChildren().add(orderByAlphaButton);
		
		orderByAlphaButton.setOnAction(e -> {
			textArea.clear();
			int j = 1;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	ArrayList<Manager> managerList = new ArrayList<Manager>();
        	Manager newManager = em.find(Manager.class, j);
        	ArrayList<String> managerNameList = new ArrayList<String>();
        	while (newManager != null) {
        		managerList.add(newManager);
        		managerNameList.add(newManager.getName().getFname());
        		j ++;
        		newManager = em.find(Manager.class, j);
        	}
        	Collections.sort(managerNameList);
        	for (int i = 0; i < managerNameList.size(); i ++) {
        		for (int p = 0; p < managerList.size(); p ++) {
        			if (managerNameList.get(i) == managerList.get(p).getName().getFname()) {
        				String entity = (managerList.get(p).getName().getFname() + " " + managerList.get(p).getName().getMname() + " " + managerList.get(p).getName().getLname() + " " + managerList.get(p).getPhone() + " " + managerList.get(p).getEmail() + " " + managerList.get(p).getDOB() + " " + managerList.get(p).getRating() + "\n");
        				textArea.appendText(entity);
        				managerList.get(p).setRating(0);
        				break;
        			}
        		}
        	}
		});
		
		final Text orderByRating = new Text();
		orderByRating.setText("List managers by rating");
		GridPane.setConstraints(orderByRating, 2, 1);
		management.getChildren().add(orderByRating);
		
		Button orderByRatingButton = new Button("list by rating");
		orderByRatingButton.setMinSize(100, 30);
		GridPane.setConstraints(orderByRatingButton,3,1);
		management.getChildren().add(orderByRatingButton);
		
		orderByRatingButton.setOnAction(e -> {
			textArea.clear();
			int j = 1;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        	EntityManager em = emf.createEntityManager();
        	ArrayList<Manager> managerList = new ArrayList<Manager>();
        	Manager newManager = em.find(Manager.class, j);
        	ArrayList<Integer> managerRatingList = new ArrayList<Integer>();
        	while (newManager != null) {
        		managerList.add(newManager);
        		managerRatingList.add(newManager.getRating());
        		j ++;
        		newManager = em.find(Manager.class, j);
        	}
        	Collections.sort(managerRatingList, Collections.reverseOrder());
        	for (int i = 0; i < managerRatingList.size(); i ++) {
        		for (int p = 0; p < managerList.size(); p ++) {
        			if (managerRatingList.get(i) == managerList.get(p).getRating()) {
        				String entity = (managerList.get(p).getName().getFname() + " " + managerList.get(p).getName().getMname() + " " + managerList.get(p).getName().getLname() + " " + managerList.get(p).getPhone() + " " + managerList.get(p).getEmail() + " " + managerList.get(p).getDOB() + " " + managerList.get(p).getRating() + "\n");
        				textArea.appendText(entity);
        				managerList.get(p).setRating(0);
        				break;
        			}
        		}
        	}
		});

		this.setContent(management);
		this.setText("Manager Management");
	}
}
