package edu.vt.cs5044;

public class main
{

    public static void main(String[] args)
    {

    }
    
    public int count7(int n)
    {
        if (n == 0) {
            return 0;
        }
        
        if ((n % 10) == 7){
            return 1 + count7(n / 10);
        }
        
        return count7(n / 10);
    }
    
    public int count8(int n)
    {
        if (n == 0) {
            return 0;
        }
        
        if ((n % 10) == 8){
            if (((n / 10) % 10) == 8) 
            {
                return 2 + count8(n / 10);
            }
            else 
            {
                return 1 + count8(n / 10);   
            }            
        }
    
        return count8(n / 10);     
    }
    
    public int countHi(String str)
    {
        if (str.length() < 2) {
            return 0;
        }
        
        if (str.endsWith("hi")) 
        {
            return 1 + countHi(str.substring(0, str.length() - 2));
        }
        
        return countHi(str.substring(0, str.length() - 1));
    }
    
    public String changeXY(String str)
    {
        if (str.length() < 1) {
            return "";
        }
        
        if (str.endsWith("x")) 
        {
            return changeXY(str.substring(0, str.length() - 1)) + "y";
        }
        
        return changeXY(str.substring(0, str.length() - 1)) + str.charAt(str.length() - 1);
    }
    
    public String changePi(String str)
    {
        if (str.length() < 1) {
            return "";
        }
        
        if (str.endsWith("pi")) 
        {
            return changePi(str.substring(0, str.length() - 2)) + "3.14";
        }
        
        return changePi(str.substring(0, str.length() - 1)) + str.charAt(str.length() - 1);
    }
    
    public boolean strCopies(String str, String sub, int n)
    {
        if (n == 0) {
            return true;
        }
        
        if (str.length() == 0) {
            return false;
        }
        
        if (str.endsWith(sub)) {
            return strCopies(str.substring(0, str.length() - 1), sub, n-1);
        }
        
        return strCopies(str.substring(0, str.length() - 1), sub, n);
    }
    
    public boolean nestParen(String str)
    {
        if (str.length() == 0) {
            return true;
        }
        
        if (str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')') {
            return nestParen(str.substring(1, str.length() - 1));
        }
        
        return false;
    }
	
    /**
		strDist("catcowcat", "cat") = 9
		strDist("catcowcat", "cow") = 3
		strDist("cccatcowcatxx", "cat") = 9
	*/
    public int strDist(String str, String sub)
    {
        if (str.length() == 0) {
            return 0;
        }
        
        if (str.startsWith(sub)) {
            if (str.endsWith(sub)) {
                return str.length();
            }
            
            return strDist(str.substring(0, str.length() - 1), sub);
        }
        
        return strDist(str.substring(1), sub);
    }
    
    public int array11(int[] nums, int index)
    {
        if (index == nums.length) {
            return 0;
        }
        
        if(nums[index] == 11) {
            return 1 + array11(nums, index + 1);
        }
        
        return array11(nums, index + 1);
    }
    
    public int powerN(int base, int n)
    {
        if (n == 0) {
            return 1;
        }
        
        return base * powerN(base, n-1);
    }

}
