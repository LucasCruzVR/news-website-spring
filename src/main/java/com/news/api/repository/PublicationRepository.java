package com.news.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.news.api.domain.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    
}
