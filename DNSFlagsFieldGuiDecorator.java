import javax.swing.tree.DefaultMutableTreeNode;

/**
 * A decorator for the DNSFlagsField interface that uses a tree to 
 * create a representaiton of state that can be displayed graphically.
 *
 * @author chris Blades 
 * @version 21/3/10
 */
public class DNSFlagsFieldGuiDecorator extends DefaultMutableTreeNode
                                                    implements DNSFlagsField { 
    /**
     * DNSFlagsField object that this decorator wraps
     */
    DNSFlagsField flags;

    /**
     * Creates a new DNSFlagsFieldGuiDecorator that contains the given
     * DNSFlagsField.
     *
     * @param flags the DNSFlagsField this object should wrap and whose state
     *               it should reflect
     */
    public DNSFlagsFieldGuiDecorator(DNSFlagsField flags) {
        this.flags = flags;
        setupGui();
    }

    /**
     * Create a tree that reflects flag's state.
     */
    private void setupGui() {
        // destroy previous tree since we're rebuilding
        super.removeAllChildren();

        // set name of root node
        super.setUserObject("Flags:");

        // add is request
        String request = flags.isRequest() ? "Response:  Message is a Query" :                                               "Response:  Message is a Response";
        
        super.add(new DefaultMutableTreeNode(request));

        // add opcode
        super.add(new DefaultMutableTreeNode("Opcode:  " + 
                                        flags.getOpcode().getName()));
        
        // add truncated    
        String truncated = flags.isTruncated() ? 
                            "Truncated:  Message is Truncated" :
                            "Truncated:  Message is not Truncated";
        super.add(new DefaultMutableTreeNode(truncated)); 

        // add recursion desired
        String recDesired = flags.recursionDesired() ?
                            "Recursion Desired:  Yes" :
                            "Recursion Desired:  No";
        super.add(new DefaultMutableTreeNode(recDesired));

        // add recursion available
        String recAvailable = flags.recursionAvailable() ?
                            "Recursion Available:  Yes" :
                            "Recursion Available:  No";
        super.add(new DefaultMutableTreeNode(recAvailable));

        // add authoritative
        String authority = flags.isAuthorative() ?
                            "Authoritative Answer:  Yes" :
                            "Authoritative Answer:  No";  
        super.add(new DefaultMutableTreeNode(authority)); 
            
        // add return code    
        super.add(new DefaultMutableTreeNode("Return Code:  " + 
                                            flags.getReturnCode())); 
    }

    //
    // DNSFlagsField
    //


    /**
     * Returns wether the response flag is set or not
     *
     * @return true if this flags field represents a request
     */
    public boolean isRequest() {
        return flags.isRequest();
    }

    /**
     * Returns the opcode vale of this flags field
     *
     * @return the opcode value of this flags field
     */
    public DNSOpcode getOpcode() {
        return flags.getOpcode();
    }
    /**
     * Returns wether the authoritative bit is set in this flags field.
     *
     * @return the state of the authoritative bit
     */
    public boolean isAuthorative() {
        return flags.isAuthorative();
    }

    /**
     * Returns wether the truncated bit is set in this flags field.
     *
     * @return the state of the truncated bit
     */
    public boolean isTruncated() {
        return flags.isTruncated();
    }

    /**
     * Returns wether the recursion desired bit is set in this flags field.
     *
     * @return the state of the recursion desired bit.
     */
    public boolean recursionDesired() {
        return flags.recursionDesired();
    }

    /**
     * Returns wether the recursion available bit is set in this flags field.
     *
     * @return the state of the recursion available bit.
     */
    public boolean recursionAvailable() {
        return flags.recursionAvailable();
    }
    
    /**
     * Returns the return code value of the flags field.
     *
     * @return the return code of this flags field
     */
    public int getReturnCode() {
        return flags.getReturnCode();
    }


    /**
     * Sets wether this flags field represents a request.
     *
     * @param request wether this flags field is a request.
     */
    public void setIsRequest(boolean request) {
        setupGui();
        flags.setIsRequest(request);
    }

    /**
     * Sets the opcode of this flags field.
     *
     * @param opcode the new Opcode of this flags field.
     */
    public void setOpcode(DNSOpcode opcode) {
        setupGui();
        flags.setOpcode(opcode);
    }

    /**
     * sets the state of the authoritative bit.
     *
     * @param authoritative the state of the authoritative bit
     */
    public void setAuthoritative(boolean authoritative) {
        setupGui();
        flags.setAuthoritative(authoritative);
    }

    /**
     * sets the state of the truncated bit.
     *
     * @param truncated the state of the truncated bit
     */
    public void setTruncated(boolean truncated) {
        setupGui();
        flags.setTruncated(truncated);
    }

    /**
     * sets the state of the recursion desired bit.
     *
     * @param recursionDesired the state of the recursion desired bit
     */
    public void setRecursionDesired(boolean recursionDesired) {
        setupGui();
        flags.setRecursionDesired(recursionDesired);
    }

    /**
     * sets the state of the recursion available bit.
     *
     * @param recursionAvailable the state of the recursion available bit
     */
    public void setRecursionAvailable(boolean recursionAvailable) {
        setupGui();
        flags.setRecursionAvailable(recursionAvailable);
    }

    /**
     * Sets the return code of this flags field.
     *
     * @param code the new return code of this flags field
     */
    public void setReturnCode(int code) {
        setupGui();
        flags.setReturnCode(code);
    }
    
    /**
     * Returns the length of the serialzied form of this DNSFlagsField.
     *
     * @return the length of the serialized form of this DNSFlagsField
     */
    public int getLength() {
        return flags.getLength();
    }

    /**
     * Returns a serialized version of this DNSFlagsField as per
     * DNS protocol
     *
     * @return serialized version of this DNSFlagsField 
     */
    public byte[] serialize() {
        return flags.serialize();
    }
    
    /**
     * Returns a mapping of the state values of this DNSFlagsField.
     *
     * @return The state of this DNSFlagsField
     */
    public Map stateValues() {
        return flags.stateValues();
    }
}
