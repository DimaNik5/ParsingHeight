package org.example.parser;

class Color {
    private int r;
    private int g;
    private int b;

    private static final int MINIMAL = 128;
    private static final int MAXIMAL = 255;

    public Color(int code){
        int delta = 0;
        r = (code >> 16) & 0xFF;
        if(r < MINIMAL) delta = MINIMAL - r;
        g = (code >> 8) & 0xFF;
        if(g < MINIMAL) delta = Math.max(MINIMAL - g, delta);
        b = code & 0xFF;
        if(b < MINIMAL) delta = Math.max(MINIMAL - b, delta);
        r += delta;
        g += delta;
        b += delta;
        delta = 0;
        if(r > MAXIMAL) delta = r - MAXIMAL;
        if(g > MAXIMAL) delta = Math.max(g - MAXIMAL, delta);
        if(b > MAXIMAL) delta = Math.max(b - MAXIMAL, delta);
        r -= delta;
        g -= delta;
        b -= delta;
    }

    public int like(Color s){
        return Math.abs(r - s.r) + Math.abs(g - s.g) + Math.abs(b - s.b);
    }

    @Override
    public String toString() {
        return r + " " + g + " " + b;
    }
}
