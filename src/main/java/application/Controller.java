package application;


import javafx.scene.control.TabPane;
/**
 * The controller class of the application
 * @author Maciej Becmer
 *
 */
public class Controller extends TabPane {
	
	TabPane tabPane = new TabPane();
	
	/**
	 * Controller method
	 * @author Maciej Becmer
	 *
	 */
	public Controller() {
		tabPane = new TabPane();
		IntroTab intro = new IntroTab();			
		ManagerTab manager = new ManagerTab();
		PlayerTab player = new PlayerTab();
		TeamTab team = new TeamTab();
		Search search = new Search();
		
		intro.setClosable(false);
		manager.setClosable(false);
		player.setClosable(false);
		team.setClosable(false);
		search.setClosable(false);

        tabPane.getTabs().add(intro);
        tabPane.getTabs().add(player);
        tabPane.getTabs().add(manager);
        tabPane.getTabs().add(team);
        tabPane.getTabs().add(search);
	}
	
	public TabPane returnTabPane() {
		return this.tabPane;
	}
}

