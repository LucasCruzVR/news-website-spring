package com.news.api.controller;

import com.news.api.model.PublicationDTO;
import org.springframework.web.bind.annotation.RestController;

import com.news.api.domain.Publication;
import com.news.api.model.AllPublicationsDTO;
import com.news.api.service.PublicationService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publications")
public class PublicationController {
    
    private final PublicationService publicationService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<AllPublicationsDTO>> getPublications(Publication publication) {
        return ResponseEntity.ok().body(publicationService.allPublications(publication));
    }

    @GetMapping(value= "/{id}")
    @ResponseBody
    public ResponseEntity<Publication> getPublication(@PathVariable Long id) {
        return ResponseEntity.ok().body(publicationService.getPublication(id));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Publication> createPublications(@RequestBody @Valid Publication publication) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(publicationService.savePublication(publication));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Publication> updatePublications(@PathVariable Long id, @RequestBody @Valid PublicationDTO publication) {
        return ResponseEntity.ok().body(publicationService.updatePublication(id, publication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePublications(@PathVariable Long id) {
        publicationService.deletePublication(id);
        return new ResponseEntity(HttpStatus.OK);
    }
} 
