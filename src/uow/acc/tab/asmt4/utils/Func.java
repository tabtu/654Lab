package uow.acc.tab.asmt4.utils;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import org.jsoup.Jsoup;
import uow.acc.tab.asmt4.language.htmlparser.HTMLtoText;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Functions in Assignment 4
 *
 * @Author Tab Tu
 * @Updated Nov.23 2017
 */
public class Func {
    public static String[] getwords(String allcontent) {
        String regEx="[^a-z0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(allcontent);
        String tmp =  m.replaceAll("\n").trim();
        StringTokenizer st = new StringTokenizer(tmp.toString(),",.! \n");
        List<String> stmp = new ArrayList<>();
        while (st.hasMoreTokens()) {
            stmp.add(st.nextToken());
        }
        String[] result = new String[stmp.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stmp.get(i);
        }
        return result;
    }

    public static String findByPattern(String pattern, String content) {
        String result = "";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);
        while (m.find( )) {
            result += "\n" + m.group() + " at " + m.start();
        }
        return result;
    }

    public static String readFile2String(String filename) {
        In in = new In(filename);
        return in.readAll();
    }

    public static void writeString2File(String filename, String data) {
        Out out = new Out(filename);
        out.print(data);
    }

    public static String[] getFilesFromPath(String path) {
        File[] files = new File(path).listFiles();
        List<String> tmp = new ArrayList<>();
        for (File file : files) {
            if(!file.isDirectory()) {
                if(file.getName().compareTo(".DS_Store") != 0) {
                    tmp.add(file.getName());
                }
            }
        }
        String[] result = new String[tmp.size()];
        return tmp.toArray(result);
    }

    public static String convertHTMLfile2String(String htmlfilename) {
        try {
            FileReader in = new FileReader(htmlfilename);
            HTMLtoText parser = new HTMLtoText();
            parser.parse(in);
            in.close();
            return parser.getText();
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getHTMLfileStringFromURL(String url, String path) {
        try {
            org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
            String text = doc.text();
            String filename = UUID.randomUUID().toString();

            PrintWriter out = new PrintWriter(path + filename + ".txt");
            out.println(text);
            out.close();

            String html = doc.html();
            out = new PrintWriter( path + filename + ".html");
            out.println(html);
            out.close();

            return filename;
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
