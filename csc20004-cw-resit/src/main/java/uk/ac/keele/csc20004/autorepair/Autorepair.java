

package uk.ac.keele.csc20004.autorepair;

/** A very basic interface for an autorepair.
 *
 */
public interface Autorepair {
    /** the maximum number of requests that may be accepted at the same time. 
     * Further requests may just be rejected. 
     */
    public static final int MAX_REQUESTS = 50;
    
    /** Accept a request and store it until it's processed.
     * 
     * @param r the ServiceRequest to be accepted
     */
    public void placeRequest(ServiceRequest r);
    
    /** Fetch a request from the waiting line.
     * 
     * @return the "next" request waiting, in the order decided by the autorepair
     */
    public ServiceRequest getNextRequest();    
    
    /** Place the request in the default "chain" for delivery
     * By default, this will be the "auto+bike" chain, or the only one available, 
     * depending on the scenario. This method will likely be called by a Mechanic.
     * 
     * @param r the request containing the vehicles to be delivered
     */
    public void completeService(ServiceRequest r);
    
    /** Get the number of service requests still to be processed from the waiting line.
     * 
     * @return 
     */
    public int getNumOfWaitingRequests();
    
    /** Take one instance of a vehicle part from the corresponding shelf.
     * 
     * @return a Part of type oil filter
     */
    public Part fetchOilFilter();

    /** Take one instance of a vehicle part from the corresponding shelf.
     * 
     * @return a Part of type battery
     */
    public Part fetchBattery();
    
    /** Take one instance of a vehicle part from the corresponding shelf.
     * 
     * @return a Part of type brakes
     */
    public Part fetchBrakes();

    /** Take one instance of a vehicle part from the corresponding shelf.
     * 
     * @return a Part of type tyres
     */
    public Part fetchTyres();
    
    
    /** Puts one instance of a vehicle part into the corresponding shelf.
     * In this case, in the shelf for oil filters
     */
    public void refillOilFilter();

    /** Puts one instance of a vehicle part into the corresponding shelf.
     * In this case, in the shelf for batteries
     */
    public void refillBattery();
    
    /** Puts one instance of a vehicle part into the corresponding shelf.
     * In this case, in the shelf for brakes
     */
    public void refillBrakes();
    
    /** Puts one instance of a vehicle part into the corresponding shelf.
     * In this case, in the shelf for tyres
     */
    public void refillTyres();
    
    
    /** Helper method to get the number of parts of type oil filter are
     * currently present in the corresponding shelf.
     * 
     * @return the number of oil filter items in the corresponding shelf 
     */
    public int getOilFilterStorageLevel();

    /** Helper method to get the number of parts of type battery are
     * currently present in the corresponding shelf.
     * 
     * @return the number of battery items in the corresponding shelf 
     */
    public int getBatteryStorageLevel();
    
    /** Helper method to get the number of parts of type brakes are
     * currently present in the corresponding shelf.
     * 
     * @return the number of brakes items in the corresponding shelf 
     */
    public int getBrakesStorageLevel();

    /** Helper method to get the number of parts of type tyres are
     * currently present in the corresponding shelf.
     * 
     * @return the number of tyres items in the corresponding shelf 
     */
    public int getTyresStorageLevel();
    
}
