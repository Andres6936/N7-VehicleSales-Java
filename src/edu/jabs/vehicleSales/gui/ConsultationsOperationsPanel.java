package edu.jabs.vehicleSales.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel with the different operations and consultations
 */
public class ConsultationsOperationsPanel extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ADD = "AddVehicle";

    private static final String BUY = "BuyVehicle";

    private static final String OLDEST = "Oldest";

    private static final String CHEAPEST = "Cheapest";

    private static final String MOST_POWERFUL = "MostPowerful";

    private static final String REDUCE_PRICE = "ReducePrice";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Reference to the main class of the interface
     */
    private VehiclesSalesGUI mainWindow;

    // -----------------------------------------------------------------
    // GUI Attributes
    // -----------------------------------------------------------------

    /**
     * Add vehicle button
     */
    private JButton addButton;

    /**
     * Button used to search the oldest vehicle
     */
    private JButton oldestButton;

    /**
     * Button used to search the cheapest vehicle
     */
    private JButton cheapestButton;

    /**
     * Button used to search the most powerful vehicle
     */
    private JButton mostPowerfulButton;

    /**
     * Button used to reduce price of the vehicle
     */
    private JButton reducePriceButton;

    /**
     * Button used to buy the vehicle
     */
    private JButton buyButton;

    // -----------------------------------------------------------------
    // Constructor Methods
    // -----------------------------------------------------------------

    /**
     * Constructs the panel and initializes all the components
     * @param vsg Reference to the main window - vsg != null
     */
    public ConsultationsOperationsPanel( VehiclesSalesGUI vsg )
    {
        mainWindow = vsg;
        setLayout( new GridBagLayout( ) );

        setBorder( new CompoundBorder( new EmptyBorder( 4, 3, 3, 3 ), new TitledBorder( "Operations and Consultations" ) ) );

        addButton = new JButton( "Add Vehicle" );
        addButton.setActionCommand( ADD );
        addButton.addActionListener( this );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 0, 0, 5, 5 );
        add( addButton, gbc );

        buyButton = new JButton( "Buy Vehicle" );
        buyButton.setActionCommand( BUY );
        buyButton.addActionListener( this );
        gbc.gridy = 1;
        add( buyButton, gbc );

        reducePriceButton = new JButton( "Reduce Price" );
        reducePriceButton.setActionCommand( REDUCE_PRICE );
        reducePriceButton.addActionListener( this );
        gbc.gridx = 1;
        gbc.gridy = 0;
        add( reducePriceButton, gbc );

        oldestButton = new JButton( "Oldest" );
        oldestButton.setActionCommand( OLDEST );
        oldestButton.addActionListener( this );
        gbc.gridy = 1;
        add( oldestButton, gbc );

        cheapestButton = new JButton( "Cheapest" );
        cheapestButton.setActionCommand( CHEAPEST );
        cheapestButton.addActionListener( this );
        gbc.gridx = 2;
        gbc.gridy = 0;
        add( cheapestButton, gbc );

        mostPowerfulButton = new JButton( "Most Powerful" );
        mostPowerfulButton.setActionCommand( MOST_POWERFUL );
        mostPowerfulButton.addActionListener( this );
        gbc.gridy = 1;
        add( mostPowerfulButton, gbc );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Executes an action depending on the pressed button
     * @param event Click event - event!=null
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );

        if( ADD.equals( command ) )
        {
            mainWindow.mostrarDialogoAgregarVehiculo( );
        }
        else if( OLDEST.equals( command ) )
        {
            mainWindow.searchOldest( );
        }
        else if( CHEAPEST.equals( command ) )
        {
            mainWindow.searchCheapest( );
        }
        else if( MOST_POWERFUL.equals( command ) )
        {
            mainWindow.searchMostPowerful( );
        }
        else if( BUY.equals( command ) )
        {
            mainWindow.purchaseVehicle( );
        }
        else if( REDUCE_PRICE.equals( command ) )
        {
            mainWindow.reducePrice( );
        }
    }
}