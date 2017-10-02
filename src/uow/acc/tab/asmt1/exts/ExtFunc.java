package uow.acc.tab.asmt1.exts;

import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tab Tu
 */
public class ExtFunc {
    // Get Random String with length
    public static String getRandomString(int length) {
        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

    // Get Random Integer Maxium for max
    public static int getRandomNumber(int max) {
        return (int)Math.round(Math.random() * max);
    }

    /**
     * Read file to Buffer
     * @param buffer buffer
     * @param filePath file path
     * @throws IOException
     * @author Tab Tu
     * @date Oct.1 2017
     */
    private static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine();
        while (line != null) {
            buffer.append(line);
            buffer.append("\n");
            line = reader.readLine();
        }
        reader.close();
        is.close();
    }

    /**
     * ReadFiles to String Content
     * @param filePath the file path
     * @return Content
     * @throws IOException
     * @author Tab Tu
     * @date Oct.1 2017
     */
    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, filePath);
        return sb.toString();
    }

    public static String[] findallfiles(String pathName, int depth) throws IOException {
        File dirFile = new File(pathName);
        if (!dirFile.exists()) {
            System.out.println("do not exit");
            return null;
        }
        if (!dirFile.isDirectory()) {
            if (dirFile.isFile()) {
                System.out.println(dirFile.getCanonicalFile());
            }
            return null;
        }
        String[] fileList = dirFile.list();
        int currentDepth = depth + 1;
        for (int i = 0; i < fileList.length; i++) {
            fileList[i] = pathName + fileList[i];
        }
        return fileList;
    }

    /**
     * combine all file contents in a string
     * @param files filenames
     * @return all contents
     */
    public static String contentallfiles(String[] files) {
        // get all content in the dictionary
        String allcontent = "";
        for (int i = 0; i < files.length; i++) {
            try {
                String content = ExtFunc.readFile(files[i]);
                content = content.toLowerCase();
                allcontent += content;
            } catch(IOException e) {
                System.out.println(e);
            }
        }
        return allcontent;
    }

    /**
     * Build the HashMap for Words and Keys
     * @param allcontent mapping content
     * @return HashMap for Word and Value
     */
    public static Map<String,Integer> mappingwords(String allcontent) {
        Map<String,Integer> map = new HashMap<>();
        String regEx="[^a-z0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(allcontent);
        String tmp =  m.replaceAll("\n").trim();
        StringTokenizer st = new StringTokenizer(tmp.toString(),",.! \n");
        while (st.hasMoreTokens()) {
            String letter = st.nextToken();
            int count;
            if (map.get(letter) == null) {
                count = 1;
            } else {
                count = map.get(letter).intValue() + 1;
            }
            map.put(letter, count);
        }
        return map;
    }

    /**
     * get file list from a dictionary
     * @param pathName dictionary path
     * @param depth finding depth
     * @throws IOException
     */
    public static void find(String pathName,int depth) throws IOException {
        File dirFile = new File(pathName);
        if (!dirFile.exists()) {
            System.out.println("do not exit");
            return;
        }
        if (!dirFile.isDirectory()) {
            if (dirFile.isFile()) {
                System.out.println(dirFile.getCanonicalFile());
            }
            return;
        }

        for (int j = 0; j < depth; j++) {
            System.out.print("  ");
        }
        System.out.print("|--");
        System.out.println(dirFile.getName());
        String[] fileList = dirFile.list();
        int currentDepth = depth + 1;
        for (int i = 0; i < fileList.length; i++) {
            String string = fileList[i];
            File file = new File(dirFile.getPath(), string);
            String name = file.getName();
            if (file.isDirectory()) {
                find(file.getCanonicalPath(), currentDepth);
            } else {
                for (int j = 0; j < currentDepth; j++) {
                    System.out.print("   ");
                }
                System.out.print("|--");
                System.out.println(name);
            }
        }
    }
}
