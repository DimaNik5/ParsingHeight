package org.example.parser;

class Pixel {
    private final Color color;
    private int h;
    private double angle = 0;

    public Pixel(int color, int min, int max){
        this.color = new Color(color, min, max);
    }

    public void countHeight(Color[] spector, int low, int high){
        int minLike = color.like(spector[0]);
        int minI = 0;
        for (int i = 1; i < spector.length; i++) {
            int t = color.like(spector[i]);
            if(t < minLike){
                minLike = t;
                minI = i;
                if (t == 0) break;
            }
        }

        double step = (double) (Math.abs(low) + Math.abs(high)) / spector.length;
        if(minLike == 0){
            h = (int) (high - minI * step);
        }
        else {
            if(minI == 0){
                h = (int) (high - ((double) minLike / (color.like(spector[1]) + minLike)) * step);
            }
            else if(minI == spector.length - 1){
                h = (int) (low + ((double) minLike / (color.like(spector[minI - 1]) + minLike)) * step);
            }
            else{
                h = (int) (high - minI * step);
                int lower = color.like(spector[minI + 1]);
                int higher = color.like(spector[minI - 1]);
                if(lower < higher){
                    h -= (int) (((double) minLike / (lower + minLike)) * step);
                }
                else {
                    h += (int) (((double) minLike / (higher + minLike)) * step);
                }
            }
        }
    }

    public int getH(){
        return h;
    }

    public void setAngle(int h, double distance){
        angle = Math.max(angle, Math.abs(Math.atan((this.h - h) / distance) * 180 / Math.PI));
    }

    public double getAngle(){
        return angle;
    }
}
