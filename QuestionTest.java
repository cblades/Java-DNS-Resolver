import javax.swing.JFrame;
import javax.swing.JTree;

/**
 * Tests DNSQuestionImpl and DNSQuestionGuiDecorator.
 *
 * @author Chris Blades
 * @version 20/3/2010
 */
public class QuestionTest {
    public static void main(String[] args) {
        DNSQuestion question = new DNSQuestionImpl();
        DNSUrl url = new DNSUrlImpl("www.google.com");
        DNSRecordType type = DNSRecordTypeEnum.A;
        DNSRecordClass clss = DNSRecordClassEnum.IN;

        question.setName(url);
        question.setType(type);
        question.setClass(clss);

        DNSQuestionGuiDecorator dec =
            new DNSQuestionGuiDecorator(question);


        JFrame frame = new JFrame("DNS Resolver");
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JTree(dec));
        frame.setVisible(true);
    }
}
