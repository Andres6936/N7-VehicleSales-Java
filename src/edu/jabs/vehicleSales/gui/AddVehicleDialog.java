package edu.jabs.vehicleSales.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

/**
 * This is the dialog used to add a vehicle
 */
public class AddVehicleDialog extends JDialog
{
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
     * The panel that is used to add the info of the vehicle
     */
    private AddVehiclePanel addPanel;

    // -----------------------------------------------------------------
    // Constructor Methods
    // -----------------------------------------------------------------

    /**
     * Constructs the dialog and initializes the components
     * @param vsg It's a reference to the main window
     */
    public AddVehicleDialog( VehiclesSalesGUI vsg )
    {
        super( vsg, true );
        mainWindow = vsg;

        setLayout( new BorderLayout( ) );

        addPanel = new AddVehiclePanel( this );
        add( addPanel );

        setTitle( "Add Vehicle" );
        pack( );

        center( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Center the dialog
     */
    private void center( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int cornerX = ( screen.width - getWidth( ) ) / 2;
        int cornerY = ( screen.height - getHeight( ) ) / 2;
        setLocation( cornerX, cornerY );
    }

    /**
     * Adds a new vehicle
     * @param vModel Model name - modeloV != null
     * @param vBrand Vehicle brand - marcaV != null
     * @param vImage Vehicle image - imagenP != null
     * @param vType The vehicle type - vType belongs to {BUS, CAR, MOTORBIKE, TRUCK}
     * @param vYear The vehicle year - vYear > 0
     * @param vCylinderCapacity The vehicle cylinder capacity - vCylinderCapacity > 0
     * @param vAxes Number of axes of the vehicle - vAxes > 0
     * @param price The vehicle price - price > 0
     */
    public void addVehicle( String vModel, String vBrand, String vImage, String vType, int vYear, int vCylinderCapacity, int vAxes, int price )
    {
        mainWindow.addVehicle( this, vModel, vBrand, vImage, vType, vYear, vCylinderCapacity, vAxes, price );
    }
}