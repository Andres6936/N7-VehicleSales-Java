package edu.jabs.vehicleSales.test;

import junit.framework.TestCase;

import edu.jabs.vehicleSales.domain.Vehicle;

/**
 * Class to test the vehicle
 */
public class VehicleTest extends TestCase
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The first vehicle use for the test
     */
    private Vehicle vehicle1;

    /**
     * The second vehicle use for the test
     */
    private Vehicle vehicle2;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Creates a couple of vehicles
     */
    private void setupScenary1( )
    {
        vehicle1 = new Vehicle( "model1", "brand1", "image1", Vehicle.CAR, 1, 1, 2, 8000000 );
        vehicle2 = new Vehicle( "model2", "brand2", "image2", Vehicle.CAR, 2, 2, 2, 4500000 );
    }

    /**
     * Verifies the constructor of the Vehicle class. <br>
     * <b> Test methods: </b> <br>
     * Vehicle. <br>
     * <b> Objective: </b> Test that the constructor method creates a vehicle in a the right way. <br>
     * <b> Expected results: </b> <br>
     * 1. When creating a vehicle, all it's information must be well initialized
     *
     */
    public void testVehicle1( )
    {
        setupScenary1( );

        assertEquals( "Wrong model", "model1", vehicle1.getModel( ) );
        assertEquals( "Wrong brand", "brand1", vehicle1.getBrand( ) );
        assertEquals( "Wrong image", "image1", vehicle1.getImage( ) );
        assertEquals( "Wrong type", Vehicle.CAR, vehicle1.getType( ) );
        assertEquals( "Wrong cylinder capacity", 1, vehicle1.getCylinderCapacity( ) );
        assertEquals( "Wrong axes", 2, vehicle1.getAxes( ) );
        assertEquals( "Wrong year", 1, vehicle1.getYear( ) );
        assertEquals( "Wrong price", 8000000, vehicle1.getPrice( ) );
    }

    /**
     * Verifies the method compare by year. <br>
     * <b> Test methods: </b> <br>
     * compareByYear. <br>
     * <b> Objective: </b> Test that the method compareByYear compares the year correctly. <br>
     * <b> Expected results: </b> <br>
     * 1. When comparing two vehicles which year is equal must return 0. <br>
     * 2. When comparing vehicle A with vehicle B, B have a higher year, it must return -1. <br>
     * 3. When comparing vehicle A with vehicle B, B have a lower year, it must return 1. <br>
     */
    public void testCompareByYear( )
    {
        setupScenary1( );

        assertEquals( "Wrong year comparison", -1, vehicle1.compareByYear( vehicle2 ) );
        assertEquals( "Wrong year comparison", 0, vehicle1.compareByYear( vehicle1 ) );
        assertEquals( "Wrong year comparison", 1, vehicle2.compareByYear( vehicle1 ) );

    }

    /**
     * Verifies the method compareByCylinder <br>
     * <b> Test methods: </b> <br>
     * compareByCylinder. <br>
     * <b> Objective: </b> Test that the method comparByCylinder() compares the cylinder correctly
     * <b> Expected results: </b> <br>
     * 1. When comparing two vehicles which cylinder capacity is equal
     * 2. When comparing vehicle A with vehicle B, B have a higher cylinder capacity, it must return -1. <br>
     * 3. When comparing vehicle A with vehicle B, B have a lower cylinder capacity, it must return 1. <br>
     *
     */
    public void testCompareByCylinder( )
    {
        setupScenary1( );

        assertEquals( "Wrong cylinder capacity comparison", -1, vehicle1.compareByCylinderCapacity( vehicle2 ) );
        assertEquals( "Wrong cylinder capacity comparison", 0, vehicle1.compareByCylinderCapacity( vehicle1 ) );
        assertEquals( "Wrong cylinder capacity comparison", 1, vehicle2.compareByCylinderCapacity( vehicle1 ) );
    }

    /**
     * Verifies the method compareByBrand <br>
     * <b> Test methods: </b> <br>
     * compareByBrand. <br>
     * <b> Objective: </b> Test that the method compareByBrand() compares the cylinder correctly
     * <b> Expected results: </b> <br>
     * 1. When comparing two vehicles which brand is equal
     * 2. When comparing vehicle A with vehicle B, B have a higher brand, it must return -1. <br>
     * 3. When comparing vehicle A with vehicle B, B have a lower brand, it must return 1. <br>
     *
     */
    public void testCompareByBrand( )
    {
        setupScenary1( );

        assertEquals( "Wrong brand comparison", -1, vehicle1.compareByBrand( vehicle2 ) );
        assertEquals( "Wrong brand comparison", 0, vehicle1.compareByBrand( vehicle1 ) );
        assertEquals( "Wrong brand comparison", 1, vehicle2.compareByBrand( vehicle1 ) );

    }

    /**
     * Verifies the method compareByPrice <br>
     * <b> Test methods: </b> <br>
     * compareByPrice. <br>
     * <b> Objective: </b> Test that the method compareByPrice() compares the cylinder correctly
     * <b> Expected results: </b> <br>
     * 1. When comparing two vehicles which price is equal
     * 2. When comparing vehicle A with vehicle B, B have a higher price, it must return -1. <br>
     * 3. When comparing vehicle A with vehicle B, B have a lower price, it must return 1. <br>
     *
     */
    public void testCompareByProce( )
    {
        setupScenary1( );

        assertEquals( "Wrong price comparison", 1, vehicle1.compareByValue( vehicle2 ) );
        assertEquals( "Wrong price comparison", 0, vehicle1.compareByValue( vehicle1 ) );
        assertEquals( "Wrong price comparison", -1, vehicle2.compareByValue( vehicle1 ) );
    }
}
