/**
 * Represents a URL in DNS notation.
 *
 * @author Chris Blades
 * @version 1/18/2010
 */
public interface DNSUrl extends DNSResource {
    /**
     * Sets the  url to the given string.
     *
     * @param url the url to change to
     */
    public void setUrl(String url);
}
