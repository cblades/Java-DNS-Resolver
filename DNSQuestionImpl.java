/**
 * Basic implementation of DNSQuestion.
 *
 * @author Chris Blades
 * @version 2/25/10
 */
public class DNSQuestionImpl extends AbstractDNSObject implements DNSQuestion {
    /**
     * Name of this DNSQuestion
     */
    private DNSUrl questionName;

    /** the type of this question */
    private DNSRecordType questionType;

    /** the class of this question */
    private DNSRecordClass questionClass;
    
    /**
     * Returns the name of the record.
     *
     * @return the name of the record
     */
    public DNSUrl getName() {
        return this.questionName;
    }

    /**
     * Returns the type of the record.
     *
     * @return the type of the record.
     */
    public DNSRecordType getType(){
        return this.questionType;
    }

    /**
     * Returns the DNS class of the record.
     *
     * @return the DNS class of the record.
     */
    public DNSRecordClass getRecordClass(){
        return this.questionClass;
    }

    /**
     * Changes the name of the record.
     *
     * @param name the new name of the record
     */
    public void setName(DNSUrl questionName){
        this.questionName = questionName;
        changed = true;
    }

    /**
     * Changes the type of the record.
     *
     * @param type the new type of the record
     */
    public void setType(DNSRecordType questionType){
        this.questionType = questionType;
        changed = true;
    }

    /**
     * Changes the class of the record.
     *
     * @param recordClass the new class of the record
     */
    public void setClass(DNSRecordClass questionClass){
        this.questionClass = questionClass;
        changed = true;
    }
    
    /**
     * Serialize should translate the DNSObject into an array of bytes
     * as per the DNS protocol.
     *
     * @return a byte array representing this DNSObject
     */
    public byte[] serialize() {
        generateTransient();
        return serialized;
    }

    /**
     * Should return a description of the state of this DNSObject in the
     * format [label][value]
     * @return descriptions of the state of this DNSObject
     */
    public Map stateValues() {
        generateTransient();
        return values;
    }
    
    /**
     * Generates serialized and values based on current state of this
     * object.
     */
    protected void generateTransient() {
        // if there's been no change, don't bother regenerating anything
        if (changed) {
            //
            // construct serialized
            //

            // length will equal length of name + 4 bytes for type and class
            serialized = new byte[questionName.getLength() + 4];
            // find length of the url
            byte[] urlBytes = questionName.serialize();

            // copy url bytes
            for (int i = 0; i < urlBytes.length; i++) {
                serialized[i] = urlBytes[i];
            }

           // copy type bytes, only copy 2 bytes (shouldn't be anymore)
           byte[] typeBytes = questionType.serialize();
           serialized[serialized.length - 4] = typeBytes[0];
           serialized[serialized.length - 3] = typeBytes[1];
           
           // copy class bytes, only copy 2 bytes (shouldn't be anymore)
           byte[] classBytes = questionClass.serialize();
           serialized[serialized.length - 2] = classBytes[0];
           serialized[serialized.length - 1] = classBytes[1];

            // 
            // construct values
            //
            
            values.clear();
            values.put("Name", questionName.stateValues());
            values.put("Type", questionType.stateValues());
            values.put("Class", questionClass.stateValues());
        
            changed = false;
        }

    }
}    
