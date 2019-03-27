import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

public class MainController {

    public static void main(String[] args) throws IOException {
        switch (args[0]) {
            case "generate":
                generateFiles(args[1], args[2]);
                break;

            case "union":
                union(args[1], args[2], args[3]);
                break;

            case "import":
                importInSQL(args[1]);
                break;

            case "count":
                generateFiles();
                break;
        }
    }

    private static void importInSQL(String arg) {
    }

    private static void union(String absolutePath, String targetFileName, String deleteString) {
    }

    private static void generateFiles(String absolutePath, String fileName) throws IOException {
        File f;
        for (int i = 0; i < 100; i++) {
            f.createNewFile(absolutePath + fileName + i);
            FileWriter fw = new FileWriter(f);
            for (int j = 0; j < 100000; j++) {
                fw.write(generateString());
            }
            fw.close();
        }
    }


    private static String generateString() {
        StringBuffer result = new StringBuffer();
        result.append(generateDate());
        result.append(generateLatin());
        result.append(generateRussian());
        result.append(generateInt());
        result.append(generateFloat());
    }

    private static Float generateFloat() {
    }

    private static Integer generateInt() {
    }

    private static String generateRussian() {
    }

    private static String generateLatin() {
        return null;
    }

    private static LocalDate generateDate() {
        Random rand = new Random();
        int randYear = rand.nextInt()
        return new LocalDate(
                LocalDate.now() -
        );

    }
}
