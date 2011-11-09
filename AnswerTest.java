import javax.swing.JFrame;
import javax.swing.JTree;

/**
 * Tests DNSAnswer and DNSAnswerGuiDecorator.
 *
 * @author Chris Blades
 * @version 20/3/2010
 */
public class AnswerTest {

    /**
     * Create a new DNSAnswer and give it fudged values, wrap in a 
     * DNSAnswerGuiDecorator and then display it.
     *
     * @param args  no purpose here
     */
    public static void main(String[] args) {
        DNSAnswer answer = new DNSAnswerImpl();
        DNSUrl    url    = new DNSUrlImpl("www.google.com");
        
        // fudge values
        answer.setName(url);
        answer.setType(DNSRecordTypeEnum.CNAME);
        answer.setClass(DNSRecordClassEnum.IN);
        answer.setTTL(299);
        answer.setData(new DNSUrlImpl("www.yahoo.com"));
        
        // wrap in decorator
        DNSAnswerGuiDecorator dec = new DNSAnswerGuiDecorator(answer);
        
        // display decorator
        JFrame frame = new JFrame("DNS Resolver");
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JTree(dec));
        frame.setVisible(true);
    }
}
