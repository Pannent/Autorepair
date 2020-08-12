

package uk.ac.keele.csc20004.autorepair;

/** An interface defining the basic behaviour of a mechanic.
 *
 */
public interface Mechanic {
    
    /** The only mandatory task for a mechanic is to be able to work on
     * a service request.
     * 
     * You can of course add further functionalities, if necessary
     * 
     * @param r the service request
     */
    public void processRequest(ServiceRequest r);
}
