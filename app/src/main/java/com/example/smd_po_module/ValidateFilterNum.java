package com.example.smd_po_module;

import android.text.InputFilter;
import android.text.Spanned;

public class ValidateFilterNum implements InputFilter {
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                               int dstart, int dend) {
        return FormRules.onlyAllowed(source, start, end, FormRules.ALPHANUMERIC_CHARS) ? null : "";
    }
}
