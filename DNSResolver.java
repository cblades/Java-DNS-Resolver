import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JTree;
import javax.swing.JScrollPane;

/**
 * Resolves a given url and graphically displays the results.
 *
 * @author Chris Blades
 * @version 20/3/10
 */
public class DNSResolver {
    /**
     * DNS server to use.
     */
    private static final String DNS_SERVER = "8.8.4.4";

    /**
     * DNS Port.
     */
    private static final int DNS_PORT = 53;

    /**
     * The url being resolved.
     */
    private String url;
    
    /**
     * Wether or not to request recursion when resolving url.
     */
    private boolean recursion;

    /**
     * Constructs a new DNSResolver with the given target url.
     *
     * @param url the url to resolve.
     */
    public DNSResolver(String url, boolean recursionDesired) {
        this.url       = url;
        this.recursion = recursionDesired;
        resolve();
    }

    /**
     * Resolves the url.
     */
    public void resolve() {
        try {
            // construct question
            DNSFactory factory = new DNSFactory();
            DNSPacket question = factory.getPacket(url, recursion);
            // resolve question
            DatagramSocket sock = new DatagramSocket(); 
            sock.send(new DatagramPacket(question.serialize(),
                                         question.serialize().length,
                                         InetAddress.getByName(DNS_SERVER),
                                         DNS_PORT));    

            DatagramPacket get = new DatagramPacket(new byte[512], 512);
            sock.receive(get);
            
            // parse response
            byte[] packetbytes = get.getData();
            Byte[] packetBytes = new Byte[packetbytes.length];
            for (int i = 0; i < packetbytes.length; i++) {
                packetBytes[i] = packetbytes[i];
            }
            DNSPacketParserDecorator response = 
                                new DNSPacketParserDecorator(
                                new IndexedArrayList<Byte>(packetBytes));
            
            response.parse();
            //
            // display question
            DNSPacketGuiDecorator gui =
                new DNSPacketGuiDecorator(question);
            
            JFrame      questionFrame = new JFrame("Question");
            JTree       tree          = new JTree(gui);
            JScrollPane scroll        = new JScrollPane(tree);

            questionFrame.setSize(tree.getPreferredScrollableViewportSize());
            questionFrame.getContentPane().add(scroll);
            questionFrame.setVisible(true);
            
            //
            //
            // display response
            gui = new DNSPacketGuiDecorator(response);

            JFrame responseFrame = new JFrame("Response");
                            tree = new JTree(gui);
            
            responseFrame.setSize(tree.getPreferredScrollableViewportSize());

            scroll = new JScrollPane(tree);
            responseFrame.getContentPane().add(scroll);
            // set locatin relative to questionFrame
            responseFrame.setLocation(
                (int)questionFrame.getLocation().getX() + 5 + 
                                                    questionFrame.getWidth(),
                0);
            responseFrame.setVisible(true);
        } catch (IOException e) {
            System.out.println("Network error");    
        }
    }   
}
