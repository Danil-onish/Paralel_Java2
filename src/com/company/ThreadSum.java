package com.company;

public class ThreadSum extends Thread {
    private final int startIndex;
    private final int finishIndex;
    private final int threadArrIndex;
    private final ArrClass arrClass;

    public ThreadSum(int startIndex, int finishIndex, ArrClass arrClass, int threadArrIndex) {
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
        this.arrClass = arrClass;
        this.threadArrIndex = threadArrIndex;
    }

    @Override
    public void run() {
        int min = arrClass.partMin(startIndex, finishIndex);
        arrClass.collectMins(min, threadArrIndex);
        arrClass.incThreadCount();
    }
}