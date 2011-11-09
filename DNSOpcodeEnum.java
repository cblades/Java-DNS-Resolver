/**
 * Basic implementation of DNSOpcode using an enumeration of the most common
 * values used in the Opcode field.
 *
 * @author chris Blades
 * @version 3/12/2010
 */
public enum DNSOpcodeEnum implements DNSOpcode {
    QUERY   ("Standard Query",          (byte)0x00),
    IQUERY  ("Inverse Query",           (byte)0x01),
    STATUS  ("Server Status Request",   (byte)0x02);

    /** the length of the opcode is really 4 bits, but it's masked onto a byte*/
    private static final int OPCODE_LENGTH = 1;

    /** the opcode's name */
    private String name;

    /**
     * Byte-version of this object in DNS format
     */
    private byte[] serialized;

    /**
     * A Map of the state of this DNSObject
     */
    private byte value;

    /** 
     * wether this DNSObjectqhas been changed, if it has, serialized and
     * values will have to be re-generated
     */
    private boolean changed;

    /**
     * Creates a new DNSOpcode with the given name and value.
     *
     * @param name the name of this opcode
     * @param value the value of this opcode that can be masked onto a
     *              byte
     */
    private DNSOpcodeEnum(String name, byte value) {
        this.value = value;
        this.name = name;
        this.serialized  = new byte[OPCODE_LENGTH];
    }
    
    /**
     * Returns the name of this opcode.
     *
     * @return this Opcode's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the length of an opcode, which is a constant. 
     * 
     * <b>IS NOT ACCURATE.</b>  Opcodes are only 4 bits long but length
     * returned is 1 byte.
     *
     * @return length of opcode
     */  
    public int getLength() {
        return this.OPCODE_LENGTH;
    }

    /**
     * Returns this opcode stored in the correct location in the first byte
     * of an array.
     *
     * @return this opcode as a byte array
     */
    public byte[] serialize() {
        return new byte[]{value};
    }

    /**
     * Returns a mapping of the state values of this DNSOpcode.
     *
     * @return the state values of this DNSOpcode.
     */
    public Map stateValues() {
        Map<Object, Object> map = new Map<Object, Object>();
        map.put(name, (int)(value & 0xFF));
        return map;
    }
}
