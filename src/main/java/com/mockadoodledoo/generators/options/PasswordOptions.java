package com.mockadoodledoo.generators.options;

import com.mockadoodledoo.generators.types.Password;

public class PasswordOptions {
    private Password type;
    private int length;
    private Case passwordCase;

    public enum Case {
        LOWER, UPPER, MIXED
    }

    private static final int DEFAULT_LENGTH = 16;
    private static final int MAX_LENGTH = 128;

    /**
     * Creates a new {@link PasswordOptions} instance defaulting to a length of 16 characters.
     * 
     * @param type
     */
    public PasswordOptions(Password type) {
        this(type, DEFAULT_LENGTH);
    }

    /**
     * Creates a new {@link PasswordOptions} instance with a specified length and default character
     * case {@link Case#MIXED}.
     * 
     * @param type
     * @param length
     */
    public PasswordOptions(Password type, int length) {
        this(type, length, Case.MIXED);
    }

    /**
     * Creates a new {@link PasswordOptions} instance with a default length and default character
     * case {@link Case#MIXED}.
     * 
     * @param type
     * @param length
     */
    public PasswordOptions(Password type, Case passwordCase) {
        this(type, DEFAULT_LENGTH, passwordCase);
    }

    /**
     * Creates a new {@link PasswordOptions} instance with a specified length and character case.
     * 
     * @param type
     * @param length
     */
    public PasswordOptions(Password type, int length, Case passwordCase) {
        this.type = type;
        if (length > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "Length must be less than or equal to " + MAX_LENGTH);
        }
        this.length = length;
        this.passwordCase = passwordCase;
    }

    public Password getType() {
        return type;
    }

    public int getLength() {
        return length;
    }

    public Case getPasswordCase() {
        return passwordCase;
    }
}
