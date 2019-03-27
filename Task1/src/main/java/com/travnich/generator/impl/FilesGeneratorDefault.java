package com.travnich.generator.impl;

import com.travnich.generator.FilesGenerator;
import com.travnich.generator.StringGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilesGeneratorDefault implements FilesGenerator {

    private final String absolutePath;
    private final String fileName;
    private final StringGenerator stringGenerator;

    public FilesGeneratorDefault(String absolutePath, String fileName, StringGenerator stringGenerator) {
        this.absolutePath = absolutePath;
        this.fileName = fileName;
        this.stringGenerator = stringGenerator;
    }

    @Override
    public void generateFiles(int filesAmount, int stringsAmount) throws IOException {
        File f;
        for (int i = 0; i < filesAmount; i++) {
            f = new File(absolutePath + "\\" + fileName + i);
            f.createNewFile();
            System.out.println("[INFO] --- File '" + fileName + i + "' created!");
            FileWriter fw = new FileWriter(f);

            for (int j = 0; j < stringsAmount; j++) {
                fw.write(stringGenerator.generateString());
            }
            fw.close();
        }
    }
}
