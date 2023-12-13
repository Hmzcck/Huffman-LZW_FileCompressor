import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class FileManager {

    public byte[] read(File file) throws IOException {

        byte[] arr = Files.readAllBytes(file.toPath());

        return arr;
    }

    public File write(byte[] bitArr, String fileName,String add) throws IOException {

        File f = new File(fileName + add);
        Files.write(f.toPath(), bitArr);
        return f;
    }

    public File writeD(byte[] bitArr, String fileName, int deleter) throws IOException {

        File f = new File(fileName.substring(0, fileName.length() - deleter));
        Files.write(f.toPath(), bitArr);
        return f;
    }

}
