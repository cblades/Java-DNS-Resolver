/**
 * Creates new DNSPackets containing a query.
 *
 * @author Chris Blades
 * @version 20/3/10
 */
public class DNSFactory {

    /**
     * Return a new DNSPacket with a question containing the given url
     * and recursion set according to recursionDesired.
     *
     * @param url the contents of the question in the returned DNSPacket
     * @param recursionDesired wether to set the recursion desired flag
     *                         in the returned DNSpacket.
     */
    public DNSPacket getPacket(String url, boolean recursionDesired) {
        DNSPacket packet = new DNSPacketImpl();

        // flags field
        DNSFlagsField flags = new DNSFlagsFieldImpl();
        flags.setIsRequest(true);
        // set recursion desired according to recursionDesired
        flags.setRecursionDesired(recursionDesired);
        flags.setOpcode(DNSOpcodeEnum.QUERY);

        // build header, insert flags
        DNSHeader header = new DNSHeaderImpl();
        header.setIdentifier(1024);
        header.setFlags(flags);
        header.setNumQuestions(1);

        // build question
        DNSQuestion question = new DNSQuestionImpl();
        DNSUrl questionUrl = new DNSUrlImpl(url);
        DNSRecordType type = DNSRecordTypeEnum.A;
        DNSRecordClass clss = DNSRecordClassEnum.IN;

        question.setName(questionUrl);
        question.setType(type);
        question.setClass(clss);

        packet.setHeader(header);
        packet.setQuestion(question);
    
        return packet;
    }
}
