package com.run.leetcode.palindrome;

public class IsValidPalindrome {

	public static void main(String[] args) {
		System.out.println(isValidPalindrome("Red rum, sir, is murder"));
		
		System.out.println(isValidPalindrome("Red rum, sir, is murder12312"));
		
System.out.println(isValidPalindromeWhileInsideWhile("Red rum, sir, is murder"));
		
		System.out.println(isValidPalindromeWhileInsideWhile("Red rum, sir, is murder12312"));

	}

	public static boolean isValidPalindrome(String s) {
		//Valid palindrome is chars are mirror image and can be read backwards. However we need to ignore the
		// spaces and commas and other characters.
		
		if(s== null || s.length() <2) {
			return false;// based on the requirement
		}
		s= s.toLowerCase();
		int i =0;
		int j = s.length() -1 ;
		while(i< j) {
			char si = s.charAt(i);
			char sj = s.charAt(j);
			boolean toContinue = false;
			// Check if the si char is non alphanumeric character.
			if(!((si>='a' && si<='z') || (si>='0' && si <='9'))) {
				i++;
				toContinue = true;
			}
			
			if(!((sj >='a' && sj <='z')||(sj>='0' && sj<='9'))) {
				j--;
				toContinue = true;
			}
			if(toContinue)
				continue;
			
			if(si != sj)
				return false;
			
			i++;
			j--;
			
		}
		return true;
	}
	
	// While inside while loop better solution
	
	public static boolean isValidPalindromeWhileInsideWhile(String s) {
		if(s==null || s.length()<2) {
			return false;
		}
		
		int i =0;
		int j = s.length()-1;
		s = s.toLowerCase();
		
		while(i<j) {
			
			while(i<j && !(( s.charAt(i) >='a' && s.charAt(i) <='z')||(s.charAt(i) >='0' && s.charAt(i) <='9')))
				i++;
			
			while(i<j && !(( s.charAt(j) >='a' && s.charAt(j) <='z')||(s.charAt(j) >='0' && s.charAt(j) <='9')))
				j--;
			
			if(s.charAt(i) != s.charAt(j))
				return false;
			
			i++;
			j--;
		}
		return true;
	}
	// This solutioni s not right since space is extra o(n)
	  public boolean isPalindrome(String s) {
	        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
	        String rev = new StringBuffer(actual).reverse().toString();
	        return actual.equals(rev);
	    }
	  
	  
	  public boolean isPalindromeUsingCharBuiltinFunc(String s) {
	        char[] c = s.toCharArray();
	        for (int i = 0, j = c.length - 1; i < j; ) {
	            if (!Character.isLetterOrDigit(c[i])) i++;
	            else if (!Character.isLetterOrDigit(c[j])) j--;
	            else if (Character.toLowerCase(c[i++]) != Character.toLowerCase(c[j--])) 
	                return false;
	        }
	        return true;
	    }
}
