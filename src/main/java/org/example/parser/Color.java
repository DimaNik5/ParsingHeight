package org.example.parser;

class Color {
    private int r;
    private int g;
    private int b;

    public Color(int code){
        r = (code >> 16) & 0xFF;
        g = (code >> 8) & 0xFF;
        b = code & 0xFF;
    }

    public Color(int code, int minimum, int maximum){
        int delta = 0;
        r = (code >> 16) & 0xFF;
        if(r < minimum) delta = minimum - r;
        g = (code >> 8) & 0xFF;
        if(g < minimum) delta = Math.max(minimum - g, delta);
        b = code & 0xFF;
        if(b < minimum) delta = Math.max(minimum - b, delta);
        r += delta;
        g += delta;
        b += delta;
        delta = 0;
        if(r > maximum) delta = r - maximum;
        if(g > maximum) delta = Math.max(g - maximum, delta);
        if(b > maximum) delta = Math.max(b - maximum, delta);
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
