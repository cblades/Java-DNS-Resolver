/**
 * Defines a funtionality that will be implemented by any
 * DNS Record Response.  Does not expose a setDataLength method
 * because it should be set dynamically when the data field is changed.
 *
 * @author Chris Blades
 * @version 2/25/2010
 */
public interface DNSAnswer extends DNSRecord {
    /** 
     * Returns the Time-to-Live for this Record
     *
     * @return the time-to-live for this Record
     */
    public int getTTL();

    /** 
     * Returns the data portion of this Record.
     *
     * @return the data contained in this Record.
     */
    public DNSResource getData();
    
    /**
     * Returns the length of the data in this Record.
     *
     * @return the length field of this Record
     */
    public int getDataLength();

    /**
     * Set the Time-to-Live for this Record.
     *
     * @param TTL new Time-to-Live for this Record
     */
    public void setTTL(int TTL);
    
    /**
     * Set the data contained in this Record.
     *
     * @param data new data to insert in this record.
     */
    public void setData(DNSResource data);

}
