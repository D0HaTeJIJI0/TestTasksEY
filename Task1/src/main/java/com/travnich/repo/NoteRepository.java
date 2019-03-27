package com.travnich.repo;

import com.travnich.entity.Note;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository {
    Note create(Note note);
}
