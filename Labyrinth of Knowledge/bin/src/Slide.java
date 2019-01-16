import javax.swing.JPanel;

/**
 * This is the Slide class that extends the JPanel. 
 It is a template for all other slide in the program which are displayed on the screen.
 * 
 * <h3>Modifications</h3>
 * <p>
 * <b>June 02,2017</b> Daniel made the entire class
 * <p>
 * 
 * Authors: Daniel Arakcheev
 * Version: 2.0
 * ICS 4U0
 * Date: June 04, 2017
 * Time Spent: 30min
 * Ms. Krasteva
 * */
public class Slide extends JPanel
{
    /*Reference variable to the Application class*/
    Application app;
    
    /*The class constructor which assigns the app variable.
       @param Application app the reference variable for the application class.   
    */
    public Slide (Application app)
    {
	super (null);
	this.app = app;
    }

    /*The activate method is a template method that is overridden all other Slide classes*/
    public void activate ()
    {
    }
}
