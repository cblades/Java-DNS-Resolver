/**
 * Basic implementation of DNSIP
 *
 * @author Chris Blades
 * @version 3/16/2010
 */
public class DNSIPImpl extends AbstractDNSObject implements DNSIP {
    /**
     * The String representation of this IP
     */
    private String ip;

    /**
     * Creates a new IP with the loopback address
     */
    public DNSIPImpl() {
        this("127.0.0.1");
    }
    
    /**
     * Creates a new DNSIP with the given ip
     *
     * @param ip the IP address this DNSIP should wrap
     */
    public DNSIPImpl(String ip) {
        this.ip = ip;
    }

    /**
     * Changes the IP the DNSIP object wraps.
     *
     * @param IP the new IP
     */
    public void setIP(String ip) {
        this.ip = ip;
        changed = true;
    }

    /**
     * Returns a string representation of this IP.
     *
     * @return a string representation of this IP
     */
    public String getData() {
        return this.ip;
    }

    /**
     * Return this IP as an array of bytes.
     *
     * @return this IP as an array of bytes
     */
    public byte[] serialize() {
        generateTransient();
        return this.serialized;
    }

    /**
     * Return a mapping of state values of this IP
     *
     * @return the state values of this DNSIP
     */
    public Map stateValues() {
        generateTransient();
        return this.values;
    }

    /**
     * Generate serialized and values if there has been change to the
     * state of this DNSIP.
     */
    public void generateTransient() {
        if (changed) {
            //
            // generate serialized
            //
            serialized = new byte[4];
            String[] parts = ip.split("\\.");
            for (int i = 0; i < parts.length && i < serialized.length; i++) {
                serialized[i] = (byte)Integer.parseInt(parts[i]);
            }

            //
            // generate values
            //
            values.clear();
            values.put("IP", this.ip);

            changed = false;
        }
    }
}
