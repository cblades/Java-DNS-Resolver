
/**
 * A DNSObject is any object representing a part of the DNS protocol
 * and implementing the ability to be serialized (translated into an
 * array of bytes) and produce values for various parts.
 *
 * @author Chris Blades
 * @version 12/9/2009
 */
public interface DNSObject {
    /**
     * Serialize should translate the DNSObject into an array of bytes
     * as per the DNS protocol.
     *
     * @return a byte array representing this DNSObject
     */
    public byte[] serialize();

    /**
     * Should return a description of the state of this DNSObject in the
     * format [label][value]
     * @return descriptions of the state of this DNSObject
     */
    public Map stateValues();

    /**
     * Return's the length of this DNSObject once serialized.
     *
     * @return the length of this DNSObject
     */
    public int getLength();
}
