import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.plaf.metal.*;

/**
 * Tests DNSPacketImpl and DNSPacketGuiDecorator.
 *
 * @author Chris Blades
 * @version 20/3/10
 */ 
public class PacketTest {
    public static void main(String[] args) {
        DNSPacket packet = new DNSPacketImpl();

        DNSFlagsField flags = new DNSFlagsFieldImpl();
        flags.setIsRequest(true);
        flags.setAuthoritative(true);
        flags.setOpcode(DNSOpcodeEnum.QUERY);

        DNSHeader header = new DNSHeaderImpl();
        header.setIdentifier(666);
        header.setFlags(flags);
        header.setNumQuestions(1);
        header.setNumAnswers(0);
        header.setNumAuthorityAnswers(1);
        header.setNumAdditionalAnswers(0);

        DNSQuestion question = new DNSQuestionImpl();
        DNSUrl url = new DNSUrlImpl("www.google.com");
        DNSRecordType type = DNSRecordTypeEnum.A;
        DNSRecordClass clss = DNSRecordClassEnum.IN;

        question.setName(url);
        question.setType(type);
        question.setClass(clss);

        DNSAnswer answer = new DNSAnswerImpl();
                  url    = new DNSUrlImpl("www.google.com");
        
        answer.setName(url);
        answer.setType(DNSRecordTypeEnum.CNAME);
        answer.setClass(DNSRecordClassEnum.IN);
        answer.setTTL(299);
        answer.setData(new DNSUrlImpl("www.yahoo.com"));
        
        packet.setHeader(header);
        packet.setQuestion(question);
        packet.addAuthoritativeAnswer(answer);

        DNSPacketGuiDecorator dec =
            new DNSPacketGuiDecorator(packet);
        try {       
            UIManager.setLookAndFeel(new MetalLookAndFeel());
            JFrame frame = new JFrame("DNS Resolver");
            frame.setSize(300, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new JTree(dec));
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println("!!!!");
        }
    }
}
