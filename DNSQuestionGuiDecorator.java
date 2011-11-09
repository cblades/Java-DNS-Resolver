import javax.swing.tree.DefaultMutableTreeNode;

/**
 * A decorator for the DNSQuestion interface that uses a tree to 
 * create a representaiton of state that can be displayed graphically.
 *
 * @author chris Blades 
 * @version 21/3/10
 */
public class DNSQuestionGuiDecorator extends DefaultMutableTreeNode
                                            implements DNSQuestion {
    /**
     * DNSQuestion object that this decorator wraps
     */
    private DNSQuestion question;

    /**
     * Creates a new DNSQuestionGuiDecorator that contains the given
     * DNSQuestion.
     *
     * @param question the DNSQuestion this object should wrap and whose state
     *               it should reflect
     */
    public DNSQuestionGuiDecorator(DNSQuestion question) {
        this.question = question;
        setupGui();
    }

    /**
     * Create a tree that reflects question's state.
     */
    private void setupGui() {
        // since this will be rebuilt everytime the state of question changes,
        // scratch everything
        super.removeAllChildren();
        // set name of root node for this tree
        super.setUserObject(question.getName().getData());
        
        // add name
        super.add(new DefaultMutableTreeNode("Name:  " + 
                                            question.getName().getData()));
        // add type
        super.add(new DefaultMutableTreeNode("Type:  " + 
                                                question.getType().getName()));
        // add class
        super.add(new DefaultMutableTreeNode("Class:  " + 
                                       question.getRecordClass().getName()));     
    }

    //
    // DNSQuestion
    //

    /*
     * Returns the name of question.
     *
     * @returns the name of question
     */
    public DNSUrl getName() {
        return question.getName();
    }

    /**
     * returns the type of record question.
     *
     * @return question's record type
     */
    public DNSRecordType getType() {
        return question.getType();
    }

    /**
     * returns question's DNS class
     *
     * @return question's DNS class
     */
    public DNSRecordClass getRecordClass() {
        return question.getRecordClass();
    }

    /**
     * Set the name of the underlying DNSQuestion object and
     * regenerates the tree.
     *
     * @param name the new name of question
     */ 
    public void setName(DNSUrl name) {
        question.setName(name);
        setupGui();
    }

    /**
     * Sets the type of the underlying DNSQuestion object and
     * regenerates the tree.
     *
     * @param type new DNS type of question
     */
    public void setType(DNSRecordType type) {
        question.setType(type);
        setupGui();
    }

    /**
     * Sets the DNS class of the underlying DNSQuestion object
     * and regenerates the tree
     *
     * @param recordClass new DNS class of question
     */
    public void setClass(DNSRecordClass recordClass) {
        question.setClass(recordClass);
        setupGui();
    }

    /**
     * Returns the length of the underlying DNSQuestion in serialized form
     *
     * @return the length of the serialized form of this DNSQuestion
     */
    public int getLength() {
        return question.getLength();
    }

    /**
     * Returns the serialized version of the underlying DNSQuestion.
     *
     * @return DNSQuestion serialized according to DNS protocol
     */
    public byte[] serialize() {
        return question.serialize();
    }

    /**
     * Returns a mapping of the state values of the underlying DNSQuestion
     *
     * @return mapping of the state of DNSQuestion
     */
    public Map stateValues() {
        return question.stateValues();
    }
}
