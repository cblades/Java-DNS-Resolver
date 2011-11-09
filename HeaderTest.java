import javax.swing.JFrame;
import javax.swing.JTree;


/**
 * Tests the DNSHeaderImpl and DNSHeaderGuiDecorator by creating a new
 * DNSHeader, filling it with fudged values, wrapping it in a
 * DNSHeaderGuiDecorator and displaying it.
 *
 * @author Chris Blades
 * @version 20/3/2010
 */
public class HeaderTest {

    public static void main(String[] args) {
        DNSFlagsField flags = new DNSFlagsFieldImpl();
        flags.setIsRequest(true);
        flags.setAuthoritative(true);
        flags.setOpcode(DNSOpcodeEnum.STATUS);

        DNSHeader header = new DNSHeaderImpl();
        header.setIdentifier(666);
        header.setFlags(flags);
        header.setNumQuestions(2);
        header.setNumAnswers(3);
        header.setNumAuthorityAnswers(4);
        header.setNumAdditionalAnswers(5);

        DNSHeaderGuiDecorator dec =
                    new DNSHeaderGuiDecorator(header);

        JFrame frame = new JFrame("DNS Resolver");
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JTree(dec));
        frame.setVisible(true);
    }
}
