package com.example.smd_po_module;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class num implements InputFilter {
    @Override
    public CharSequence filter (CharSequence source , int start , int end , Spanned dest ,
                                int dstart , int dend) {
        for ( int i = start ; i < end ; i++) {
            String checkMe = String. valueOf (source.charAt(i)) ;
            Pattern pattern =
                    Pattern. compile ( "[.1234567890]*" ) ;
            Matcher matcher = pattern.matcher(checkMe) ;
            boolean valid = matcher.matches() ;
            if (!valid) {
                Log. d ( "" , "invalid" ) ;
                return "" ;
            }
        }
        return null;
    }
}
