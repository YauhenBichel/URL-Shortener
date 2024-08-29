package com.bichel.urlshortener.job;

import com.bichel.urlshortener.storage.UrlStorage;
import com.bichel.urlshortener.utility.Base62;
import org.springframework.stereotype.Component;

@Component
public class Base62ShortenerGenerator implements ShortenerGenerator {

    private final UrlStorage urlStorage;

    public Base62ShortenerGenerator(UrlStorage urlStorage) {
        this.urlStorage = urlStorage;
    }

    @Override
    public String generateUrlShortener(String originalUrl) {
        //short url length between 5 and 8 characters
        //characters include latin symbols [a-zA-Z] and digits [0-9]
        final int originalUrlHashCode = originalUrl.hashCode() & 0xfffffff;
        final String shortUrl = Base62.fromBase10(originalUrlHashCode);

        urlStorage.addMapOriginalToShort(originalUrl, shortUrl);
        urlStorage.addMapShortToOriginal(shortUrl, originalUrl);

        return shortUrl;
    }
}
