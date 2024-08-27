package com.bichel.urlshortener.controller;

import com.bichel.urlshortener.job.ShortenerGenerator;
import com.bichel.urlshortener.vo.UrlShortenerRequestVO;
import com.bichel.urlshortener.vo.UrlShortenerResponseVO;
import jakarta.validation.Valid;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/url-shortener")
public class UrlShortenerController {

    private ShortenerGenerator shortenerGenerator;

    public UrlShortenerController(ShortenerGenerator shortenerGenerator) {
        this.shortenerGenerator = shortenerGenerator;
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<UrlShortenerResponseVO> generate(@Valid @RequestBody UrlShortenerRequestVO request) {
        /*if(Strings.isBlank(request.getOriginalUrl())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Original URL is missing");
        }*/

        final UUID id  = UUID.randomUUID();
        UrlShortenerResponseVO responseVO = new UrlShortenerResponseVO();
        responseVO.setId(id);
        responseVO.setOriginalUrl(request.getOriginalUrl());

        final String shortUrl = shortenerGenerator.generateUrlShortener(request.getOriginalUrl());
        responseVO.setShortUrl(shortUrl);

        return ResponseEntity.ok(responseVO);
    }

}
