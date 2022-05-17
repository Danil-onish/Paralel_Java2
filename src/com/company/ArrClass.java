package com.company;

public class ArrClass {
    private final int threadNum;
    public int[] arr;
    public int[] partsArr;

    public ArrClass(int length, int threadNum) {
        Random rand = new Random();
        arr = new int[length];
        this.threadNum = threadNum;
        for (int i = 0; i < length; i++) {
            arr[i] = rand.Next(0, 99);
        }
        arr[rand.Next(0, arr.length - 1)] = rand.Next(-99, 0);
    }

    public int partMin(int startIndex, int finishIndex) {
        int min = arr[finishIndex];
        int minIndex = finishIndex;
        for (int i = startIndex; i < finishIndex; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    synchronized private int[] getMins() {
        while (getThreadCount() < threadNum) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return partsArr;
    }

    synchronized public void collectMins(int min, int threadArrIndex) {
        this.partsArr[threadArrIndex] = min;
    }

    private int threadCount = 0;

    synchronized public void incThreadCount() {
        threadCount++;
        notify();
    }

    private int getThreadCount() {
        return threadCount;
    }

    public int threadMin() {
        ThreadSum[] threadSums = new ThreadSum[threadNum];
        partsArr = new int[threadNum];
        int portion = arr.length / threadNum;
        int ostacha = arr.length % threadNum;
        System.out.println("\nPortion: "+portion+"\nOstacha: "+ostacha);
        for (int i = 0; i < threadNum - 1; i++) {
            threadSums[i] = new ThreadSum(i * portion, (i + 1) * portion - 1, this, i);
            threadSums[i].start();
            System.out.println("thread["+(i)+"] = new Bound("+(i * portion)+", "+((i + 1) * portion - 1)+")");
        }
        int j = threadSums.length - 1;
        threadSums[j] = new ThreadSum(j * portion, (j + 1) * portion - 1 + ostacha, this, j);
        threadSums[j].start();
        System.out.println("thread["+(j)+"] = new Bound("+(j * portion)+", "+((j + 1) * portion - 1 + ostacha)+")");

        int[] res = getMins();

        int min = arr[res[0]];
        int minIndex = 0;
        for (int i = 1; i < res.length; i++) {
            if (arr[res[i]] < min) {
                min = arr[res[i]];
                minIndex = i;
            }
        }
        return res[minIndex];
    }
}
