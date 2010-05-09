package org.jailsframework.util;

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
        return value.replaceFirst(value.charAt(0) + "", value.toUpperCase().charAt(0) + "");
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
            index++;
            tabelizedValue.append("_");
        }
        String table_name = tabelizedValue.toString();
        return table_name.substring(0, table_name.lastIndexOf('_'));
    }

    private String pluralize(String word) {
        return word.concat("s");
    }

    private String[] breakByCamelCases() {
        return new String[]{"Employee", "Record"};
    }
}