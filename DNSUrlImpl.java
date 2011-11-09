/**
 * Basic implementation of DNSUrl.  Stores the url and when serializes
 * the url in DNS format.
 *
 * @author Chris Blades
 * @version 3/12/2010
 */
public class DNSUrlImpl extends AbstractDNSObject implements DNSUrl {
    /** The URL this DNSUrl is wrapping */
    private String url;

    /** Creates a new empty DNSUrl */
    public DNSUrlImpl() {
        this("");
    }

    /**
     * Creats a new DNSUrl that wraps the given url.
     *
     * @param url the URL to wrap
     */
    public DNSUrlImpl(String url) {
        this.url = url;
    }

    /**
     * Sets the  url to the given string.
     *
     * @param url the url to change to
     */
    public void setUrl(String url) {
        this.url = url;
        changed = true;
    }

    /**
     * Return the URL this DNSUrl is wrapping.
     *
     * @return the URL this DNSUrl is wrapping.
     */
    public String getData() {
        return this.url;
    }

    /**
     * Returns this DNSUrl as a byte array in DNS format.
     *
     * @return this DNSUrl as a byte array
     */
    public byte[] serialize() {
        generateTransient();
        return this.serialized;
    }

    /**
     * Returns a mapping of the state values of this DNSUrl
     * 
     * @return the state values of this DNSUrl
     */
    public Map stateValues() {
        generateTransient();
        return this.values;
    }

    /**
     * Re-generates serialized and values when state-changes have occured.
     */
    protected void generateTransient() {
        if (changed) {
            //
            // contruct serialized
            //

            // parse url and translate into DNS format
            serialized = new byte[url.length() + 2];
            String[] parts = url.split("\\.");
            
            int index = 0;
            for (int i = 0; i < parts.length; i++) {
                serialized[index] = (byte)parts[i].length();
                index++;
                char[] temp = parts[i].toCharArray();
                for (int j = 0; j < parts[i].length(); j++) {
                    serialized[index] = (byte)temp[j];
                    index++;
                }
            }

            //
            // construct values
            //
            values.clear();
            values.put("URL", this.url);

            changed = false;
        }
    }
}
