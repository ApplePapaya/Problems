package com.run.leetcode;
//https://leetcode.com/problems/zigzag-conversion/discuss/3403/Easy-to-understand-Java-solution
public class ZigZagConversion {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(convert("PAYPALISHIRING", 3));
	}
	 public static String convert(String s, int rows) {
	      int n = s.length();
	        StringBuilder[] sb = new StringBuilder[rows];
	        for(int i=0; i<rows ;i++){
	            sb[i] = new StringBuilder();
	        }
	        int i = 0;
	        
	        //COnstructing the zigzag by going vertically down in the first loop and then 
	        //obliquely from left bottom to right top
	        while(i<n){
	            for(int j=0; i<n && j<rows; j++){
	                sb[j].append(s.charAt(i));
	                i++;
	            }
	            for(int k=rows-2; i<n && k>0; k--){
	                sb[k].append(s.charAt(i));
	                i++;
	            }
	        }
	      
	        for(int m=1; m<rows; m++){
	        	  System.out.println(sb[0]);
	            sb[0].append(sb[m]);
	        }
	        
	        return sb[0].toString();
	        
	    }

}
