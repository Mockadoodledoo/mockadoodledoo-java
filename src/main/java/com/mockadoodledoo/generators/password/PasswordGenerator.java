package com.mockadoodledoo.generators.password;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import com.mockadoodledoo.generators.options.PasswordOptions;
import com.mockadoodledoo.generators.options.PasswordOptions.Case;

public class PasswordGenerator {

    private static final String AZ_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUM_STRING = "0123456789";
    private static final String SYMBOL_STRING = "!£$%^&*()-+=_[]{};:@,.<>/?#~";

    public static List<String> of(int numberOfElements, PasswordOptions options) {
        List<String> passwords = new ArrayList<>(numberOfElements);
        var alphabet = getAlphabet(options);
        StringBuilder sb = new StringBuilder(options.getLength());
        for (int i = 0; i < numberOfElements; i++) {
            for (int j = 0; j < options.getLength(); j++) {
                sb.append(alphabet[ThreadLocalRandom.current().nextInt(alphabet.length)]);
            }
            passwords.add(sb.toString());
            sb.delete(0, options.getLength());
        }
        return passwords;
    }

    private static char[] getAlphabet(PasswordOptions options) {
        String alphabet = switch (options.getType()) {
            case ALPHA -> getAlphaString(options.getPasswordCase());
            case ALPHA_NUMERIC -> getAlphaString(options.getPasswordCase()) + NUM_STRING;
            case ALPHA_NUMERIC_SYMBOLIC -> getAlphaString(options.getPasswordCase()) + NUM_STRING
                    + SYMBOL_STRING;
            case NUMERIC -> NUM_STRING;
            case SYMBOLIC -> SYMBOL_STRING;
        };
        return alphabet.toCharArray();
    }

    private static String getAlphaString(Case characterCase) {
        return switch (characterCase) {
            case LOWER -> AZ_STRING.toLowerCase();
            case UPPER -> AZ_STRING;
            case MIXED -> AZ_STRING + AZ_STRING.toLowerCase();
        };
    }
}
