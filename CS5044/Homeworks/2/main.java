package edu.vt.cs5044;

public class main
{

    // Solutions for problems in Homework 2 at https://codeworkout.cs.vt.edu/courses/vt/5044/fall-2019/3621
    
    public static void main(String[] args)
    {
        
    }
    
    public int matchUp(int[] nums1, int[] nums2)
    {
        int count = 0;
        int diff = 0;
        for(int i = 0; i < nums1.length; i++) 
        {
            diff = Math.abs(nums2[i] - nums1[i]);
            if(diff<=2 && diff!=0) 
            {
                count++;
            }
        }
        return count;
    }

    public boolean modThree(int[] nums)
    {
        for(int i = 0; i < nums.length; i++) 
        {
            if(i+2<nums.length && 
                    ((nums[i]%2 == 0 && nums[i+1]%2 == 0 && nums[i+2]%2 == 0) 
                            || (nums[i]%2 != 0 && nums[i+1]%2 != 0 && nums[i+2]%2 != 0))) 
            {
                return true;
            }
        }
        return false;
    }

    public int[] tenRun(int[] nums)
    {
        int tenModNum = -1;
        for(int i = 0; i < nums.length; i++) 
        {
            if(nums[i]%10 == 0) 
            {
                tenModNum = nums[i];
            }
            else if(tenModNum != -1)
            {
                nums[i] = tenModNum;
            }
        }
        return nums;
    }
    
    public int sum3(int[] nums)
    {
        return nums[0] + nums[1] + nums[2];
    }

    public int[] plusTwo(int[] a, int[] b)
    {
        int[] newArr = {a[0],a[1],b[0],b[1]};
        return newArr;
    }

    public boolean isEverywhere(int[] nums, int val) //1 2 1 3 4, 1
    {
        for(int i = 0; i < nums.length; i++) 
        {
            System.out.println(nums[i]);
            if((i+1<nums.length && nums[i] != val && nums[i+1] != val)) 
            {
                return false;
            }
        }
        return true;
    }
    
    public int[] withoutTen(int[] nums)
    {
        int[] noTenArr = new int[nums.length];
        int noTenArrIndex = 0;
        for(int i = 0; i < nums.length; i++) 
        {
            if(nums[i]!=10) 
            {
                noTenArr[noTenArrIndex++] = nums[i];
            }
        }
        return noTenArr;
    }

    public int sum13(int[] nums)
    {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) 
        {
            if(nums[i]!=13) 
            {
                sum+=nums[i];
            }
            else 
            {
                i++;
            }
        }
        return sum;
    }
    
    public int countEvens(int[] nums)
    {
        int evenCount = 0;
        for(int i = 0; i < nums.length; i++) 
        {
            if(nums[i] % 2 == 0) 
            {
              evenCount++;
            }
        }
        return evenCount;
    }
    
    public int sum67(int[] nums)
    {
        int sum = 0;
        boolean found6 = false;
        for(int i = 0; i < nums.length; i++) 
        {
            if(nums[i] == 6) 
            {
                found6 = true;
            }
            else if(nums[i] == 7) 
            {
                if(found6) 
                {
                    found6 = false;    
                }
                else 
                {
                    sum += 7;
                }
                
            }
            else if(!found6) 
            {
              sum += nums[i];
            }
        }
        return sum;
    }
}