package edu.jabs.vehicleSales.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.jabs.vehicleSales.domain.Vehicle;

/**
 * Panel where the vehicles list is shown
 */
public class VehiclesListPanel extends JPanel implements ListSelectionListener
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Reference to the main window
     */
    private VehiclesSalesGUI mainWindow;

    // -----------------------------------------------------------------
    // GUI Attributes
    // -----------------------------------------------------------------

    /**
     * Vehicles list
     */
    private JList vehiclesList;

    // -----------------------------------------------------------------
    // Constructor Methods
    // -----------------------------------------------------------------

    /**
     * Constructs the panel and initializes all it's components
     * @param vsg Reference to the main window - iec != vsg
     */
    public VehiclesListPanel( VehiclesSalesGUI vsg )
    {
        mainWindow = vsg;
        setLayout( new BorderLayout( ) );

        JPanel panelListaYBotones = new JPanel( );
        panelListaYBotones.setLayout( new BorderLayout( ) );
        panelListaYBotones.setBorder( new CompoundBorder( new EmptyBorder( 4, 3, 3, 3 ), new TitledBorder( "Vehicles for Sale" ) ) );

        vehiclesList = new JList( );
        vehiclesList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        vehiclesList.addListSelectionListener( this );

        JScrollPane scroll = new JScrollPane( );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setBorder( new CompoundBorder( new EmptyBorder( 3, 3, 3, 3 ), new LineBorder( Color.BLACK, 1 ) ) );
        scroll.getViewport( ).add( vehiclesList );

        panelListaYBotones.add( scroll, BorderLayout.CENTER );
        add( panelListaYBotones, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the vehicles list
     * @param updatedVehiclesList List with the vehicles that must be shown
     */
    public void updateList( ArrayList updatedVehiclesList )
    {
        vehiclesList.setListData( updatedVehiclesList.toArray( ) );
        vehiclesList.setSelectedIndex( 0 );

    }

    /**
     * Selects an elements from the list
     * @param selected The position of the element that must be selected
     */
    public void select( int selected )
    {
        vehiclesList.setSelectedIndex( selected );
        vehiclesList.ensureIndexIsVisible( selected );
    }

    /**
     * Informs if there is a selected item
     * @return true an item is selected otherwise false
     */
    public boolean isSelected( )
    {
        return !vehiclesList.isSelectionEmpty( );
    }

    /**
     * Returns the selected vehicle of the list
     * @return returns the selected vehicle
     */
    public Vehicle getSelectedVehicle( )
    {
        Vehicle selectedV = null;

        if( vehiclesList.getSelectedValue( ) != null )
        {
            selectedV = ( Vehicle )vehiclesList.getSelectedValue( );
        }

        return selectedV;
    }

    /**
     * Changes the information of the candidate that is being shown with the information of the new candidate
     * @param e Event of the change of item
     */
    public void valueChanged( ListSelectionEvent e )
    {
        if( vehiclesList.getSelectedValue( ) != null )
        {
            Vehicle vehicle = ( Vehicle )vehiclesList.getSelectedValue( );
            mainWindow.viewInfo( vehicle );
        }

    }
}
