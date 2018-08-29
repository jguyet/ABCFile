package com.flagstone.transform.as3.abcfile.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internatiolizer {
    private static Locale defLoc = Locale.FRANCE;
    public static ResourceBundle msgs = ResourceBundle.getBundle("Language", defLoc);
}