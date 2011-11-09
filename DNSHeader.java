/**
 * Represents a DNSObject conforming to the DNS protcol for the header
 * of a DNS packet.
 *
 * @author Chris Blades
 * @version 12/20/2009
 */
public interface DNSHeader extends DNSObject {
    /**
     * Returns the identifier of this header.
     *
     * @return this headers identifier
     */
    public int getIdentifier();
    
    /**
     * Returns the flags field of this header.
     *
     * @return the flags field of this header.
     */
    public DNSFlagsField getFlags();
    
    /**
     * Returns the number of questions in a packet with this header.
     *
     * @return the number of questions field
     */
    public int getNumQuestions();
    
    /**
     * Returns the number of answers in a packet with this header.
     *
     * @return the number of answers field
     */
    public int getNumAnswers();
    
    /**
     * Returns the number of authority answers in a packet with this header.
     *
     * @return the number of authority answers field
     */
    public int getNumAuthorityAnswers();
    
    /**
     * Returns the number of additional answers in a packet with this header.
     *
     * @return the number of additional answers field
     */
    public int getNumAdditionalAnswers();


    /**
     * Sets the identifier of this header.
     *
     * @param identifier the new identifier of this header
     */
    public void setIdentifier(int identifier);
    
    /**
     * Sets the flags field of this header
     *
     * @param flags the new flags field of this header.
     */
    public void setFlags(DNSFlagsField flags);
    
    /**
     * Sets the number of questions field of this header.
     *
     * @param numQuestions the new number of questions
     */
    public void setNumQuestions(int numQuestions);

    /**
     * Sets the number of answers field of this header.
     *
     * @param numAnswers the new number of answers
     */
    public void setNumAnswers(int numAnswers);

    /**
     * Sets the number of authority answers field of this header.
     *
     * @param numAuthorityAnswers the new number of authority answers
     */
    public void setNumAuthorityAnswers(int numAuthorityAnswers);

    /**
     * Sets the number of additional answers field of this header.
     *
     * @param numAdditionalAnswers the new number of additional answers
     */

    public void setNumAdditionalAnswers(int numAdditionalAnswers);
}
