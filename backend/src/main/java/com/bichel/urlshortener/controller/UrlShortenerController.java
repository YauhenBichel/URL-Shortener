package com.bichel.urlshortener.controller;

import com.bichel.urlshortener.exception.OriginalUrlIsMissingException;
import com.bichel.urlshortener.job.ShortenerGenerator;
import com.bichel.urlshortener.model.ShortUrlEntity;
import com.bichel.urlshortener.repository.ShortUrlRepository;
import com.bichel.urlshortener.storage.UrlMemoryStorage;
import com.bichel.urlshortener.storage.UrlStorage;
import com.bichel.urlshortener.vo.UrlOriginalResponseVO;
import com.bichel.urlshortener.vo.UrlShortenerRequestVO;
import com.bichel.urlshortener.vo.UrlShortenerResponseVO;
import jakarta.validation.Valid;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/url-shortener")
public class UrlShortenerController {

    private final ShortenerGenerator shortenerGenerator;
    private final UrlStorage urlStorage;
    private final ShortUrlRepository shortUrlRepository;

    public UrlShortenerController(ShortenerGenerator shortenerGenerator,
                                  UrlMemoryStorage urlStorage,
                                  ShortUrlRepository shortUrlRepository) {
        this.shortenerGenerator = shortenerGenerator;
        this.urlStorage = urlStorage;
        this.shortUrlRepository = shortUrlRepository;
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

        ShortUrlEntity shortUrlEntity = new ShortUrlEntity();
        shortUrlEntity.setId(shortUrl);
        shortUrlEntity.setOriginalUrl(request.getOriginalUrl());

        try {
            shortUrlRepository.save(shortUrlEntity);
        }catch(Exception ex) {
            System.out.println(ex);
        }

        responseVO.setShortUrl(shortUrl);
        return ResponseEntity.ok(responseVO);
    }

    @GetMapping(value = "/{shortUrl}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<UrlOriginalResponseVO> getOriginalUrl(@PathVariable("shortUrl") String shortUrl) {

        String originalUrl = "";
        //String originalUrl = urlStorage.getOriginalUrl(shortUrl);

        Optional<ShortUrlEntity> optOriginalUrl = shortUrlRepository.findById(shortUrl);
        if(optOriginalUrl.isPresent()) {
            originalUrl = optOriginalUrl.get().getOriginalUrl();
        }

        UrlOriginalResponseVO responseVO = new UrlOriginalResponseVO();
        responseVO.setOriginalUrl(originalUrl);

        return ResponseEntity.ok(responseVO);
    }
}
