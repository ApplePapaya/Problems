package com.run.hackerrank;

public class InterviewingIO {
	import java.io.*;
	import java.util.*;

	/*
	function int[]/list grep(string haystack, string needle)
	haystack = "aaabcdddbbddddabcdefghi"
	needle = "abc"
	[2,14]

	"abc".equals("abc")
	345345 == 345345

	"abc" => (int)'a'+ (int)'b'


	"38574656345345"
	"385" => 3*10**2 + 8*10**1 + 5*10**0 = 385

	Rabin Karp
	 */

	class Solution {
	  public static void main(String[] args) {
	    
	    System.out.println(grep("aaaaa" ,"aaa" ));

	  }
	  
	  public static List<Integer> grep(String h, String n){
	    List<Integer> result = new ArrayList<>();
	  
	    if(h==null || n== null || n.length() <1 ){
	    
	      return result;
	    }
	    
	    if(n.length() > h.length()){
	    
	      return result;
	    }
	  
	    
	    for(int i =0;i< h.length()- n.length() + 1 ;i++){
	     
	      if(n.charAt(0)  == h.charAt(i)){
	      
	        int right =1;
	        
	        while( right< n.length()){
	          
	          if(n.charAt(right) == h.charAt(i+right)){
	          
	          right++;
	            
	          }else{
	            break;
	          
	          }
	          if(right == n.length())
	             {
	               result.add(i);
	              
	             }
	          
	      }//whike ending
	             
	             
	      }//if ending
	      
	    }//for loop endiung
	  
	             return result;
	  }
	}
}
