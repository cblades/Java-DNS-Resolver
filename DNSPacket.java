import java.util.List;
/**
 * Defines getter and setter methods for the contents of a
 * DNS packet.
 *
 * @author Chris Blades
 * @version 1/20/2010
 */
public interface DNSPacket extends DNSObject {
    /**
     * Returns the header of this DNSPacket.
     *
     * @return This packet's header
     */
    public DNSHeader getHeader();
    
    /**
     * Returns a list of all the questions contained in this packet.
     *
     * @return a list of all the questions in this packet
     */
    public List<DNSQuestion> getQuestions();

    /**
     * Returns a list of all the answers contained in this packet.
     *
     * @return a list of all the answers in this packet
     */
    public List<DNSAnswer> getAnswers();

    /**
     * Returns a list of all the authority answers contained in this packet.
     *
     * @return a list of all the authority answers in this packet
     */
    public List<DNSAnswer> getAuthoritativeAnswers();

    /**
     * Returns a list of all the additional answers contained in this packet.
     *
     * @return a list of all the additional answers in this packet
     */
    public List<DNSAnswer> getAdditionalAnswers();
    
    /**
     * Sets the header of this DNSPacket.
     *
     * @param header the new header of this DNSpacket.
     */
    public void setHeader(DNSHeader header);
    
    /**
     * Sets the question contained in this packet.
     *
     * @param question the question contained in this packet.
     */
    public void setQuestion(DNSQuestion question);
    
    /**
     * adds an answer to this packet
     *
     * @param answer the answer to add
     */
    public void addAnswer(DNSAnswer answer);

    /**
     * adds an authority answer to this packet
     *
     * @param answer the authority answer to add
     */
    public void addAuthoritativeAnswer(DNSAnswer answer);

    /**
     * adds an additional answer to this packet
     *
     * @param answer the additional answer to add
     */
    public void addAdditionalAnswer(DNSAnswer answer);
}  
