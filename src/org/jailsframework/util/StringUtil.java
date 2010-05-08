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
}