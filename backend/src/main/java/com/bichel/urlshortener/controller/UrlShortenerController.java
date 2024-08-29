package com.bichel.urlshortener.controller;

import com.bichel.urlshortener.exception.OriginalUrlIsMissingException;
import com.bichel.urlshortener.job.ShortenerGenerator;
import com.bichel.urlshortener.storage.UrlMemoryStorage;
import com.bichel.urlshortener.storage.UrlStorage;
import com.bichel.urlshortener.vo.UrlShortenerRequestVO;
import com.bichel.urlshortener.vo.UrlShortenerResponseVO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/url-shortener")
public class UrlShortenerController {

    private final ShortenerGenerator shortenerGenerator;
    private final UrlStorage urlStorage;

    public UrlShortenerController(ShortenerGenerator shortenerGenerator,
                                  UrlMemoryStorage urlStorage) {
        this.shortenerGenerator = shortenerGenerator;
        this.urlStorage = urlStorage;
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<UrlShortenerResponseVO> generate(@Valid @RequestBody UrlShortenerRequestVO request) {
        if(Strings.isBlank(request.getOriginalUrl())) {
            throw new OriginalUrlIsMissingException("Original URL is missing");
        }

        final UUID id = UUID.randomUUID();
        UrlShortenerResponseVO responseVO = new UrlShortenerResponseVO();
        responseVO.setId(id);
        responseVO.setOriginalUrl(request.getOriginalUrl());

        final String shortUrl = shortenerGenerator.generateUrlShortener(request.getOriginalUrl());
        responseVO.setShortUrl(shortUrl);

        return ResponseEntity.ok(responseVO);
    }

    @RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public void redirectToOriginalUrl(HttpServletResponse httpServletResponse, @PathVariable("shortUrl") String shortUrl) {

        final String originalUrl = urlStorage.getOriginalUrl(shortUrl);

        httpServletResponse.setHeader("Location", originalUrl);
        httpServletResponse.setStatus(302);
    }
}
