package edu.jabs.vehicleSales.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import edu.jabs.vehicleSales.domain.Vehicle;

/**
 * This is the panel where vehicles are added
 */
public class AddVehiclePanel extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ADD = "AddVehicle";

    private static final String SEARCH = "SearchImage";

    private static final String[] TYPES = new String[]{ Vehicle.BUS, Vehicle.CAR, Vehicle.TRUCK, Vehicle.MOTORBIKE };

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * This is a reference to the dialog that contains the panel
     */
    private AddVehicleDialog dialog;

    // -----------------------------------------------------------------
    // GUI Attributes
    // -----------------------------------------------------------------

    /**
     * The field for the path to the image
     */
    private JTextField imageTxt;

    /**
     * Vehicle model field
     */
    private JTextField modelTxt;

    /**
     * Vehicle brand field
     */
    private JTextField brandTxt;

    /**
     * Vehicle year field
     */
    private JTextField yearTxt;

    /**
     * Vehicle cylinder capacity field
     */
    private JTextField cylinderCapacityField;

    /**
     * Vehicle axes field
     */
    private JTextField axesTxt;

    /**
     * Vehicle price field
     */
    private JTextField priceTxt;

    /**
     * Select vehicle type comboBox
     */
    private JComboBox typeCombo;

    /**
     * Vehicle image label
     */
    private JLabel imageLabel;

    /**
     * Vehicle model label
     */
    private JLabel modelLabel;

    /**
     * Vehicle brand label
     */
    private JLabel brandLabel;

    /**
     * Vehicle type label
     */
    private JLabel typeLabel;

    /**
     * Vehicle year label
     */
    private JLabel yearLabel;

    /**
     * Cylinder capacity label
     */
    private JLabel cylinderCapacityLabel;

    /**
     * Vehicle axes label
     */
    private JLabel axesLabel;

    /**
     * Vehicle price label
     */
    private JLabel priceLabel;

    /**
     * Add button
     */
    private JButton addButton;

    /**
     * Browse button
     */
    private JButton browseButton;

    // -----------------------------------------------------------------
    // Constructor Methods
    // -----------------------------------------------------------------

    /**
     * Constructs the panel and initializes it's components
     * @param avd reference to the dialog that contains the panel
     */
    public AddVehiclePanel( AddVehicleDialog avd )
    {
        dialog = avd;

        setLayout( new BorderLayout( ) );
        setBorder( new CompoundBorder( new EmptyBorder( 4, 3, 3, 3 ), new TitledBorder( "Add Vehicle" ) ) );

        JPanel panelDatos = new JPanel( new GridLayout( 8, 2 ) );

        // Model
        modelLabel = new JLabel( "Model: " );
        panelDatos.add( modelLabel );
        modelTxt = new JTextField( "" );
        panelDatos.add( modelTxt );

        // Brand
        brandLabel = new JLabel( "Brand: " );
        panelDatos.add( brandLabel );
        brandTxt = new JTextField( "" );
        panelDatos.add( brandTxt );

        // Image
        imageLabel = new JLabel( "Image: " );
        panelDatos.add( imageLabel );
        imageTxt = new JTextField( "" );
        browseButton = new JButton( "Browse" );
        browseButton.setActionCommand( SEARCH );
        browseButton.addActionListener( this );

        JPanel panelImagen = new JPanel( new GridLayout( 1, 2 ) );
        panelImagen.add( imageTxt );
        panelImagen.add( browseButton );
        panelDatos.add( panelImagen );

        // Type
        typeLabel = new JLabel( "Type: " );
        panelDatos.add( typeLabel );
        typeCombo = new JComboBox( TYPES );
        panelDatos.add( typeCombo );

        // Year
        yearLabel = new JLabel( "Year: " );
        panelDatos.add( yearLabel );
        yearTxt = new JTextField( "" );
        panelDatos.add( yearTxt );

        // Cylinder Capacity
        cylinderCapacityLabel = new JLabel( "Cylinder Capacity: " );
        panelDatos.add( cylinderCapacityLabel );
        cylinderCapacityField = new JTextField( "" );
        panelDatos.add( cylinderCapacityField );

        // Axes
        axesLabel = new JLabel( "Axes: " );
        panelDatos.add( axesLabel );
        axesTxt = new JTextField( "" );
        panelDatos.add( axesTxt );

        // Price
        priceLabel = new JLabel( "Price: " );
        panelDatos.add( priceLabel );
        priceTxt = new JTextField( "" );
        panelDatos.add( priceTxt );

        // Add button
        JPanel panelBoton = new JPanel( );
        addButton = new JButton( "Add Vehicle" );
        addButton.setActionCommand( ADD );
        addButton.addActionListener( this );
        panelBoton.add( addButton );

        add( panelDatos, BorderLayout.CENTER );
        add( panelBoton, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * This method is executed when the button is clicked
     * @param event The click event - event!=null
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );

        if( ADD.equals( command ) )
        {
            try
            {
                String model = modelTxt.getText( );
                String brand = brandTxt.getText( );
                String image = imageTxt.getText( );
                String type = ( String )typeCombo.getSelectedItem( );
                int year = Integer.parseInt( yearTxt.getText( ) );
                int cylinderCapacity = Integer.parseInt( cylinderCapacityField.getText( ) );
                int axes = Integer.parseInt( axesTxt.getText( ) );
                int price = Integer.parseInt( priceTxt.getText( ) );
                dialog.addVehicle( model, brand, image, type, year, cylinderCapacity, axes, price );

                modelTxt.setText( "" );
                brandTxt.setText( "" );
                imageTxt.setText( "" );
                yearTxt.setText( "" );
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "The year, cylinder capacity, axes and price must be positive values", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( SEARCH.equals( command ) )
        {
            JFileChooser fc = new JFileChooser( "./data" );
            fc.setDialogTitle( "Search the image of the vehicle" );
            fc.setMultiSelectionEnabled( false );

            int result = fc.showOpenDialog( this );
            if( result == JFileChooser.APPROVE_OPTION )
            {
                String image = fc.getSelectedFile( ).getAbsolutePath( );
                imageTxt.setText( image );
            }
        }

    }

}