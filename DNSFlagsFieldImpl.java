/**
 * Basic implementation of DNSFlagsField.
 *
 * @author Chris Blades
 * @version 12/3/10
 */
public class DNSFlagsFieldImpl extends AbstractDNSObject 
                                                    implements DNSFlagsField {
    /** length of a DNSFlagsField in serialized form */
    public static final int FLAGS_LENGTH = 2;

    /** Is the response bit set? */
    private boolean     request;

    /** the opcode of the flags field */
    private DNSOpcode   opcode;

    /** the state of the authoritative bit */
    private boolean     authoritative;

    /** the state of the truncated bit */
    private boolean     truncated;

    /** the state of the recursion desired bit */
    private boolean     recursionDesired;

    /** the state of the recursion available bit */
    private boolean     recursionAvailable;

    /** the return code of this flags field */
    private int         returnCode;

    /**
     * Creates a new DNSFlagsField with all flags set to false, return code
     * of 0, and the QUERY opcode.
     */
    public DNSFlagsFieldImpl() {
        serialized              = new byte[FLAGS_LENGTH];
        this.request            = false;
        this.opcode             = DNSOpcodeEnum.QUERY;
        this.authoritative      = false;
        this.truncated          = false;
        this.recursionDesired   = false;
        this.recursionAvailable = false;
        this.returnCode         = 0;
    }
    
    /**
     * Returns wether the response flag is set or not
     *
     * @return true if this flags field represents a request
     */
    public boolean isRequest() {
        return request;
    }

    /**
     * Returns the opcode vale of this flags field
     *
     * @return the opcode value of this flags field
     */
    public DNSOpcode getOpcode() {
        return opcode;
    }

    /**
     * Returns wether the authoritative bit is set in this flags field.
     *
     * @return the state of the authoritative bit
     */
    public boolean isAuthorative() {
        return authoritative;
    }

    /**
     * Returns wether the truncated bit is set in this flags field.
     *
     * @return the state of the truncated bit
     */
    public boolean isTruncated() {
        return truncated;
    }
    /**
     * Returns wether the recursion desired bit is set in this flags field.
     *
     * @return the state of the recursion desired bit.
     */
    public boolean recursionDesired() {
        return recursionDesired;
    }

    /**
     * Returns wether the recursion available bit is set in this flags field.
     *
     * @return the state of the recursion available bit.
     */
    public boolean recursionAvailable() {
        return recursionAvailable;
    }

    /**
     * Returns the return code value of the flags field.
     *
     * @return the return code of this flags field
     */
    public int getReturnCode() {
        return returnCode;
    }


    /**
     * Sets wether this flags field represents a request.
     *
     * @param request wether this flags field is a request.
     */
    public void setIsRequest(boolean request) {
        this.request = request;
        changed = true;
    }
    /**
     * Sets the opcode of this flags field.
     *
     * @param opcode the new Opcode of this flags field.
     */
    public void setOpcode(DNSOpcode opcode) {
        this.opcode = opcode;
        changed = true;
    }

    /**
     * sets the state of the authoritative bit.
     *
     * @param authoritative the state of the authoritative bit
     */
    public void setAuthoritative(boolean authoritative) {
        this.authoritative = authoritative;
        changed = true;
    }

    /**
     * sets the state of the truncated bit.
     *
     * @param truncated the state of the truncated bit
     */
    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
        changed = true;
    }
    /**
     * sets the state of the recursion desired bit.
     *
     * @param recursionDesired the state of the recursion desired bit
     */
    public void setRecursionDesired(boolean recursionDesired) {
        this.recursionDesired = recursionDesired;
        changed = true;
    }

    /**
     * sets the state of the recursion available bit.
     *
     * @param recursionAvailable the state of the recursion available bit
     */
    public void setRecursionAvailable(boolean recursionAvailable) {
        this.recursionAvailable = recursionAvailable;
        changed = true;
    }

    /**
     * Sets the return code of this flags field.
     *
     * @param code the new return code of this flags field
     */
    public void setReturnCode(int code) {
        this.returnCode = code;
        changed = true;
    }
 
    /**
     * Return the length of the DNS Flag Field, which is a constant.
     *
     * @return length of a flag field
     */
    public int getLength() {
        return FLAGS_LENGTH;
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
            serialized = new byte[FLAGS_LENGTH];
            //
            // construct values
            //
            if (!request) {
                serialized[0] = (byte)(serialized[0] | 0x80);
            }
            byte[] opcodeValue = opcode.serialize();
            opcodeValue[0] = (byte)(opcodeValue[0] << 3);
            serialized[0] = (byte)(serialized[0] | opcodeValue[0]);

            if (authoritative) {
                serialized[0] = (byte)(serialized[0] | 0x04);
            }
            if (truncated) {
                serialized[0] = (byte)(serialized[0] | 0x02);
            }
            if (recursionDesired) {
                serialized[0] = (byte)(serialized[0] | 0x01);
            }
            if (recursionAvailable) {
                serialized[1] = (byte)(serialized[1] | 0x80);
            }
            serialized[1] = (byte)(serialized[1] | returnCode);

            //
            // generate values
            //
            values.clear();
            values.put("Is Request", request);
            values.put("Opcode", opcode.stateValues());
            values.put("Authoratative", authoritative);
            values.put("Truncated", truncated);
            values.put("Recursion Desired", recursionDesired);
            values.put("Recursion Available", recursionAvailable);
            values.put("Return Code", returnCode);           

            changed = false;
        }
    }
}
