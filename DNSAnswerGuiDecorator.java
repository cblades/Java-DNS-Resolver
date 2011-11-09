import javax.swing.tree.DefaultMutableTreeNode;

/**
 * A decorator for the DNSAnswer interface that uses a tree to 
 * create a representaiton of state that can be displayed graphically.
 *
 * @author chris Blades 
 * @version 21/3/10
 */
public class DNSAnswerGuiDecorator extends DefaultMutableTreeNode
                                            implements DNSAnswer {
    /**
     * DNSAnswer object that this decorator wraps
     */
    private DNSAnswer answer;

    /**
     * Creates a new DNSAnswerGuiDecorator that contains the given
     * DNSAnswer.
     *
     * @param answer the DNSAnswer this object should wrap and whose state
     *               it should reflect
     */
    public DNSAnswerGuiDecorator(DNSAnswer answer) {
        this.answer = answer;
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
        super.setUserObject(answer.getName().getData());

        // add answer's name
        super.add(new DefaultMutableTreeNode("Name:  " + 
                                            answer.getName().getData()));
        
        // add answer's type
        super.add(new DefaultMutableTreeNode("Type:  " + 
                                                answer.getType().getName()));

        // add answer's class
        super.add(new DefaultMutableTreeNode("Class:  " + 
                                        answer.getRecordClass().getName()));

        // add answers TTL
        super.add(new DefaultMutableTreeNode("Time to Live:  " + 
                                                answer.getTTL() + " seconds"));

        // add answer's data length
        super.add(new DefaultMutableTreeNode("Data Length:  " + 
                                          answer.getDataLength() + " bytes"));

        // add answers data
        super.add(new DefaultMutableTreeNode("Address:  " + 
                                                answer.getData().getData() ));
            
    }
    
    //
    // DNSAnswer
    //

    /*
     * Returns the name of answer.
     *
     * @returns the name of answer
     */
    public DNSUrl getName() {
        return answer.getName();
    }

    /**
     * returns the type of record answer.
     *
     * @return answer's record type
     */
    public DNSRecordType getType() {
        return answer.getType();
    }

    /**
     * returns answer's DNS class
     *
     * @return answer's DNS class
     */
    public DNSRecordClass getRecordClass() {
        return answer.getRecordClass();
    }

    /**
     * Set the name of the underlying DNSAnswer object and
     * regenerates the tree.
     *
     * @param name the new name of answer
     */ 
    public void setName(DNSUrl name) {
        answer.setName(name);
        setupGui();
    }

    /**
     * Sets the type of the underlying DNSAnswer object and
     * regenerates the tree.
     *
     * @param type new DNS type of answer
     */
    public void setType(DNSRecordType type) {
        answer.setType(type);
        setupGui();
    }

    /**
     * Sets the DNS class of the underlying DNSAnswer object
     * and regenerates the tree
     *
     * @param recordClass new DNS class of answer
     */
    public void setClass(DNSRecordClass recordClass) {
        answer.setClass(recordClass);
        setupGui();
    }
    
    /**
     * Returns the Time-to-Live of the underlying DNSAnswer object.
     *
     * @return the TTL of the underlying DNSAnswer
     */
    public int getTTL() {
        return answer.getTTL();
    }

    /**
     * returns the data length of hte underlying DNSAnswer object.
     *
     * @return the data length of the underying DNSAnswer
     */
    public int getDataLength() {
        return answer.getDataLength();
    }

    /**
     * Returns the data contained in the underlying DNSAnswer object.
     *
     * @return the data of the underlying DNSAnswer
     */
    public DNSResource getData() {
        return answer.getData();
    }

    /**
     * Sets the TTL of the underlying DNSAnswer
     *
     * @param TTL new time to live
     */
    public void setTTL(int TTL) {
        answer.setTTL(TTL);
        setupGui();
    }

    /**
     * sets the data contained within the underlying DNSAnswer
     *
     * @param data new data contained within the underlying DNSAnswer
     */
    public void setData(DNSResource data) {
        answer.setData(data);
        setupGui();
    }

    /**
     * Returns the length of the underlying DNSAnswer in serialized form
     *
     * @return the length of the serialized form of this DNSAnswer
     */
    public int getLength() {
        return answer.getLength();
    }

    /**
     * Returns the serialized version of the underlying DNSAnswer.
     *
     * @return DNSAnswer serialized according to DNS protocol
     */
    public byte[] serialize() {
        return answer.serialize();
    }

    /**
     * Returns a mapping of the state values of the underlying DNSAnswer
     *
     * @return mapping of the state of DNSAnswer
     */
    public Map stateValues() {
            return answer.stateValues();
    }
}
