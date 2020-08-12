

package uk.ac.keele.csc20004.autorepair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/** An implementation of an request for an autorepair. 
 * The request is basically a Collection storing a list of (up to 2) Services for
 * vehicles. The inner data structure used as storage does not need to be disclosed. 
 * The only functionality that is advertised to users of this class is the fact 
 * that it is possible to iterate through the elements of the class itself.
 *
 * @author Marco Ortolani
 */
public class ServiceRequest implements Iterable<Vehicle> {
    private final ArrayList<Vehicle> vehicles;
        
    /** This constructor initialises the request with only 1 vehicle to service.
     * 
     * @param v a Vehicle object, specifying the vehicle and type of service 
 requested.
     */
    public ServiceRequest(Vehicle v) {
        vehicles = new ArrayList<Vehicle>(1);
        vehicles.add(v);
    }

    /** This constructor initialises the request with 2 vehicles to service.
     * 
     * @param v1 a Vehicle object, specifying the first vehicle for which a 
 service is requested
     * @param v2 a Vehicle object, specifying the second vehicle for which a 
 service is requested
     */
    public ServiceRequest(Vehicle v1, Vehicle v2) {
        vehicles = new ArrayList<Vehicle>(2);
        vehicles.add(v1);
        vehicles.add(v2);
    }

    /** Builds an iterator for this order. In this implementation an ArrayList is
     * used internally as a data structure, so its iterator is returned.
     * 
     * @return the iterator to go through the pizzas in the order
     */
    @Override
    public Iterator<Vehicle> iterator() {
        return vehicles.iterator();
    }
    
    private ServiceRequest createRandomRequest() {
        Random random = new Random();

        Vehicle[] v = new Vehicle[2];
        
        // create at most 2 random vehicles
        int quantity = random.nextInt(2)+1;
        
        for (int i = 0; i < quantity; i++) {
            int vehicleType = random.nextInt(2);
            int serviceType = random.nextInt(Vehicle.SERVICE_TYPES)+1;
            switch (serviceType) {
                case Vehicle.QUICK_SERVICE: 
                    v[i] = Vehicle.createQuickService(vehicleType);
                    break;
                case Vehicle.BASIC_SERVICE:
                    v[i] = Vehicle.createBasicService(vehicleType);
                    break;
                case Vehicle.PREMIUM1_SERVICE:
                    v[i] = Vehicle.createPremium1Service(vehicleType);
                    break;
                default:
                    v[i] = Vehicle.createPremium2Service(vehicleType);
                    break;
            }
        }
        
        if (quantity == 1) {
            return new ServiceRequest(v[0]);
        } else {
            return new ServiceRequest(v[0], v[1]);
        }
    }
    
    /** Overridden toString() method to provide a concise textual visualisation
     * of the request
     * 
     * @return a string representing the vehicles in the request and the type of
     * service
     */
    @Override
    public String toString() {
        String description = "[ ";
        
        for (Vehicle v : vehicles) {
            description += v.toString() + "; ";
        }
        
        description += " ]";
        
        return description;
    }
}
