/**
 * Wraps an IP address for DNS operations
 *
 * @author Chris Blades
 * @version 3/10/2010
 */
public interface DNSIP extends DNSResource {
    /**
     * Changes the IP the DNSIP object wraps.
     *
     * @param IP the new IP
     */
    public void setIP(String ip);
}
