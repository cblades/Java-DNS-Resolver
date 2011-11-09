import java.util.List;
import java.util.ArrayList;

/**
 * A decorator for DNSPacket that parses a byte buffer to create a
 * DNSPacket.
 *
 * @author Chris Blades
 * @version 20/3/10
 */
public class DNSPacketParserDecorator implements DNSPacket {
    /** The DNSPacket object this object wraps */
    private DNSPacket packet;

    /** byte buffer to parse and pass to other objects */
    private IndexedArrayList<Byte> bytes;

    /**
     * Creates a new DNSPacketParserDecorator with the given byte
     * buffer.
     *
     * @param bytes the byte buffer to parse.
     */
    public DNSPacketParserDecorator(IndexedArrayList<Byte> bytes) {
        this.packet = new DNSPacketImpl();
        this.bytes  = bytes;
    }

    /**
     * Parses the byte buffer to form a DNSPacket object.
     */
    public void parse() {

        DNSHeaderParserDecorator head = new DNSHeaderParserDecorator(bytes);
        head.parse();
        packet.setHeader(head);
        
       
        // parse questions
        for (int i = 0; i < head.getNumQuestions(); i++) {
            DNSQuestionParserDecorator current = 
                                new DNSQuestionParserDecorator(bytes);
            current.parse();
            packet.setQuestion(current);
        }

        // parse answers
        for (int i = 0; i < head.getNumAnswers(); i++) {
            DNSAnswerParserDecorator current = 
                                new DNSAnswerParserDecorator(bytes);

            current.parse();
            packet.addAnswer(current);
        }
        // parse authoritative answers
        for (int i = 0; i < head.getNumAuthorityAnswers(); i++) {
            DNSAnswerParserDecorator current = 
                                new DNSAnswerParserDecorator(bytes);
            current.parse();
            packet.addAuthoritativeAnswer(current);
        }

        // parse additional answers
        for (int i = 0; i < head.getNumAdditionalAnswers(); i++) {
            DNSAnswerParserDecorator current = 
                                new DNSAnswerParserDecorator(bytes);
            current.parse();
            packet.addAdditionalAnswer(current);
        } 
    }
    public DNSPacket getPacket() {
        return this.packet;
    }
    
    //
    // DNSPacket
    //
    

    /**
     * Returns the header of this DNSPacket.
     *
     * @return This packet's header
     */
    public DNSHeader getHeader() {
        return packet.getHeader();
    }

    /**
     * Returns a list of all the questions contained in this packet.
     *
     * @return a list of all the questions in this packet
     */
    public List<DNSQuestion> getQuestions() {
        return packet.getQuestions();
    }

    /**
     * Returns a list of all the answers contained in this packet.
     *
     * @return a list of all the answers in this packet
     */
    public List<DNSAnswer> getAnswers() {
        return packet.getAnswers();
    }

    /**
     * Returns a list of all the authority answers contained in this packet.
     *
     * @return a list of all the authority answers in this packet
     */
    public List<DNSAnswer> getAuthoritativeAnswers() {
        return packet.getAuthoritativeAnswers();
    }

    /**
     * Returns a list of all the additional answers contained in this packet.
     *
     * @return a list of all the additional answers in this packet
     */
    public List<DNSAnswer> getAdditionalAnswers() {
        return packet.getAdditionalAnswers();
    }
    
    /**
     * Sets the header of this DNSPacket.
     *
     * @param header the new header of this DNSpacket.
     */
    public void setHeader(DNSHeader header) {
        packet.setHeader(header);
    }

    /**
     * Sets the question contained in this packet.
     *
     * @param question the question contained in this packet.
     */
    public void setQuestion(DNSQuestion question) { 
        packet.setQuestion(question);
    }

    /**
     * adds an answer to this packet
     *
     * @param answer the answer to add
     */
    public void addAnswer(DNSAnswer answer) {
        packet.addAnswer(answer);
    }

    /**
     * adds an authority answer to this packet
     *
     * @param answer the authority answer to add
     */
    public void addAuthoritativeAnswer(DNSAnswer answer) {
        packet.addAuthoritativeAnswer(answer);
    }

    /**
     * adds an additional answer to this packet
     *
     * @param answer the additional answer to add
     */
    public void addAdditionalAnswer(DNSAnswer answer) {
        packet.addAdditionalAnswer(answer);
    }
    
    /**
     * Returns the length of the parsed DNSPacket object.
     *
     * @return the length of the parsed DNSPacket object.
     */ 
    public int getLength() {
        return packet.getLength();
    }
    
    /** 
     * Returns a mapping of the state values of the parsed DNSPacket object.
     *
     * @return the state values of the parsed DNSPacket object.
     */
    public Map stateValues() {
        return packet.stateValues();
    }

    /**
     * returns the serialized version of the parsed dnsanswer object.
     * can't just return the byte buffer because we don't know what else is in
     * it.
     *
     * @return the serialized version of the parsed dnsanswer object.
     */
    public byte[] serialize() {
        return packet.serialize();
    }
}
