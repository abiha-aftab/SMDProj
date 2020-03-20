package com.example.smd_po_module;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FormRulesTest {

    @Test
    public void nameCharsAcceptLettersSpacesAndUnderscores() {
        assertTrue(FormRules.onlyAllowed("Jane Doe_2", 0, 9, FormRules.NAME_CHARS));
        assertFalse(FormRules.onlyAllowed("Jane2", 0, 5, FormRules.NAME_CHARS));
        assertFalse(FormRules.onlyAllowed("O'Neil", 0, 6, FormRules.NAME_CHARS));
    }

    @Test
    public void onlyChecksTheRequestedRange() {
        assertTrue(FormRules.onlyAllowed("12abc", 2, 5, FormRules.NAME_CHARS));
        assertTrue(FormRules.onlyAllowed("anything!", 3, 3, FormRules.NAME_CHARS));
    }

    @Test
    public void alphanumericCharsRejectSymbols() {
        assertTrue(FormRules.onlyAllowed("pass_123", 0, 8, FormRules.ALPHANUMERIC_CHARS));
        assertFalse(FormRules.onlyAllowed("pass 123", 0, 8, FormRules.ALPHANUMERIC_CHARS));
        assertFalse(FormRules.onlyAllowed("p@ss", 0, 4, FormRules.ALPHANUMERIC_CHARS));
    }

    @Test
    public void decimalCharsAllowDigitsAndPoint() {
        assertTrue(FormRules.onlyAllowed("3.75", 0, 4, FormRules.DECIMAL_CHARS));
        assertFalse(FormRules.onlyAllowed("3,75", 0, 4, FormRules.DECIMAL_CHARS));
    }

    @Test
    public void emailAcceptsCommonAddresses() {
        assertTrue(FormRules.isEmail("student@uni.edu"));
        assertTrue(FormRules.isEmail("  first.last+jobs@mail.uni.edu.pk "));
        assertTrue(FormRules.isEmail("Officer@Example.COM"));
    }

    @Test
    public void emailRejectsMalformedAddresses() {
        assertFalse(FormRules.isEmail(null));
        assertFalse(FormRules.isEmail(""));
        assertFalse(FormRules.isEmail("no-at-sign.com"));
        assertFalse(FormRules.isEmail("two@@example.com"));
        assertFalse(FormRules.isEmail("user@localhost"));
        assertFalse(FormRules.isEmail("user@example.c"));
    }

    @Test
    public void allFilledNeedsEveryValue() {
        assertTrue(FormRules.allFilled("a", "b"));
        assertTrue(FormRules.allFilled());
        assertFalse(FormRules.allFilled("a", " "));
        assertFalse(FormRules.allFilled("a", null));
    }

    @Test
    public void gpaIsOnTheFourPointScale() {
        assertTrue(FormRules.isGpa("0"));
        assertTrue(FormRules.isGpa("3.5"));
        assertTrue(FormRules.isGpa("2.75"));
        assertTrue(FormRules.isGpa("4.00"));
        assertFalse(FormRules.isGpa("4.01"));
        assertFalse(FormRules.isGpa("5"));
        assertFalse(FormRules.isGpa("3..5"));
        assertFalse(FormRules.isGpa("3.555"));
        assertFalse(FormRules.isGpa(""));
        assertFalse(FormRules.isGpa(null));
    }

    @Test
    public void parseYearsReturnsMinusOneForBadInput() {
        assertEquals(0, FormRules.parseYears("0"));
        assertEquals(12, FormRules.parseYears(" 12 "));
        assertEquals(-1, FormRules.parseYears(""));
        assertEquals(-1, FormRules.parseYears("-3"));
        assertEquals(-1, FormRules.parseYears("two"));
        assertEquals(-1, FormRules.parseYears(null));
        assertEquals(-1, FormRules.parseYears("99999999999"));
    }
}
