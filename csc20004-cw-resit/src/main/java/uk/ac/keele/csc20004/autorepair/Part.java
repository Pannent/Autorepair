
package uk.ac.keele.csc20004.autorepair;

/** A class whose objects represent any of the possible spare parts of vehicles 
 * for our simulated auto repair shop.
 */
public class Part {
    private static final int OIL_FILTER = 0;
    private static final int BATTERY    = 1;
    private static final int BRAKES     = 2;
    private static final int TYRES      = 3;
    
    private final int partType;
    
    /** This constructor is private; instances of Parts can only be created 
     * via the create*() methods below. This ensures that only ingredients of the 
     * correct types may be created.
     * 
     * @param type the inner int used to represent the different types of ingredients
     */
    private Part(int type) {
        partType = type;
    }
    
    /** The constructor of this class is private, hence it is not accessible, not 
     * even by subclasses; instances of Ingredient can only be created 
     * via the create*() methods like this. This ensures that only Parts of 
     * the correct types may be created. Note how the partType variable is
     * never exposed.
     * 
     * @return a Part object representing an oil filter
     */
    public static Part createOilFilter() {
        return new Part(OIL_FILTER);
    }

    /** The constructor of this class is private, hence it is not accessible, not 
     * even by subclasses; instances of Ingredient can only be created 
     * via the create*() methods like this. This ensures that only Parts of 
     * the correct types may be created. Note how the partType variable is
     * never exposed.
     * 
     * @return a Part object representing a battery for a vehicle
     */
    public static Part createBattery() {
        return new Part(BATTERY);
    }

    /** The constructor of this class is private, hence it is not accessible, not 
     * even by subclasses; instances of Part can only be created 
     * via the create*() methods like this. This ensures that only Parts of 
     * the correct types may be created. Note how the partType variable is
     * never exposed.
     * 
     * @return a Part object representing a set of brakes
     */
    public static Part createBrakes() {
        return new Part(BRAKES);
    }

    /** The constructor of this class is private, hence it is not accessible, not 
     * even by subclasses; instances of Part can only be created 
     * via the create*() methods like this. This ensures that only Parts of 
     * the correct types may be created. Note how the partType variable is
     * never exposed.
     * 
     * @return a Part object representing a set of tyres
     */
    public static Part createTyres() {
        return new Part(TYRES);
    }
    
    /** Checks the if this Part is of a specific type.
     * 
     * @return true if this is an oil filter
     */
    public boolean isOilFilter() {
        return (partType == OIL_FILTER);
    }

    /** Checks the if this Part is of a specific type.
     * 
     * @return true if this is a battery
     */
    public boolean isBattery() {
        return (partType == BATTERY);
    }

    /** Checks the if this Part is of a specific type.
     * 
     * @return true if this is a set of brakes
     */
    public boolean isBrakes() {
        return (partType == BRAKES);
    }

    /** Checks the if this Part is of a specific type.
     * 
     * @return true if this is a set of tyres
     */
    public boolean isTyres() {
        return (partType == TYRES);
    }

    /** Overridden version of the equals() method of Object(). Two Parts are
     * equal if they are of the same type (and of course, both instances of class 
     * Part)
     * 
     * @param o the Object to compare to
     * @return true if both objects are instances of Part and are of the 
     * same type
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Part) {
            return (((Part)o).partType == this.partType);
        } else {
            return super.equals(o);
        }
    }
    
    /** Overridden version of the toString() method of Object(), that just prints a 
     *  string representing the part type.
     * 
     * @return the "name" of the part type
     */
    @Override
    public String toString() {
        switch (partType) {
            case OIL_FILTER:
                return "oil filter";
            case BATTERY:
                return "battery";
            case BRAKES:
                return "brakes";
            case TYRES:
                return "tyres";
            default:
                return super.toString();
        }
    }
}
