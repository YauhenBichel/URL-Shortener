package com.bichel.urlshortener.utility;

import com.bichel.urlshortener.job.Base62ShortenerGenerator;
import com.bichel.urlshortener.job.ShortenerGenerator;
import com.bichel.urlshortener.storage.UrlMemoryStorage;
import com.bichel.urlshortener.storage.UrlStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Base62Tests {
    @Test
    public void testBase62Text() {
        final String originalUrl = "https://www.google.com/maps/place/Central+Park/@40.7825547,-73.9681583,17z/data=!3m1!4b1!4m6!3m5!1s0x89c2589a018531e3:0xb9df1f7387a94119!8m2!3d40.7825547!4d-73.9655834!16zL20vMDljN3Y?entry=ttu&g_ep=EgoyMDI0MDgyMS4wIKXMDSoASAFQAw%3D%3D";

        UrlStorage urlStorage = new UrlMemoryStorage();
        ShortenerGenerator shortUrlGenerator = new Base62ShortenerGenerator(urlStorage);
        final String actual = shortUrlGenerator.generateUrlShortener(originalUrl);

        assertEquals("qJdGG", actual);
    }
}
