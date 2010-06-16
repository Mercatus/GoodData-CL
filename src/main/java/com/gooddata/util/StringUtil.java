package com.gooddata.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * GoodData
 *
 * @author zd <zd@gooddata.com>
 * @version 1.0
 */
public class StringUtil {

    private static String[] DISCARD_CHARS = {"\"", " ", "!", "?", "%", "&", "#", "*", "+", "-", "=", "/", ",", ".", ">", "<",
            "$", "%", ",", "(", ")", "�", "�", "�","@", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "{" ,"}",
            "[", "]","\\"};

    private static String[] INVALID_CSV_HEADER_CHARS = {"\"", "'", "!", "?", "%", "&", "#", "*", "+", "-", "=", "/", ",", ".", ">", "<",
            "$", "%", ",", "(", ")", "�", "�", "�","@", "{" ,"}",
            "[", "]","\\"};

    private static String[] WHITESPACE = {"\n","\t"};

    private static String[][] DATE_FORMAT_CONVERSION = {{"MM","%m"},{"yyyy","%Y"},{"yy","%y"},{"dd","%d"}};
    
    /**
     * Formats a string as identifier
     * Currently only converts to the lowercase and replace spaces
     * @param s the string to convert to identifier
     * @return converted string
     */
    public static String formatShortName(String s) {
        for ( String r : DISCARD_CHARS ) {
            s = s.replace(r,"");
        }
        return s.toLowerCase();
    }

    /**
     * Remove whitespace
     * Currently only converts to the lowercase and replace spaces
     * @param s the string to process
     * @return converted string
     */
    public static String removeWhitespace(String s) {
        for ( String r : WHITESPACE ) {
            s = s.replace(r,"");
        }
        return s;
    }

    /**
     * Formats a string as title
     * Currently does nothing TBD
     * @param s the string to convert to a title
     * @return converted string
     */
    public static String formatLongName(String s) {
        return s;
    }

    /**
     * Formats a CSV header
     * @param s the string to convert to identifier
     * @return converted string
     */
    public static String csvHeaderToIdentifier(String s) {
        for ( String r : INVALID_CSV_HEADER_CHARS ) {
            s = s.replace(r,"");
        }
        return s.toLowerCase();
    }

    /**
     * Formats a CSV header
     * @param s the string to convert to identifier
     * @return converted string
     */
    public static String csvHeaderToTitle(String s) {
        for ( String r : INVALID_CSV_HEADER_CHARS ) {
            s = s.replace(r,"");
        }
        return s;
    }

    /**
     * Converts the Java date format string to the MySQL format
     * @param dateFormat Java date format
     * @return MySQL date format
     */
    public static String convertJavaDateFormatToMySql(String dateFormat) {
        for(int i=0; i < DATE_FORMAT_CONVERSION.length; i++)
            dateFormat = dateFormat.replace(DATE_FORMAT_CONVERSION[i][0],
                            DATE_FORMAT_CONVERSION[i][1]);
        return dateFormat;
    }
    
    /**
     * Converts a {@link Collection} to a <tt>separator<tt> separated string
     * 
     * @param separator
     * @param list
     * @return <tt>separator<tt> separated string version of the given list
     */
    public static String join(String separator, Collection<String> list) {
    	return join(separator, list, null);
    }

    /**
     * Converts a {@link Collection} to a <tt>separator<tt> separated string.
     * If the <tt>replacement</tt> parameter is not null, it is used to populate
     * the result string instead of list elements.
     * 
     * @param separator
     * @param list
     * @param replacement
     * @return <tt>separator<tt> separated string version of the given list
     */
    public static String join(String separator, Collection<String> list, String replacement) {
    	StringBuffer sb = new StringBuffer();
    	boolean first = true;
    	for (final String s : list) {
    		if (first)
    			first = false;
    		else
    			sb.append(separator);
			sb.append(replacement == null ? s : replacement);
		}
    	return sb.toString();
    }

    public static List<String> parseLine(String elements) {
        if (elements == null) {
            return new ArrayList<String>();
        }
        // TODO proper CSV parsing
        String[] result = elements.trim().split("\\s*,\\s*");
        return Arrays.asList(result);
    }
}
