package edu.jabs.vehicleSales.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel with search and sort operations
 */
public class SearchSortPanel extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String SEARCH = "Search";

    private static final String SORT_CYLINDER = "SortCylinderCapacity";

    private static final String SORT_YEAR = "SortYear";

    private static final String SORT_BRAND = "SortBrand";

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
     * Sort by brand button
     */
    private JButton sortBrandButton;

    /**
     * Sort by cylinder button
     */
    private JButton sortCylinderButton;

    /**
     * Sort by year button
     */
    private JButton sortYearButton;

    /**
     * Search button
     */
    private JButton searchButton;

    // -----------------------------------------------------------------
    // Constructor Methods
    // -----------------------------------------------------------------

    /**
     * Constructs the panel and initializes it's components
     * @param vsg Reference to the main window - vsg != null
     */
    public SearchSortPanel( VehiclesSalesGUI vsg )
    {
        mainWindow = vsg;
        setLayout( new GridBagLayout( ) );

        setBorder( new TitledBorder( "Search and Sorts" ) );

        sortBrandButton = new JButton( "Sort by Brand" );
        sortBrandButton.setActionCommand( SORT_BRAND );
        sortBrandButton.addActionListener( this );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 3, 3, 3, 3 );
        add( sortBrandButton, gbc );

        sortYearButton = new JButton( "Sort by Year" );
        sortYearButton.setActionCommand( SORT_YEAR );
        sortYearButton.addActionListener( this );
        gbc.gridy = 1;
        add( sortYearButton, gbc );

        sortCylinderButton = new JButton( "Sort by Cylinder Capacity" );
        sortCylinderButton.setActionCommand( SORT_CYLINDER );
        sortCylinderButton.addActionListener( this );
        gbc.gridx = 1;
        gbc.gridy = 0;
        add( sortCylinderButton, gbc );

        searchButton = new JButton( "Search Vehicle" );
        searchButton.setActionCommand( SEARCH );
        searchButton.addActionListener( this );
        gbc.gridy = 1;
        add( searchButton, gbc );

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

        if( SORT_BRAND.equals( command ) )
        {
            mainWindow.sortByBrand( );
        }
        else if( SORT_CYLINDER.equals( command ) )
        {
            mainWindow.sortByCylinder( );
        }
        else if( SORT_YEAR.equals( command ) )
        {
            mainWindow.sortByYear( );
        }
        else if( SEARCH.equals( command ) )
        {
            mainWindow.search( );
        }
    }
}
