/**
 * Tests DNSIP and DNSIPParserDecorator by creating a DNSIP, filling it with
 * fudge values, and wrapping it's serialized version in a DNSIPParserDecorator.
 *
 * @author Chris Blades
 * @version 20/3/2010
 */
public class IPTest {
    public static void main(String[] args) {
        DNSIP ip = new DNSIPImpl("11.22.33.44");

        byte[] bytes = ip.serialize();
        Byte[] Bytes = new Byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            Bytes[i] = bytes[i];
        }

        IndexedArrayList<Byte> list = new IndexedArrayList<Byte>(Bytes);

        DNSIPParserDecorator dec =
            new DNSIPParserDecorator(list);
        dec.parse();
        System.out.println(dec.stateValues() + "\n" + list.getPosition());
    }
}
