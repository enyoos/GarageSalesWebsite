package com.api.grg.envy.Utils;

import java.util.ArrayList;

// converts the array list into csv
public final class Utils {

    public static String aL2Csv ( ArrayList<? extends Object> posts ) {
        String str = posts.toString();
        String out = str.substring(0, str.length()-1);
        out = out.toString().replaceFirst("\\[", "");
        return out;
    }

    // public static boolean isCredentialsTaken( String name, String email )
    // {
    //     if ( )
    // }

}
