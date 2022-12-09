package com.vtmw.algorithms;

//Resolves the N-Queens problem
public class NQueens {
	
	// WARNING : first index is 1 and not 0
	
	// Initially full of 0, then set to the corresponding column index i
	// in order to tell that the row at index k has a queen at column index i
    static int[] arr;
    static int counter; // numbers of possible arrangements
 
    static void nQueens(int k, int n) {
        for (int i = 1; i <= n; i++) { // iterates on the number of columns
            if (canBePlaced(k, i)) {
                arr[k] = i;
                if (k == n){ // indicates we found a solution
                	display(n);
                }
                else {
                    nQueens(k + 1, n); // analyzes for the next row
                }
            }
        }
    }

    // Checks that the queen to place at (k,i) will not be on the same column or diagonal of a previously placed queen
    static boolean canBePlaced(int k, int i) {
        for (int j = 1; j <= k - 1; j++) {
            if (arr[j] == i || (Math.abs(arr[j] - i) == Math.abs(j - k))) {
                return false;
            }
        }
        return true;
    }

    
    // Display method
    static void display(int n)
    {
        System.out.println("Arrangement No. " + ++counter);
 
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i] != j) {
                    System.out.print("\t.");
                }
                else {
                    System.out.print("\tQ");
                }
            }
            System.out.println("");
        }
 
        System.out.println("---------------------------------");
    }
 
    
    public static void main(String[] args) {
        int n = 4;
        arr = new int[n+1];
        nQueens(1, n);
    }

}