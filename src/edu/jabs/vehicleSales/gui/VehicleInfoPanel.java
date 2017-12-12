package edu.jabs.vehicleSales.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import edu.jabs.vehicleSales.domain.Vehicle;

/**
 * Panel where the info of the vehicles is shown
 */
public class VehicleInfoPanel extends JPanel
{
    // -----------------------------------------------------------------
    // GUI Attributes
    // -----------------------------------------------------------------

    /**
     * Image label
     */
    private JLabel imageLabel;

    /**
     * Model label
     */
    private JLabel modelLabel;

    /**
     * Brand label
     */
    private JLabel brandLabel;

    /**
     * Type label
     */
    private JLabel typeLabel;

    /**
     * Year label
     */
    private JLabel yearLabel;

    /**
     * Cylinder capacity label
     */
    private JLabel CylinderLabel;

    /**
     * Axes label
     */
    private JLabel axesLabel;

    /**
     * Price label
     */
    private JLabel priceLabel;

    /**
     * Model field
     */
    private JTextField modelTxt;

    /**
     * Brand field
     */
    private JTextField brandTxt;

    /**
     * Year field
     */
    private JTextField yearTxt;

    /**
     * Cylinder capacity field
     */
    private JTextField cylinderTxt;

    /**
     * Axes field
     */
    private JTextField axesTxt;

    /**
     * Type field
     */
    private JTextField typeTxt;

    /**
     * Price field
     */
    private JTextField priceTxt;

    // -----------------------------------------------------------------
    // Constructor Methods
    // -----------------------------------------------------------------

    /**
     * Constructs the panel and initializes the components
     */
    public VehicleInfoPanel( )
    {
        setLayout( new GridBagLayout( ) );
        setBorder( new CompoundBorder( new EmptyBorder( 4, 3, 3, 3 ), new TitledBorder( "Vehicle Info" ) ) );

        GridBagConstraints gbcE = new GridBagConstraints( 0, 0, 1, 1, 0, 1, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        GridBagConstraints gbcC = new GridBagConstraints( 1, 0, 1, 1, 0, 0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets( 5, 5, 5, 5 ), 0, 0 );

        modelLabel = new JLabel( "Model: " );
        modelTxt = new JTextField( 10 );
        modelTxt.setEditable( false );
        add( modelLabel, gbcE );
        add( modelTxt, gbcC );

        gbcE.weighty = 0;
        gbcE.gridy = 1;
        gbcC.gridy = 1;
        brandLabel = new JLabel( "Brand: " );
        brandTxt = new JTextField( 10 );
        brandTxt.setEditable( false );
        add( brandLabel, gbcE );
        add( brandTxt, gbcC );

        gbcE.gridy = 2;
        gbcC.gridy = 2;
        typeLabel = new JLabel( "Type: " );
        typeTxt = new JTextField( 10 );
        typeTxt.setEditable( false );
        add( typeLabel, gbcE );
        add( typeTxt, gbcC );

        gbcE.gridy = 3;
        gbcC.gridy = 3;
        yearLabel = new JLabel( "Year: " );
        yearTxt = new JTextField( 10 );
        yearTxt.setEditable( false );
        add( yearLabel, gbcE );
        add( yearTxt, gbcC );

        gbcE.gridy = 4;
        gbcC.gridy = 4;
        CylinderLabel = new JLabel( "Cylinder Capacity: " );
        cylinderTxt = new JTextField( 10 );
        cylinderTxt.setEditable( false );
        add( CylinderLabel, gbcE );
        add( cylinderTxt, gbcC );

        gbcE.gridy = 5;
        gbcC.gridy = 5;
        axesLabel = new JLabel( "Axes: " );
        axesTxt = new JTextField( 10 );
        axesTxt.setEditable( false );
        add( axesLabel, gbcE );
        add( axesTxt, gbcC );

        gbcE.gridy = 6;
        gbcC.gridy = 6;
        priceLabel = new JLabel( "Price: " );
        priceTxt = new JTextField( 10 );
        priceTxt.setEditable( false );
        add( priceLabel, gbcE );
        add( priceTxt, gbcC );

        GridBagConstraints gbcI = new GridBagConstraints( 2, 0, 1, 7, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        JPanel panelImagen = new JPanel( new GridBagLayout( ) );
        imageLabel = new JLabel( );
        imageLabel.setBorder( new LineBorder( Color.BLACK, 1 ) );
        imageLabel.setMinimumSize( new Dimension( 200, 200 ) );
        panelImagen.add( imageLabel );
        add( panelImagen, gbcI );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Shows the information vehicle
     * @param vehicle The vehicles that are going to be shown - vehicle != null
     */
    public void showInfo( Vehicle vehicle )
    {
        String image = vehicle.getImage( );
        imageLabel.setIcon( new ImageIcon( image ) );

        modelTxt.setText( vehicle.getModel( ) );
        brandTxt.setText( vehicle.getBrand( ) );
        typeTxt.setText( vehicle.getType( ) );
        yearTxt.setText( "" + vehicle.getYear( ) );
        cylinderTxt.setText( "" + vehicle.getCylinderCapacity( ) );
        axesTxt.setText( "" + vehicle.getAxes( ) );
        priceTxt.setText( "" + vehicle.getPrice( ) );

        validate( );
    }

    /**
     * Cleans all the fields
     */
    public void cleanInfo( )
    {
        imageLabel.setIcon( null );
        modelTxt.setText( "" );
        brandTxt.setText( "" );
        typeTxt.setText( "" );
        yearTxt.setText( "" );
        cylinderTxt.setText( "" );
        axesTxt.setText( "" );
        priceTxt.setText( "" );
    }
}