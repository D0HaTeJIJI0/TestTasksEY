package com.travnich.combiner;

import java.io.IOException;

public interface FilesCombiner {
    void combine() throws IOException;
    void combineWithExclusion(String exclusive) throws IOException;
}
