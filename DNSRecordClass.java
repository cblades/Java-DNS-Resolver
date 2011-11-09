/**
 * Represents the class of a dns record.
 *
 * @author Chris Blades
 * @version 20/1/2010
 */
public interface DNSRecordClass extends DNSObject {
    /**
     * Returns a string represention of this record class.
     *
     * @return a string representation of this record class.
     */
    public String getName();
}
