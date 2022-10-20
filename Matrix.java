package com.company;

public class Matrix {
    public static void main(String[] args) {
        int sizeOne = 1000;
        int sizeTwo = 1000;
        // создание первой матрицы
        Matrix matrixOne = new Matrix(sizeOne, sizeTwo);
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
//        System.out.println(matrixOne);

        // создание второй матрицы
        Matrix matrixTwo = new Matrix(sizeTwo, sizeOne);
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
//        System.out.println(matrixTwo);
        // обычное умножение
        long start = System.currentTimeMillis();
        Matrix.resultMatrixMultiplication(matrixOne, matrixTwo);
        long end = System.currentTimeMillis();
        float time = (float) (end - start);
        System.out.println("Matrix #3: ");
      //  System.out.println(Matrix.resultMatrixMultiplication(matrixOne, matrixTwo));
        System.out.println("Обычное умножение матриц: " + time + "ms");
    }
    public static Matrix resultMatrixMultiplication(Matrix matrixOne, Matrix matrixTwo) {

        int result[][] = new int[matrixOne.getSizeRow()][matrixTwo.getSizeColumn()];

        for (int i = 0; i < matrixOne.getSizeRow(); i++) {
            for (int j = 0; j < matrixTwo.getSizeColumn(); j++) {
                for (int k = 0; k < matrixOne.getSizeRow(); k++) {

                    result[i][j] = (result[i][j] + matrixOne.getMatrix()[i][k] * matrixTwo.getMatrix()[k][j]);
                }
            }
        }
        return new Matrix(result);
    }

    private static int[][] matrix;

    public Matrix(int row, int column) {
        matrix = new int[row][column];
    }

    public Matrix(int[][] result) {
        this.matrix = result;
    }

    public void setElement(int row, int column, int value) {
        matrix[row][column] = value;
    }

    public int getElement(int row, int column) {
        return matrix[row][column];
    }

    public int getSizeColumn() {
        return matrix[0].length;
    }

    public static int getSizeRow() {
        return matrix.length;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        StringBuilder matrixToString = new StringBuilder();
        for (int i = 0; i < getSizeRow(); i++) {
            matrixToString.append("[");
            for (int j = 0; j < getSizeColumn(); j++) {
                matrixToString.append(matrix[i][j]);
                if (j != getSizeColumn() - 1) matrixToString.append(" ");
            }
            matrixToString.append("]\n");
        }
        return matrixToString.toString();
    }
}
        /*int[][] matrixA = new int[100][100];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                if ((i + j) % 2 == 0) {
                    matrixA[i][j] = 66;
                } else {
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
                    matrixB[i][j] = 88;
                } else {
                    matrixB[i][j] = 66;
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

        long startTime = System.nanoTime();
        int[][] matrixC = new int[matrixA.length][matrixB[0].length];
        for (int i = 0; i < matrixC[0].length; i++) {
            for (int j = 0; j < matrixC.length; j++) {
                for (int k = 0; k < matrixA[0].length; k++) {
                    matrixC[i][j] = (matrixC[i][j] + matrixA[i][k] * matrixB[k][j]);
                }
            }
        }

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
/*int[][] matrixC = new int[matrixA.length][matrixB[0].length];
        for (int i = 0; i < matrixC[0].length; i++) {
        for (int j = 0; j < matrixC.length; j++) {
            for (int k = 0; k < matrixA[0].length; k++) {
                matrixC[i][j] = (matrixC[i][j] + matrixA[i][k] * matrixB[k][j]);
            }
        }
    }*/