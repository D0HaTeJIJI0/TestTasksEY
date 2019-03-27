package com.travnich.combiner.impl;

import com.travnich.combiner.FilesCombiner;

import java.io.*;
import java.util.Scanner;

public class FilesCombinerDefault implements FilesCombiner {

    private final File sourceDir, targetDir;

    public FilesCombinerDefault(String sourcePath, String targetPath) {
        this.sourceDir = new File(sourcePath);
        this.targetDir = new File(targetPath);
    }

    @Override
    public void combine() throws IOException {
        targetDir.createNewFile();
        File[] dirListing = sourceDir.listFiles();
        FileWriter fw = new FileWriter(targetDir);
        FileReader fr;
        Scanner sc;
        if (dirListing != null) {
            for (File child: dirListing) {
                fr = new FileReader(child);
                sc = new Scanner(fr);
                while (sc.hasNextLine()) {
                    fw.write(sc.nextLine());
                }
                fr.close();
            }
        }
        fw.close();
    }

    @Override
    public void combineWithExclusion(String exclusive) throws IOException {
        targetDir.createNewFile();
        File[] dirListing = sourceDir.listFiles();
        String line;
        FileWriter fw = new FileWriter(targetDir);
        FileReader fr;
        Scanner sc;
        long counterDeleteStrings = 0;
        int start;
        if (dirListing != null) {
            for (File child: dirListing) {
                fr = new FileReader(child);
                sc = new Scanner(fr);
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    start = 0;
                    start = line.indexOf(exclusive, start);
                    while (start != -1) {
                        counterDeleteStrings++;
                        start = line.indexOf(exclusive, start);
                    }
                    fw.write(line.replaceAll(exclusive, ""));
                }
                fr.close();
            }
        }
        System.out.println("Deleted strings with " + "'" +  exclusive + "': " + counterDeleteStrings);
        fw.close();
    }
}
