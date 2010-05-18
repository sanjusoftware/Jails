package org.jailsframework.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:51:30 PM
 */
public class StringUtil {
    private String value;

    public StringUtil(String string) {
        this.value = string;
    }

    public String camelize() {

        StringBuffer camelizedValue = new StringBuffer();
        String[] words = breakByUnderScores();
        for (String word : words) {
            camelizedValue.append(camelize(word));
        }
        return camelizedValue.toString();
    }

    private String camelize(String word) {
        return word.replaceFirst(word.charAt(0) + "", word.toUpperCase().charAt(0) + "");
    }

    private String[] breakByUnderScores() {
        return value.split("_");
    }

    public String tabelize() {
        StringBuffer tabelizedValue = new StringBuffer();
        String[] words = breakByCamelCases();
        int index = 0;
        for (String word : words) {
            if (index == words.length - 1) {
                tabelizedValue.append(pluralize(word.toLowerCase()));
            } else {
                tabelizedValue.append(word.toLowerCase());
            }
            tabelizedValue.append("_");
            index++;
        }
        String table_name = tabelizedValue.toString();
        return table_name.substring(0, table_name.lastIndexOf('_'));
    }

    private String pluralize(String word) {
        return word.concat("s");
    }

    private String[] breakByCamelCases() {
        List<String> words = new ArrayList<String>();
        char[] chars = value.toCharArray();
        int wordStartIndex = 0;
        int wordEndIndex = 0;
        char prevCapChar = chars[0];
        for (char aChar : chars) {
            int ascciValue = (int) aChar;
            if (isCaps(ascciValue) && aChar != prevCapChar) {
                words.add(getWord(wordStartIndex, wordEndIndex));
                wordStartIndex = wordEndIndex;
                prevCapChar = aChar;
            }
            wordEndIndex++;
        }
        words.add(getWord(wordStartIndex, wordEndIndex));
        return words.toArray(new String[words.size()]);
    }

    private String getWord(int wordStartIndex, int wordEndIndex) {
        return value.substring(wordStartIndex, wordEndIndex);
    }

    private boolean isCaps(int ascciValue) {
        return ascciValue >= 65 && ascciValue <= 90;
    }
}