
/**
 * Basic implementation of DNSRecordType using an Enum since
 * type will never (for the purposes of this program) be anytying other
 * than 1.
 *
 * @author Chris Blades
 * @version 3/12/2010
 */
public enum DNSRecordTypeEnum implements DNSRecordType {
    A       ("A (Host Record)",                 new byte[]{(byte)0x00,
                                                       (byte)0x01}),
    NS      ("NS (Authoritative Name Server)",  new byte[]{(byte)0x00,
                                                       (byte)0x02}),
    CNAME   ("CNAME (Canonical Name)",          new byte[]{(byte)0x00,
                                                       (byte)0x05}),
    PTR     ("PTR (Domain Name Pointer)",       new byte[]{(byte)0x00,
                                                       (byte)0x0C}),
    MX      ("MS (Mail Exchange)",              new byte[]{(byte)0x00,
                                                       (byte)0x0F}),
    SRV     ("SRV (Service Selection)",         new byte[]{(byte)0x00,
                                                       (byte)0x21}),
    IXFR    ("IXFR (Incremental Zone Transfer)", new byte[]{(byte)0x00,
                                                       (byte)0xFB}),
    AXFR    ("AXFR (Entire Zone Transfer)",      new byte[]{(byte)0x00,
                                                       (byte)0xFC}),
    ALL     ("* (All Records)",                  new byte[]{(byte)0x00,
                                                       (byte)0xFF});
    /** length of the type field */
    private static final int TYPE_LENGTH = 2;
    /** Name of this DNSRecordType */
    private String name;
    /** mapping of state values of this DNSRecordType */
    private Map<Object, Object> values;
    /** Representation of this type as a byte array */
    private byte[] serialized;

    /**
     * Creates a new DNSRecordType with the given name and value.
     *
     * @param name the name of this type
     * @param serialized the value of the type as a byte array
     */
    private DNSRecordTypeEnum(String name, byte[] serialized) {
        this.name = name;
        this.serialized = serialized;
        this.values = new Map<Object, Object>();
        values.put(name, serialized[1]);
    }

    /**
     * Returns the name of this type.
     *
     * @return the name of this type
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the length of this type when serialized
     *
     * @return the length of this type when serialized
     */
    public int getLength() {
        return this.TYPE_LENGTH;
    }

    /**
     * Returns a byte representation of this type.
     *
     * @return a byte representation of this type
     */
    public byte[] serialize() {
        return this.serialized;
    }
    
    /**
     * Returns a mapping of the state values of this type.
     *
     * @return the state values of this type.
     */
    public Map stateValues() {
        return this.values;
    }
}
