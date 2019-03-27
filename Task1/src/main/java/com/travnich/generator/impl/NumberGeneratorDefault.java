package com.travnich.generator.impl;

public class NumberGeneratorDefault implements com.travnich.generator.NumberGenerator {

    @Override
    public Integer generate(int min, int max) {
        return min + (int)Math.round(Math.random() * (max - min));
    }

    @Override
    public Double generate(double min, double max) {
        return min + Math.random() * (max - min);
    }
}
