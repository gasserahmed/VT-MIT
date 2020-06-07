package edu.vt.cs5044;

public class main
{

    // Solutions for problems in Homework 1 at https://codeworkout.cs.vt.edu/courses/vt/5044/fall-2019/3605
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }

    public int caughtSpeeding(int speed, boolean isBirthday)
    {
        if (isBirthday)
        {
            if (speed >= 86)
            {
                return 2;
            }
            else if (speed >= 66 && speed < 86)
            {
                return 1;
            }

            else
            {
                return 0;
            }
        }
        if (speed >= 81)
        {
            return 2;
        }
        else if (speed >= 61 && speed < 81)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public boolean makeBricks(int small, int big, int goal)
    {
        if ((small == goal || (big * 5) == goal || (small + big * 5) == goal))

        {

            return true;
        }

        else if (big * 5 > goal && big < 100000)

        {

            return makeBricks(small, big - 1, goal);
        }

        else if (small > 0 && big == 0)

        {

            return makeBricks(small - 1, big, goal);
        }

        else if (small == 0 || big == 0)

        {

            return false;

        }
        else

        {

            return makeBricks(small - 1, big, goal);

        }
    }

    public int noTeenSum(int a, int b, int c)
    {
        return fixTeen(a) + fixTeen(b) + fixTeen(c);

    }

    public int fixTeen(int n)
    {
        if (n >= 13 && n <= 19 && n != 15 && n != 16)
        {
            return 0;
        }
        return n;
    }

    public boolean evenlySpaced(int a, int b, int c)
    {
        int small = 0, medium = 0, large = 0;
        if (a >= b && a >= c)
        {
            large = a;
            if (b > c)
            {
                medium = b;
                small = c;
            }
            else
            {
                medium = c;
                small = b;
            }
        }
        else if (b >= a && b >= c)
        {
            large = b;
            if (c > a)
            {
                medium = c;
                small = a;
            }
            else
            {
                medium = a;
                small = c;
            }
        }
        else if (c >= a && c >= b)
        {
            large = c;
            if (b > a)
            {
                medium = b;
                small = a;
            }
            else
            {
                medium = a;
                small = b;
            }
        }
        return ((large - medium) == (medium - small));
    }

    public String atFirst(String str)
    {
        if (str.length() >= 2)
        {
            return str.substring(0, 2);
        }
        else if (str.length() == 1)
        {
            return str + '@';
        }
        return "@@";
    }
    
    public String repeatEnd(String str, int n)
    {
        String endStr = str.substring(str.length()-n); 
        String finalStr = "";
        for(int i=0 ; i<n ; i++) 
        {
            finalStr += endStr;
        }
        return finalStr;
    }
    
    public String zipZap(String str)
    {
        String finalStr = "";
        for(int i = 0; i<str.length(); i++) 
        {
            if((i+2) < str.length() && str.charAt(i) == 'z' && str.charAt(i+2) == 'p') 
            {
                finalStr += "zp";
                i += 2;
            }
            else 
            {
                finalStr += str.charAt(i);
            }
        }
        
        return finalStr;
    }
    
    public int countCode(String str)
    {
        int codeCount = 0;
        for(int i = 0; i<str.length(); i++) 
        {
            if((i+3) < str.length() && str.charAt(i) == 'c' && str.charAt(i+1) == 'o' && str.charAt(i+3) == 'e') 
            {
                codeCount++;
                i += 3;
            }
        }
        
        return codeCount;
    }
    
    public boolean endOther(String a, String b)
    {
        if(a.toLowerCase().endsWith(b.toLowerCase()) || b.toLowerCase().endsWith(a.toLowerCase())) 
        {
            return true;
        }
        return false;
    }

    public boolean xyBalance(String str)
    {
        boolean balancedX = true;
        for(int i = 0; i<str.length(); i++) 
        {
            if(str.charAt(i) == 'x') 
            {
                balancedX = false;                       
            }
            
            if(str.charAt(i) == 'y') 
            {
                balancedX = true;
            }
        }
        return balancedX;
    }

}
