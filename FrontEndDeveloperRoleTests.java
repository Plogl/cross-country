import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.application.*;
import edu.wisc.cs.cs400.JavaFXTester;

public class FrontEndDeveloperRoleTests  extends JavaFXTester{

	public FrontEndDeveloperRoleTests() {
        // you must specify the Application being tested by passing its class
        // to the parent class through the constructor, like this: 
        super(FrontendMapper.class);
    }
	
	/**
	 * Labeltest ensures that the main label is correctly updated when application is opened when clicking capital 
	 * buttons
	 */
	@Test
    public void Labeltest() {
        // lookup nodes in the scene graph using CSS style selectors:
		Button buttonSacramento = lookup("#Sacramento").query();
		Button buttonBostom = lookup("#Boston").query();
        Label label = lookup("#TheLabel").query();
        // events must be fired on the JavaFX Application thread, 
        // which this interact method helps you accomplish:
        assertEquals("Choose Your Starting US Capital",label.getText());
        interact( () -> buttonSacramento.fireEvent(new ActionEvent()) );
        // then we can assert that the expected state has been updated
        assertEquals("Choose Your Destination US Capital",label.getText());
        interact( () -> buttonBostom.fireEvent(new ActionEvent()) );
        //checks final message
        assertEquals("Input Integer for Max Kilometer Distance Between Capitals",label.getText());
        //gets textField, submit button, errorLabel, and inputLabel
        TextField textField = lookup("#textfield").query();
		Button submit = lookup("#submit").query();
		//changes textField text		
		textField.setText("500");
		interact( () -> submit.fireEvent(new ActionEvent()) );
		
		assertEquals("ERROR: NO PATH EXISTS, LONGER MAX KILOMETER IS NEEDED", label.getText());
		
    }
	/**
	 * Labeltest2 ensures that labels like errorLabel and inputLabel are correctly updated when 
	 * application is opened and screens are changing
	 */
	@Test
	public void Labeltest2(){
		// lookup nodes in the scene graph using CSS style selectors:
				Button buttonSacramento = lookup("#Sacramento").query();
				Button buttonBostom = lookup("#Boston").query();
		        Label label = lookup("#TheLabel").query();
		        // events must be fired on the JavaFX Application thread, 
		        interact( () -> buttonSacramento.fireEvent(new ActionEvent()) );
		        interact( () -> buttonBostom.fireEvent(new ActionEvent()) );
		        //checks final message
		        assertEquals("Input Integer for Max Kilometer Distance Between Capitals",label.getText());
		        //gets textField, submit button, errorLabel, and inputLabel
		        TextField textField = lookup("#textfield").query();
				Button submit = lookup("#submit").query();
				Label inputLabel = lookup("#inputlabel").query();
				
				
				//asserts textField starts as an Empty String
				assertEquals("",textField.getText());
				//asserts inputLabel is correct
				assertEquals("Max Km: ", inputLabel.getText());
				//changes textField text
				textField.setText("asdf");
				
				interact( () -> submit.fireEvent(new ActionEvent()) );
				Label errorLabel = lookup("#errorlabel").query();
				assertEquals("Insert number between 500 and 1000", errorLabel.getText());
				
	}
	
	@Test
	/**
	 * testCapitalNum ensures that the grid has 48 buttons for the 48 capitals
	 */
	public void testCapitalNum() {
		
		//finds grid ID
		 GridPane grid = lookup("#grid").query();
		 //checks number of grid children
		 assertEquals(48,grid.getChildrenUnmodifiable().size());
		 
	}
	
	@Test
	/**
	 * testExitButton ensures that the exit button works correctly and window closes
	 */
	public void testExitButton() {
		//finds exit button
		Button exitButton = lookup("#ExitButton").query();
		//presses exit button
		interact( () -> exitButton.fireEvent(new ActionEvent()) );
		//gets number of windows after exit button was pressed
		List<Window> window = listTargetWindows();
		//checks that number of windows is 0.
		assertEquals(0, window.size());	
	}
	
	@Test
	/**
	 * testCirclePlacement checks that circle objects are placed correctly
	 */
	public void testCirclePlacement() {
		Button buttonAustin = lookup("#Austin").query();
		Button buttonBostom = lookup("#Boston").query();
		Label label = lookup("#TheLabel").query();
		//presses button test
		interact( () -> buttonAustin.fireEvent(new ActionEvent()) );
		interact( () -> buttonBostom.fireEvent(new ActionEvent()) );
		TextField textField = lookup("#textfield").query();
		Button submit = lookup("#submit").query();
		textField.setText("800");
		interact( () -> submit.fireEvent(new ActionEvent()) );
		//finds circles on map
		Circle circle0 = lookup("#circle0").query();
		Circle circle1 = lookup("#circle1").query();
		Circle circle2 = lookup("#circle2").query();
		Circle circle3 = lookup("#circle3").query();
		Circle circle4 = lookup("#circle4").query();
		
		
		//checks circle zero is correct
		assertEquals(circle0.fillProperty().getValue(), Color.GREEN);
		assertEquals(circle0.getCenterX(), 471.0);
		assertEquals(circle0.getCenterY(), 466.0);
		
		//checks circle one is correct
		assertEquals(circle1.fillProperty().getValue(), Color.YELLOW);
		assertEquals(circle1.getCenterX(), 562.0);
		assertEquals(circle1.getCenterY(), 376.0);
		
		//checks circle two is correct
		assertEquals(circle2.fillProperty().getValue(), Color.YELLOW);
		assertEquals(circle2.getCenterX(), 671.0);
		assertEquals(circle2.getCenterY(), 303.0);
		
		//checks circle three is correct
		assertEquals(circle3.fillProperty().getValue(), Color.YELLOW);
		assertEquals(circle3.getCenterX(), 790.0);
		assertEquals(circle3.getCenterY(), 268.0);
		
		//checks circle four is correct
		assertEquals(circle4.fillProperty().getValue(), Color.RED);
		assertEquals(circle4.getCenterX(), 856.0);
		assertEquals(circle4.getCenterY(), 184.0);
		
		assertEquals(label.getText(), "Your Travel Map: Austin -> Little Rock -> Frankfort -> Annapolis -> Boston\n"
				+ "    Kilometers Travled is: 2803.7397136734066");
	}
	
	@Test
	/**
	 * testLinePlacement checks that line objects are placed correctly
	 */
	public void testLinePlacement() {
		Button buttonAustin = lookup("#Austin").query();
		Button buttonBostom = lookup("#Boston").query();
		Label label = lookup("#TheLabel").query();
		//presses button test
		interact( () -> buttonAustin.fireEvent(new ActionEvent()) );
		interact( () -> buttonBostom.fireEvent(new ActionEvent()) );
		TextField textField = lookup("#textfield").query();
		Button submit = lookup("#submit").query();
		textField.setText("800");
		interact( () -> submit.fireEvent(new ActionEvent()) );
		//finds lines on map
		Line line0 = lookup("#line0").query();
		Line line1 = lookup("#line1").query();
		Line line2 = lookup("#line2").query();
		Line line3 = lookup("#line3").query();
		
		//checks line zeros x and y cords
		assertEquals(line0.getStartX(), 471.0);
		assertEquals(line0.getStartY(), 466.0);
		assertEquals(line0.getEndX(), 562.0);
		assertEquals(line0.getEndY(), 376.0);
		
		//checks line ones x and y cords
		assertEquals(line1.getStartX(), 562.0);
		assertEquals(line1.getStartY(), 376.0);
		assertEquals(line1.getEndX(), 671.0);
		assertEquals(line1.getEndY(), 303.0);
		
		//checks line twos x and y cords
		assertEquals(line2.getStartX(), 671.0);
		assertEquals(line2.getStartY(), 303.0);
		assertEquals(line2.getEndX(), 790.0);
		assertEquals(line2.getEndY(), 268.0);
		
		//checks line threes x and y cords
		assertEquals(line3.getStartX(), 790.0);
		assertEquals(line3.getStartY(), 268.0);
		assertEquals(line3.getEndX(), 856.0);
		assertEquals(line3.getEndY(), 184.0);
		
	}
	
	/**
	 * Checks that objects are correct with same start end capitals
	 */
	@Test
	public void sameStartEndtest() {
		Button buttonAustin = lookup("#Austin").query();
		Label label = lookup("#TheLabel").query();
		//presses button test
		interact( () -> buttonAustin.fireEvent(new ActionEvent()) );
		interact( () -> buttonAustin.fireEvent(new ActionEvent()) );
		TextField textField = lookup("#textfield").query();
		Button submit = lookup("#submit").query();
		textField.setText("800");
		interact( () -> submit.fireEvent(new ActionEvent()) );
		
		Circle circle0 = lookup("#circle0").query();
		
		//checks circle zero is correct
		assertEquals(circle0.fillProperty().getValue(), Color.GREEN);
		assertEquals(circle0.getCenterX(), 471.0);
		assertEquals(circle0.getCenterY(), 466.0);	
		
		assertEquals(label.getText(), "Your Travel Map: Austin\n"
				+ "    Kilometers Travled is: 0.0");
	}
	/**
	 * Checks that when BackendMapper object is created, it correctly calls load capitals
	 * and sets Algorithm Engineers' maxDist correctly
	 * 
	 */
	@Test
	public void CodeReviewofBackendDevelopertest1() {
		//creates BackendMapper object 
		
		BackendMapper bm = new BackendMapper(700);
		//checks that graph size is correct when sending ArrayList to graph iterator, should be 50
		assertEquals(bm.GI.graph.vertices.size(), 50);
		
		//checks that BackendMapper correctly changes maxDist
		assertEquals(bm.GI.maxDist, 700);

		
	}
	
	
	/**
	 * Checks that when BackendMapper object calls getRoute and getPath it returns
	 * the correct values
	 */
	@Test
	public void CodeReviewofBackendDevelopertest2() {
		//creates BackendMapper object 
		
		BackendMapper bm = new BackendMapper(700);
		//checks that cost and path is correct for Boston to Phoenix
		assertEquals(bm.getRoute("Boston", "Phoenix"), 4253.18694242243);
		assertEquals(bm.getPath("Boston", "Phoenix"), "[Boston, Dover, Columbus, Springfield, Lincoln, Cheyenne, Santa Fe, Phoenix]");
		
		//checks that path is correct from Agusta to Olympia
		assertEquals(bm.getRoute("Augusta", "Olympia"), 4966.863984301163);
		assertEquals(bm.getPath("Augusta", "Olympia"), "[Augusta, Hartford, Dover, Columbus, Springfield, Lincoln, Cheyenne, Salt Lake City, Boise, Olympia]");
		
		

		
	}
	

}
