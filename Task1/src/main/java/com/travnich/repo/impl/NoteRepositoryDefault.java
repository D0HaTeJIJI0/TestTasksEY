package com.travnich.repo.impl;

import com.travnich.entity.Note;
import com.travnich.repo.NoteRepository;
import com.travnich.util.PostgreSQLDatabaseManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class NoteRepositoryDefault implements NoteRepository {

    private EntityManager entityManager = PostgreSQLDatabaseManager.getInstance().getEntityManager();

    @Override
    public Note create(Note note) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(note);
        tx.commit();
        return note;
    }

}
