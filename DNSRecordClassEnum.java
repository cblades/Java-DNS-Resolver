/**
 * Basic implementation of DNSRecordClass using an Enum since
 * class will never (for the purposes of this program) be anytying other
 * than 1.
 *
 * @author Chris Blades
 * @version 3/12/2010
 */
public enum DNSRecordClassEnum implements DNSRecordClass {
    IN ("IN (Internet)", new byte[]{(byte)0x00, (byte)0x01});
    
    /** length of the class field */
    private static final int CLASS_LENGTH = 2;

    /** Representation of this class as a byte array */
    private byte[] serialized;

    /** mapping of state values of this DNSRecordClass */
    private Map<Object, Object> values;

    /** Name of this DNSRecordClass */
    private String name;

    /**
     * Creates a new DNSRecordClass with the given name and value.
     *
     * @param name the name of this class
     * @param serialized the value of the class as a byte array
     */
    private DNSRecordClassEnum(String name, byte[] serialized) {
        this.name = name;
        this.serialized = serialized;
        values = new Map<Object, Object>();
        values.put(name, serialized[1]);
    }

    /**
     * Returns the name of this class.
     *
     * @return the name of this class
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the length of this class when serialized
     *
     * @return the length of this class when serialized
     */
    public int getLength() {
        return this.CLASS_LENGTH;
    }

    /**
     * Returns a byte representation of this class.
     *
     * @return a byte representation of this class
     */
    public byte[] serialize() {
        return this.serialized;
    }

    /**
     * Returns a mapping of the state values of this class.
     *
     * @return the state values of this class.
     */
    public Map stateValues() {
        return this.values;
    }
}
