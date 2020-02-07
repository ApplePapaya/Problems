package com.run.leetcode;

public class GCDOfStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 public String gcdOfStrings(String str1, String str2) {
	        
	        int len1 = str1.length();
	        int len2 = str2.length();
	        
	        int gcd = computeGcd(len1, len2);
	        
	        String res = str1.substring(0,gcd);
	        
	        String str1Generated = generateString(res, len1/gcd);
	        String str2Generated = generateString(res, len2/gcd);
	        
	        if(str1.equals(str1Generated) && str2.equals(str2Generated))
	            return res;
	        
	        return "";
	        
	    }
	    
	    public String generateString(String res, int times){
	        String str = "";
	        for(int i =0;i< times ;i++){
	            str+= res;
	        }
	        return str;
	    }
	    
	    public int computeGcd(int a, int b){
	        if(b==0)
	        {
	            return a;
	        }
	        return computeGcd(b, a%b);
	    }
}
