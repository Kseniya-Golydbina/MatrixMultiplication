package com.company;

public class ParallelMatrix {
    public static void main(String[] args) {
        int sizeOne = 1000;
        int sizeTwo = 1000;
        // создание первой матрицы
        ParallelMatrix matrixOne = new ParallelMatrix(sizeOne, sizeTwo);
        for (int i = 0; i < sizeOne; i++) {
            for (int j = 0; j < sizeTwo; j++) {
                if ((i + j) % 2 == 0) {
                    matrixOne.setElement(i, j, 66);
                } else {
                    matrixOne.setElement(i, j, 88);
                }
            }
        }
        System.out.println("Matrix #1:");
        //System.out.println(matrixOne);

        // создание второй матрицы
        ParallelMatrix matrixTwo = new ParallelMatrix(sizeTwo, sizeOne);
        for (int i = 0; i < sizeTwo; i++) {
            for (int j = 0; j < sizeOne; j++) {
                if ((i + j) % 2 == 0) {
                    matrixTwo.setElement(i, j, 88);
                } else {
                    matrixTwo.setElement(i, j, 66);
                }
            }
        }
        System.out.println("Matrix #2:");
       // System.out.println(matrixTwo);

        long start = System.currentTimeMillis();
        ParallelMatrix parallelMatrixMultiplication = new ParallelMatrix(8);
        parallelMatrixMultiplication.resultParallelMatrix(matrixOne, matrixTwo);
        long end = System.currentTimeMillis();
        float time = (float) (end - start);
        System.out.println("Matrix #3: ");
        //System.out.println(parallelMatrixMultiplication.resultParallelMatrix(matrixOne, matrixTwo));
        System.out.println("Параллельное умножение матриц: " + time + "ms");
    }
    private class Multiply extends Thread {
        private int firstRow, lastRow, sizeRow;
        private ParallelMatrix matrixOne, matrixTwo, result;
       // private int[][] matrix;

        Multiply(int firstIndexRow, int secondIndexRow, ParallelMatrix matrixOne, ParallelMatrix matrixTwo, ParallelMatrix result) {
            this.firstRow = firstIndexRow;
            this.lastRow = secondIndexRow;
            this.matrixOne = matrixOne;
            this.matrixTwo = matrixTwo;
            this.result = result;
            this.sizeRow = ParallelMatrix.getSizeRow();
            System.out.println(firstIndexRow +" "+secondIndexRow);

        }

        private int calcValue(int row, int column) {
            int sum = 0;
            for (int i = 0; i < sizeRow; i++)
                sum += matrixOne.getElement(row, i) * matrixTwo.getElement(i, column);
            return sum;
        }

        @Override
        public void run() {
            int sizeColumn = result.getSizeColumn();
            for (int row = firstRow; row <= lastRow; row++)
                for (int col = 0; col < sizeColumn; col++)
                    result.setElement(row, col, calcValue(row, col));
        }
    }
    private int numberThreads;

    ParallelMatrix( int numberOfThreads){

        numberThreads = numberOfThreads;
    }

    ///////////////
    public ParallelMatrix resultParallelMatrix (ParallelMatrix matrixOne, ParallelMatrix matrixTwo){
        int row = matrixOne.getSizeRow();
        int column = matrixTwo.getSizeColumn();
        ParallelMatrix result = new ParallelMatrix(row, column);
        if (numberThreads > row) {
            numberThreads = row;
        }

        int count = row / numberThreads;

        Thread[] threads = new Thread[numberThreads];
        int startThread = 0;
        for (int i = 0; i < numberThreads; i++) {
            int cnt = ((i == 0) ? count : count);
            threads[i] = new Multiply(startThread, startThread + cnt - 1, matrixOne, matrixTwo, result);
            startThread += cnt;
            threads[i].start();
            System.out.println("Запущен поток # " + i);
            System.out.println();

        }
        try {
            for (Thread thread : threads) {
                //System.out.println("Выполнен поток #"+thread);
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
        return result;
    }
    @Override
    public String toString() {
        StringBuilder matrixToString = new StringBuilder();
        for (int i = 0; i < getSizeRow(); i++) {
            matrixToString.append("[");
            for (int j = 0; j < getSizeColumn(); j++) {
                matrixToString.append(matrixParallel[i][j]);
                if (j != getSizeColumn() - 1) matrixToString.append(" ");
            }
            matrixToString.append("]\n");
        }
        return matrixToString.toString();
    }

    private static int[][] matrixParallel;

    public ParallelMatrix(int row, int column) {
        matrixParallel = new int[row][column];
    }

    public ParallelMatrix(int[][] result) {
        this.matrixParallel = result;
    }

    public void setElement(int row, int column, int value) {
        matrixParallel[row][column] = value;
    }

    public int getElement(int row, int column) {
        return matrixParallel[row][column];
    }

    public int getSizeColumn() {
        return matrixParallel[0].length;
    }

    public static int getSizeRow() {
        return matrixParallel.length;
    }

    public int[][] getMatrix() {
        return matrixParallel;
    }

    public void setMatrix(int[][] matrix) {
        this.matrixParallel = matrix;
    }

    }
        /*int[][] matrixA = new int[100][100];
            for (int i = 0; i < matrixA.length; i++) {
                for (int j = 0; j < matrixA[0].length; j++) {
                    if ((i + j) % 2 == 0) {
                        matrixA[i][j] = 66;
                }   else {
                        matrixA[i][j] = 88;
                }
            }
        }

        System.out.println("Matrix №1: ");
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                System.out.print(" [" + matrixA[i][j] + "] ");
            }
            System.out.println();
        }

        int[][] matrixB = new int[100][100];
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                if ((i + j) % 2 == 0) {
                    matrixB[i][j] = 66;
                } else {
                    matrixB[i][j] = 88;
                }
            }
        }

        System.out.println("Matrix №2: ");
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                System.out.print(" [" + matrixB[i][j] + "] ");
            }
            System.out.println();
        }


        *//*for (int i=0; i < 8; i++){
            MyThread thread=new MyThread();
            thread.start();
        }*//*

        //ArrayList<Task> threadArray=new ArrayList<Task>();

        int[][] matrixC = new int[matrixA.length][matrixB[0].length];
        for (int i = 0; i < matrixC[0].length; i++) {
            for (int j = 0; j < matrixC.length; j++) {
                for (int k = 0; k < matrixA[0].length; k++) {
                    matrixC[i][j] = (matrixC[i][j] + matrixA[i][k] * matrixB[k][j]);
                }
            }
        }
        long startTime = System.nanoTime();

        *//*MyThread t1=new MyThread();
        MyThread t2=new MyThread();*//**//*
        t1.start();
        try{
            t1.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("the end");*//*
         *//* new MyThread(1).start();
        new MyThread(2).start();
        new MyThread(3).start();
        new MyThread(4).start();
        new MyThread(5).start();
        new MyThread(6).start();
        new MyThread(7).start();
        new MyThread(8).start();*//*
        System.out.println("Matrix №3: ");
        for (int i = 0; i < matrixC.length; i++) {
            for (int j = 0; j < matrixC[0].length; j++) {
                System.out.print(" [" + matrixC[i][j] + "] ");
            }
            System.out.println();
        }

        long endTime = System.nanoTime() - startTime;
        System.out.println("Program execution TIME: " + endTime + " ms.");
    }*/
/*class MyThread extends Thread{
    int n;

    MyThread(int n){
        this.n=n;
    }
    @Override
    public void run(){
        System.out.println("The flow is executed " + getName());
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(getName() +" end" );
    }*/
