package com.company;

public class Random {

    public int Next(int a, int b) {
        return (int) (Math.random() * (b - a + 1) + a);
    }
}
