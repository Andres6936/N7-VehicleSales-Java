package edu.jabs.vehicleSales.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.jabs.vehicleSales.domain.VehiclesSales;
import edu.jabs.vehicleSales.domain.Vehicle;

/**
 * The main class of the interface
 */
public class VehiclesSalesGUI extends JFrame
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Path where the file with the vehicle info is
     */
    public static final String VEHICLES_FILE = "./data/vehicles.dat";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Reference to the vehicles sales
     */
    private VehiclesSales vehicleSales;

    // -----------------------------------------------------------------
    // GUI Attributes
    // -----------------------------------------------------------------

    /**
     * The panel where the list is shown
     */
    private VehiclesListPanel listPanel;

    /**
     * The panel where the info is shown
     */
    private VehicleInfoPanel infoPanel;

    /**
     * The panel where the extension buttons are shown
     */
    private ExtensionPanel extensionPanel;

    /**
     * The panel where the search and sort buttons are shown
     */
    private SearchSortPanel searchSortPanel;

    /**
     * The panel where the consultation and operations buttons are shown
     */
    private ConsultationsOperationsPanel consultationsOperationsPanel;

    /**
     * Image panel
     */
    private ImagePanel imagePanel;

    // -----------------------------------------------------------------
    // Constructor Methods
    // -----------------------------------------------------------------

    /**
     * Constructs the interface and it's components
     */
    public VehiclesSalesGUI( )
    {
        vehicleSales = new VehiclesSales( );

        loadVehicles( VEHICLES_FILE );

        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbc = new GridBagConstraints( 1, 1, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        infoPanel = new VehicleInfoPanel( );
        add( infoPanel, gbc );

        gbc = new GridBagConstraints( 0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        listPanel = new VehiclesListPanel( this );
        add( listPanel, gbc );

        gbc = new GridBagConstraints( 0, 3, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        extensionPanel = new ExtensionPanel( this );
        add( extensionPanel, gbc );

        searchSortPanel = new SearchSortPanel( this );
        gbc = new GridBagConstraints( 0, 2, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( searchSortPanel, gbc );

        consultationsOperationsPanel = new ConsultationsOperationsPanel( this );
        gbc = new GridBagConstraints( 1, 2, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( consultationsOperationsPanel, gbc );

        imagePanel = new ImagePanel( );
        gbc = new GridBagConstraints( 0, 0, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 3, 5, 3, 5 ), 0, 0 );
        add( imagePanel, gbc );

        updateList( );

        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( "Vehicles Sales" );

        setSize( new Dimension( 911, 576 ) );
        setResizable( false );

        center( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Centers the window
     */
    private void center( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int cornerX = ( screen.width - getWidth( ) ) / 2;
        int cornerY = ( screen.height - getHeight( ) ) / 2;
        setLocation( cornerX, cornerY );
    }

    /**
     * Updates the vehicles list
     */
    private void updateList( )
    {
        listPanel.updateList( vehicleSales.getVehicles( ) );
    }

    /**
     * Sort the vehicles by brand
     */
    public void sortByBrand( )
    {
        vehicleSales.sortByBrand( );
        infoPanel.cleanInfo( );
        updateList( );
    }

    /**
     * Sorts the vehicles by cylinder capacity
     */
    public void sortByCylinder( )
    {
        vehicleSales.sortByCylinderCapacity( );
        infoPanel.cleanInfo( );
        updateList( );
    }

    /**
     * Sorts the vehicles by year
     */
    public void sortByYear( )
    {
        vehicleSales.sortByYear( );
        infoPanel.cleanInfo( );
        updateList( );
    }

    /**
     * Searches a vehicle using the model and the year
     */
    public void search( )
    {
        String searchedModel = JOptionPane.showInputDialog( this, "Searched Model" );

        if( searchedModel != null )
        {
            String searchedYear = JOptionPane.showInputDialog( this, "Searched Year" );
            if( searchedYear != null )
            {
                try
                {
                    int year = Integer.parseInt( searchedYear );

                    int position = vehicleSales.searchVehicle( searchedModel, year );

                    updateList( );
                    if( position != -1 )
                    {
                        listPanel.select( position );
                        Vehicle v = ( Vehicle )vehicleSales.getVehicles( ).get( position );
                        viewInfo( v );
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, "Vehicle was not found" );
                    }
                }
                catch( NumberFormatException nfe )
                {
                    JOptionPane.showMessageDialog( this, "The year must be an integer", "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
    }

    /**
     * Shows the info of a vehicle in the corresponding panel
     * @param vehicle The selected vehicle - vehicle != null
     */
    public void viewInfo( Vehicle vehicle )
    {
        infoPanel.showInfo( vehicle );
        pack( );
    }

    /**
     * Shows the dialog to add a vehicle
     */
    public void mostrarDialogoAgregarVehiculo( )
    {
        AddVehicleDialog avd = new AddVehicleDialog( this );
        avd.setVisible( true );
    }

    /**
     * Adds a new vehicle
     * @param dialog The dialog used to add the vehicle
     * @param vModel Model name - modeloV != null
     * @param vBrand Vehicle brand - marcaV != null
     * @param vImage Vehciel image - imagenP != null
     * @param vType The vehicle type - vType belongs to {BUS, CAR, MOTORBIKE, TRUCK}
     * @param vYear The vehicle year - vYear > 0
     * @param vCylinderCapacity The vehicle cylinder capacity - vCylinderCapacity > 0
     * @param vAxes Number of axes of the vehicle - vAxes > 0
     * @param price The vehicle price - price > 0
     */
    public void addVehicle( AddVehicleDialog dialog, String vModel, String vBrand, String vImage, String vType, int vYear, int vCylinderCapacity, int vAxes, int price )
    {

        boolean added = vehicleSales.addVehicle( vModel, vBrand, vImage, vType, vYear, vCylinderCapacity, vAxes, price );

        if( added )
        {
            updateList( );
            dialog.dispose( );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "The vehicle was not added", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Searches the oldest vehicle
     */
    public void searchOldest( )
    {
        int position = vehicleSales.searchOldestVehicle( );

        updateList( );
        if( position != -1 )
        {
            listPanel.select( position );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "There are not vehicles on sale", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Searches the most powerful vehicle
     */
    public void searchMostPowerful( )
    {
        int position = vehicleSales.searchMostPowerfullVehicle( );

        updateList( );
        if( position != -1 )
        {
            listPanel.select( position );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "There are not vehicles on sale", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Searches the cheapest vehicle
     */
    public void searchCheapest( )
    {
        int position = vehicleSales.searchCheapestVehicle( );

        updateList( );
        if( position != -1 )
        {
            listPanel.select( position );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "There are not vehicles on sale", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Buys the selected vehicles
     */
    public void purchaseVehicle( )
    {
        Vehicle v = listPanel.getSelectedVehicle( );

        if( v != null )
        {
            vehicleSales.purchaseVehicle( v.getModel( ), v.getYear( ) );
            infoPanel.cleanInfo( );
            updateList( );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "You must select a vehicle", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Reduces the price of a vehicle
     */
    public void reducePrice( )
    {
        String precio = JOptionPane.showInputDialog( this, "Price to make the discount" );

        if( precio != null )
        {
            if( precio.equals( "" ) )
            {
                JOptionPane.showMessageDialog( this, "Price must be an integer", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                try
                {
                    int valor = Integer.parseInt( precio );

                    if( valor > 0 )
                    {
                        int reducedVehicles = vehicleSales.reducePrice( valor );

                        if( reducedVehicles > 0 )
                        {
                            JOptionPane.showMessageDialog( this, "The price of " + reducedVehicles + " was reduced", "Vehicles Sales", JOptionPane.INFORMATION_MESSAGE );
                        }
                        else
                        {
                            JOptionPane.showMessageDialog( this, "The price was not reduced", "Vehicles Sales", JOptionPane.INFORMATION_MESSAGE );
                        }

                        updateList( );
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, "Price must be a positive integer", "Error", JOptionPane.ERROR_MESSAGE );
                    }
                }
                catch( NumberFormatException nfe )
                {
                    JOptionPane.showMessageDialog( this, "Price must be an integer", "Error", JOptionPane.ERROR_MESSAGE );
                }
            }

        }
    }

    /**
     * Loads the initial vehicles using a properties file
     * @param file  name of the properties file - file!=null
     */
    private void loadVehicles( String file )
    {

        try
        {
            FileInputStream fis = new FileInputStream( new File( file ) );
            Properties properties = new Properties( );
            properties.load( fis );

            // load the vehicles
            String data;
            String model;
            String brand;
            String image;
            String type;
            int year;
            int cylinderCapacity;
            int axes;
            int value;
            String aux;
            data = "total.vehicles";
            aux = properties.getProperty( data );
            int quantity = Integer.parseInt( aux );

            for( int count = 1; count <= quantity; count++ )
            {

                // Loads a vehicle
                data = "vehicle" + count + ".model";
                model = properties.getProperty( data );

                data = "vehicle" + count + ".brand";
                brand = properties.getProperty( data );

                data = "vehicle" + count + ".brand";
                brand = properties.getProperty( data );

                data = "vehicle" + count + ".image";
                image = properties.getProperty( data );

                data = "vehicle" + count + ".type";
                type = properties.getProperty( data );

                data = "vehicle" + count + ".year";
                aux = properties.getProperty( data );
                year = Integer.parseInt( aux );

                data = "vehicle" + count + ".cylinderCapacity";
                aux = properties.getProperty( data );
                cylinderCapacity = Integer.parseInt( aux );

                data = "vehicle" + count + ".axes";
                aux = properties.getProperty( data );
                axes = Integer.parseInt( aux );

                data = "vehicle" + count + ".price";
                aux = properties.getProperty( data );
                value = Integer.parseInt( aux );

                // The vehicle is load only if the info is complete
                if( model != null && brand != null && image != null && type != null && year > 0 && cylinderCapacity > 0 && axes > 0 && value > 0 )
                    vehicleSales.addVehicle( model, brand, image, type, year, cylinderCapacity, axes, value );
                fis.close( );
            }
        }
        catch( FileNotFoundException e )
        {
            JOptionPane.showMessageDialog( this, "Problems loading the info of the vehicles", "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog( this, "Problems loading the info of the vehicles", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    // -----------------------------------------------------------------
    // Extension Points
    // -----------------------------------------------------------------

    /**
     * Executes the extension point 1
     */
    public void funcReqOption1( )
    {
        String answer = vehicleSales.answer1( );
        JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Executes the extension point 2
     */
    public void funcReqOption2( )
    {
        String answer = vehicleSales.answer2( );
        JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Executes the application
     * @param args application parameters. They are not used
     */
    public static void main( String[] args )
    {
        VehiclesSalesGUI vsg = new VehiclesSalesGUI( );
        vsg.setVisible( true );
    }
}
