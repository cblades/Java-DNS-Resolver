import java.nio.ByteBuffer;

/**
 * A decorator for DNSHeader that parses a byte buffer to create a
 * DNSHeader.
 *
 * @author Chris Blades
 * @version 20/3/10
 */
public class DNSHeaderParserDecorator implements DNSHeader {
    
    /** The DNSHeader object this object wraps */
    private DNSHeader header;

    /** byte buffer to parse and pass to other objects */
    private IndexedArrayList<Byte> bytes;
       
    /**
     * Creates a new DNSHeaderParserDecorator with the given byte
     * buffer.
     *
     * @param bytes the byte buffer to parse.
     */
    public DNSHeaderParserDecorator(IndexedArrayList<Byte> bytes) {
        this.bytes = bytes;
        this.header = new DNSHeaderImpl();
    }
    
    /**
     * Parses the byte buffer to form a DNSHeader object.
     *
     */
    public void parse() {
        // translate identifier
        ByteBuffer translator = ByteBuffer.allocate(4);
        translator.put((byte)0x00);
        translator.put((byte)0x00);
        translator.put(bytes.getNext());
        translator.put(bytes.getNext());
        header.setIdentifier(translator.getInt(0));
        
        // parse flags
        DNSFlagsFieldParserDecorator flags = 
                                new DNSFlagsFieldParserDecorator(bytes);
        flags.parse();
        header.setFlags(flags);
 

        // translate num questions
        translator = ByteBuffer.allocate(4);
        translator.put((byte)0x00);
        translator.put((byte)0x00);
        translator.put(bytes.getNext());
        translator.put(bytes.getNext());
        header.setNumQuestions(translator.getInt(0));

        // translate num answers
        translator = ByteBuffer.allocate(4);
        translator.put((byte)0x00);
        translator.put((byte)0x00);
        translator.put(bytes.getNext());
        translator.put(bytes.getNext());
        header.setNumAnswers(translator.getInt(0));

        // translate num authoritative answers
        translator = ByteBuffer.allocate(4);
        translator.put((byte)0x00);
        translator.put((byte)0x00);
        translator.put(bytes.getNext());
        translator.put(bytes.getNext());
        header.setNumAuthorityAnswers(translator.getInt(0));

        // translate num additional answers
        translator = ByteBuffer.allocate(4);
        translator.put((byte)0x00);
        translator.put((byte)0x00);
        translator.put(bytes.getNext());
        translator.put(bytes.getNext());
        header.setNumAdditionalAnswers(translator.getInt(0));
    }

    //
    // DNSHeader
    //

    /**
     * Returns the identifier of this header.
     *
     * @return this headers identifier
     */
    public int getIdentifier() {
        return header.getIdentifier();
    }

    /**
     * Returns the flags field of this header.
     *
     * @return the flags field of this header.
     */
    public DNSFlagsField getFlags() {
        return header.getFlags();
    }

    /**
     * Returns the number of questions in a packet with this header.
     *
     * @return the number of questions field
     */
    public int getNumQuestions() {
        return header.getNumQuestions();
    }

    /**
     * Returns the number of answers in a packet with this header.
     *
     * @return the number of answers field
     */
    public int getNumAnswers() {
        return header.getNumAnswers();
    }

    /**
     * Returns the number of authority answers in a packet with this header.
     *
     * @return the number of authority answers field
     */
    public int getNumAuthorityAnswers() {
        return header.getNumAuthorityAnswers();
    }

    /**
     * Returns the number of additional answers in a packet with this header.
     *
     * @return the number of additional answers field
     */
    public int getNumAdditionalAnswers() {
        return header.getNumAdditionalAnswers();
    }



    /**
     * Sets the identifier of this header.
     *
     * @param identifier the new identifier of this header
     */
    public void setIdentifier(int identifier) {
        header.setIdentifier(identifier);
    }

    /**
     * Sets the flags field of this header
     *
     * @param flags the new flags field of this header.
     */
    public void setFlags(DNSFlagsField flags) {
        header.setFlags(flags);
    }
    /**
     * Sets the number of questions field of this header.
     *
     * @param numQuestions the new number of questions
     */

    public void setNumQuestions(int numQuestions) {
        header.setNumQuestions(numQuestions);
    }

    /**
     * Sets the number of answers field of this header.
     *
     * @param numAnswers the new number of answers
     */
    public void setNumAnswers(int numAnswers) {
        header.setNumAnswers(numAnswers);
    }

    /**
     * Sets the number of authority answers field of this header.
     *
     * @param numAuthorityAnswers the new number of authority answers
     */
    public void setNumAuthorityAnswers(int numAuthorityAnswers) {
        header.setNumAuthorityAnswers(numAuthorityAnswers);
    }

    /**
     * Sets the number of additional answers field of this header.
     *
     * @param numAdditionalAnswers the new number of additional answers
     */
    public void setNumAdditionalAnswers(int numAdditionalAnswers) {
        header.setNumAdditionalAnswers(numAdditionalAnswers);
    }

    /**
     * Returns the length of the parsed DNSHeader object.
     *
     * @return the length of the parsed DNSHeader object.
     */ 
    public int getLength() {
        return header.getLength();
    }

    /**
     * returns the serialized version of the parsed dnsanswer object.
     * can't just return the byte buffer because we don't know what else is in
     * it.
     *
     * @return the serialized version of the parsed dnsanswer object.
     */
    public byte[] serialize() {
        return header.serialize();
    }

    /** 
     * Returns a mapping of the state values of the parsed DNSHeader object.
     *
     * @return the state values of the parsed DNSHeader object.
     */
    public Map stateValues() {
        return header.stateValues();
    }
}
