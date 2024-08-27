package com.bichel.urlshortener.utility;

public final class Base62 {

    public static final String CHARACTERS_BASE_62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static final int BASE = CHARACTERS_BASE_62.length();

    private Base62() {
    }

    public static String fromBase10(int hashCode) {
        StringBuilder sbUrlShort = new StringBuilder();

        if (hashCode == 0) {
            return String.valueOf(CHARACTERS_BASE_62.charAt(0));
        }

        while (hashCode > 0) {
            hashCode = convertNumToBase62(hashCode, sbUrlShort);
        }

        return sbUrlShort.reverse().toString();
    }

    private static int convertNumToBase62(int hashCode, final StringBuilder sbUrlShort) {
        int reminder = hashCode % BASE;
        sbUrlShort.append(CHARACTERS_BASE_62.charAt(reminder));

        return hashCode / BASE;
    }
}
