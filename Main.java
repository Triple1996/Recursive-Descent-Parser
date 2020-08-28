/*
 * Recursive Descent Parsing
 * Adam Awany
 * NJIT ID: 31267624
 * New Jersey Institute of Technology
 * Prof. Jonathan Kapleau
 * CS280 - sec. 141
 * */



/*
 * <ASS> -> <IDENT> = <EXPR>
 * <EXPR> -> <PROD> <OPER> <PROD> | <PROD>
 * <OPER> -> + | - | * | / | **
 * <PROD> -> <IDENT> | <LITERAL> | <UNARY> <IDENT> | <UNARY> <LITERAL> | ( <EXPR> )
 * <UNARY> -> + | - | !
 * <IDENT> -> <CHAR> | <CHAR> <IDENT>
 * <CHAR> -> a | b | ... | y | z
 * <LITERAL> -> <DIGIT> | <DIGIT> <LITERAL>
 * <DIGIT> -> 0 | 1 | ... | 8 | 9
 * */

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main{
	
	private static String s = null;
	private static int i = 0;
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));	//prints working directory
		File input = new File("input.txt");
		File out = new File("output.txt");				

		/* try (Scanner scan = new Scanner(input); PrintWriter pw = new PrintWriter(new FileWriter(out, true))) {
		
			// while more input
			while (scan.hasNext()) {
				// give s the next line
				s = scan.next();
				
				//if assignment, print so
				if (asgn() && i == s.length()) {
					pw.println("The string \"" + s + "\" is in the language.");
				}
				else {
					pw.println("The string \"" + s + "\" is NOT in the language.");
				}
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} */
		
		s = "a=(a+b)*(c^d)";
		if (asgn() && i == s.length()) {
			System.out.println("The string \"" + s + "\" is in the language.");
		}
		else {
			System.out.println("The string \"" + s + "\" is NOT in the language.");
		}
		
	}		
	// COMPLETE
	private static boolean asgn() {
		if (ident()) {
			if (i < s.length() && s.charAt(i) == '=') {
				++i;
				if (expr()) {
					return true;	//	<IDENT> = <EXPR>
				}
				return false;	// <IDENT> = 
			}
			return false;	// <IDENT>
		}
		return false;	// Something else
	}
	
	// COMPLETE
	private static boolean expr() {
		if (prod()) {
			if (oper()) {
				if (prod()) {
					return true;	// <PROD> <OPER> <PROD>
				}
				return false;	// <PROD> <OPER>
			}
			return true;	// <PROD>
		}
		return false;	//Something else
	}
	
	// COMPLETE
	private static boolean oper() {
		if (i < s.length() && s.charAt(i) == '+') {
			++i;
			return true;	// '+'
		}
		else if (i < s.length() && s.charAt(i) == '-') {
			++i;
			return true;	// '-'
		}
		else if (i < s.length() && s.charAt(i) == '*') {
			++i;
			if (i < s.length() && s.charAt(i) =='*') {
				++i;
				return true;	// '**'
			}
			else {
				return true;	// '*'
			}
		}
		else if (s.charAt(i) == '/') {
			++i;
			return true;	// '/'
		}
		return false;	// Something else
	}
	
	// COMPLETE
	private static boolean prod() {
		if (ident()) {
			return true;	//<IDENT>
		}
		else if(literal()) {
			return true;	//<LITERAL>
		}
		else if (unary()) {
			if(ident()) {
				return true;	// <UNARY> <IDENT>
			}
			else if (literal()) {
				return true;	// <UNARY> <LITERAL>
			}
			return false;	// <UNARY>
		}
		else if (i < s.length() && s.charAt(i) == '(') {
			++i;
			if (expr()) {
				if (i < s.length() && s.charAt(i) == ')') {
					++i;
					return true;	// ( < EXPR> )
				}
				return false;	// ( <EXPR>
			}
			return false;	// (
		}
		return false;	// Something else
	}
	
	// COMPLETE
	private static boolean unary() {
		if (i < s.length() && s.charAt(i) == '+') {
			++i;
			return true;	// '+'
		}
		else if (i < s.length() && s.charAt(i) == '-') {
			++i;
			return true;	// '='
		}
		else if (i < s.length() && s.charAt(i) == '!') {
			++i;
			return true;	// '!'
		}
		else {
			return false;	// Something else
		}
	}
	
	// COMPLETE
	private static boolean ident() {
		if (letter()) {
			if(ident()) {
				return true;	// <LETTER> <IDENT>
			}
			return true;	// <LETTER>
			
		}
		return false;	// Something else
	}
	
	// COMPLETE
	private static boolean letter() {
		if (i < s.length() && s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
			++i;
			return true;	// a-z
		}
		return false;	// Something else
	}
	
	// COMPLETE
	private static boolean literal() {
		if (digit()) {
			if(literal()) {
				return true;	// <DIGIT> <LITERAL>
			}
			return true; // <DIGIT>
			
		}
		return false;	// Something else
	}
	
	// COMPLETE
	private static boolean digit() {
		if (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
			++i;
			return true;	// 0-9
		}
		return false;	// Something else
	}
}






