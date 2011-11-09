import javax.swing.tree.DefaultMutableTreeNode;

/**
 * A decorator for the DNSHeader interface that uses a tree to 
 * create a representaiton of state that can be displayed graphically.
 *
 * @author chris Blades 
 * @version 21/3/10
 */
public class DNSHeaderGuiDecorator extends DefaultMutableTreeNode
                                        implements DNSHeader {
    /**
     * DNSHeader object that this decorator wraps
     */
    private DNSHeader header;

    /**
     * Creates a new DNSHeaderGuiDecorator that contains the given
     * DNSHeader.
     *
     * @param header the DNSHeader this object should wrap and whose state
     *               it should reflect
     */
    public DNSHeaderGuiDecorator(DNSHeader header) {
        this.header = header;
        setupGui();
    }

    /**
     * Create a tree that reflects answer's state.
     */
    private void setupGui() {
        // since this will be rebuilt everytime the state of answer changes,
        // scratch everything
        super.removeAllChildren();

        // set name of root node for this tree
        super.setUserObject("Header");
        
        // add identifier
        super.add(new DefaultMutableTreeNode("Transaction ID:  " + 
                                            header.getIdentifier()));

        // add flags field
        super.add(new DNSFlagsFieldGuiDecorator(header.getFlags()));

        // add num questions
        super.add(new DefaultMutableTreeNode("Questions: " + 
                                                header.getNumQuestions()));    

        // add num answers
        super.add(new DefaultMutableTreeNode("Answer RRs: " + 
                                                header.getNumAnswers()));    

        // add num authority answers
        super.add(new DefaultMutableTreeNode("Authority RRs: " + 
                                          header.getNumAuthorityAnswers()));    

        // add num additional answers
        super.add(new DefaultMutableTreeNode("Additional RRs: " + 
                                          header.getNumAdditionalAnswers()));    
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
        setupGui();
    }

    /**
     * Sets the flags field of this header
     *
     * @param flags the new flags field of this header.
     */
    public void setFlags(DNSFlagsField flags) {
        header.setFlags(flags);
        setupGui();
    }

    /**
     * Sets the number of questions field of this header.
     *
     * @param numQuestions the new number of questions
     */
    public void setNumQuestions(int numQuestions) {
        header.setNumQuestions(numQuestions);
        setupGui();
    }

    /**
     * Sets the number of answers field of this header.
     *
     * @param numAnswers the new number of answers
     */
    public void setNumAnswers(int numAnswers) {
        header.setNumAnswers(numAnswers);
        setupGui();
    }

    /**
     * Sets the number of authority answers field of this header.
     *
     * @param numAuthorityAnswers the new number of authority answers
     */
    public void setNumAuthorityAnswers(int numAuthorityAnswers) {
        header.setNumAuthorityAnswers(numAuthorityAnswers);
        setupGui();
    }

    /**
     * Sets the number of additional answers field of this header.
     *
     * @param numAdditionalAnswers the new number of additional answers
     */
    public void setNumAdditionalAnswers(int numAdditionalAnswers) {
        header.setNumAdditionalAnswers(numAdditionalAnswers);
        setupGui();
    }
    
    /**
     * Returns the length of the serialized version of the underlying 
     * DNSHeader obejct
     *
     * @return the length of this DNSheader when serialized
     */
    public int getLength() {
        return header.getLength();
    }

    /**
     * Returns the underlying DNSheader serialized as per the DNS protocol
     * 
     * @return serialized version of this DNSHeader
     */
    public byte[] serialize() {
        return header.serialize();
    }

    /**
     * Returns a mapping of the state values of the underlying DNSHeader
     *
     * @return the state values of this DNSHeader
     */
    public Map stateValues() {
        return header.stateValues();
    }
}
