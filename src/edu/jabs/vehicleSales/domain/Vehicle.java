package edu.jabs.vehicleSales.domain;

/**
 *
 * Represents a vehicle <br>
 * <b>inv: </b> <br>
 * model != null <br>
 * image != null <br>
 * brand != null <br>
 * year > 0 <br>
 * type belongs to {BUS, CAR, MOTORBIKE, TRUCK} <br>
 * cylinder capacity > 0 <br>
 * axes > 1 <br>
 * price>0
 */
public class Vehicle
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Indicates that the vehicle is a bus
     */
    public static final String BUS = "Bus";

    /**
     * Indicates that the vehicle is a car
     */
    public static final String CAR = "Car";

    /**
     * Indicates that the vehicle is a motorbike
     */
    public static final String MOTORBIKE = "Motorbike";

    /**
     * Indicates that the vehicle is a truk
     */
    public static final String TRUCK = "Truck";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Vehicle model
     */
    private String model;

    /**
     * Vehicle brand
     */
    private String brand;

    /**
     * Vehicle image
     */
    private String image;

    /**
     * Vehicle type
     */
    private String type;

    /**
     * Vehicle year
     */
    private int year;

    /**
     * Vehicle cylinder capacity
     */
    private int cylinderCapacity;

    /**
     * Axes number
     */
    private int axes;

    /**
     * Vehicle commercial price
     */
    private int price;

    // -----------------------------------------------------------------
    // Constructor Methods
    // -----------------------------------------------------------------

    /**
     * Constructs a new vehicle with the given parameters
     * @param vModel Model name - modeloV != null
     * @param vBrand Vehicle brand - marcaV != null
     * @param vImage Vehicle image - imagenP != null
     * @param vType The vehicle type - vType belongs to {BUS, CAR, MOTORBIKE, TRUCK}
     * @param vYear The vehicle year - vYear > 0
     * @param vCylinderCapacity The vehicle cylinder capacity - vCylinderCapacity > 0
     * @param vAxes Number of axes of the vehicle - vAxes > 0
     * @param price The vehicle price - price > 0
     */
    public Vehicle( String vModel, String vBrand, String vImage, String vType, int vYear, int vCylinderCapacity, int vAxes, int vPrice )
    {
        model = vModel;
        brand = vBrand;
        image = vImage;
        type = vType;
        year = vYear;
        cylinderCapacity = vCylinderCapacity;
        axes = vAxes;
        price = vPrice;

        verifyInvariant( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the vehicle model
     * @return model
     */
    public String getModel( )
    {
        return model;
    }

    /**
     * Returns the vehicle brand
     * @return brand
     */
    public String getBrand( )
    {
        return brand;
    }

    /**
     * Returns the vehicle image
     * @return imagen
     */
    public String getImage( )
    {
        return image;
    }

    /**
     * Returns the vehicle type
     * @return type
     */
    public String getType( )
    {
        return type;
    }

    /**
     * Returns the vehicles year
     * @return year
     */
    public int getYear( )
    {
        return year;
    }

    /**
     * Returns the vehicle cylinder capacity
     * @return cylinder capacity
     */
    public int getCylinderCapacity( )
    {
        return cylinderCapacity;
    }

    /**
     * Returns the number of axes
     * @return axes
     */
    public int getAxes( )
    {
        return axes;
    }

    /**
     * Returns the vehicle price
     * @return price
     */
    public int getPrice( )
    {
        return price;
    }

    /**
     * Compares two vehicles by it's cylinder capacity
     * @param v The vehicle that is going to be compared - v != null
     * @return Returns 0 if the vehicles have the same cylinder capacity. <br>
     *         Returns -1 if vehicle v has a higher cylinder capacity. <br>
     *         Returns 1 if vehicle v has a fewer cylinder capacity.  <br>
     */
    public int compareByCylinderCapacity( Vehicle v )
    {
        int result = ( cylinderCapacity == v.getCylinderCapacity( ) ) ? 0 : ( ( cylinderCapacity > v.getCylinderCapacity( ) ) ? 1 : -1 );
        return result;
    }

    /**
     * Compares two vehicles by brand
     * @param v The vehicle that is going to be compared - v!=null
     * @return Returns 0 if the vehicles have the same brand. <br>
     *         Returns -1 if vehicle v has a higher brand. <br>
     *         Returns 1 if vehicle v has a fewer brand.  <br>
     *
     */
    public int compareByBrand( Vehicle v )
    {
        return brand.toLowerCase( ).compareTo( v.getBrand( ).toLowerCase( ) );
    }

    /**
     * Compares two vehicles by year
     * @param v The vehicle that is going to be compared - v!=null
     * @return Returns 0 if the vehicles have the same year. <br>
     *         Returns -1 if vehicle v has year after. <br>
     *         Returns 1 if vehicle v has a year before.  <br>
     */
    public int compareByYear( Vehicle v )
    {
        int result = ( year == v.getYear( ) ) ? 0 : ( ( year > v.getYear( ) ) ? 1 : -1 );
        return result;
    }

    /**
     * Compares tow vehicles by price
     * @param v The vehicle that is going to be compared - v!=null
     * @return Returns 0 if the vehicles have the same price. <br>
     *         Returns -1 if vehicle v has a higher price. <br>
     *         Returns 1 if vehicle v has a fewer price.  <br>
     */
    public int compareByValue( Vehicle v )
    {
        int result = ( price == v.getPrice( ) ) ? 0 : ( ( price > v.getPrice( ) ) ? 1 : -1 );
        return result;
    }

    /**
     * Changes the vehicle price
     * @param nPrice new price
     */
    public void changePrice( int nPrice )
    {
        price = nPrice;
    }

    /**
     * Returns a string with the name and the brand of the vehicle
     * @return <brand> <model> (<year>);
     */
    public String toString( )
    {
        return brand + " " + model + " (" + year + ")";
    }

    // -----------------------------------------------------------------
    // Invariant
    // -----------------------------------------------------------------
    /**
     * Verifies the class invariant <br>
     * <b>inv: </b> <br>
     * model != null <br>
     * image != null <br>
     * brand != null <br>
     * year > 0 <br>
     * type belongs to {BUS, CAR, MOTORBIKE, TRUCK} <br>
     * cylinder capacity > 0 <br>
     * axes > 1 <br>
     * price>0
     */
    private void verifyInvariant( )
    {
        assert ( type.equals( BUS ) || type.equals( CAR ) || type.equals( MOTORBIKE ) || type.equals( TRUCK )  ) : "Type must be one of the valid types";
        assert ( year > 0 ) : "Year can't be 0";
        assert ( image != null ) : "Image can't be null";
        assert ( model != null ) : "Model can't be null";
        assert ( brand != null ) : "Brand can't be null";
        assert ( cylinderCapacity > 0 ) : "Cylinder capacity can't be 0";
        assert ( axes > 1 ) : "Axes number must by higher than 1";
        assert ( price > 0 ) : "Vehicle price must by higher than 1";
    }
}
