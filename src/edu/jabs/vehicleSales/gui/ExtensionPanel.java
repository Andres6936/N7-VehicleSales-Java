package edu.jabs.vehicleSales.gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * This panel contains the buttons to execute the extension points
 */
public class ExtensionPanel extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Button 1 command
     */
    private final String OPTION_1 = "option 1";

    /**
     * Button 2 command
     */
    private final String OPTION_2 = "option 2";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Reference to the interface of the application
     */
    private VehiclesSalesGUI mainWindow;

    // -----------------------------------------------------------------
    // GUI Attributes
    // -----------------------------------------------------------------

    /**
     * Bu2ton 1
     */
    private JButton option1Button;

    /**
     * Button2
     */
    private JButton option2Button;

    // -----------------------------------------------------------------
    // Constructor Methods
    // -----------------------------------------------------------------

    /**
     * Constructs the panel with a reference to the main window
     * @param gvs Main window reference - gvs!=null
     */
    public ExtensionPanel( VehiclesSalesGUI gvs )
    {
        mainWindow = gvs;
        initialize( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Initialize the panel components
     */
    private void initialize( )
    {
        setBorder( new TitledBorder( "Extension Points" ) );

        setLayout( new FlowLayout( ) );
        option1Button = new JButton( "Option 1" );
        option1Button.setActionCommand( OPTION_1 );
        option1Button.addActionListener( this );

        option2Button = new JButton( "Option 2" );
        option2Button.setActionCommand( OPTION_2 );
        option2Button.addActionListener( this );

        add( option1Button );
        add( option2Button );
    }

    /**
     * This method is call when a button is pressed
     * @param event Click event - event!=null
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );
        if( OPTION_1.equals( command ) )
        {
            mainWindow.funcReqOption1( );
        }
        else if( OPTION_2.equals( command ) )
        {
            mainWindow.funcReqOption2( );
        }
    }

}
