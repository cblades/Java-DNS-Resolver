/**
 * A decorator for DNSQuestion that parses a byte buffer to create a
 * DNSQuestion.
 *
 * @author Chris Blades
 * @version 20/3/10
 */
public class DNSQuestionParserDecorator implements DNSQuestion {
    /** The DNSQuestion object this object wraps */
    private DNSQuestion question;

    /** byte buffer to parse and pass to other objects */
    private IndexedArrayList<Byte> bytes;

    /**
     * Creates a new DNSQuestionParserDecorator with the given byte
     * buffer.
     *
     * @param bytes the byte buffer to parse.
     */
    public DNSQuestionParserDecorator(IndexedArrayList<Byte> bytes) {
        this.bytes = bytes;
        this.question = new DNSQuestionImpl();
    }
    

    /**
     * Parses the byte buffer to form a DNSQuestion object.
     *
     */
    public void parse() {
        // 
        // parse question name
        //
        DNSUrlParserDecorator url = new DNSUrlParserDecorator(bytes);
        url.parse();
        question.setName(url);

        //
        // parse question type
        //
        DNSRecordTypeParserDecorator type = 
                     new DNSRecordTypeParserDecorator(bytes);

        type.parse();
        question.setType(type);

        // 
        // parse question class
        //
        DNSRecordClassParserDecorator clss =
                     new DNSRecordClassParserDecorator(bytes);
        clss.parse();
        question.setClass(clss);
    }

    //
    // DNSQuestion
    //

    /**
     * Returns the name of the parsed DNSQuestion object.
     *
     * @return the name of the parsed DNSQuestion object.
     */ 
    public DNSUrl getName() {
        return question.getName();
    }

    /**
     * Returns the type of the parsed DNSQuestion object.
     *
     * @return the type of the parsed DNSQuestion object.
     */ 
    public DNSRecordType getType() {
        return question.getType();
    }

    /**
     * Returns the class of the parsed DNSQuestion object.
     *
     * @return the class of the parsed DNSQuestion object.
     */ 
    public DNSRecordClass getRecordClass() {
        return question.getRecordClass();
    }

    /**
     * Sets the name of the parsed DNSQuestion object.
     *
     * @param name the new name of the parsed DNSQuestion object.
     */
    public void setName(DNSUrl name) {
        question.setName(name);
    }

    /**
     * Sets the type of the parsed DNSQuestion object.
     *
     * @param type the new type of the parsed DNSQuestion object.
     */
    public void setType(DNSRecordType type) {
        question.setType(type);
    }

    /**
     * Sets the DNS class of the parsed DNSQuestion object.
     *
     * @param recordClass the new class of the parsed DNSQuestion object.
     */
    public void setClass(DNSRecordClass recordClass) {
        question.setClass(recordClass);
    }

    /**
     * Returns the length of the parsed DNSQuestion object.
     *
     * @return the length of the parsed DNSQuestion object.
     */ 
    public int getLength() {
        return question.getLength();
    }

    /**
     * returns the serialized version of the parsed dnsquestion object.
     * can't just return the byte buffer because we don't know what else is in
     * it.
     *
     * @return the serialized version of the parsed dnsquestion object.
     */
    public byte[] serialize() {
        return question.serialize();
    }

    /** 
     * Returns a mapping of the state values of the parsed DNSQuestion object.
     *
     * @return the state values of the parsed DNSQuestion object.
     */
    public Map stateValues() {
        return question.stateValues();
    }
}
