package com.company;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();
        int length = rand.Next(12, 50);
        int threadNum = rand.Next(4, 10);

        ArrClass arrClass = new ArrClass(length, threadNum);
        System.out.println("ThreadNum: "+threadNum+"\nLength: " + length + "\nArray");
        for (int i = 0; i < length; i++) {
            System.out.print(arrClass.arr[i] + " ");
        }

        int minIndex = arrClass.threadMin();
        System.out.println("\nMin is: " + arrClass.arr[minIndex] + "\nWith index: " + minIndex);
        System.out.println();
    }
}
