import java.nio.ByteBuffer;

/**
 * A decorator for DNSAnswer that parses a byte buffer to create a
 * DNSAnswer.
 *
 * @author Chris Blades
 * @version 20/3/10
 */
public class DNSAnswerParserDecorator implements DNSAnswer {
    /** The DNSAnswer object this object wraps */
    private DNSAnswer answer;


    /** byte buffer to parse and pass to other objects */
    private IndexedArrayList<Byte> bytes;

    /**
     * Creates a new DNSAnswerParserDecorator with the given byte
     * buffer.
     *
     * @param bytes the byte buffer to parse.
     */
    public DNSAnswerParserDecorator(IndexedArrayList<Byte> bytes) {
        this.bytes = bytes;
        this.answer = new DNSAnswerImpl();
    }

    /**
     * Parses the byte buffer to form a DNSAnswer object.
     *
     */
    public void parse() {
        // parse name
        DNSUrlParserDecorator url = new DNSUrlParserDecorator(bytes);
        url.parse();
        answer.setName(url);
       
        // parse type 
        DNSRecordTypeParserDecorator type = 
                     new DNSRecordTypeParserDecorator(bytes);
        type.parse();
        answer.setType(type);
       
        // parse class
        DNSRecordClassParserDecorator clss =
                     new DNSRecordClassParserDecorator(bytes);
        clss.parse();
        answer.setClass(clss);
        
        // parse TTL
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.put(bytes.getNext());
        bb.put(bytes.getNext());
        bb.put(bytes.getNext());
        bb.put(bytes.getNext());
        answer.setTTL(bb.getInt(0));
        
        //
        // do not parse Data Length, let DNSAnswerImpl handle it.
        // But be sure to forward buffer. 
        //
        bytes.getNext();
        bytes.getNext();
              
        if (answer.getType().stateValues().equals(
                                        DNSRecordTypeEnum.A.stateValues())) {

            DNSIPParserDecorator ip = new DNSIPParserDecorator(bytes);
            ip.parse();
            answer.setData(ip);
        } else {
            url = new DNSUrlParserDecorator(bytes);
            url.parse();
            answer.setData(url);
        }
    }


    //
    // DNSAnswer
    //

    /**
     * Returns the name of the parsed DNSAnswer object.
     *
     * @return the name of the parsed DNSAnswer object.
     */ 
    public DNSUrl getName() {
        return answer.getName();
    }
    
    /**
     * Returns the type of the parsed DNSAnswer object.
     *
     * @return the type of the parsed DNSAnswer object.
     */ 
    public DNSRecordType getType() {
        return answer.getType();
    }

    /**
     * Returns the class of the parsed DNSAnswer object.
     *
     * @return the class of the parsed DNSAnswer object.
     */ 
    public DNSRecordClass getRecordClass() {
        return answer.getRecordClass();
    }

    /**
     * Sets the name of the parsed DNSAnswer object.
     *
     * @param name the new name of the parsed DNSAnswer object.
     */
    public void setName(DNSUrl name) {
        answer.setName(name);
    }

    /**
     * Sets the type of the parsed DNSAnswer object.
     *
     * @param type the new type of the parsed DNSAnswer object.
     */
    public void setType(DNSRecordType type) {
        answer.setType(type);
    }

    /**
     * Sets the DNS class of the parsed DNSAnswer object.
     *
     * @param recordClass the new class of the parsed DNSAnswer object.
     */
    public void setClass(DNSRecordClass recordClass) {
        answer.setClass(recordClass);
    }
    
    /**
     * Returns the time to live of the parsed DNSAnswer object.
     *
     * @return the time to live of the parsed DNSAnswer object.
     */ 
    public int getTTL() {
        return answer.getTTL();
    }

    /**
     * Returns the data length of the parsed DNSAnswer object.
     *
     * @return the data length of the parsed DNSAnswer object.
     */ 
    public int getDataLength() {
        return answer.getDataLength();
    }

    /**
     * Returns the data field of the parsed DNSAnswer object.
     *
     * @return the data field of the parsed DNSAnswer object.
     */ 
    public DNSResource getData() {
        return answer.getData();
    }

    /**
     * Sets the time to live of the parsed DNSAnswer object.
     *
     * @param TTL the new time to live of the parsed DNSAnswer object.
     */
    public void setTTL(int TTL) {
        answer.setTTL(TTL);
    }

    /**
     * Sets the contents of the data field of the parsed DNSAnswer object.
     *
     * @param data the new contents of the data field 
     *             of the parsed DNSAnswer object.
     */
    public void setData(DNSResource data) {
        answer.setData(data);
    }

    /**
     * Returns the length of the parsed DNSAnswer object.
     *
     * @return the length of the parsed DNSAnswer object.
     */ 
    public int getLength() {
        return answer.getLength();
    }

    /**
     * returns the serialized version of the parsed dnsanswer object.
     * can't just return the byte buffer because we don't know what else is in
     * it.
     *
     * @return the serialized version of the parsed dnsanswer object.
     */
    public byte[] serialize() {
        return answer.serialize();
    }

    /** 
     * Returns a mapping of the state values of the parsed DNSAnswer object.
     *
     * @return the state values of the parsed DNSAnswer object.
     */
    public Map stateValues() {
            return answer.stateValues();
    }

}

