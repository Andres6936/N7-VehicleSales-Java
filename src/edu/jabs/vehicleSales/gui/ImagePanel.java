package edu.jabs.vehicleSales.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Panel where the image is shown
 */
public class ImagePanel extends JPanel
{

    // -----------------------------------------------------------------
    // GUI Attributes
    // -----------------------------------------------------------------

    /**
     * Title image
     */
    private JLabel image;

    // -----------------------------------------------------------------
    // Constructor Methods
    // -----------------------------------------------------------------

    /**
     * Panel constructor
     */
    public ImagePanel( )
    {
        FlowLayout layout = new FlowLayout( );
        layout.setHgap( 0 );
        layout.setVgap( 0 );
        setLayout( layout );

        // Load the image
        ImageIcon icon = new ImageIcon( "data/title.png" );

        // Add the image to the label
        image = new JLabel( "" );
        image.setIcon( icon );
        add( image );

        // Background color
        setBackground( Color.WHITE );
        setBorder( new LineBorder( Color.GRAY ) );
    }
}
