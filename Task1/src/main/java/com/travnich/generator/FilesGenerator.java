package com.travnich.generator;

import java.io.IOException;

public interface FilesGenerator {
    void generateFiles(int filesAmount, int stringsAmount) throws IOException;
}
