/**
 * Represents anything that can be in the name or data section of
 * a DNS record (URL or IP).
 *
 * @author Chris Blades
 * @version 1/20/2010
 */
public interface DNSResource extends DNSObject {
    /**
     * Returns a string representation of this resource.
     *
     * @return a string representation of this resource
     */
    public String getData();
}
