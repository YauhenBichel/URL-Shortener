package com.bichel.urlshortener.controller;

import com.bichel.urlshortener.job.ShortenerGenerator;
import com.bichel.urlshortener.vo.UrlShortenerRequestVO;
import com.bichel.urlshortener.vo.UrlShortenerResponseVO;
import jakarta.validation.Valid;
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
        final UUID id  = UUID.randomUUID();
        UrlShortenerResponseVO responseVO = new UrlShortenerResponseVO();
        responseVO.setId(id);
        responseVO.setUrlOriginal(request.getUrlOriginal());

        final String shortUrl = shortenerGenerator.generateUrlShortener(request.getUrlOriginal());
        responseVO.setUrlShortener(shortUrl);

        return ResponseEntity.ok(responseVO);
    }

}
