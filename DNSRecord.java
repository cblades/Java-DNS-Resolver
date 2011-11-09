/**
 * Represents a DNS Record:  either a query or a response.
 *
 * @author Chris Blades
 * @version 3/12/2010
 */
public interface DNSRecord extends DNSObject {
    /**
     * Returns the name of the record.
     *
     * @return the name of the record
     */
    public DNSUrl getName();
    
    /**
     * Returns the type of the record.
     *
     * @return the type of the record.
     */
    public DNSRecordType getType();
    
    /**
     * Returns the DNS class of the record.
     *
     * @return the DNS class of the record.
     */
    public DNSRecordClass getRecordClass();

    /**
     * Changes the name of the record.
     *
     * @param name the new name of the record
     */
    public void setName(DNSUrl name);
    
    /**
     * Changes the type of the record.
     *
     * @param type the new type of the record
     */
    public void setType(DNSRecordType type);
    
    /**
     * Changes the class of the record.
     *
     * @param recordClass the new class of the record
     */
    public void setClass(DNSRecordClass recordClass);
}
