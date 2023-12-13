import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

public class Compressor {
    CompressAlgorithm algorithm;
    FileManager fileManager = new FileManager();

    public void setAlgorithm(CompressAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public File compress(File file) throws IOException {
        byte[] AsciiArr = fileManager.read(file);
        byte[] returnedArr = algorithm.compress(AsciiArr);
        String fileName = file.getName();
        String add = algorithm.getAdd();
        return (fileManager.write(returnedArr, fileName, add));

    }

    public File decompress(File file) throws IOException {
        byte[] AsciiArr = fileManager.read(file);
        byte[] returnedArr = algorithm.decompress(AsciiArr);
        int delete = algorithm.getDelete();
        String fileName = file.getName();

        return (fileManager.writeD(returnedArr, fileName,delete));
    }
}
