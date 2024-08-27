package com.bichel.urlshortener.job;

import com.bichel.urlshortener.utility.Base62;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Base62ShortenerGenerator implements ShortenerGenerator {

    //key is short url
    //value is original url
    private Map<String, String> mapOriginalToShortener;

    @Override
    public String generateUrlShortener(String urlOriginal) {
        //short url length between 5 and 8 characters
        //characters include latin symbols [a-zA-Z] and digits [0-9]
        final int originalUrlHashCode = urlOriginal.hashCode() & 0xfffffff;

        final String shortUrl = Base62.fromBase10(originalUrlHashCode);
        return shortUrl;
    }

    private String encodeBase62(int num) {
        if(num == 0) {
            return String.valueOf(Base62.CHARACTERS_BASE_62.charAt(0));
        }

        StringBuilder sbEncoding = new StringBuilder();
        while(num > 0) {
            num = num / 62;
            int reminder = num % 62;

            sbEncoding.insert(0, Base62.CHARACTERS_BASE_62.charAt(reminder));
        }

        return sbEncoding.toString();
    }
}
