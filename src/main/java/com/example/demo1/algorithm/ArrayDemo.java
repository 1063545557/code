package com.example.demo1.algorithm;

public class ArrayDemo {
    public static void main(String[] args) {

    }
    //数组中重复的数字
    //在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
    public boolean duplicate(int[] nums,int length,int[] duplication){
        if (nums==null||length<=0)
            return false;
        for (int i=0;i<length;i++){
            while(nums[i]!=i){
                if (nums[i]==nums[nums[i]]){
                    duplication[0]=nums[i];
                    return true;
                }
                swap(nums,i,nums[i]);
            }
        }
        return false;
    }
    public void swap(int[] nums,int i,int j){
        int t=nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }

    //二维数组中的查找
    //给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中。
    int[][] matrix=new int[][]{
            {1,4,7,11,15},
            {2,5,8,12,19},
            {3,6,9,16,22},
            {10,13,14,17,24},
            {18,21,23,26,30}};
    public boolean Find(int[][] matrix,int target){
        if (matrix==null||matrix.length==0||matrix[0].length==0)
            return false;
        int rows=matrix.length,cols=matrix[0].length;
        int r=0,c=cols-1;
        while(r<=rows-1&&c>=0){
            if (target==matrix[r][c])
                return true;
            else if (target>matrix[r][c])
                r++;
            else
                c--;
        }
        return false;
    }
}
