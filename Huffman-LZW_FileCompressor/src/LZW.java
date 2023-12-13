import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LZW implements CompressAlgorithm {

    public byte[] compress(byte[] inputArr) {
        String input = new String(inputArr, StandardCharsets.UTF_8);

        HashMap<String, Short> dict = new HashMap<>();
        for (short i = 0; i < 256; i++) {
            dict.put(Character.toString((char) i), i);
        }

        short current = 0;
        ArrayList<Short> finisher = new ArrayList<>();
        for (char c : input.toCharArray()) {
            String next = Character.toString(c);
            if (dict.containsKey(current + next)) {
                current = dict.get(current + next);
            } else {
                finisher.add(current);
                dict.put(current + next, (short) dict.size());
                current = dict.get(next);
            }
        }
        finisher.add(current);

        byte[] returnArray = new byte[finisher.size() * 2];
        ByteBuffer buffer = ByteBuffer.wrap(returnArray);
        for (short s : finisher) {
            buffer.putShort(s);
        }
        return returnArray;
    }

    public byte[] decompress(byte[] byteArray) {
        ArrayList<Short> input = new ArrayList<>();
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        while (buffer.hasRemaining()) {
            input.add(buffer.getShort());
        }

        HashMap<Short, String> dict = new HashMap<>();
        for (short i = 0; i < 256; i++) {
            dict.put(i, Character.toString((char) i));
        }

        short myn = input.remove(0);
        StringBuilder finisher = new StringBuilder(dict.get(myn));
        for (short code : input) {
            String entry;
            if (dict.containsKey(code)) {
                entry = dict.get(code);
            } else if (code == dict.size()) {
                entry = dict.get(myn) + dict.get(myn).charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid compressed data");
            }
            finisher.append(entry);

            dict.put((short) dict.size(), dict.get(myn) + entry.charAt(0));

            myn = code;
        }
        String convertedBack = finisher.toString();
        // System.out.println(convertedBack);
        byte[] returnArr = convertedBack.getBytes();
        return returnArr;
    }
    public String getAdd()
    {
        return ".lzw";
    }
    public int getDelete()
    {
        return 4;
    }

    public String getFileFormat() {
        throw new UnsupportedOperationException("Unimplemented method 'getFileFormat'");
    }

}
