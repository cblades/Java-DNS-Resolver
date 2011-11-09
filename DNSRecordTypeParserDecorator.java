/**
 * A decorator for DNSRecordType that parses a byte buffer to create a
 * DNSRecordType.
 *
 * @author Chris Blades
 * @version 20/3/10
 */
public class DNSRecordTypeParserDecorator implements DNSRecordType {
    /** The DNSRecordType object this object wraps */
    private DNSRecordType type;

    /** byte buffer to parse and pass to other objects */
    private IndexedArrayList<Byte> bytes;

    /**
     * Creates a new DNSRecordTypeParserDecorator with the given byte
     * buffer.
     *
     * @param bytes the byte buffer to parse.
     */
    public DNSRecordTypeParserDecorator(IndexedArrayList<Byte> bytes) {
        this.type = DNSRecordTypeEnum.ALL;
        this.bytes = bytes;
    }

    /**
     * Parses the byte buffer to form a DNSRecordType object.
     *
     */
    public void parse() {
        bytes.getNext();  //throw away, we won't use first byte
        int numType = (int)(bytes.getNext() & 0xFF);
        switch (numType) {
            case 1   : type = DNSRecordTypeEnum.A; break;
            case 2   : type = DNSRecordTypeEnum.NS; break;
            case 5   : type = DNSRecordTypeEnum.CNAME; break;
            case 12  : type = DNSRecordTypeEnum.PTR; break;
            case 15  : type = DNSRecordTypeEnum.MX; break;
            case 33  : type = DNSRecordTypeEnum.SRV; break;
            case 251 : type = DNSRecordTypeEnum.IXFR; break;
            case 252 : type = DNSRecordTypeEnum.AXFR; break;
            case 255 : type = DNSRecordTypeEnum.ALL; break;
        }
    }
    
    /**
     * Returns a string representation of this record type
     *
     * @return a string representation of this record type
     */
    public String getName() {
        return type.getName();
    }
    
    /**
     * Returns the length of the parsed DNSRecordType object.
     *
     * @return the length of the parsed DNSRecordType object.
     */ 
    public int getLength() {

        return type.getLength();
    }

    /**
     * returns the serialized version of the parsed DNSRecordType object.
     * can't just return the byte buffer because we don't know what else is in
     * it.
     *
     * @return the serialized version of the parsed DNSRecordType object.
     */
    public byte[] serialize() {
        return type.serialize();
    }

    /** 
     * Returns a mapping of the state values of the parsed DNSRecordType
     * object.
     *
     * @return the state values of the parsed DNSRecordType object.
     */
    public Map stateValues() {
        return type.stateValues();
    }
}
