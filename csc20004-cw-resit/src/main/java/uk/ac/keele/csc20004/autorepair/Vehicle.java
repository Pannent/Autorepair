
package uk.ac.keele.csc20004.autorepair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** The representation of a vehicle and the service it requires. 
 * Only 4 different types of services are currently available 
 *
 */
public class Vehicle {
    public static final int SERVICE_TYPES = 4;
    
    public static final int QUICK_SERVICE = 0;
    public static final int BASIC_SERVICE = 1;
    public static final int PREMIUM1_SERVICE = 2;
    public static final int PREMIUM2_SERVICE = 3;
    
    public static final int SERVICE_TIME_QUICK = 1000;
    public static final int SERVICE_TIME_BASIC = 1500;
    public static final int SERVICE_TIME_PREMIUM1 = 2000;
    public static final int SERVICE_TIME_PREMIUM2 = 2000;
    
    public static final int AUTO = 0;
    public static final int BIKE = 1;
    
    private final int vehicleType;
    private final ArrayList<Part> faultyParts;
    private final int serviceTime;

    /** This constructor is private; instances of Vehicle can only be created 
     * via the create*() methods below. This ensures that only services of the 
     * correct types may be created. The type of the service is inferred from the 
     * parts.
     * 
     * @param type the type of vehicle for which the service is requested
     * @param in an array of Part used to initialise the service. Only some
     * combinations of parts are allowed (as specified in the coursework text).
     * An IllegalArgumentException is thrown is the wrong combination of parts
     * is used
     * @param servTime the (simulated) time it takes for a service to be done 
     * 
     */
    private Vehicle(int type, Part[] in, int servTime) {
        int serviceType = checkParts(in);
        if (serviceType < 0 || serviceType >= Vehicle.SERVICE_TYPES) {
            throw new IllegalArgumentException("Wrong sequence of parts");
        }
        
        vehicleType = type;
        faultyParts = new ArrayList<>(in.length);
        Collections.addAll(faultyParts, in);
        
        serviceTime = servTime;        
    }

    /** A helper method to check if a combination of parts represents one
     * of the offered services.
     * 
     * @param in the array with the combination of parts to be checked.
     * @return the int used to encode the type of service
     */
    private int checkParts(Part[] in) {
        Part oilFilter = Part.createOilFilter();
        Part battery = Part.createBattery();
        Part brakes = Part.createBrakes();
        Part tyres = Part.createTyres();
        
        List inList = Arrays.asList(in);
        if (inList.contains(oilFilter) && inList.contains(battery) &&
            !(inList.contains(brakes) || inList.contains(tyres))) {
            return Vehicle.QUICK_SERVICE;
        } else if (inList.contains(oilFilter) && inList.contains(brakes) &&
            !(inList.contains(battery) || inList.contains(tyres))) {
            return Vehicle.BASIC_SERVICE;
        } else if (inList.contains(brakes) && inList.contains(tyres) &&
            !(inList.contains(oilFilter) || inList.contains(battery))) {
            return Vehicle.PREMIUM1_SERVICE;
        } else if (inList.contains(tyres) && inList.contains(battery) &&
            !(inList.contains(oilFilter) || inList.contains(brakes))) {
            return Vehicle.PREMIUM2_SERVICE;
        } else {
            throw new IllegalArgumentException("Wrong sequence of parts");
        }
    }
    
    /** The constructor of this class is private, hence it is not accessible, not 
 even by subclasses; instances of Vehicle can only be created 
 via the create*() methods like this.This ensures that only Vehicle of 
 the correct types may be created. 
     * 
     * @param type the type of vehicle for which the service is requested
     * @return a properly formed Vehicle object, of type QUICK_SERVICE
     */
    public static Vehicle createQuickService(int type) {
        if (!(type == AUTO || type == BIKE)) {
            throw new IllegalArgumentException("Cannot recognise this type  type of vehicle");
        }
        
        Part oilFilter = Part.createOilFilter();
        Part battery = Part.createBattery();
        Part[] in = {oilFilter, battery};
        
        return new Vehicle(type, in, Vehicle.SERVICE_TIME_QUICK);
    }

    /** The constructor of this class is private, hence it is not accessible, not 
     * even by subclasses; instances of Service can only be created 
     * via the create*() methods like this. This ensures that only Service of 
     * the correct types may be created. 
     * 
     * @param type the type of vehicle for which the service is requested
     * @return a properly formed Vehicle object, of type BASIC_SERVICE
     */
    public static Vehicle createBasicService(int type) {
        if (!(type == AUTO || type == BIKE)) {
            throw new IllegalArgumentException("Cannot recognise this type  type of vehicle");
        }

        Part oilFilter = Part.createOilFilter();
        Part brakes = Part.createBrakes();
        Part[] in = {oilFilter, brakes};
        
        return new Vehicle(type, in, Vehicle.SERVICE_TIME_BASIC);
    }
    
    /** The constructor of this class is private, hence it is not accessible, not 
     * even by subclasses; instances of Service can only be created 
     * via the create*() methods like this. This ensures that only Service of 
     * the correct types may be created. 
     * 
     * @param type the type of vehicle for which the service is requested
     * @return a properly formed Vehicle object, of type PREMIUM1_SERVICE
     */
    public static Vehicle createPremium1Service(int type) {
        if (!(type == AUTO || type == BIKE)) {
            throw new IllegalArgumentException("Cannot recognise this type of vehicle: " + type);
        }

        Part brakes = Part.createBrakes();
        Part tyres = Part.createTyres();
        Part[] in = {brakes, tyres};
        
        return new Vehicle(type, in, Vehicle.SERVICE_TIME_PREMIUM1);
    }
    
    /** The constructor of this class is private, hence it is not accessible, not 
     * even by subclasses; instances of Service can only be created 
     * via the create*() methods like this. This ensures that only Service of 
     * the correct types may be created. 
     * 
     * @param type the type of vehicle for which the service is requested
     * @return a properly formed Vehicle object, of type PREMIUM2_SERVICE
     */
    public static Vehicle createPremium2Service(int type) {
        if (!(type == AUTO || type == BIKE)) {
            throw new IllegalArgumentException("Cannot recognise this type  type of vehicle");
        }

        Part tyres = Part.createTyres();
        Part battery = Part.createBattery();
        Part[] in = {tyres, battery};
        
        return new Vehicle(type, in, Vehicle.SERVICE_TIME_PREMIUM2);
    }
    
    /** Checks the list of parts to decide which kind of service this is.
     * I preferred to check the parts as opposed to storing the type, in case
     * the inner representation is changed in a later version.
     * 
     * @return true if this is a QUICK_SERVICE service
     */
    public boolean isQuickService() {
        return (hasOilFilter() && hasBattery()) &&
                !(hasBrakes() || hasTyres());
    }
    
    /** Checks the list of parts to decide which kind of service this is.
     * I preferred to check the parts as opposed to storing the type, in case
     * the inner representation is changed in a later version.
     * 
     * @return true if this is a BASIC_SERVICE service
     */
    public boolean isBasicService() {
        return (hasOilFilter() && hasBrakes()) &&
                !(hasBattery() || hasTyres());
    }
    
    /** Checks the list of parts to decide which kind of service this is.
     * I preferred to check the parts as opposed to storing the type, in case
     * the inner representation is changed in a later version.
     * 
     * @return true if this is a PREMIUM1_SERVICE service
     */
    public boolean isPremium1Service() {
        return (hasBrakes() && hasTyres()) &&
                !(hasOilFilter() || hasBattery());
    }

    /** Checks the list of parts to decide which kind of service this is.
     * I preferred to check the parts as opposed to storing the type, in case
     * the inner representation is changed in a later version.
     * 
     * @return true if this is a PREMIUM2_SERVICE service
     */
    public boolean isPremium2Service() {
        return (hasTyres() && hasBattery()) &&
                !(hasOilFilter() || hasBrakes());
    }

    /** Checks whether the vehicle to be serviced is an automobile.
     * 
     * @return true the vehicle to be serviced is an automobile.
     */
    public boolean isAuto() {
        return (vehicleType == AUTO);
    }    
    
    /** Checks whether the vehicle to be serviced is a motorbike.
     * 
     * @return true the vehicle to be serviced is a motorbike.
     */
    public boolean isBike() {
        return (vehicleType == BIKE);
    }    

    /** Helper method to check if this service involves substituting the oil filter.
     * 
     * @return true if the service requires substituting the oil filter
     */
    public boolean hasOilFilter() {
        Part oilFilter = Part.createOilFilter();

        return faultyParts.contains(oilFilter);
    }

    /** Helper method to check if this service involves substituting the battery.
     * 
     * @return true if the service requires substituting the battery
     */
    public boolean hasBattery() {
        Part oilFilter = Part.createOilFilter();
        Part battery = Part.createBattery();
        Part brakes = Part.createBrakes();
        Part tyres = Part.createTyres();

        return faultyParts.contains(battery);
    }

    /** Helper method to check if this service involves substituting the brakes.
     * 
     * @return true if the service requires substituting the brakes
     */
    public boolean hasBrakes() {
        Part brakes = Part.createBrakes();

        return faultyParts.contains(brakes);
    }

    /** Helper method to check if this service involves substituting the tyres.
     * 
     * @return true if the service requires substituting the tyres
     */
    public boolean hasTyres() {
        Part tyres = Part.createTyres();

        return faultyParts.contains(tyres);
    }

    
    /** Helper method to get a list of all parts in this service request.
     * 
     * @return a (copy of the) list of the parts to be serviced
     */
    public Part[] getParts() {
        Part[] i = new Part[faultyParts.size()];
        
        return faultyParts.toArray(i);
    }
    
    /** Helper method to get the time it takes for the service to be carried out.
     * 
     * @return the service time (millisec)
     */
    public int getServiceTime() {
        return serviceTime;
    }
    
    /** Overridden toString() method to provide a concise textual visualisation
     * of the service
     * 
     * @return a string representing the list of parts in the service offered
     */
    @Override
    public String toString(){
        String vehicleDescr = (vehicleType == AUTO) ? "auto": "bike";
        if(this.isQuickService()) return vehicleDescr + " - quick (oil+battery)";
        else if(this.isBasicService()) return vehicleDescr + " - basic (oil+brakes)";
        else if(this.isPremium1Service()) return vehicleDescr + " - premium1 (brakes+tyres)";
        else if(this.isPremium2Service()) return vehicleDescr + " - premium2 (tyres+battery)";
        else return super.toString();
    }
}
