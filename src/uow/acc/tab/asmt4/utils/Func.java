package uow.acc.tab.asmt4.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
}
