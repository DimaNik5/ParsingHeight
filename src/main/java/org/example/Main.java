package org.example;

import org.example.parser.HeightParser;

public class Main {
    public static void main(String[] args) {
        HeightParser parser = new HeightParser("spector.png", -4377, 2368, 50);
        parser.setMinimumColorOfPixel(128);
        parser.parsing("10x10.png", "out.txt");
    }
}