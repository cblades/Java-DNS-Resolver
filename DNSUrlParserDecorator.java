/**
 * A decorator for DNSUrl that parses a byte buffer to create a
 * DNSUrl.
 *
 * @author Chris Blades
 * @version 20/3/10
 */
public class DNSUrlParserDecorator implements DNSUrl {
    /** The DNSUrl object this object wraps */
    private DNSUrl target;

    /** byte buffer to parse and pass to other objects */
    private IndexedArrayList<Byte> bytes;

    /**
     * Creates a new DNSUrlParserDecorator with the given byte
     * buffer.
     *
     * @param bytes the byte buffer to parse.
     */
    public DNSUrlParserDecorator(IndexedArrayList<Byte> bytes) {
        this.bytes = bytes;
        this.target = new DNSUrlImpl();
    }

    
    /**
     * Parses the byte buffer to form a DNSUrl object.
     *
     */
    public void parse() {
        target.setUrl(interpretUrl());
    }

    public String interpretUrl() {
        byte current = bytes.getNext();
        StringBuilder builder = new StringBuilder();
        // TODO
        // could be replaced with an iterator if iterator started
        // from the current position of bytes
        //
        for (int i = bytes.getPosition(); i < bytes.size() && current != 0x00; 
                                                     i = bytes.getPosition()) {
            // detect pointers
            if ((int)(current & 0xFF) >= 0x000000C0) {
                // get location pointer points to
                int pointerIndex = (int)(bytes.getNext() & 0xFF);
                // save the current buffer
                int oldIndex = bytes.getPosition();
                // set index at pointer location
                bytes.setPosition(pointerIndex);
                String tmp = interpretUrl();
                // interpret pointed to url
                builder.append(tmp);

                // restore bytes
                bytes.setPosition(oldIndex);
                break;
            // else read from current index
            } else {
                // else translate part
                char[] part = new char[current & 0xFF];
                for (int j = 0; j < part.length; j++) {
                    part[j] = (char)(bytes.getNext() & 0xFF);
                }
                builder.append(part);
                builder.append(".");
                current = bytes.getNext();
            }
            
        }      
        if (builder.length() > 0) {
            if (builder.charAt(builder.length() - 1) == '.') {
                builder.deleteCharAt(builder.length() - 1);
            }
        }
        return builder.toString();
    }

    //
    // DNSUrl
    //

    /**
     * Sets the  url to the given string.
     *
     * @param url the url to change to
     */
    public void setUrl(String url) {
        target.setUrl(url);
    }

    /**
     * Returns a string representation of this resource.
     *
     * @return a string representation of this resource
     */
    public String getData() {
        return target.getData();
    }

    /**
     * Returns the length of the parsed DNSUrl object.
     *
     * @return the length of the parsed DNSUrl object.
     */ 
    public int getLength() {
        return target.getLength();
    }

    /**
     * returns the serialized version of the parsed DNSUrl object.
     * can't just return the byte buffer because we don't know what else is in
     * it.
     *
     * @return the serialized version of the parsed DNSUrl object.
     */
    public byte[] serialize() {
        return target.serialize();
    }

    /** 
     * Returns a mapping of the state values of the parsed DNSUrl object.
     *
     * @return the state values of the parsed DNSUrl object.
     */
    public Map stateValues() {
        return target.stateValues();
    }
}
