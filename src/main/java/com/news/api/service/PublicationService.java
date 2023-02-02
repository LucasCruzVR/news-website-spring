package com.news.api.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.news.api.converter.MapperClass;
import com.news.api.domain.Publication;
import com.news.api.model.AllPublicationsDTO;

import java.util.Optional;
import java.util.List;

import com.news.api.repository.CategoryRepository;
import com.news.api.repository.PublicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final CategoryRepository categoryRepository;

    public List<AllPublicationsDTO> allPublications(Publication publication) {
        return MapperClass.converter(publicationRepository.findAll(), AllPublicationsDTO.class);
    }

    @Transactional
    public Publication savePublication(Publication publication) {
        var publicationSaved = publicationRepository.save(publication);
        publicationSaved.setCategory(categoryRepository.findById(publicationSaved.getCategory().getId()));
        return publicationSaved;
    }

    public Publication getPublication(Long publicationId) {
        Optional<Publication> publication = publicationRepository.findById(publicationId);
        return publication.orElseThrow(() -> new ObjectNotFoundException(publicationId, "Not found"));
    }
}
