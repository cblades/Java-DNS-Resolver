import java.util.List;
import java.util.ArrayList;

/**
 * Basic Implementation of DNSPacket, acts as a container for other DNSObjects.
 *
 * @author Chris Blades
 * @version 3/16/2010
 */
public class DNSPacketImpl extends AbstractDNSObject implements DNSPacket {
    /** The header of this packet */
    private DNSHeader         header;

    /** list of questions contained in this packet */
    private List<DNSQuestion> questions;

    /** list of answers contained in this packet */
    private List<DNSAnswer>   answers;

    /** list of authority answers contained in this packet */
    private List<DNSAnswer>   authoritativeAnswers;

    /** list of additional answers contained in this packet */
    private List<DNSAnswer>   additionalAnswers;

    /**
     * Creates a new DNSPacket with no header, questions, or answers.
     */
    public DNSPacketImpl() {
        this.questions            = new ArrayList<DNSQuestion>();
        this.answers              = new ArrayList<DNSAnswer>();
        this.authoritativeAnswers = new ArrayList<DNSAnswer>();
        this.additionalAnswers    = new ArrayList<DNSAnswer>();
    }

    /**
     * Returns the header of this DNSPacket.
     *
     * @return This packet's header
     */
    public DNSHeader getHeader() {
        return this.header;
    }

    /**
     * Returns a list of all the questions contained in this packet.
     *
     * @return a list of all the questions in this packet
     */
    public List<DNSQuestion> getQuestions() {
        return this.questions;
    }

    /**
     * Returns a list of all the answers contained in this packet.
     *
     * @return a list of all the answers in this packet
     */
    public List<DNSAnswer> getAnswers() {
        return this.answers;
    }

    /**
     * Returns a list of all the authority answers contained in this packet.
     *
     * @return a list of all the authority answers in this packet
     */
    public List<DNSAnswer> getAuthoritativeAnswers() {
        return this.authoritativeAnswers;
    }

    /**
     * Returns a list of all the additional answers contained in this packet.
     *
     * @return a list of all the additional answers in this packet
     */
    public List<DNSAnswer> getAdditionalAnswers() {
        return this.additionalAnswers;
    }

    /**
     * Sets the header of this DNSPacket.
     *
     * @param header the new header of this DNSpacket.
     */
    public void setHeader(DNSHeader header) {
        this.header = header;
        changed = true;
    }
    
    /**
     * Sets the question contained in this packet.
     *
     * @param question the question contained in this packet.
     */
    public void setQuestion(DNSQuestion question) {
        this.questions.add(question);
        changed = true;
    }

    /**
     * adds an answer to this packet
     *
     * @param answer the answer to add
     */
    public void addAnswer(DNSAnswer answer) {
        this.answers.add(answer);
        changed = true;
    }

    /**
     * adds an authority answer to this packet
     *
     * @param answer the authority answer to add
     */
    public void addAuthoritativeAnswer(DNSAnswer answer) {
        this.authoritativeAnswers.add(answer);
        changed = true;
    }

    /**
     * adds an additional answer to this packet
     *
     * @param answer the additional answer to add
     */
    public void addAdditionalAnswer(DNSAnswer answer) {
        this.additionalAnswers.add(answer);
        changed = true;
    }
    
    /**
     * Returns the length of this DNSPacket when serialized.
     *
     * @return the length of the serialized version of this packet.
     */
    public int getLength() {
        generateTransient();
        return serialized.length;
    }

    /**
     * Returns a byte array containing this packet serialized as per the
     * DNS protocol
     *
     * @return this packet serialized as per the DNS protol
     */
    public byte[] serialize() {
        generateTransient();
        return this.serialized;
    }

    /**
     * Returns a mapping of state values of this DNSPacket
     *
     * @return the state values of this DNSPacket
     */
    public Map stateValues() {
        generateTransient();
        return this.values;
    }

    /**
     * Re-generates serialized and values when changes are made to the state
     * of this packet
     */
    protected void generateTransient() {
        if (changed) {
            //
            // generate values
            //
            
            // find size
            int size = header.getLength();        
            for (DNSObject o : questions) {
                size += o.getLength();
            }

            for (DNSObject o : answers) {
                size += o.getLength();
            }
            
            for (DNSObject o : authoritativeAnswers) {
                size += o.getLength();
            }

            for (DNSObject o : additionalAnswers) {
                size += o.getLength();
            }
            serialized = new byte[size];

            int index = 0;
            // copy header bytes
            byte[] tempBytes = header.serialize();
            for (int i = 0; i < tempBytes.length; i++) {
                serialized[index] = tempBytes[i];
                index++;
            }

            // copy question bytes
            for (DNSObject o : questions) {
                tempBytes = o.serialize();
                for (int i = 0; i < tempBytes.length; i++) {
                    serialized[index] = tempBytes[i];
                    index++;
                }
            }

            // copy answer bytes
            for (DNSAnswer o : answers) {
                tempBytes = o.serialize();
                for (int i = 0; i < tempBytes.length; i++) {
                    serialized[index++] = tempBytes[i];
                }
            }

            // copy authoritative answer bytes
            for (DNSObject o : authoritativeAnswers) {
                tempBytes = o.serialize();
                for (int i = 0; i < tempBytes.length; i++) {
                    serialized[index] = tempBytes[i];
                    index++;
                }
            }

            // copy additional answer bytes
            for (DNSObject o : additionalAnswers) {
                tempBytes = o.serialize();
                for (int i = 0; i < tempBytes.length; i++) {
                    serialized[index] = tempBytes[i];
                    index++;
                }
            }

            //
            // generate values
            //

            // build header string
            values.clear();
            values.put("Header", header.stateValues());           

            // build question strings
            Map<Object, Object>  temp = new Map<Object, Object>();
            int i = 1;
            for (DNSObject o : questions) {
                temp.put(i, o.stateValues());
                i++; 
            }
            values.put("Questions", temp);

            // build answer strings
            temp = new Map<Object, Object>();
            i = 1;
            for (DNSObject o : answers) {
                temp.put(i, o.stateValues());
                i++; 
            }
            values.put("Answers", temp);
            
            // build authoritative answer strings
            temp = new Map<Object, Object>();
            i = 1;
            for (DNSObject o : authoritativeAnswers) {
                temp.put(i, o.stateValues());
                i++; 
            }
            values.put("Authoritative Answers", temp);
            
            // build additional answer strings
            temp = new Map<Object, Object>();
            i = 1;
            for (DNSObject o : additionalAnswers) {
                temp.put(i, o.stateValues());
                i++; 
            }
            values.put("Additional Answers", temp);

            changed = false;
        }
    }
}


