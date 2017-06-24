package com.epam.lab.mentoring.rest;

import com.epam.lab.mentoring.domain.Tag;
import com.epam.lab.mentoring.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TagRest.class);

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/tags-rest/tag/{tagId}")
    public Tag findTagById(@PathVariable String tagId) {
        LOGGER.info("Attempt to get tag by id [{}].", tagId);
        return tagRepository.findOne(tagId);
    }

    @GetMapping("/tags-rest/search")
    public List<Tag> findAll() {
        LOGGER.info("Attempt to find all tags.");
        return tagRepository.findAll();
    }

    @PostMapping("/tags-rest/tag")
    public void addTag(@RequestBody Tag tag) {
        LOGGER.info("Attempt to insert tag: [{}].", tag);
        tagRepository.insert(tag);
    }
}