/**
 * Represents the type of a DNS record.
 *
 * @author Chris Blades
 * @version 21/1/2010
 */
public interface DNSRecordType extends DNSObject {
    /**
     * Returns a string representation of this record type
     *
     * @return a string representation of this record type
     */
    public String getName();
}
