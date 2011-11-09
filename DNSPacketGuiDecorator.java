import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

/**
 * A decorator for the DNSPacketGuiDecorator interface that uses a tree to 
 * create a representaiton of state that can be displayed graphically.
 *
 * @author chris Blades 
 * @version 21/3/10
 */
public class DNSPacketGuiDecorator extends DefaultMutableTreeNode
                                            implements DNSPacket {
    /**
     * DNSPacket object that this decorator wraps
     */
    private DNSPacket packet;
    
    /**
     * Creates a new DNSPacketGuiDecorator that contains the given
     * DNSPacket.
     *
     * @param packet the DNSPacket this object should wrap and whose state
     *               it should reflect
     */
    public DNSPacketGuiDecorator(DNSPacket packet) {
        this.packet = packet;
        setupGui();
    }
    
    /**
     * Create a tree that reflects packet's state.
     */
    public void setupGui() {
        // since this will be rebuilt everytime the state of answer changes,
        // scratch everything
        super.removeAllChildren();

        // set name of root node for this tree
        super.setUserObject("Domain Name System"); 
        
        // add header
        super.add(new DNSHeaderGuiDecorator(packet.getHeader()));
        
        // add questions
        DefaultMutableTreeNode questions = new DefaultMutableTreeNode(
                                                                    "Queries");
        for (DNSQuestion q : packet.getQuestions()) {
            questions.add(new DNSQuestionGuiDecorator(q));
        }
        // only add sub-tree if there are 1 or more nodes
        if (packet.getQuestions().size() > 0) {
            super.add(questions);
        }
        
        // add answers
        DefaultMutableTreeNode answers = new DefaultMutableTreeNode(
                                                                   "Answers");
        for (DNSAnswer a : packet.getAnswers()) {
            answers.add(new DNSAnswerGuiDecorator(a));
        }
        // only add sub-tree if there are 1 or more nodes
        if (packet.getAnswers().size() > 0) {
            super.add(answers);
        }

        // add authority answers
        answers = new DefaultMutableTreeNode(
                                                          "Authority Answers");
        for (DNSAnswer a : packet.getAuthoritativeAnswers()) {
            answers.add(new DNSAnswerGuiDecorator(a));
        }
        // only add sub-tree if there are 1 or more nodes
        if (packet.getAuthoritativeAnswers().size() > 0) {
            super.add(answers);
        }

        // add additional answers
        answers = new DefaultMutableTreeNode(
                                                          "Additional Answers");
        for (DNSAnswer a : packet.getAdditionalAnswers()) {
            answers.add(new DNSAnswerGuiDecorator(a));
        }
        // only add sub-tree if there are 1 or more nodes
        if (packet.getAdditionalAnswers().size() > 0) {
            super.add(answers);
        }
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
     * Returns the length of the serialized version of the underlying
     * DNSPacket.
     *
     * @return the length of the serialized version of this packet
     */
    public int getLength() {
        return packet.getLength();
    }
    
    /**
     * Returns a mapping of the state values of this DNSpacket.
     *
     * @return the state values of this DNSPacket
     */
    public Map stateValues() {
        return packet.stateValues();
    }

    /**
     * Returns the serialized version of this DNSPacket.
     *
     * @return this packet serialized as per the DNS protocol
     */
    public byte[] serialize() {
        return packet.serialize();
    }
}
