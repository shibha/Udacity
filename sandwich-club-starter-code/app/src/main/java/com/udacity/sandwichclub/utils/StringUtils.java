package com.udacity.sandwichclub.utils;

import java.util.List;

/**
 * A class for utility methods for String based collections
 */
public class StringUtils {

    /**
     *  Method to convert a List of Strings to single appended string
     * @param arrayItems
     * @return
     */
    public static String getAppendedString(List<String> arrayItems) {
        StringBuilder builder = new StringBuilder();

        for (String arrayItem : arrayItems) {
            builder = builder.append(arrayItem);
        }

        return builder.toString();
    }
}
