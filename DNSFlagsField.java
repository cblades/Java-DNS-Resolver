/**
 * Representation of the flags field of a DNS header.  Contains getters and 
 * setters for all of the flags and values contained within the flags field.
 *
 * @author Chris Blades
 * @version 3/2/10
 */
public interface DNSFlagsField extends DNSObject {
    /**
     * Returns wether the response flag is set or not
     *
     * @return true if this flags field represents a request
     */
    public boolean isRequest();
    
    /**
     * Returns the opcode vale of this flags field
     *
     * @return the opcode value of this flags field
     */
    public DNSOpcode getOpcode();
    
    /**
     * Returns wether the authoritative bit is set in this flags field.
     *
     * @return the state of the authoritative bit
     */
    public boolean isAuthorative();
    
    /**
     * Returns wether the truncated bit is set in this flags field.
     *
     * @return the state of the truncated bit
     */
    public boolean isTruncated();
    
    /**
     * Returns wether the recursion desired bit is set in this flags field.
     *
     * @return the state of the recursion desired bit.
     */
    public boolean recursionDesired();
    
    /**
     * Returns wether the recursion available bit is set in this flags field.
     *
     * @return the state of the recursion available bit.
     */
    public boolean recursionAvailable();
    
    /**
     * Returns the return code value of the flags field.
     *
     * @return the return code of this flags field
     */
    public int getReturnCode();

    
    /**
     * Sets wether this flags field represents a request.
     *
     * @param request wether this flags field is a request.
     */
    public void setIsRequest(boolean request);
    
    /**
     * Sets the opcode of this flags field.
     *
     * @param opcode the new Opcode of this flags field.
     */
    public void setOpcode(DNSOpcode opcode);
    
    /**
     * sets the state of the authoritative bit.
     *
     * @param authoritative the state of the authoritative bit
     */
    public void setAuthoritative(boolean authoritative);

    /**
     * sets the state of the truncated bit.
     *
     * @param truncated the state of the truncated bit
     */
    public void setTruncated(boolean truncated);

    /**
     * sets the state of the recursion desired bit.
     *
     * @param recursionDesired the state of the recursion desired bit
     */
    public void setRecursionDesired(boolean recursionDesired);

    /**
     * sets the state of the recursion available bit.
     *
     * @param recursionAvailable the state of the recursion available bit
     */
    public void setRecursionAvailable(boolean recursionAvailable);

    /**
     * Sets the return code of this flags field.
     *
     * @param code the new return code of this flags field
     */
    public void setReturnCode(int code);
}
