import java.io.UnsupportedEncodingException;

interface CompressAlgorithm {
    public byte[] compress(byte bit[]);

    public byte[] decompress(byte[] bit) throws UnsupportedEncodingException;
    public int getDelete();
    public String getAdd();
    public String getFileFormat();
}