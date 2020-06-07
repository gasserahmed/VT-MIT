package edu.vt.cs5044;

import java.util.ArrayList;

public class main
{

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }
    
    public int bigDiff(int[] nums)
    {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for(int num : nums)
        {
            if(num > max) 
            {
                max = num;
            }
            
            if(num <min) 
            {
                min = num;
            }
        }
        
        return max-min;
    }
    
    public String[] fizzArray2(int n)
    {
        String [] fizzArr = new String[n];
        
        for(int i = 0; i < fizzArr.length; i++) 
        {
            fizzArr[i] = String.valueOf(i);
        }
        
        return fizzArr;
    }
    
    public boolean lucky13(int[] nums)
    {        
        for(int i = 0; i < nums.length; i++) 
        {
            if(nums[i] == 1 || nums[i] == 3) 
            {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean sum28(int[] nums)
    {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) 
        {
            if(nums[i] == 2) 
            {
                sum += 2;
            }    
        }        
        
        if(sum != 8) 
        {
            return false;
        }
        
        return true;
    }
    
    public boolean more14(int[] nums)
    {
        int ones = 0;
        int fours = 0;
        
        for(int i = 0; i < nums.length; i++) 
        {
            if(nums[i] == 1) 
            {
                ones++;
            }
            else if(nums[i] == 4) 
            {
                fours++;
            }
        }
        
        if(ones <= fours) 
        {
            return false;
        }
        
        return true;
    }
    
    public String[] fizzBuzz(int start, int end)
    {
        int index = 0;
        int arrSize = end - start;
        String[] fizzBuzzArr = new String[arrSize];
        
        for(int i = start; i < end; i++) 
        {
            if(i % 3 == 0 && i % 5 == 0) 
            {
                fizzBuzzArr[index] = "FizzBuzz";
            }
            else if(i % 3 == 0) 
            {
                fizzBuzzArr[index] = "Fizz";
            }
            else if(i % 5 == 0) 
            {
                fizzBuzzArr[index] = "Buzz";
            }
            else 
            {
                fizzBuzzArr[index] = String.valueOf(i);
            }
            
            index++;
        }
        
        return fizzBuzzArr;
    }

    public boolean no14(int[] nums)
    {
        boolean oneExists = false;
        boolean fourExists = false;
        
        for(int i = 0; i < nums.length; i++) 
        {
            if (nums[i] == 1) 
            {
                oneExists = true;
            }
            else if (nums[i] == 4) 
            {
                fourExists = true;
            }
        }
        if(oneExists && fourExists) 
        {
            return false;
        }
        
        return true;
    }

    public boolean either24(int[] nums)
    {
        boolean twoTwoExists = false;
        boolean fourFourExists = false;
        
        for(int i = 0; i < nums.length; i++) 
        {
            if (i + 1 < nums.length && nums[i] == 2 && nums[i+1] == 2) 
            {
                twoTwoExists = true;
            }
            else if (i + 1 < nums.length && nums[i] == 4 && nums[i+1] == 4) 
            {
                fourFourExists = true;
            }
        }
        if (twoTwoExists && fourFourExists) 
        {
            return false;
        }
        else if (twoTwoExists || fourFourExists)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    public boolean has12(int[] nums)
    {
        boolean oneExists = false;
        boolean twoExists = false;
        
        for(int i = 0; i < nums.length; i++) 
        {
            if (nums[i] == 1) 
            {
                oneExists = true;
            }
            else if (nums[i] == 2 && oneExists) 
            {
                twoExists = true;
            }
        }
        if(oneExists && twoExists) 
        {
            return true;
        }
        
        return false;
    }
    
    public int[] fix34(int[] nums)
    {
        int[] threeFourArr = new int[nums.length];
        String temp = "";
        
        for (int i = 0; i < nums.length; i++) 
        {
            if (i>0 && nums[i-1] != 3 && nums[i] == 4 && !temp.isEmpty()) 
            {
                threeFourArr[i] = Integer.valueOf(temp);
            }
            else if (threeFourArr[i] != 4) 
            {
                threeFourArr[i] = nums[i];
            }
            
            if (nums[i] == 3 && nums[i+1] != 4) 
            {
                temp = String.valueOf(nums[i+1]);
                threeFourArr[i+1] = 4;
            }
        }
        
        return threeFourArr;
    }


}
