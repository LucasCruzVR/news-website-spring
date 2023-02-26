package com.news.api.service;

import com.news.api.dto.publication.PublicationCreateDTO;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.news.api.converter.MapperClass;
import com.news.api.domain.Publication;
import com.news.api.dto.publication.AllPublicationsDTO;

import java.util.Map;
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
    public Publication savePublication(PublicationCreateDTO publicationCreateDTO) {
        var category = categoryRepository.findById(publicationCreateDTO.getCategory().getId()).orElseThrow(
                () -> new ObjectNotFoundException(publicationCreateDTO.getCategory().getId(), "Category not found"));
        Publication publication = MapperClass.converter(publicationCreateDTO, Publication.class);
        publication.setCategory(category);

        return publicationRepository.save(publication);
    }

    public Publication getPublication(Long publicationId) {
        Optional<Publication> publication = publicationRepository.findById(publicationId);
        return publication.orElseThrow(() -> new ObjectNotFoundException(publicationId, "Publication not found"));
    }

    @Transactional
    public Publication updatePublication(Long id, PublicationCreateDTO newPublication) {
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
