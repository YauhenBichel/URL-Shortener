package com.bichel.urlshortener.repository;

import com.bichel.urlshortener.model.ShortUrlEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends CrudRepository<ShortUrlEntity, String> {}
