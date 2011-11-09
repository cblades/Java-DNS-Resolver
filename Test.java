import java.net.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.plaf.metal.*;

/**
 * Creates a DNSPacket wiht a query, resolves that query, and
 * displays the results
 *
 * @author Chris Blades
 * @version 20/3/2010
 */
public class Test {
    public static void main(String[] args) {
        try {
            DNSPacket packet = new DNSPacketImpl();
            
            DNSFlagsField flags = new DNSFlagsFieldImpl();
            flags.setIsRequest(false);
            flags.setOpcode(DNSOpcodeEnum.QUERY);

            DNSHeader header = new DNSHeaderImpl();
            header.setIdentifier(456789);
            header.setFlags(flags);
            header.setNumQuestions(1);

            DNSQuestion question = new DNSQuestionImpl();
            question.setName(new DNSUrlImpl("www.yahoo.com"));
            question.setType(DNSRecordTypeEnum.A);
            question.setClass(DNSRecordClassEnum.IN);

            packet.setHeader(header);
            packet.setQuestion(question);
            System.out.println(packet.stateValues().toString());
            DatagramSocket sock = new DatagramSocket(); 
            sock.send(new DatagramPacket(packet.serialize(),
                                         packet.serialize().length,
                                         InetAddress.getByName("8.8.4.4"),
                                         53));    

            DatagramPacket get = new DatagramPacket(new byte[512], 512);
            sock.receive(get);
            
            byte[] packetbytes = get.getData();
            Byte[] packetBytes = new Byte[packetbytes.length];
            for (int i = 0; i < packetbytes.length; i++) {
                packetBytes[i] = packetbytes[i];
            }
            DNSPacketParserDecorator dec = 
                                new DNSPacketParserDecorator(
                                new IndexedArrayList<Byte>(packetBytes));
            
            dec.parse();
            
            DNSPacketGuiDecorator gui =
                new DNSPacketGuiDecorator(dec);

            UIManager.setLookAndFeel(new MetalLookAndFeel());
            JFrame frame = new JFrame("DNS Resolver");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JTree tree = new JTree(gui);
            //frame.setSize(tree.getPreferredScrollableViewportSize());
            frame.getContentPane().add(tree);
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println("_______________________");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

