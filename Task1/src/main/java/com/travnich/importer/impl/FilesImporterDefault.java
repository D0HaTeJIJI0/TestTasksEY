package com.travnich.importer.impl;

import com.travnich.entity.Note;
import com.travnich.file.FileAnalyzer;
import com.travnich.repo.NoteRepository;
import com.travnich.repo.impl.NoteRepositoryDefault;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FilesImporterDefault implements com.travnich.importer.FilesImporter {

    private final String sourcePath;

    public FilesImporterDefault(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    public void importInDB() throws IOException {
        File f = new File(sourcePath);
        File[] files = null;
        if (f.isFile()) {
            files = new File[1];
            files[0] = f;
        }
        else if (f.isDirectory()) {
            files = f.listFiles();
        }
        if (files != null) {
            FileReader fr;
            Scanner sc;
            NoteRepository noteRepository = new NoteRepositoryDefault();
            int size, lineCounter;
            for (File child : files) {
                System.out.println("[INFO] --- File '" + child.getName() + "':");
                fr = new FileReader(child);
                sc = new Scanner(fr);
                size = FileAnalyzer.countLines(child.getAbsolutePath());
                lineCounter = 0;
                while (sc.hasNextLine()) {
                    noteRepository.create(Note.createNote(sc.nextLine()));
                    lineCounter++;
                    System.out.println("[INFO] ---  " + lineCounter + " lines imported out of " + size);
                }
            }
        }
    }
}
