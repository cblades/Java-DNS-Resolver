/**
 * A decorator for DNSOpcode that parses a byte buffer to create a
 * DNSOpcode.
 *
 * @author Chris Blades
 * @version 20/3/10
 */
public class DNSOpcodeParserDecorator implements DNSOpcode {
    /** The DNSOpcode object this object wraps */
    private DNSOpcode opcode;

    /** byte buffer to parse and pass to other objects */
    private IndexedArrayList<Byte> bytes;

    /**
     * Creates a new DNSOpcodeParserDecorator with the given byte
     * buffer.
     *
     * @param bytes the byte buffer to parse.
     */
    public DNSOpcodeParserDecorator(IndexedArrayList<Byte> bytes) {
        this.bytes = bytes;
    }

    /**
     * Parses the byte buffer to form a DNSOpcode object.
     *
     */
    public void parse() {
        // opcode is in the middle of the byte...
        byte temp = bytes.getNext();
        // ... so shift and mask
        temp = (byte)(temp >>> 3);
        temp = (byte)(temp & 0x0F);

        // compare to known values
        switch(temp) {
            case 0: opcode = DNSOpcodeEnum.QUERY; break;
            case 1: opcode = DNSOpcodeEnum.IQUERY; break;
            case 2: opcode = DNSOpcodeEnum.STATUS; break;
        }
    }


    /**
     * Return a string representation of the opcode
     * 
     * @return a string representation of the opcode
     */
    public String getName() {
        return opcode.getName();
    }

    /**
     * Returns the length of the parsed DNSOpcode object.
     *
     * @return the length of the parsed DNSOpcode object.
     */ 
    public int getLength() {
        return opcode.getLength();
    }

    /**
     * returns the serialized version of the parsed dnsanswer object.
     * can't just return the byte buffer because we don't know what else is in
     * it.
     *
     * @return the serialized version of the parsed dnsanswer object.
     */
    public byte[] serialize() {
        return opcode.serialize();
    }

    /** 
     * Returns a mapping of the state values of the parsed DNSOpcode object.
     *
     * @return the state values of the parsed DNSOpcode object.
     */
    public Map stateValues() {
        return opcode.stateValues();
    }
}
