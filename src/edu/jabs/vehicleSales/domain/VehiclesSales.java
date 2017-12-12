package edu.jabs.vehicleSales.domain;

import java.util.ArrayList;

/**
 * The class that manages, sorts, loads and saves the vehicles.
 * <b>inv </b> <br>
 * vehicles != null and there are not two vehicles with the same name
 */
public class VehiclesSales
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The list that contains all the vehicles
     */
    private ArrayList vehicles;

    // -----------------------------------------------------------------
    // Constructor Methods
    // -----------------------------------------------------------------

    /**
     * Constructs a new empty vehicle manager.
     */
    public VehiclesSales( )
    {
        vehicles = new ArrayList( );

    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns a list of vehicles. The list that is returned is not the one stored in this class,<br>
     * but it has the same order.
     * @return vehicles list
     */
    public ArrayList getVehicles( )
    {
        ArrayList copyList = new ArrayList( vehicles );
        return copyList;
    }

    /**
     * Sorts the vehicles list by brand using the bubble algorithm <br>
     * <b>post: </b> The vehicles list is sorted by brand
     */
    public void sortByBrand( )
    {
        int initial;

        for( initial = 0; initial < vehicles.size( ); initial++ )
        {
            for( int i = vehicles.size( ) - 1; i > initial; i-- )
            {
                Vehicle p2 = ( Vehicle )vehicles.get( i );
                Vehicle p1 = ( Vehicle )vehicles.get( i - 1 );

                if( p1.compareByBrand( p2 ) > 0 )
                {
                    vehicles.set( i, p1 );
                    vehicles.set( i - 1, p2 );
                }
            }
        }
        verifyInvariant( );
    }

    /**
     * Sorts the list of vehicles by cylinder capacity using the insertion algorithm <br>
     * <b>post: </b> The sorted list of vehicles by cylinder capacity
     */
    public void sortByCylinderCapacity( )
    {
        int initial;

        // In each step it is known that:
        // 1. The positions before the vehicles[initial] are sorted
        // In each step what is done is: found the position between vehicles[0] and vehicles{initial]
        // in which the vehicle that in this moment is in vehicles[0] must be.
        for( initial = 1; initial < vehicles.size( ); initial++ )
        {
            Vehicle inserted = ( Vehicle )vehicles.get( initial );

            boolean finish = false;
            int i = initial - 1;

            while( !finish )
            {
                // If a higher cylinder capacity is found, then exchange them
                Vehicle vehiclePosition = ( Vehicle )vehicles.get( i );

                if( vehiclePosition.compareByCylinderCapacity( inserted ) > 0 )
                {
                    vehicles.set( i, inserted );
                    vehicles.set( i + 1, vehiclePosition );
                    i--;
                }
                // if an equal or fewer cylinder capacity is found then the position is found
                else
                {
                    finish = true;
                }

                // if it's the beginning of the list then there is not more to do
                if( i < 0 )
                {
                    finish = true;
                }
            }
        }
        verifyInvariant( );
    }

    /**
     * Sorts the vehicles list by year using the selection algorithm
     * <b>post: </b> the vehicles list is sorted by year
     */
    public void sortByYear( )
    {
        int initial;

        // In each step it is known that:
        // 1. The positions before the vehicles[initial] are sorted
        // There is not any value after vehicles[initial-1] that is fewer than vehicles[initial-1]
        // In each step the fewer value between vehicles[initial] and vehicles[final] is searched and is set in vehicles[initial]

        for( initial = 0; initial < vehicles.size( ); initial++ )
        {
            int fewerPosition = initial;
            Vehicle fewerVehicle = ( Vehicle )vehicles.get( initial );

            for( int i = initial + 1; i < vehicles.size( ); i++ )
            {
                Vehicle vehiclePosition = ( Vehicle )vehicles.get( i );

                //The vehicle in the current position is fewer than the found
                if( vehiclePosition.compareByYear( fewerVehicle ) < 0 )
                {
                    fewerVehicle = vehiclePosition;
                    fewerPosition = i;
                }
            }

            if( fewerPosition != initial )
            {
                Vehicle temp = ( Vehicle )vehicles.get( initial );
                vehicles.set( initial, fewerVehicle );
                vehicles.set( fewerPosition, temp );
            }

        }
        verifyInvariant( );
    }

    /**
     * Searches a vehicle by its model and returns the its position<br>
     * @param model The searched model of the vehicle - model != null
     * @param year The year of the searched vehicle - year > 0
     * @return Returns the position of a vehicle with the given model and year. If it is not found returns -1.
     */
    public int searchVehicle( String model, int year )
    {
        int position = -1;
        boolean finish = false;

        for( int i = 0; i < vehicles.size( ) && !finish; i++ )
        {
            Vehicle vehiclePosition = ( Vehicle )vehicles.get( i );
            String vehicleModel = vehiclePosition.getModel( );

            // The models are equal
            if( vehicleModel.equals( model ) && vehiclePosition.getYear( ) == year )
            {
                position = i;
                finish = true;
            }
        }

        return position;
    }

    /**
     * Adds a new vehicle
     * @param vModel The vehicle model - vModel != null
     * @param vBrand The vehicle brand - vBrand != null
     * @param pImage The vehicle image route - pImage != null
     * @param vType The vehicle type - vType belongs to {BUS, CAR, MOTORBIKE, TRUCK}
     * @param vYear The vehicle year - vYear > 0
     * @param vCylinderCapacity The vehicle cylinder capacity - vCylinderCapacity > 0
     * @param vAxes Number of axes of the vehicle - vAxes > 0
     * @param price The vehicle price - price > 0
     * @return Returns true if the vehicle was added otherwise false
     */
    public boolean addVehicle( String vModel, String vBrand, String pImage, String vType, int vYear, int vCylinderCapacity, int vAxes, int value )
    {
        boolean added = false;
        int searchedVehicle = searchVehicle( vModel, vYear );
        if( searchedVehicle == -1 )
        {
            Vehicle newVehicle = new Vehicle( vModel, vBrand, pImage, vType, vYear, vCylinderCapacity, vAxes, value );
            vehicles.add( newVehicle );
            added = true;
            verifyInvariant( );
        }

        return added;

    }

    /**
     * Removes the given vehicle from the list
     * @param model The vehicle model - model != null
     * @param year The vehicle year - year>0
     * @return Returns true if the vehicle was purchased otherwise false
     */
    public boolean purchaseVehicle( String model, int year )
    {
        boolean purchased = false;

        for( int count = 0; count < vehicles.size( ) && !purchased; count++ )
        {
            Vehicle v = ( Vehicle )vehicles.get( count );

            if( v.getYear( ) == year && v.getModel( ).equalsIgnoreCase( model ) )
            {
                vehicles.remove( count );
                purchased = true;
            }
        }

        return purchased;
    }

    /**
     * Searches the oldest vehicle
     * @return Returns the position of the oldest vehicle. if there are not vehicles returns -1.
     */
    public int searchOldestVehicle( )
    {
        int position = -1;

        if( vehicles.size( ) > 0 )
        {
            Vehicle oldestVehicle = ( Vehicle )vehicles.get( 0 );
            position = 0;
            for( int i = 1; i < vehicles.size( ); i++ )
            {
                Vehicle vPosition = ( Vehicle )vehicles.get( i );

                if( oldestVehicle.compareByYear( vPosition ) == 1 )
                {
                    position = i;
                    oldestVehicle = vPosition;
                }
            }
        }
        return position;
    }

    /**
     * Searches The cheapest vehicle
     * @return Returns the position of the cheapest vehicle. if there are not vehicles returns -1.
     */
    public int searchCheapestVehicle( )
    {
        int position = -1;

        if( vehicles.size( ) > 0 )
        {
            Vehicle cheapestV = ( Vehicle )vehicles.get( 0 );
            position = 0;
            for( int i = 1; i < vehicles.size( ); i++ )
            {
                Vehicle vPosition = ( Vehicle )vehicles.get( i );

                if( cheapestV.compareByValue( vPosition ) == 1 )
                {
                    position = i;
                    cheapestV = vPosition;
                }
            }
        }
        return position;
    }

    /**
     * Searches the most powerful vehicle.
     * @return Returns the position of the most powerful vehicle. If there are not vehicles returns -1.
     */
    public int searchMostPowerfullVehicle( )
    {
        int position = -1;

        if( vehicles.size( ) > 0 )
        {
            Vehicle mostPowerfullV = ( Vehicle )vehicles.get( 0 );
            position = 0;
            for( int i = 1; i < vehicles.size( ); i++ )
            {
                Vehicle vPosition = ( Vehicle )vehicles.get( i );

                if( mostPowerfullV.compareByCylinderCapacity( vPosition ) == -1 )
                {
                    position = i;
                    mostPowerfullV = vPosition;
                }
            }
        }
        return position;
    }

    /**
     * Reduces the in a 10% the price of all vehicles which price is higher than the given. <br>
     * The price of a vehicle is reduced if it's value does not become 0.
     * @param value The value to do the prices reduction. value > 0
     * @return The quantity of vehicles which price was reduced.
     */
    public int reducePrice( int value )
    {
        int reduced = 0;

        for( int count = 0; count < vehicles.size( ); count++ )
        {
            Vehicle v = ( Vehicle )vehicles.get( count );

            if( v.getPrice( ) > value )
            {
                int nValue = ( int ) ( v.getPrice( ) * 0.9 );

                if( nValue > 0 )
                {
                    v.changePrice( nValue );
                    reduced++;
                }
            }
        }
        return reduced;
    }

    // -----------------------------------------------------------------
    // Invariant
    // -----------------------------------------------------------------
    /**
     * Verifies the invariant
     *
     */
    private void verifyInvariant( )
    {
        assert ( vehicles != null ) : "The vehicle list must not be null";
        assert ( !searchVehiclesWithRepeatedModelYear( ) ) : "There are two vehicles with the same model and year";
    }

    /**
     * Verifies if there are two vehicles with the same model an year
     * @return Returns true if there are two vehicles with the same model and year
     */
    private boolean searchVehiclesWithRepeatedModelYear( )
    {
        for( int i = 0; i < vehicles.size( ); i++ )
        {
            Vehicle fi = ( Vehicle )vehicles.get( i );
            for( int j = 0; j < vehicles.size( ); j++ )
            {
                if( i != j )
                {
                    Vehicle fj = ( Vehicle )vehicles.get( j );
                    if( fj.getModel( ).equals( fi.getModel( ) ) && fj.getYear( ) == fi.getYear( ) )
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // -----------------------------------------------------------------
    // Extension Points
    // -----------------------------------------------------------------

    /**
     * Executes the extension point 1
     * @return answer 1
     */
    public String answer1( )
    {
        return "answer1";
    }

    /**
     * Executes the extension point 2
     * @return answer 2
     */
    public String answer2( )
    {
        return "answer2";
    }
}
