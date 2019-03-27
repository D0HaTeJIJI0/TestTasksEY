package com.travnich.generator.impl;

import com.travnich.generator.CharacterGenerator;
import com.travnich.generator.NumberGenerator;

public class CharacterGeneratorDefault implements CharacterGenerator {

    private final String alphabet;
    private final NumberGenerator numberGenerator;

    public CharacterGeneratorDefault(String alphabet) {
        this.alphabet = alphabet;
        numberGenerator = new NumberGeneratorDefault();
    }

    @Override
    public String generate(int length) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            result.append(alphabet.charAt(numberGenerator.generate(0, length - 1)));
        }
        return result.toString();
    }
}
