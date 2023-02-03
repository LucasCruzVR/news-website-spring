package com.news.api.service;

import com.news.api.domain.Category;
import com.news.api.model.PublicationDTO;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

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
        return publication.orElseThrow(() -> new ObjectNotFoundException(publicationId, "Publication not found"));
    }

    @Transactional
    public Publication updatePublication(Long id, PublicationDTO newPublication) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        Publication category = publicationRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Not found"));
        modelMapper.map(newPublication, category);
        return publicationRepository.save(category);
    }

    @Transactional
    public void deletePublication(Long id) {
        try {
            publicationRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not delete this publication");
        }
    }
}
