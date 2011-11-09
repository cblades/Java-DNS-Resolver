
/**
 * Represents a DNSObject conforming to the DNS protcol for the header
 * of a DNS packet.
 *
 * @author Chris Blades
 * @version 12/20/2009
 */
public class DNSHeaderImpl extends AbstractDNSObject implements DNSHeader {
    /** the length of a DNS header */
    private static final int    HEADER_LENGTH = 12;
    
    /** a numeric identifier used to pair questions with responses */
    private int                 identifier;

    /** flags field for this header */
    private DNSFlagsField       flags;

    /** the number of questions a packet with this header would contain */
    private int                 numQuestions;

    /** the number of answrs a packet with this header would contain */
    private int                 numAnswers;

    /** 
     * the number of authority answers a packet with this header would contain 
     */
    private int                 numAuthorityAnswers;

    /** 
     * the number of additional answers a packet with this header would contain 
     */
    private int                 numAdditionalAnswers;

    /**
     * Create a new DNSHeader with a random identifier, and all quantitities
     * set to 0
     */
    public DNSHeaderImpl() {
        serialized                = new byte[HEADER_LENGTH];
        this.identifier           = (int)Math.random();
        this.flags                = new DNSFlagsFieldImpl();
        this.numQuestions         = 0;
        this.numAnswers           = 0;
        this.numAuthorityAnswers  = 0;
        this.numAdditionalAnswers = 0;
    }

    /**
     * Returns the identifier of this header.
     *
     * @return this headers identifier
     */
    public int getIdentifier() {
        return this.identifier;
    }

    /**
     * Returns the flags field of this header.
     *
     * @return the flags field of this header.
     */
    public DNSFlagsField getFlags() {
        return this.flags;
    }

    /**
     * Returns the number of questions in a packet with this header.
     *
     * @return the number of questions field
     */
    public int getNumQuestions() {
        return this.numQuestions;
    }

    /**
     * Returns the number of answers in a packet with this header.
     *
     * @return the number of answers field
     */
    public int getNumAnswers() {
        return this.numAnswers;
    }

    /**
     * Returns the number of authority answers in a packet with this header.
     *
     * @return the number of authority answers field
     */
    public int getNumAuthorityAnswers() {
        return this.numAuthorityAnswers;
    }

    /**
     * Returns the number of additional answers in a packet with this header.
     *
     * @return the number of additional answers field
     */
    public int getNumAdditionalAnswers() {
        return this.numAdditionalAnswers;
    }


    /**
     * Sets the identifier of this header.
     *
     * @param identifier the new identifier of this header
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
        changed = true;
    }

    /**
     * Sets the flags field of this header
     *
     * @param flags the new flags field of this header.
     */
    public void setFlags(DNSFlagsField flags) {
        this.flags = flags;
        changed = true;
    }

    /**
     * Sets the number of questions field of this header.
     *
     * @param numQuestions the new number of questions
     */
    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
        changed = true;
    }

    /**
     * Sets the number of answers field of this header.
     *
     * @param numAnswers the new number of answers
     */
    public void setNumAnswers(int numAnswers) {
        this.numAnswers = numAnswers;
        changed = true;
    }

    /**
     * Sets the number of authority answers field of this header.
     *
     * @param numAuthorityAnswers the new number of authority answers
     */
    public void setNumAuthorityAnswers(int numAuthorityAnswers) {
        this.numAuthorityAnswers = numAuthorityAnswers;
        changed = true;
    }
    
    /**
     * Sets the number of additional answers field of this header.
     *
     * @param numAdditionalAnswers the new number of additional answers
     */
    public void setNumAdditionalAnswers(int numAdditionalAnswers) {
        this.numAdditionalAnswers = numAdditionalAnswers;
        changed = true;
    }

    /**
     * Returns the length of this object when serialized.
     *
     * @return the length of this object when serialized as a DNS header
     */
    public int getLength() {
        return this.HEADER_LENGTH;
    }

    /**
     * Serialize should translate the DNSObject into an array of bytes
     * as per the DNS protocol.
     *
     * @return a byte array representing this DNSObject
     */
    public byte[] serialize() {
        generateTransient();
        return serialized;
    }

    /**
     * Should return a description of the state of this DNSObject in the
     * format [label][value]
     * @return descriptions of the state of this DNSObject
     */
    public Map stateValues() {
        generateTransient();
        return values;
    }
    
    /**
     * Generates serialized and values based on current state of this
     * object.
     */
    protected void generateTransient() {
        // if there's been no change, don't bother regenerating anything
        if (changed) {
            //
            // generate serialized
            //
            //
            // translate identifier
            serialized[0] = (byte)(identifier >>> 8);
            serialized[1] = (byte)(identifier);
            // copy flag bytes
            byte[] flagsBytes = flags.serialize();
            for (int i = 0; i < flagsBytes.length; i++) {
                serialized[i + 2] = flagsBytes[i];
            }
            // translate numQuestions
            serialized[4] = (byte)(numQuestions >>> 8);
            serialized[5] = (byte)(numQuestions);
            // translate numAnswers
            serialized[6] = (byte)(numAnswers >>> 8);
            serialized[7] = (byte)(numAnswers);
            // translate numAuthorityAnswers
            serialized[8] = (byte)(numAuthorityAnswers >>> 8);
            serialized[9] = (byte)(numAuthorityAnswers);
            // translate numAdditionalAnswers
            serialized[10] = (byte)(numAdditionalAnswers >>> 8);
            serialized[11] = (byte)(numAdditionalAnswers);
            
            //
            // generate values
            //
            values.clear();
            values.put("Identifier", identifier);
            values.put("Flags", flags.stateValues());
            values.put("Number of Questions", numQuestions);
            values.put("Number of Answers", numAnswers);
            values.put("Number of Authority Answers", numAuthorityAnswers);
            values.put("Number of Additional Answers", numAdditionalAnswers);
            
            // reset changed
            changed = false;
        }
    }
}
