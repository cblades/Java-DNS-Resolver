import javax.swing.UIManager;
import javax.swing.plaf.metal.*;

/**
 * Creates a new DNSResolverWindow, which handles user input and 
 * allows DNS lookups
 *
 * @author Chris Blades
 * @version 20/3/2010
 */
public class DNSResolverDriver {

    public static void main(String[] args) {
        try {
            DNSResolverWindow window = 
                        new DNSResolverWindow("DNS Resolver");

            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
