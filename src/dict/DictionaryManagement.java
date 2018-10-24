package dict;

import java.io.*;
import java.io.File;

/**
 *
 * @author Nguyen Hoang Tuyen
 */
public class DictionaryManagement {

    static final String source = "file\\dict.txt";

    public static void deleteFile() throws IOException {

        File file = new File(source);
        if (file.exists()) {
            file.delete();

            System.out.println("Deleted!");
        }
    }

    public static void createFile() throws IOException {
        try (FileWriter fileout = new FileWriter(source)) {
            for (Word i : Dictionary.word_list) {
                i.setWord_explain(i.getWord_explain().replace("\r\n", "\t"));
                i.setWord_explain(i.getWord_explain().replace("\t○ ", "--"));
                fileout.write(i.getWord_target() + "\t" + i.getSound() + "#" + i.getWord_explain() + "\r\n");
                System.out.println("Done!");
            }
            fileout.close();
        } catch (Exception e) {
            System.out.println("Writing error!");
        }
    }
/*
    public static void insertFromFile() throws IOException {
        try {
            File fileDir = new File(source);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("#");
                Word temp = new Word();
                temp.setWord_target(words[0]);
                temp.setType(words[1]);
                temp.setSound(words[2]);
                temp.setWord_explain(words[3]);
                Dictionary.word_list.add(temp);
            }
            System.out.println("File reading is successful");
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }
*/
    public static void insertFromFile2() throws IOException {
        try {
            File fileDir = new File("file\\old\\dict.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\t", 2);
                Word temp = new Word();
                temp.setWord_target(words[0]);
                String[] words2 = words[1].split("#", 2);
                if (words2.length == 2) {
                    temp.setSound(words2[0]);
                    temp.setWord_explain(words2[1]);
                } else {
                    temp.setWord_explain(words2[0]);
                }
                Dictionary.word_list.add(temp);

            }
            System.out.println("File reading is successful");
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }
    
        
    public static String renderExplain(String explain) {
        String renderedExplain = null;
        renderedExplain = explain.replace("\t", "\r\n");
        renderedExplain = renderedExplain.replace("--", "\t○ ");
        return renderedExplain;
    }
}
