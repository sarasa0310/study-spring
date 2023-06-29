package com.jimmy.studyspring.quoters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
