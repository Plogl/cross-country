
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;

public class FrontendMapper extends Application implements FrontendMapperInterface{
	
	//String needed for the x and y positions of capitals on the map
	String capitalWindowLocations = "X POS Y POS\n"
			+ "814.0 191.0\n"
			+ "790.0 268.0\n"
			+ "690.0 388.0\n"
			+ "863.0 143.0\n"
			+ "471.0 466.0\n"
			+ "585.0 465.0\n"
			+ "441.0 142.0\n"
			+ "223.0 166.0\n"
			+ "856.0 184.0\n"
			+ "152.0 239.0\n"
			+ "719.0 294.0\n"
			+ "370.0 242.0\n"
			+ "739.0 373.0\n"
			+ "695.0 264.0\n"
			+ "842.0 170.0\n"
			+ "363.0 275.0\n"
			+ "543.0 245.0\n"
			+ "809.0 262.0\n"
			+ "671.0 303.0\n"
			+ "784.0 243.0\n"
			+ "836.0 201.0\n"
			+ "290.0 127.0\n"
			+ "648.0 275.0\n"
			+ "595.0 422.0\n"
			+ "562.0 304.0\n"
			+ "665.0 215.0\n"
			+ "493.0 258.0\n"
			+ "562.0 376.0\n"
			+ "596.0 214.0\n"
			+ "660.0 417.0\n"
			+ "824.0 149.0\n"
			+ "646.0 343.0\n"
			+ "479.0 367.0\n"
			+ "159.0 81.0\n"
			+ "241.0 380.0\n"
			+ "440.0 193.0\n"
			+ "853.0 195.0\n"
			+ "783.0 333.0\n"
			+ "788.0 298.0\n"
			+ "117.0 245.0\n"
			+ "539.0 180.0\n"
			+ "136.0 122.0\n"
			+ "271.0 239.0\n"
			+ "345.0 353.0\n"
			+ "600.0 281.0\n"
			+ "700.0 451.0\n"
			+ "503.0 295.0\n"
			+ "817.0 238.0";
	
	/**
	 * mapLoation class that holds the x and y position for the capitals on the map
	 * @author michaelbonfiglio
	 *
	 */
	
	private class mapLocation{
		float xPos, yPos;
		
		public mapLocation(float x, float y) {
			this.xPos = x;
			this.yPos = y;
		}
	}
	
	//backend reference
	BackendMapperInterface backend;
	//starting and ending capitals
	ArrayList<String> startEnd = new ArrayList<String>();
	//hashmap to key capital name to mapLocation value
	HashMap<String, mapLocation> capitalLocations = new HashMap<String, mapLocation>();
	
	//String Path
	String path;
	//mile cost
	double mileCost;
	
	//FrontendMapper constructor class
	void FrontendMapper()
    {
        
    }

	@Override
	/**
	 * Runs the JavaFX application
	 */
	public void runCommandLoop() {
		Application.launch();

	}

	/**
	 * Start to launch JavaFX application
	 */
	public void start(final Stage stage) {
        // creates a BoarderPane, GridPane, and Pane that will be used
        BorderPane startBoarderPane = new BorderPane();
        startBoarderPane.setId("boarderPane");
        GridPane grid = new GridPane();
        grid.setId("grid");
        Pane pane = new Pane();
        pane.setId("pane");
        //scene is created and title is set
        Scene scene = new Scene(startBoarderPane, 1000, 740);
        stage.setTitle("US CAPITAL VACATION");
        
        // Label creation and alignment at top of window
        Label label = new Label("Choose Your Starting US Capital");
        label.setId("TheLabel");
        label.setMinWidth(50);
        label.setMinHeight(50);
        startBoarderPane.setTop(label);
        label.setFont(new Font("Arial", 30));
        BorderPane.setAlignment(label, Pos.TOP_CENTER);

        //exit button creation
        Button exitButton = new Button("Exit");
        exitButton.setId("ExitButton");
        exitButton.setOnAction(event -> {
        	//exit action
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        });
        //exit button added to BoarderPane
        startBoarderPane.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);
        
        //grid added to boarderPane and aligned
        startBoarderPane.setCenter(grid);
        BorderPane.setAlignment(grid, Pos.BOTTOM_RIGHT);

        //scnr is created to read mapLoactions
        Scanner scnr;
		scnr = new Scanner(capitalWindowLocations);
		scnr.useDelimiter("\n");
		String skip = scnr.nextLine();
		//Capital names are added to buttonLabels
        String[] buttonLabels = new String[] {"Albany", "Annapolis", "Atlanta", "Augusta", "Austin", "Baton Rouge", "Bismarck", "Boise", "Boston", "Carson City", "Charleston", "Cheyenne", "Columbia", "Columbus", "Concord", "Denver", "Des Moines", "Dover", "Frankfort", "Harrisburg", "Hartford", "Helena", "Indianapolis", "Jackson", "Jefferson City", "Lansing", "Lincoln", "Little Rock", "Madison", "Montgomery", "Montpelier", "Nashville", "Oklahoma City", "Olympia", "Phoenix", "Pierre", "Providence", "Raleigh", "Richmond", "Sacramento", "Saint Paul", "Salem", "Salt Lake City", "Santa Fe", "Springfield", "Tallahassee", "Topeka", "Trenton"};
        Button[] buttons = new Button[buttonLabels.length];
        //buttons are made from button labels, buttons receive actions, and capitalLocations
        // is filled
        for(int i=0; i<buttons.length ;i++) {
        	//buttons are created and IDs are set
        	Button bt = new Button(buttonLabels[i]);
        	bt.setId(buttonLabels[i]);
            buttons[i] = bt;
            
            //buttons added to grid as children
            grid.getChildren().add( buttons[i] );
            GridPane.setColumnIndex( buttons[i], i % 10);
            GridPane.setRowIndex( buttons[i], i/10 );
            
            //Scanner reads line and maps Capital name to mapLocation
            String scnr_line = scnr.nextLine();
		    String[] listOfLine = scnr_line.split(" ");
		    capitalLocations.put(buttonLabels[i], new mapLocation(Float.parseFloat(listOfLine[0]),Float.parseFloat(listOfLine[1])));
		    
            //action for each button
            buttons[i].setOnAction(e -> {
            	//adds button to startEnd when pressed(couldn't get ID)
            	startEnd.add(e.getSource().toString().split("'")[1]);
            	//if size of startEnd is 1, label is updated 
            	if(startEnd.size() == 1) {
            		//label update
            		label.setText("Choose Your Destination US Capital");
                	exitButton.requestFocus();
            	}
            	//if size of startEnd is 2, label is updated and screen is changed
            	else if(startEnd.size() == 2) {
            		//changes to allow user input of max KM distance between capitals
            		//change text header
            		label.setText("Input Integer for Max Kilometer Distance Between Capitals");
            		//creates input label
            		Label inputLabel = new Label("Max Km: ");
            		inputLabel.setId("inputlabel");
            		//creates textField for user input
            		TextField textField = new TextField ();
            		textField.setId("textfield");
            		//creates HBox for textfield and inputLabel
            		HBox hb = new HBox();
            		hb.setId("hb");
            		//adds  textfield and inputLabel to HBox
            		hb.getChildren().addAll(inputLabel, textField);
            		//aligns it in boarderPane
            		hb.setSpacing(10);
            		startBoarderPane.setCenter(hb);
                    BorderPane.setAlignment(hb, Pos.CENTER);
                    //creates submit button to submit user input
                    Button submit = new Button("Submit");
                    submit.setId("submit");
                    GridPane.setConstraints(submit, 1, 0);
                    //adds submit button to Hbox
                    hb.getChildren().add(submit);
                    //error label created when something other than a number from 500 to 1000 is inputed
                    Label errorLabel = new Label("Insert number between 500 and 1000");
                    errorLabel.setId("errorlabel");
                    //action set when submit button is hit
                    submit.setOnAction(p -> {
                    	//checks if text in textField is a number
                    	if (!isNum(textField.getText())) {
                    		//inserts errorLabel into HBox if not already present
                    		if(!hb.getChildren().contains(errorLabel)) {
                    			hb.getChildren().add(errorLabel);
                        		errorLabel.setTextFill(Color.RED);
                    		}
                    		
                    	}
                    	else {
                    		//ParseInts text field to get max KM inputed 
                    		int distance = Integer.parseInt(textField.getText());
                    		try {
                    			//checks if distance is between 500 and 1000
	                    		if(distance >= 500 && distance <= 1000) {
	                    			//updates label
	                        		label.setText("Your Travel Map");
	                        		//map of US capitals is retrieved and a BackgroundImage is created
	                        		Image image = new Image("printable-map-of-us-state-capitals.jpg");
	                                BackgroundImage bg = new BackgroundImage(
	                                	image,
	                                	BackgroundRepeat.NO_REPEAT,
	                                	BackgroundRepeat.NO_REPEAT,
	                                	BackgroundPosition.CENTER,
	                                	new BackgroundSize(100, 100, true, true, true, false)
	                                );
	                                
	                                //pane is centered
	                                startBoarderPane.setCenter(pane);
	                                BorderPane.setAlignment(pane, Pos.BOTTOM_RIGHT);
	                                
	                                //pane's background is set to the background image
	                                Background b = new Background(bg);
	                                pane.setBackground(b);
	                                
	                                //sets path and milecost from backend
	                                backend = new BackendMapper(distance);
	                                this.path = backend.getPath(startEnd.get(0),startEnd.get(1));
	                                this.mileCost = backend.getRoute(startEnd.get(0),startEnd.get(1));
	                                		
	                                
	                                
	                                
	                                //splits cities traveled into string
	                                String[] link = path.replaceAll("[\\[\\](){}]", "").split(", ");
	                                //places lines and circles on map to show destination route.
	                                for(int x = 0; x < link.length-1; x++) {
	                                	//creates lines between capitals that will be driven too.
	                                	Line line = new Line();
	                                	line.setFill(Color.DARKBLUE);
	                                    pane.getChildren().add(line);
	                                    mapLocation temp1 = capitalLocations.get(link[x]);
	                                    mapLocation temp2 = capitalLocations.get(link[x+1]);
	                                    line.setStartX(temp1.xPos);
	                                    line.setStartY(temp1.yPos);
	                                    line.setEndX(temp2.xPos);
	                                    line.setEndY(temp2.yPos);
	                                    line.setId("line"+x);
	                                    //places green at starting capital, red and ending, and yellow at intermediate
	                                    if(x == 0) {
	                                    	Circle circle = new Circle(temp1.xPos, temp1.yPos, 5);
	                                    	circle.setFill(Color.GREEN);
	                                    	pane.getChildren().add(circle);
	                                    	circle.setId("circle"+x);
	                                    }
	                                    else if(x == link.length-2) {
	                                    	Circle circle1 = new Circle(temp1.xPos, temp1.yPos, 5);
	                                    	circle1.setFill(Color.YELLOW);
	                                    	pane.getChildren().add(circle1);
	                                    	circle1.setId("circle"+x);
	                                    	Circle circle2 = new Circle(temp2.xPos, temp2.yPos, 5);
	                                    	circle2.setFill(Color.RED);
	                                    	pane.getChildren().add(circle2);
	                                    	circle2.setId("circle"+(x+1));
	                                    }
	                                    else {
	            		                    Circle circle = new Circle(temp1.xPos, temp1.yPos, 5);
	            		                	circle.setFill(Color.YELLOW);
	            		                	pane.getChildren().add(circle);
	            		                	circle.setId("circle"+x);
	                                    }
	                               
	                                    
	                                }
	                                //checks if start and destination are the same
	                                if(startEnd.get(0).contentEquals(startEnd.get(1))) {
	                                	mapLocation temp1 = capitalLocations.get(link[0]);
	                                	Circle circle = new Circle(temp1.xPos, temp1.yPos, 5);
                                    	circle.setFill(Color.GREEN);
                                    	pane.getChildren().add(circle);
                                    	circle.setId("circle"+0);
	                                }
	                              //creates final label with path and cost of trip and updates label
	                                String pathLabel = "Your Travel Map: " + path.replaceAll("[\\[\\](){}]", "").replaceAll(",", " ->");
	                                pathLabel = pathLabel + "\n    Kilometers Travled is: "+ mileCost;
	                                label.setText(pathLabel);
	                                label.setFont(new Font("Arial", 15));
	                                exitButton.requestFocus();
	                                
		                    		}
	                    		
	                    		else {
	                    			//if distance is not between 500 and 1000, errorLabel is placed
	                    			// if not already present
	                    			if(!hb.getChildren().contains(errorLabel)) {
	                        			hb.getChildren().add(errorLabel);
	                            		errorLabel.setTextFill(Color.RED);
	                        		}
	                    		}
                    		}catch(Exception e1) {
                    			//if not path is found, states that not path exists.
                    			hb.getChildren().clear();
                    			pane.getChildren().clear();
                    			label.setText("ERROR: NO PATH EXISTS, LONGER MAX KILOMETER IS NEEDED");
                    			label.setTextFill(Color.RED);
                    		}
                    	}
                    	
                    	
                    	
                    	
                    });
            	}
            	
            	
            });

            
        }
        
        //using for mapLocation mapping
        //pane.addEventHandler(MouseEvent.MOUSE_PRESSED, e->
	      //System.out.println(e.getX()+" "+e.getY()));
        
        //sets stage and shows stage
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    	
	}
	
	/**
	 * Checks whether the text in textField is a number or not
	 * @param num - String value in textField
	 * @return true if string is an integer, false otherwise
	 */
	public boolean isNum(String num) {
		try {
			Integer.parseInt(num);
		}catch(Exception z){
			return false;
		}
		return true;
	}
    public static void main(String[] args) {
        Application.launch();
    }

}
