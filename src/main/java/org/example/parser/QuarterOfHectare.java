package org.example.parser;

public class QuarterOfHectare {
    private Color color;
    private int h;

    public QuarterOfHectare(int color){
        this.color = new Color(color);
    }

    public void countHeight(Color[] spectr, int low, int high){
        int minLike = color.like(spectr[0]);
        int minI = 0;
        for (int i = 1; i < spectr.length; i++) {
            int t = color.like(spectr[i]);
            if(t < minLike){
                minLike = t;
                minI = i;
                if (t == 0) break;
            }
        }

        double step = (double) (Math.abs(low) + Math.abs(high)) / spectr.length;
        if(minLike == 0){
            h = (int) (high - minI * step);
        }
        else {
            if(minI == 0){
                h = (int) (high - ((double) minLike / (color.like(spectr[1]) + minLike)) * step);
            }
            else if(minI == spectr.length - 1){
                h = (int) (low + ((double) minLike / (color.like(spectr[minI - 1]) + minLike)) * step);
            }
            else{
                h = (int) (high - minI * step);
                int lower = color.like(spectr[minI + 1]);
                int higher = color.like(spectr[minI - 1]);
                if(lower < higher){
                    h -= (int) (((double) minLike / (lower + minLike)) * step);
                }
                else {
                    h += (int) (((double) minLike / (higher + minLike)) * step);
                }
            }
        }
        //System.out.println(color + ": " + h);
    }

    public int getH(){
        return h;
    }
}
