/**
 * A decorator for DNSRecordClass that parses a byte buffer to create a
 * DNSRecordClass.
 *
 * @author Chris Blades
 * @version 20/3/10
 */
public class DNSRecordClassParserDecorator implements DNSRecordClass {
    /** The DNSRecordClass object this object wraps */
    private DNSRecordClass recClass;

    /** byte buffer to parse and pass to other objects */
    private IndexedArrayList<Byte> bytes;

    /**
     * Creates a new DNSRecordClassParserDecorator with the given byte
     * buffer.
     *
     * @param bytes the byte buffer to parse.
     */
    public DNSRecordClassParserDecorator(IndexedArrayList<Byte> bytes) {
        this.bytes = bytes;
        // cheating a bit, this will always be true
        recClass = DNSRecordClassEnum.IN;
    }

    /**
     * Parses the byte buffer to form a DNSRecordClass object.
     *
     */
    public void parse() {
        // don't do anything but advance buffer so it will be in the right
        // position
        bytes.getNext();
        bytes.getNext();
    }

    /**
     * Returns a string represention of this record class.
     *
     * @return a string representation of this record class.
     */
    public String getName() {
        return recClass.getName();
    }

    /**
     * Returns the length of the parsed DNSRecordClass object.
     *
     * @return the length of the parsed DNSRecordClass object.
     */ 
    public int getLength() {
        return recClass.getLength();
    }

    /**
     * returns the serialized version of the parsed DNSRecordClass object.
     * can't just return the byte buffer because we don't know what else is in
     * it.
     *
     * @return the serialized version of the parsed DNSRecordClass object.
     */
    public byte[] serialize() {
        return recClass.serialize();
    }

    /** 
     * Returns a mapping of the state values of the parsed DNSRecordClass
     * object.
     *
     * @return the state values of the parsed DNSRecordClass object.
     */
    public Map stateValues() {
        return recClass.stateValues();
    }
}

