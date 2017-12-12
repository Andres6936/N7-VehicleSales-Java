package edu.jabs.vehicleSales.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import junit.framework.TestCase;

import edu.jabs.vehicleSales.domain.VehiclesSales;
import edu.jabs.vehicleSales.domain.Vehicle;

/**
 * Test class for VehicleSales
 */
public class VehicleSalesTest extends TestCase
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * VehicleSales manager
     */
    private VehiclesSales vehicleSales;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Creates the manager from the file vehicles1.dat
     */
    private void setupScenario1( )
    {

        vehicleSales = new VehiclesSales( );
        loadVehicles( "./test/data/vehicles1.dat" );

    }

    /**
     * Creates the manager from the file vehicles2.dat
     */
    private void setupScenario2( )
    {
        vehicleSales = new VehiclesSales( );
        loadVehicles( "./test/data/vehicles2.dat" );
    }

    /**
     * Verifies the method addVehicle. <br>
     * <b> Test Methods: </b> <br>
     * searchVehicle, addCehicle. <br>
     * <b> Objective: </b> Test that the method addVehicle() adds correctly a new vechicle. <br>
     * <b> Expected results: </b> <br>
     * 1. When adding a vehicle, it's search must return a position diferent from -1.
     */
    public void testAddVehicle1( )
    {
        // test with Scenario 1
        setupScenario1( );

        int originalSize = vehicleSales.getVehicles( ).size( );
        int addedVehicles = 10;
        for( int cont = originalSize + 1; cont <= originalSize + addedVehicles; cont++ )
        {
            String vehicleMode = "model_" + cont;
            int vehicleYear = cont;
            boolean added = vehicleSales.addVehicle( vehicleMode, vehicleMode, vehicleMode, Vehicle.CAR, vehicleYear, 1000, 2, 50000000 );
            assertTrue( "The vehicle was not added", added );
            int position = vehicleSales.searchVehicle( vehicleMode, vehicleYear );
            assertTrue( "The vehicle was not added", position != -1 );
        }

        assertEquals( "The number of added vehicles is incorrect", originalSize + addedVehicles, vehicleSales.getVehicles( ).size( ) );

        // test with Scenario 2
        setupScenario2( );

        ArrayList vehicles = vehicleSales.getVehicles( );
        assertEquals( "The number of vehicles is incorrect", 8, vehicles.size( ) );

        String model = "SL2";
        int year = 1996;
        assertTrue( "The " + model + " of the year " + year+ " was not found", vehicleSales.searchVehicle( model, year ) != -1 );

        model = "Jetta";
        year = 1998;
        assertTrue( "The " + model + " of the year " + year+ " was not found" , vehicleSales.searchVehicle( model, year ) != -1 );

        model = "Explorer";
        year = 1999;
        assertTrue( "The " + model + " of the year " + year+ " was not found", vehicleSales.searchVehicle( model, year ) != -1 );

        model = "New Beetle";
        year = 2000;
        assertTrue( "The " + model + " of the year " + year+ " was not found", vehicleSales.searchVehicle( model, year ) != -1 );

        model = "Caravan";
        year = 2000;
        assertTrue( "The " + model + " of the year " + year+ " was not found", vehicleSales.searchVehicle( model, year ) != -1 );

        model = "TT";
        year = 2002;
        assertTrue( "The " + model + " of the year " + year+ " was not found", vehicleSales.searchVehicle( model, year ) != -1 );

        model = "CLK430";
        year = 2003;
        assertTrue( "The " + model + " of the year " + year+ " was not found", vehicleSales.searchVehicle( model, year ) != -1 );

        model = "S10 Pickup";
        year = 1983;
        assertTrue( "The " + model + " of the year " + year+ " was not found", vehicleSales.searchVehicle( model, year ) != -1 );
    }

    /**
     * Verifies the method addVehicle adding a new vehicle with repeated model and year. <br>
     * <b> Test methods: </b> <br>
     * addVehicle. <br>
     * <b> Objective: </b> Test that the method addVehicle does not add repeated vehicles. <br>
     * <b> Expected results: </b> <br>
     * 1. When adding a vehicle with repeated year and model this is can not be permitted
     */
    public void testAddVehicle2( )
    {
        setupScenario1( );

        ArrayList vehicles = vehicleSales.getVehicles( );
        Vehicle v = ( Vehicle )vehicles.get( 0 );
        String vehicleModel = v.getModel( );

        assertFalse( "The vehicle was added and this is not permited", vehicleSales.addVehicle( vehicleModel, vehicleModel, vehicleModel, Vehicle.CAR, v.getYear( ), 1000, 2, 500000 ) );

        assertEquals( "The vehicle was added and this is not permited", 12, vehicles.size( ) );

        Vehicle v2 = ( Vehicle )vehicles.get( 0 );

        assertEquals( "The vehicle info should remain equal", v.getYear( ), v2.getYear( ) );

        assertEquals( "The vehicle info should remain equal", v.getCylinderCapacity( ), v2.getCylinderCapacity( ) );

        assertEquals( "The vehicle info should remain equal", v.getAxes( ), v2.getAxes( ) );

        assertEquals( "The vehicle info should remain equal", v.getImage( ), v2.getImage( ) );

        assertEquals( "The vehicle info should remain equal", v.getBrand( ), v2.getBrand( ) );

        assertEquals( "The vehicle info should remain equal", v.getModel( ), v2.getModel( ) );

        assertEquals( "The vehicle info should remain equal", v.getType( ), v2.getType( ) );

        assertEquals( "The vehicle info should remain equal", v.getPrice( ), v2.getPrice( ) );

    }

    /**
     * Verifies the method searchVehicle. <br>
     * <b> Test methods: </b> <br>
     * searchVehicle. <br>
     * <b> Objective: </b> Test that the method searchVehicle() searches correctly the vehicles. <br>
     * <b> Expected results: </b> <br>
     * 1. When searching an added vehicle, it must be found.
     *
     */
    public void testSearchVehicle( )
    {
        setupScenario2( );

        // Sort the vehicles by year
        vehicleSales.sortByYear( );
        ArrayList vehicles = vehicleSales.getVehicles( );
        Vehicle v0 = ( Vehicle )vehicles.get( 0 );
        String vehicleModel = v0.getModel( );
        int vehicleYear = v0.getYear( );

        // Sort the vehicles by brand to sorter the list
        vehicleSales.sortByBrand( );

        // Search the vehicle by model
        int position = vehicleSales.searchVehicle( vehicleModel, vehicleYear );
        assertTrue( "Vehicle was not found", position != -1 );

        vehicles = vehicleSales.getVehicles( );
        Vehicle vn = ( Vehicle )vehicles.get( position );
        assertEquals( "Vehicle was not found", vn.getModel( ), vehicleModel );
    }

    /**
     * Verifies the method sort by cylinder capacity. <br>
     * <b> Test methods: </b> <br>
     * ordenarByCylinderCapacity. <br>
     * <b> Objective: </b> Test that the method ordenarByCylinderCapacity() sorts the vehicles correctly <br>
     * <b> Expected results: </b> <br>
     * 1. When sorting by cylinder capacity, the vehicles must be in ascending position
     */
    public void testSortByCylinderCapacity( )
    {
        setupScenario2( );

        vehicleSales.sortByCylinderCapacity( );
        ArrayList vehicles = vehicleSales.getVehicles( );
        for( int i = 1; i < vehicles.size( ); i++ )
        {
            Vehicle v0 = ( Vehicle )vehicles.get( i - 1 );
            Vehicle v1 = ( Vehicle )vehicles.get( i );

            assertTrue( "Incorrect sort by cylinderCapacity", v0.getCylinderCapacity( ) <= v1.getCylinderCapacity( ) );
        }
    }

    /**
     * Verifies the method sort by year. <br>
     * <b> Test methods: </b> <br>
     * ordenarByYear. <br>
     * <b> Objective: </b> Test that the method ordenarByYear() sorts the vehicles correctly <br>
     * <b> Expected results: </b> <br>
     * 1. When sorting by year, the vehicles must be in ascending position
     */
    public void testSortByYear( )
    {
        setupScenario2( );

        vehicleSales.sortByYear( );
        ArrayList vehicles = vehicleSales.getVehicles( );
        for( int i = 1; i < vehicles.size( ); i++ )
        {
            Vehicle v0 = ( Vehicle )vehicles.get( i - 1 );
            Vehicle v1 = ( Vehicle )vehicles.get( i );

            assertTrue( "Incorrect sort by year", v0.getYear( ) <= v1.getYear( ) );
        }
    }

    /**
     * Verifies the method sort by brand. <br>
     * <b> Test methods: </b> <br>
     * ordenarByBrand. <br>
     * <b> Objective: </b> Test that the method ordenarByBrand() sorts the vehicles correctly <br>
     * <b> Expected results: </b> <br>
     * 1. When sorting by brand, the vehicles must be in ascending position
     */
    public void testSortByBrand( )
    {
        setupScenario2( );

        vehicleSales.sortByBrand( );
        ArrayList vehicles = vehicleSales.getVehicles( );
        for( int i = 1; i < vehicles.size( ); i++ )
        {
            Vehicle v0 = ( Vehicle )vehicles.get( i - 1 );
            Vehicle v1 = ( Vehicle )vehicles.get( i );

            assertTrue( "Incorrect sort by brand", v0.getBrand( ).compareTo( v1.getBrand( ) ) <= 0 );
        }
    }

    /**
     * Verifies the method search oldest vehicle. <br>
     * <b> Test methods: </b> <br>
     * searchOldestVehicle. <br>
     * <b> Objective: </b> Test that the method searchOldestVehicle() returns the position of the oldest vehicle <br>
     * <b> Expected results: </b> <br>
     * 1. If X is the oldest vehicle. When searching it must return the position of X.
     */
    public void testSearchOldestVehicle( )
    {
        // Test with scenario1
        setupScenario1( );

        int pos = vehicleSales.searchOldestVehicle( );

        assertTrue( "The oldest vehicle was not found in a correct way", pos != -1 );

        Vehicle v = ( Vehicle )vehicleSales.getVehicles( ).get( pos );

        assertEquals( "The oldest vehicle was not found in a correct way", 1, v.getYear( ) );
        assertEquals( "The oldest vehicle was not found in a correct way", "model_1", v.getModel( ) );

        // Test with scenario2
        setupScenario2( );
        pos = vehicleSales.searchOldestVehicle( );

        assertTrue( "The oldest vehicle was not found in a correct way", pos != -1 );

        v = ( Vehicle )vehicleSales.getVehicles( ).get( pos );

        assertEquals( "The oldest vehicle was not found in a correct way", 1983, v.getYear( ) );
        assertEquals( "The oldest vehicle was not found in a correct way", "S10 Pickup", v.getModel( ) );
    }

    /**
     * Verifies the method search cheapest vehicle. <br>
     * <b> Test methods: </b> <br>
     * searchcheapestVehicle. <br>
     * <b> Objective: </b> Test that the method searchCheapestVehicle() returns the position of the cheapest vehicle <br>
     * <b> Expected results: </b> <br>
     * 1. If X is the cheapest vehicle. When searching it must return the position of X.
     */
    public void testSearchCheapestVehicles( )
    {

        // Test with scenario1
        setupScenario1( );

        int pos = vehicleSales.searchCheapestVehicle( );

        assertTrue( "The cheapest vehicle was not found in a correct way", pos != -1 );

        Vehicle v = ( Vehicle )vehicleSales.getVehicles( ).get( pos );

        assertEquals( "The cheapest vehicle was not found in a correct way", 500, v.getPrice( ) );
        assertEquals( "The cheapest vehicle was not found in a correct way", "model_12", v.getModel( ) );
        assertEquals( "The cheapest vehicle was not found in a correct way", 12, v.getYear( ) );

        // Test with scenario2
        setupScenario2( );
        pos = vehicleSales.searchCheapestVehicle( );

        assertTrue( "The cheapest vehicle was not found in a correct way", pos != -1 );

        v = ( Vehicle )vehicleSales.getVehicles( ).get( pos );

        assertEquals( "The cheapest vehicle was not found in a correct way", 40000000, v.getPrice( ) );
        assertEquals( "The cheapest vehicle was not found in a correct way", "New Beetle", v.getModel( ) );
        assertEquals( "The cheapest vehicle was not found in a correct way", 2000, v.getYear( ) );
    }

    /**
     * Verifies the method search most powerful vehicle. <br>
     * <b> Test methods: </b> <br>
     * searchMostPowerfulVehicle. <br>
     * <b> Objective: </b> Test that the method searchMostPowerfulVehicle() returns the position of the most powerful vehicle <br>
     * <b> Expected results: </b> <br>
     * 1. If X is the most powerful vehicle. When searching it must return the position of X.
     */
    public void testSearchMostPowerful( )
    {
        // Test with scenario1
        setupScenario1( );

        int pos = vehicleSales.searchMostPowerfullVehicle( );

        assertTrue( "The most powerful vehicle was not found in a correct way", pos != -1 );

        Vehicle v = ( Vehicle )vehicleSales.getVehicles( ).get( pos );

        assertEquals( "The most powerful vehicle was not found in a correct way", 5000, v.getCylinderCapacity( ) );
        assertEquals( "The most powerful vehicle was not found in a correct way", "model_9", v.getModel( ) );
        assertEquals( "The most powerful vehicle was not found in a correct way", 9, v.getYear( ) );

        // Test with scenario2
        setupScenario2( );
        pos = vehicleSales.searchMostPowerfullVehicle( );

        assertTrue( "The most powerful vehicle was not found in a correct way", pos != -1 );

        v = ( Vehicle )vehicleSales.getVehicles( ).get( pos );

        assertEquals( "The most powerful vehicle was not found in a correct way", 2500, v.getCylinderCapacity( ) );
        assertEquals( "The most powerful vehicle was not found in a correct way", "CLK430", v.getModel( ) );
        assertEquals( "The most powerful vehicle was not found in a correct way", 2003, v.getYear( ) );
    }

    /**
     * Verifies the method purchase a vehicle<br>
     * <b> Test method: </b> <br>
     * purchaseVehicle. <br>
     * <b> Objective: </b> Test that the method purchaseVehicle() removes the vehicle from the list <br>
     * <b> Expected results: </b> <br>
     * 1. When purchasing an existent vehicle, it must be removed from the list.<br>
     * 2. When purchasing an existent vehicle, the number of items in the list must be reduced. <br>
     * 3. When purchasing an existent vehicle, if it is searched it could not be found.
     */
    public void testPurchaseVehicle1( )
    {
        // Test with scenario1
        setupScenario1( );

        ArrayList vehicles = vehicleSales.getVehicles( );
        int quantity = vehicles.size( );

        for( int cont = 0; cont < quantity; cont++ )
        {
            assertTrue( "The vehicle must not be purchased, but it was", vehicleSales.purchaseVehicle( "model_" + ( cont + 1 ), cont + 1 ) );
        }

        vehicles = vehicleSales.getVehicles( );
        assertEquals( "The vehicle list must be empty", 0, vehicles.size( ) );

        // Test with scenario2
        setupScenario2( );

        vehicles = vehicleSales.getVehicles( );
        quantity = vehicles.size( );

        assertTrue( "The vehicle must be purchased", vehicleSales.purchaseVehicle( "CLK430", 2003 ) );

        assertTrue( "The vehicle must be purchased", vehicleSales.purchaseVehicle( "Explorer", 1999 ) );

        assertTrue( "The vehicle must be purchased", vehicleSales.purchaseVehicle( "New Beetle", 2000 ) );

        vehicles = vehicleSales.getVehicles( );
        assertEquals( "Vehicles were not purchased in the correct way", quantity - 3, vehicles.size( ) );

        assertTrue( "The vehicle must not be purchased, but it was", vehicleSales.searchVehicle( "CLK430", 2003 ) == -1 );

        assertTrue( "The vehicle must not be purchased, but it was", vehicleSales.searchVehicle( "Explorer", 1999 ) == -1 );

        assertTrue( "The vehicle must not be purchased, but it was", vehicleSales.searchVehicle( "New Beetle", 2000 ) == -1 );
    }

    /**
     * Verifies the method purchase a vehicle<br>
     * <b> Test method: </b> <br>
     * purchaseVehicle. <br>
     * <b> Objective: </b> Test that the method purchaseVehicle() does not remove a non existent vehicle from the list <br>
     * <b> Expected results: </b> <br>
     * 1. When purchasing non existent vehicle, the list must remain equal
     */
    public void testPurchaseVehicle2( )
    {
        // Test with scenario1
        setupScenario1( );

        ArrayList vehicles = vehicleSales.getVehicles( );
        int quantity = vehicles.size( );

        for( int cont = 0; cont < quantity; cont++ )
        {
            assertFalse( "The vehicle must not be purchased, but it was", vehicleSales.purchaseVehicle( "model_" + ( cont + 1 ), cont ) );
        }

        vehicles = vehicleSales.getVehicles( );
        assertEquals( "The vehicle list must be empty", quantity, vehicles.size( ) );

        // Test with scenario2
        setupScenario2( );

        vehicles = vehicleSales.getVehicles( );
        quantity = vehicles.size( );

        assertTrue( "The vehicle must be purchased", vehicleSales.purchaseVehicle( "CLK430", 2003 ) );

        assertFalse( "The vehicle must not be purchased, but it was", vehicleSales.purchaseVehicle( "CLK430", 2003 ) );

        assertFalse( "The vehicle must not be purchased, but it was", vehicleSales.purchaseVehicle( "Explorer", 1990 ) );

        assertFalse( "The vehicle must be purchased", vehicleSales.purchaseVehicle( "New Beetless", 2000 ) );

        vehicles = vehicleSales.getVehicles( );

        assertEquals( "Vehicles were not purchased in the correct way", quantity - 1, vehicles.size( ) );
    }

    /**
     * Verifies the method that decrease the price of the vehicles<br>
     * <b> Test methods: </b> <br>
     * reducePrice. <br>
     * <b> Objective: </b> Test that the method reducePrice() reduces the price correctly <br>
     * <b> Expected Results: </b> <br>
     * 1. There are X vehicles with a price higher than Z. When reducing the price vehicles using Z, the price of X vehicles must be reduced. <br>
     * 2. The price of a vehicle before the discount is Y. When doing the discount, the discount of the vehicle must be Y*0.9
     */
    public void testReducePrice( )
    {
        // Test with scenario1
        setupScenario1( );

        Vehicle vehicle = ( Vehicle )vehicleSales.getVehicles( ).get( 0 );
        int price = vehicle.getPrice( );
        assertEquals( "Price was not reduced in a correct way", 1, vehicleSales.reducePrice( 11500 ) );

        vehicle = ( Vehicle )vehicleSales.getVehicles( ).get( 0 );
        int newPrice = vehicle.getPrice( );

        assertEquals( "Price was not reduced in a correct way", ( int ) ( price * 0.9 ), newPrice );

        assertEquals( "Price was not reduced in a correct way", 0, vehicleSales.reducePrice( 12000 ) );

        // Test with scenario2
        setupScenario2( );

        vehicle = ( Vehicle )vehicleSales.getVehicles( ).get( 0 );
        price = vehicle.getPrice( );

        vehicle = ( Vehicle )vehicleSales.getVehicles( ).get( 6 );
        int price2 = vehicle.getPrice( );

        assertEquals( "Price was not reduced in a correct way", 2, vehicleSales.reducePrice( 89000000 ) );

        vehicle = ( Vehicle )vehicleSales.getVehicles( ).get( 0 );
        newPrice = vehicle.getPrice( );

        vehicle = ( Vehicle )vehicleSales.getVehicles( ).get( 6 );
        int valorNuevo2 = vehicle.getPrice( );

        assertEquals( "Price was not reduced in a correct way", ( int ) ( price * 0.9 ), newPrice );

        assertEquals( "Price was not reduced in a correct way", ( int ) ( price2 * 0.9 ), valorNuevo2 );

        assertEquals( "Price was not reduced in a correct way", 0, vehicleSales.reducePrice( 600000000 ) );
    }

    // -----------------------------------------------------------------
    // Auxiliary Methods
    // -----------------------------------------------------------------
    /**
     * Loads the vehicles form a properties file
     * @param file name of the file that contains the properties of the vehicles
     */
    private void loadVehicles( String file )
    {
        try
        {
            FileInputStream fis = new FileInputStream( new File( file ) );
            Properties properties = new Properties( );
            properties.load( fis );

            // Load the vehicles
            String info;
            String model;
            String brand;
            String image;
            String type;
            int year;
            int cylinderCapacity;
            int axes;
            int price;
            String aux;
            info = "total.vehicles";
            aux = properties.getProperty( info );
            int cantidad = Integer.parseInt( aux );

            for( int cont = 1; cont <= cantidad; cont++ )
            {
                // Loads a vehicle
                info = "vehicle" + cont + ".model";
                model = properties.getProperty( info );

                info = "vehicle" + cont + ".brand";
                brand = properties.getProperty( info );

                info = "vehicle" + cont + ".image";
                image = properties.getProperty( info );

                info = "vehicle" + cont + ".type";
                type = properties.getProperty( info );

                info = "vehicle" + cont + ".year";
                aux = properties.getProperty( info );
                year = Integer.parseInt( aux );

                info = "vehicle" + cont + ".cylinderCapacity";
                aux = properties.getProperty( info );
                cylinderCapacity = Integer.parseInt( aux );

                info = "vehicle" + cont + ".axes";
                aux = properties.getProperty( info );
                axes = Integer.parseInt( aux );

                info = "vehicle" + cont + ".price";
                aux = properties.getProperty( info );
                price = Integer.parseInt( aux );

                // The vehicle is loaded only if the info is correct
                if( model != null && brand != null && image != null && type != null && year > 0 && cylinderCapacity > 0 && axes > 0 && price > 0 )
                    vehicleSales.addVehicle( model, brand, image, type, year, cylinderCapacity, axes, price );
                fis.close( );
            }
        }
        catch( FileNotFoundException e )
        {
            fail( "This exception must not be throwed" );
        }
        catch( IOException e )
        {
            fail( "This exception must not be throwed" );
        }
    }

}
