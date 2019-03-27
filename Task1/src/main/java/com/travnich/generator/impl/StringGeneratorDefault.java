package com.travnich.generator.impl;

import com.travnich.generator.*;

import java.time.format.DateTimeFormatter;

import static com.travnich.constant.GenerateInfo.*;

public class StringGeneratorDefault implements StringGenerator {

    private final DateGenerator dateGenerator;
    private final NumberGenerator numberGenerator;
    private final CharacterGenerator latinGenerator, russianGenerator;

    public StringGeneratorDefault() {

        this.dateGenerator = new DateGeneratorDefault();
        this.numberGenerator = new NumberGeneratorDefault();
        this.latinGenerator = new CharacterGeneratorDefault(LATIN_ALPHABET);
        this.russianGenerator = new CharacterGeneratorDefault(RUSSIAN_ALPHABET);
    }

    @Override
    public String generateString() {
        StringBuffer result = new StringBuffer();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        result.append(formatter.format(dateGenerator.generate(YEARS)));
        result.append(DELIMETER);
        result.append(latinGenerator.generate(LATIN_LENGTH));
        result.append(DELIMETER);
        result.append(russianGenerator.generate(RUSSIAN_LENGTH));
        result.append(DELIMETER);
        result.append(numberGenerator.generate(MIN_INT, MAX_INT));
        result.append(DELIMETER);
        result.append(String.format(FORMAT_DOUBLE, numberGenerator.generate(MIN_DOUBLE, MAX_DOUBLE)));
        result.append(DELIMETER);
        result.append(NEW_LINE);

        return result.toString();
    }
}
