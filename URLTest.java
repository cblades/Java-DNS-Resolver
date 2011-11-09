public class URLTest {
    public static void main(String[] args) {
        DNSUrl url = new DNSUrlImpl("www.google.com");

        byte[] bytes = url.serialize();
        Byte[] Bytes = new Byte[bytes.length + 2];
        for (int i = 0; i < bytes.length; i++) {
            Bytes[i] = bytes[i];
        }

        Bytes[Bytes.length - 2] = (byte)0xC0;
        Bytes[Bytes.length - 1] = (byte)0x04;

        Bytes = new Byte[]
                {(byte)0x03, (byte)0x55, (byte)0x55, (byte)0x55, (byte)0x00,
                 (byte)0x02, (byte)0x56, (byte)0x56, (byte)0xC0, (byte)0x00};
        IndexedArrayList<Byte> list = new IndexedArrayList<Byte>(Bytes);

        list.setPosition(5);
        DNSUrlParserDecorator dec = 
            new DNSUrlParserDecorator(list);
        dec.parse();

        System.out.println(dec.stateValues());

        System.out.println(list.getPosition());
    }
}
