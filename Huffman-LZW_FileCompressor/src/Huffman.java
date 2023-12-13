import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Huffman implements CompressAlgorithm {

    @Override
    public byte[] compress(byte[] bit) {
        // TODO Auto-generated method stub
        String rrr = Arrays.toString(bit);

        // Remove the square brackets and commas from the string
        String[] asciiValues = rrr.substring(1, rrr.length() - 1).split(", ");

        HashMap<String, Integer> hfSend = new HashMap<>();
        for (int i = 0; i < asciiValues.length; i++) {
            hfSend.put(asciiValues[i], (hfSend.containsKey(asciiValues[i])) ? hfSend.get(asciiValues[i]) + 1 : 1);
        }

        // for (Entry<String, Integer> entry : hfSend.entrySet()) {
        // String key = entry.getKey();
        // Integer value = entry.getValue();
        // System.out.println(key + " => " + value);
        // }
        HashMap<String, String> HuffmanMap = HuffmanTree.generateTreeGetValues(hfSend);
        String[] a = new String[asciiValues.length];
        // for (Entry<String, String> entry : HuffmanMap.entrySet()) {
        // String key = entry.getKey();
        // String value = entry.getValue();
        // System.out.println(key + " => " + value);
        // }
        int counter1 = 0;
        int counter2 = 0;
        String strpass = "";
        for (Entry<String, String> entry : HuffmanMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            strpass = strpass + key + ":" + value + ":";
        }

        for (int i = 0; i < asciiValues.length; i++) {
            a[i] = HuffmanMap.get(asciiValues[i]);

        }

        String b = Arrays.toString(a);
        b = b.substring(1, b.length() - 1).replaceAll(", ", "");
        int breminded = b.length() % 8;
        String reminder = "";
        if (breminded != 0) {
            reminder = b.substring(b.length() - breminded, b.length());

            for (int i = 0; i < 8 - breminded; i++) {
                reminder = "0" + reminder;
            }
        }
        strpass = (8 - breminded) + "+" + strpass;
        String lengthpass = strpass.length() + "";

        strpass = (strpass.length() + lengthpass.length() + 1) + "^" + strpass;
        byte[] bytes = strpass.getBytes();

        // Convert each byte to binary string
        StringBuilder binary = new StringBuilder();
        for (byte j : bytes) {
            String binaryStr = Integer.toBinaryString(j & 0xFF); // convert byte to unsigned int
            while (binaryStr.length() < 8) {
                binaryStr = "0" + binaryStr; // pad with leading zeros
            }
            binary.append(binaryStr);
        }

        String byteString = binary.toString();

        b = byteString + b.substring(0, b.length() - breminded) + reminder;// problem çıkabilir

        byte[] lastCompressed = new BigInteger(b, 2).toByteArray();

        return lastCompressed;
    }

    @Override
    public byte[] decompress(byte[] bit) {
        StringBuilder sb = new StringBuilder();// normal karaktere çevirme şifremi bulmak için
        for (byte p : bit) {// decompress için
            int decimalValue = Integer
                    .parseInt(String.format("%8s", Integer.toBinaryString(p & 0xFF)).replace(' ', '0'), 2);
            char c = (char) decimalValue;
            sb.append(c);
        }

        String asciiString = sb.toString();

        int passAmount = asciiString.indexOf("^");
        int pass2 = Integer.parseInt(asciiString.substring(0, passAmount));

        int howManyZeros = Integer.parseInt(asciiString.substring(passAmount + 1, passAmount + 2));
        String passArray[] = asciiString.substring(passAmount + 3, pass2).split(":");

        String codeToDecompress = asciiString.substring(pass2);

        hfTreeNode mydecNode = HuffmanTree.generateTreeForDecompiling(passArray);

        int follower = 0;
        hfTreeNode tempnode = mydecNode;
        ArrayList<String> originalFile = new ArrayList<>();
        StringBuilder binaryStringBuilder = new StringBuilder();// geri binary stringe çevirme
        for (int i = 0; i < codeToDecompress.length(); i++) {
            int decimalValue = (int) codeToDecompress.charAt(i);
            String binaryValue = String.format("%8s", Integer.toBinaryString(decimalValue)).replace(' ', '0');
            binaryStringBuilder.append(binaryValue);
        }
        codeToDecompress = binaryStringBuilder.toString();
        int checkLastByteorNot = 8;
        if (howManyZeros == 0) {
            checkLastByteorNot = 0;
        }

        while (follower < (codeToDecompress.length() - checkLastByteorNot)) {

            if (tempnode.lefhfNode == null && tempnode.righthfNode == null) {
                originalFile.add(tempnode.getkey());
                tempnode = mydecNode;
            }
            char h = codeToDecompress.charAt(follower);
            if (h == '1' && tempnode.righthfNode != null) {
                tempnode = tempnode.righthfNode;
            } else if (h == '0' && tempnode.lefhfNode != null) {
                tempnode = tempnode.lefhfNode;
            }
            follower++;
        }

        follower = 0;
        tempnode = mydecNode;
        if (checkLastByteorNot > 0) {
            String lastCheck = codeToDecompress.substring(codeToDecompress.length() - 8 + howManyZeros);
           // System.out.println(lastCheck);
            while (follower < lastCheck.length()) {
                // System.out.println("a");
                if (tempnode.lefhfNode == null && tempnode.righthfNode == null) {
                    originalFile.add(tempnode.getkey());
                    tempnode = mydecNode;
                }
                char h = lastCheck.charAt(follower);
                if (h == '1' && tempnode.righthfNode != null) {
                    tempnode = tempnode.righthfNode;
                } else if (h == '0' && tempnode.lefhfNode != null) {
                    tempnode = tempnode.lefhfNode;
                }
                follower++;
            }
        }

        byte[] byteArrayD = new byte[originalFile.size()];
        for (int i = 0; i < originalFile.size(); i++) {
            int decimalValue = Integer.parseInt(originalFile.get(i));
            byteArrayD[i] = (byte) decimalValue;
        }

        return byteArrayD;

    }
    public String getAdd()
    {
        return ".hf";
    }
    public int getDelete()
    {
        return 3;
    }

    @Override
    public String getFileFormat() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFileFormat'");
    }

}