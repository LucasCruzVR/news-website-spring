package com.news.api.controller;

import com.news.api.converter.MapperClass;
import com.news.api.domain.Publication;
import com.news.api.dto.publication.AllPublicationsDTO;
import com.news.api.dto.publication.PublicationCreateDTO;
import com.news.api.dto.publication.PublicationDetailDTO;
import com.news.api.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publications")
public class PublicationController {

    private final PublicationService publicationService;
    private final MapperClass mapperClass;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<AllPublicationsDTO>> getPublications(Publication publication) {
        return ResponseEntity.ok().body(publicationService.allPublications(publication));
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<PublicationDetailDTO> getPublication(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapperClass.toObject(publicationService.getPublication(id), PublicationDetailDTO.class));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<PublicationDetailDTO> createPublications(@RequestBody @Valid PublicationCreateDTO publicationCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(mapperClass.toObject(publicationService.savePublication(publicationCreateDTO), PublicationDetailDTO.class));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PublicationDetailDTO> updatePublications(@PathVariable Long id, @RequestBody @Valid PublicationCreateDTO publication) {
        return ResponseEntity.ok().body(mapperClass.toObject(publicationService.updatePublication(id, publication), PublicationDetailDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePublications(@PathVariable Long id) {
        publicationService.deletePublication(id);
        return new ResponseEntity(HttpStatus.OK);
    }
} 
