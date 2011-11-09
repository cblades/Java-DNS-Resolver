import java.nio.ByteBuffer;


/**
 * A decorator for DNSFlagsField that parses a byte buffer to create a
 * DNSFlagsField.
 *
 * @author Chris Blades
 * @version 20/3/10
 */
public class DNSFlagsFieldParserDecorator implements DNSFlagsField {

    /** The DNSFlagsField object this object wraps */
    private DNSFlagsField flags;

    
    /** byte buffer to parse and pass to other objects */
    private IndexedArrayList<Byte> bytes;

    /**
     * Creates a new DNSFlagsFieldParserDecorator with the given byte
     * buffer.
     *
     * @param bytes the byte buffer to parse.
     */
    public DNSFlagsFieldParserDecorator(IndexedArrayList<Byte> bytes) {
        this.bytes = bytes;
        flags = new DNSFlagsFieldImpl();
    }

    /**
     * Parses the byte buffer to form a DNSFlagsField object.
     *
     */
    public void parse() {
        
        // will need this to parse flags in first byte
        byte first = bytes.getNext();
        // rewind because opcode is in first byte too, and it will increment
        // buffer
        bytes.rewind();
        
        // parse opcode
        DNSOpcodeParserDecorator opcode = 
                                    new DNSOpcodeParserDecorator(bytes);
        opcode.parse();
        

        byte second = bytes.getNext();
        
        // parse is request
        flags.setIsRequest(((int)first & 0x00000080) != 0);
        // set opcode
        flags.setOpcode(opcode);

        // parse authoritative bit
        flags.setAuthoritative(((int)first & 0x04) != 0);
        // parse truncated bit
        flags.setTruncated(((int)first & 0x02) != 0);
        // parse recursion desired bit
        flags.setRecursionDesired(((int)first & 0x01) != 0);
        // parse recursion available bit
        flags.setRecursionAvailable(((int)second & 0x80) != 0);
        
        // parse return code
        int code = second & 0x0F;
        flags.setReturnCode(code);
    }
    

    //
    // DNSFlagsField
    //
    
    /**
     * Returns wether the response flag is set or not
     *
     * @return true if this flags field represents a request
     */
    public boolean isRequest() {
        return flags.isRequest();
    }

    /**
     * Returns the opcode vale of this flags field
     *
     * @return the opcode value of this flags field
     */
    public DNSOpcode getOpcode() {
        return flags.getOpcode();
    }

    /**
     * Returns wether the authoritative bit is set in this flags field.
     *
     * @return the state of the authoritative bit
     */
    public boolean isAuthorative() {
        return flags.isAuthorative();
    }

    /**
     * Returns wether the truncated bit is set in this flags field.
     *
     * @return the state of the truncated bit
     */
    public boolean isTruncated() {
        return flags.isTruncated();
    }

    /**
     * Returns wether the recursion desired bit is set in this flags field.
     *
     * @return the state of the recursion desired bit.
     */
    public boolean recursionDesired() {
        return flags.recursionDesired();
    }

    /**
     * Returns wether the recursion available bit is set in this flags field.
     *
     * @return the state of the recursion available bit.
     */
    public boolean recursionAvailable() {
        return flags.recursionAvailable();
    }
    
    /**
     * Returns the return code value of the flags field.
     *
     * @return the return code of this flags field
     */
    public int getReturnCode() {
        return flags.getReturnCode();
    }


    /**
     * Sets wether this flags field represents a request.
     *
     * @param request wether this flags field is a request.
     */
    public void setIsRequest(boolean request) {
        flags.setIsRequest(request);
    }

    /**
     * Sets the opcode of this flags field.
     *
     * @param opcode the new Opcode of this flags field.
     */
    public void setOpcode(DNSOpcode opcode) {
        flags.setOpcode(opcode);
    }
    /**
     * sets the state of the authoritative bit.
     *
     * @param authoritative the state of the authoritative bit
     */

    public void setAuthoritative(boolean authoritative) {
        flags.setAuthoritative(authoritative);
    }

    /**
     * sets the state of the truncated bit.
     *
     * @param truncated the state of the truncated bit
     */
    public void setTruncated(boolean truncated) {
        flags.setTruncated(truncated);
    }

    /**
     * sets the state of the recursion desired bit.
     *
     * @param recursionDesired the state of the recursion desired bit
     */
    public void setRecursionDesired(boolean recursionDesired) {
        flags.setRecursionDesired(recursionDesired);
    }

    /**
     * sets the state of the recursion available bit.
     *
     * @param recursionAvailable the state of the recursion available bit
     */
    public void setRecursionAvailable(boolean recursionAvailable) {
        flags.setRecursionAvailable(recursionAvailable);
    }

    /**
     * Sets the return code of this flags field.
     *
     * @param code the new return code of this flags field
     */
    public void setReturnCode(int code) {
        flags.setReturnCode(code);
    }
    
    /**
     * Returns the length of the serialized form of this DNSFlagsField.
     * Can't just use the byte buffer because we don't know what else is in
     * the buffer.
     *
     * @return the length of the serialized form of this DNSFlagsField.
     */
    public int getLength() {
        return flags.getLength();
    }

    /**
     * The serialized form of this flags field.
     *
     * @return this flags field serialized to the DNS protocol
     */
    public byte[] serialize() {
        return flags.serialize();
    }

    /**
     * Returns a mapping of the state values of this DNSFlagsField.
     *
     * @return the state values of this DNSFlagsField
     */
    public Map stateValues() {
        return flags.stateValues();
    }
}
