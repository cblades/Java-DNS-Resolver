/**
 * Represents an opcode for a DNS flags field.
 *
 * @author Chris Blades
 * @version 20/1/2010
 */
public interface DNSOpcode extends DNSObject {
    /**
     * Return a string representation of the opcode
     * 
     * @return a string representation of the opcode
     */
    public String getName();
}
