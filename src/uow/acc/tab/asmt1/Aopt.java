package uow.acc.tab.asmt1;

import uow.acc.tab.asmt1.exts.ExtFunc;
import uow.acc.tab.asmt1.exts.Word;
import uow.acc.tab.asmt1.exts.WordEntity;
import uow.acc.tab.asmt1.searchtrees.SplayTree;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

/**
 * Answer Sheet for Assignment 1 Optional Tasks
 * Written by Tab Tu
 * Updated Oct.1 2017
 */
public class Aopt {

    /**
     * count the most useful words in files (Question 2)
     * @param filename files list
     * @param fn maximum top list
     */
    public static void WordCount(String[] filename, Integer fn) {
        String allcontent = ExtFunc.contentallfiles(filename);
        Map<String,Integer> map = ExtFunc.mappingwords(allcontent);

        Set<WordEntity> set = new TreeSet<>();
        for (String key : map.keySet()) {
            set.add(new WordEntity(key,map.get(key)));
        }

        // counting
        int count = 1;
        for (Iterator<WordEntity> it = set.iterator(); it.hasNext(); ) {
            WordEntity w = it.next();
            System.out.println(count + "- " + w.getKey() + " : " + w.getCount());
            if (count == fn)
                break;
            count++;
        }
    }

    /**
     * analyze the appearance for the keyword in each files, and list the result with top fn (Question 3)
     * @param filename files list
     * @param keyword keyword to compare
     * @param fn maximum top list
     */
    public static void WordAnalysis(String[] filename, String keyword, Integer fn) {
        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> keymap = new HashMap<>();
        Set<WordEntity> set = new TreeSet<WordEntity>();

        for (int i = 0; i < filename.length; i++) {
            try {
                String content = ExtFunc.readFile(filename[i]);
                String regEx="[^a-z0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(content);
                String tmp =  m.replaceAll("\n").trim();
                StringTokenizer st = new StringTokenizer(tmp.toString(),",.! \n");
                int count = 0;
                while (st.hasMoreTokens()) {
                    String letter = st.nextToken();
                    if (map.get(letter) == null) {
                        count = 1;
                    } else {
                        count = map.get(letter).intValue() + 1;
                    }
                    map.put(letter, count);
                }
            } catch(IOException e) {
                System.out.println(e);
            }
            keymap.put(filename[i] + ": " + keyword, map.get(keyword));
        }

        for (String key : keymap.keySet()) {
            set.add(new WordEntity(key, keymap.get(key)));
        }

        // counting
        int count = 1;
        for (Iterator<WordEntity> it = set.iterator(); it.hasNext(); ) {
            WordEntity w = it.next();
            System.out.println(count + "- " + w.getKey() + " : " + w.getCount());
            if (count == 10)
                break;
            count++;
        }
    }


    /**
     * build the word dictionary with splaytree
     * @param filename files
     */
    public static SplayTree<Word> WordDictionary(String[] filename) {
        String allcontent = ExtFunc.contentallfiles(filename);
        Map<String,Integer> map = ExtFunc.mappingwords(allcontent);
        SplayTree<Word> st = new SplayTree<>();

        for (String key : map.keySet()) {
            Word tmp = new Word(key);
            tmp.setFreq(map.get(key));
            st.insert(tmp);
        }

        return st;
    }

    public static void main(String[] args) throws IOException{
        String[] re = ExtFunc.findallfiles("/Users/Kevin/Documents/Workspace/Java/654Lab/W3C Web Pages/Text/", 1);

        System.out.println("Print out the most useful 10 words in the dictionary.");
        // list the most 10 useful words in a dictionary
        WordCount(re, 10);

        String tarkey = "is";
        System.out.println("Print out the 10 files which use the word " + tarkey + " the most");
        // list the most 10 files which include the keyword
        WordAnalysis(re, tarkey, 10);

        // build word dictionary
        System.out.println("Build words dictionary with SpalyTree.");
        SplayTree<Word> mydict = WordDictionary(re);
        mydict.printTree();
    }
}
