package com.epam.lab.mentoring.rest.converter;

import com.epam.lab.mentoring.domain.Note;
import com.epam.lab.mentoring.domain.Tag;
import com.epam.lab.mentoring.rest.dto.NoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class NoteDtoToNoteConverter implements Converter<NoteDto, Note> {

    @Autowired
    private ConversionService conversionService;

    @Override
    public Note convert(NoteDto source) {
        Note note = new Note();
        note.setName(source.getName());
        note.setText(source.getText());
        note.setDate(source.getDate());
        note.setNoteId(source.getNoteId());
        note.setOwner(source.getOwner());
        note.setTags(source.getTags().stream()
                .map(tag -> conversionService.convert(tag, Tag.class))
                .collect(Collectors.toList()));

        return note;
    }
}
