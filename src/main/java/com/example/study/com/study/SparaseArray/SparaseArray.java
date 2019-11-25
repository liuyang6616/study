package com.example.study.com.study.SparaseArray;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class SparaseArray {
    /**
     * 稀疏数组可以简单的看作是压缩，在开发中也会用到，比如将数据序列化到磁盘上，减少数据量，提高IO效率等
     */
    /**
     * 初始化二维数组
     *          0 0 0 0 0 0 0 0 0 0 0
     *          0 0 1 0 0 0 0 0 0 0 0
     *          0 0 0 0 2 0 0 0 0 0 0
     *          0 0 0 0 0 0 0 0 0 0 0
     *          0 0 0 0 0 0 0 0 0 0 0
     *          0 0 0 0 0 0 0 0 0 0 0
     *          0 0 0 0 0 0 0 0 0 0 0
     *          0 0 0 0 0 0 0 0 0 0 0
     *          0 0 0 0 0 0 0 0 0 0 0
     *          0 0 0 0 0 0 0 0 0 0 0
     *          0 0 0 0 0 0 0 0 0 0 0
     */

    public static void main(String[] args) throws IOException {
        //初始化二维数组，并给二维数组赋值
        int [][] secondArray = new int[11][11];
        secondArray[1][2] = 1;
        secondArray[2][4] = 2;

        //遍历二维数组
        for (int[] row : secondArray){
            for(int item : row){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }

        //初始化稀疏数组，可以确定稀疏数组有三列
        //遍历二维数组，获取有效的数据量
        int sum = 0;
        for(int[] row : secondArray){
            for(int item : row){
                if(item !=0){
                    sum++;
                }
            }
        }
        System.out.println(sum);

        //初始化稀疏数组
        int sparaseArray[][] = new int[sum+1][3];
        sparaseArray[0][0] = 11;
        sparaseArray[0][1] = 11;
        sparaseArray[0][2] = sum;
        int count = 0;
        for(int i=0;i<11;i++){
            for(int j = 0;j<11;j++){
                if(secondArray[i][j] !=0){
                    count ++;
                    sparaseArray[count][0] = i;
                    sparaseArray[count][1] = j;
                    sparaseArray[count][2] = secondArray[i][j];
                }
            }
        }

        //遍历稀疏数组
        System.out.println("遍历稀疏数组-》》》》》》》》》》》》》》》》》》》");
        for(int[] row : sparaseArray){
            for(int item : row){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }

        //将稀疏数组转化为二维数组
        System.out.println("将稀疏数组转化为二维数组-》》》》》》》》》》》》》》》");
        int [][] newSecondArray = new int[sparaseArray[0][0]][sparaseArray[0][1]];
        for(int i=1;i<sum+1;i++){
            newSecondArray[sparaseArray[i][0]][sparaseArray[i][1]] = sparaseArray[i][2];
        }

        System.out.println("遍历新的二维数组-》》》》》》》》》》》》》》》》");
        for(int [] row : newSecondArray){
            for(int item : row){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }

        //将稀疏数组存到磁盘中
        sparaseArrayToIo(sparaseArray);

        //从磁盘中读取稀疏数组
        int newSparaseArray[][] = sparaseArrayFromIo(3);
        //将稀疏数组转化为二维数组
        int newSecondArrays[][] = new int [newSparaseArray[0][0]][newSparaseArray[0][1]];
        for(int i=1;i<3;i++){
            newSecondArrays[newSparaseArray[i][0]][newSparaseArray[i][1]] = newSparaseArray[i][2];
        }
        System.out.println("遍历新的二维数组-》》》sssssss》》》》》》》》》》》》》》》");
        //遍历新的二维数组
        for(int [] row : newSecondArrays){
            for(int item : row){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }


    }

    /**
     * 将稀疏数组存到磁盘中
     */
    public static void sparaseArrayToIo(int [][] array) throws IOException {
        if(Objects.isNull(array)){
            return;
        }
        File file = new File("sparaseArray.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        for(int i=0;i<array.length;i++){
            for(int j=0;j<3;j++){
                writer.write(array[i][j]);
            }
        }
        writer.flush();
        writer.close();
    }

    public static int[][] sparaseArrayFromIo(int lineNums) throws IOException{
        FileReader fileReader = new FileReader("sparaseArray.txt");
        int getNum = 0;
        int[][] sparaseArray = new int[lineNums][3];
        for(int i=0;i<lineNums;i++){
            for(int j=0;j<3;j++){
                getNum = fileReader.read();
                sparaseArray[i][j] = getNum;
            }
        }
        return sparaseArray;
    }


}
