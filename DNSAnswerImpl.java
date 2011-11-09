/**
 * Basic implementation of DNSAnswer.
 *
 * @author Chris Blades
 * @version 2/25/10
 */
public class DNSAnswerImpl extends AbstractDNSObject implements DNSAnswer{
    /**
     * Name of this DNSAnswer
     */
    private DNSUrl          answerName;
    
    /** The type of this Record */
    private DNSRecordType   answerType;

    /** the DNS class of this recrod */
    private DNSRecordClass  answerClass;

    /** Time-to-Live for this Record */
    private int             answerTTL;

    /** the length of the serialized version of this DNSAnswer */
    private int             length;

    /** The data contained within this Record */
    private DNSResource     data;

    /**
     * Create a new DNSAnswer with empty name and data
     */
    public DNSAnswerImpl() {
        this(new DNSUrlImpl(""), new DNSUrlImpl(""));
    }

    /**
     * Create a new DNSAnswer with the given name and data
     *
     * @param name the name of the new DNSAnswer
     * @param data data contained within the new DNSAnswer
     */
    public DNSAnswerImpl(DNSUrl name, DNSResource data) {
        this.answerName = name;
        setData(data);
    }

    /**
     * Return the time to live for this DNSAnswer; i.e., how long the
     * data contained within this Record is valid.
     *
     * @return the time to live for this Record.
     */
    public int getTTL() {
        return this.answerTTL;
    }

    /**
     * Get the data contained within this Record; i.e., the answer.
     *
     * @return the data portion of this Record.
     */
    public DNSResource getData() {
        return this.data;
    }
    
    /**
     * Return the length of the data portion of this record.
     *
     * @return the length of the data portion of this record.
     */
    public int getDataLength() {
        return this.length;
    }

    /**
     * Set the time to live for this record; i.e., how long the data contained
     * within this record is valid.
     *
     * @param TTL new time to live for this record
     */
    public void setTTL(int TTL) {
        this.answerTTL = TTL;
        changed = true;
    }

    /**
     * Set the contents of the data portion of this Record.
     *
     * @param data new contents of the data portion of this record.
     */
    public void setData(DNSResource data) {
        this.data = data;
        this.length = data.getLength();
        changed = true;
    }
    
    /**
     * Returns this record's name.
     *
     * @return the name of this record.
     */
    public DNSUrl getName() {
        return this.answerName;
    }

    /**
     * returns this record's type.
     *
     * @return record type of this answer
     */
    public DNSRecordType getType() {
        return this.answerType;
    }

    /**
     * returns this record's class.
     *
     * @return the DNS class of this answer.
     */
    public DNSRecordClass getRecordClass() {
        return this.answerClass;
    }

    /**
     * Sets the name of this Record.
     *
     * @param name new name for this Record.
     */
    public void setName(DNSUrl name) {
        this.answerName = name;
        changed = true;
    }

    /**
     * Sets this record's type.
     *
     * @param type new type for this record.
     */
    public void setType(DNSRecordType type) {
        this.answerType = type;
        changed = true;
    }

    /**
     * Set's this record's DNS class.
     *
     * @param recordClass new DNS class for this record.
     */
    public void setClass(DNSRecordClass recordClass) {
        this.answerClass = recordClass;
        changed = true;
    }
    
    /**
     * Return the serialized form of this DNSAnswer as per the DNS protocol.
     *
     * @return this DNSAnswer in serialized form
     */
    public byte[] serialize() {
        generateTransient();
        return this.serialized;
    }

    /**
     * Return a mapping of the state of this DNSAnswer
     *
     * @return a mappig of the state of this DNSAnswer
     */
    public Map stateValues() {
        generateTransient();
        return values;
    }

    /**
     * Generates structures that need to be re-generated everytime the state
     * of this DNSAnswer is changed; i.e., serialized and values.
     */
    protected void generateTransient() {
        if (changed) {
            //
            // construct values
            //

            // size equal to size of name + size of data + 2-byte type + 2-byte
            // class + 4-byte TTL + 2-byte length
            int nameLength = answerName.getLength();
            int dataLength = data.getLength();
            serialized = new byte[nameLength + dataLength + 10];
 
            // copy name
            byte[] nameBytes = answerName.serialize();
            for (int i = 0; i < nameLength; i++) {
                serialized[i] = nameBytes[i];
            }

            // copy Type
            byte[] typeBytes = answerType.serialize();
            for (int i = 0; i < typeBytes.length; i++) {
                serialized[i + nameLength] = typeBytes[i];
            }

            // copy Class
            byte[] classBytes = answerClass.serialize();
            for (int i = 0; i < classBytes.length; i++) {
                serialized[i + nameLength + 2] = classBytes[i];
            }

            // copy TTL
            serialized[nameLength + 4] = (byte)(answerTTL >>> 24);
            serialized[nameLength + 5] = (byte)(answerTTL >>> 16);
            serialized[nameLength + 6] = (byte)(answerTTL >>> 8);
            serialized[nameLength + 7] = (byte)(answerTTL);

            // copy data length
            serialized[nameLength + 8] = (byte)(length >>> 8);
            serialized[nameLength + 9] = (byte)length;
            
            // copy answer data
            byte[] dataBytes = data.serialize();
            for (int i = 0; i < dataBytes.length; i++) {
                serialized[i + nameLength + 10] = dataBytes[i];
            }
            

            //
            // construct values
            //
            try {
                values.put("Answer Name", answerName.stateValues());
                values.put("Answer Type", answerType.stateValues());
                values.put("Answer Class", answerClass.stateValues());
                values.put("Time-To-Live", answerTTL);
                values.put("Data Length", length);
                values.put("Answer Data", data.stateValues());
            } catch(Exception e) {}
        
            changed = false;
        }
    }
}
