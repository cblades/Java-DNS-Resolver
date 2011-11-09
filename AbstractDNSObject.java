/**
 * Abstract implementation of DNSObject to define some common functionality
 * that will be present in all implementations.
 *
 * @author Chris Blades
 * @version 1/3/10
 */
public abstract class AbstractDNSObject implements DNSObject {
    /** 
     * wether this DNSObject has been changed, if it has, serialized and
     * values will have to be re-generated
     */
    protected boolean              changed;
    /**
     * Byte-version of this object in DNS format
     */
    protected byte[]               serialized;
    /**
     * A Map of the state of this DNSObject
     */
    protected Map<Object, Object>  values;

    /**
     * Performs operations that will be common to all DNSObjects construction.
     */
    public AbstractDNSObject() {
        this.changed = true;
        this.values = new Map<Object, Object>();
    }

    /**
     * Returns the length of the serialized version of this DNSObject, 
     * in bytes
     *
     * @return the length of the serialized version of this DNSObject.
     */
    public int getLength() {
        this.generateTransient();
        return serialized.length;
    }

    /**
     * Will generate a serialized version of this DNSObject that conforms
     * to the DNS protocol.
     *
     * @return a serialized version of this DNSObject.
     */
    public abstract byte[] serialize();
    
    /**
     * Returns a mapping of the state values of this DNSObject.
     *
     * @return a mapping of the state values of this DNSObject.
     */
    public abstract Map  stateValues();
    
    /**
     * Generates transient state.  Should at least generate serialized and
     * values.  Should only re-generate when changed == true.
     */
    protected abstract void generateTransient();
}
