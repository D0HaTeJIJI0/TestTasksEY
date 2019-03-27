package com.travnich.controller;

import com.travnich.combiner.FilesCombiner;
import com.travnich.combiner.impl.FilesCombinerDefault;
import com.travnich.generator.FilesGenerator;
import com.travnich.generator.impl.FilesGeneratorDefault;
import com.travnich.generator.impl.StringGeneratorDefault;
import com.travnich.importer.FilesImporter;
import com.travnich.importer.impl.FilesImporterDefault;

import java.io.IOException;

public class MainCotroller {

    private final static int FILES_NUMBER = 100;
    private final static int STRINGS_IN_FILES = 100000;
    private final static String FILE_NAME = "output";

    private final static String ERROR_NO_ARGUMENTS = "Error: should enter arguments!";


    public static void main(String[] args) throws IOException {
        if (args.length == 0) System.out.println(ERROR_NO_ARGUMENTS);
        switch (args[0]) {
            case "-g":
                FilesGenerator fileGenerator = new FilesGeneratorDefault(args[1],
                        FILE_NAME,
                        new StringGeneratorDefault());
                fileGenerator.generateFiles(FILES_NUMBER, STRINGS_IN_FILES);
                break;

            case "-c":
                FilesCombiner filesCombiner = new FilesCombinerDefault(args[1], args[2]);
                if (args.length == 4) {
                    filesCombiner.combineWithExclusion(args[3]);
                } else {
                    filesCombiner.combine();
                }
                break;

            case "-i":
                FilesImporter filesImporter = new FilesImporterDefault(args[1]);
                filesImporter.importInDB();
                break;

            default:
                break;
        }
    }

}
