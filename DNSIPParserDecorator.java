/**
 * A decorator for DNSIP that parses a byte buffer to create a
 * DNSIP.
 *
 * @author Chris Blades
 * @version 20/3/10
 */
public class DNSIPParserDecorator implements DNSIP {
    /** The DNSIP object this object wraps */
    private DNSIP target;

    /** byte buffer to parse and pass to other objects */
    private IndexedArrayList<Byte> bytes;

    /**
     * Creates a new DNSIPParserDecorator with the given byte
     * buffer.
     *
     * @param bytes the byte buffer to parse.
     */
    public DNSIPParserDecorator(IndexedArrayList<Byte> bytes) {
        this.bytes = bytes;
        this.target = new DNSIPImpl();
    }

    /**
     * Parses the byte buffer to form a DNSIP object.
     */
    public void parse() {
        // each byte is one octet of the ip
        byte[] ip = new byte[4];
        ip[0] = bytes.getNext();
        ip[1] = bytes.getNext();
        ip[2] = bytes.getNext();
        ip[3] = bytes.getNext();

        // translate octets to strings
        StringBuilder builder = new StringBuilder();
        builder.append((int)(ip[0] & 0xFF));
        builder.append(".");
        builder.append((int)(ip[1] & 0xFF));
        builder.append(".");
        builder.append((int)(ip[2] & 0xFF));
        builder.append(".");
        builder.append((int)(ip[3] & 0xFF));

        target.setIP(builder.toString());
    }
    
    /**
     * Returns the string reprentation of this IP address.
     *
     * @return The string representation of this IP address.
     */
    public String getData() {
        return target.getData();
    }

    /**
     * Sets the IP to a new String
     *
     * @param ip the new IP
     */
    public void setIP(String ip) {
        target.setIP(ip);
    }

    /**
     * Returns the length of the parsed DNSIP object.
     *
     * @return the length of the parsed DNSIP object.
     */ 
    public int getLength() {
        return target.getLength();
    }

    /**
     * returns the serialized version of the parsed dnsanswer object.
     * can't just return the byte buffer because we don't know what else is in
     * it.
     *
     * @return the serialized version of the parsed dnsanswer object.
     */
    public byte[] serialize() {
        return target.serialize();
    }

    /** 
     * Returns a mapping of the state values of the parsed DNSIP object.
     *
     * @return the state values of the parsed DNSIP object.
     */
    public Map stateValues() {
        return target.stateValues();
    }
}
