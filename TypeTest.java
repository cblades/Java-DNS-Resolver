public class TypeTest {

    public static void main(String[] args) {
        DNSRecordType type = DNSRecordTypeEnum.A;
        byte[] bytes = type.serialize();
        Byte[] Bytes = new Byte[bytes.length];
        for (int i= 0; i < bytes.length; i++) {
            System.out.println((int)(bytes[i]& 0xFF));
            Bytes[i] = bytes[i];
        }

        DNSRecordTypeParserDecorator dec =
            new DNSRecordTypeParserDecorator(new IndexedArrayList<Byte>(Bytes));
        dec.parse();
        System.out.println(dec.stateValues());
    }
}
