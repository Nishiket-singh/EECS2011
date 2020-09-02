import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Assignment1{
  
  public int[][] denseMatrixMult(int[][] A, int[][] B, int size)	{
  		int [][] R = new int[size][size];

     	if(size == 1){

     		return new int [][] {

               {A[0][0] * B[0][0]}

            };

     	}

     	int halfSize= size/2;

     	//Empty matrix used for easy copying.

     	int [][] zeroMatrix = initMatrix(halfSize);


     	int [][] m0_first_part  = sum(A,A,0,0,halfSize,halfSize,halfSize);

     	int [][] m0_second_part = sum(B,B,0,0,halfSize,halfSize,halfSize);

     	int [][] m0 = denseMatrixMult(m0_first_part,m0_second_part,halfSize);
     	  

     	int [][] m1_first_part = sum(A,A,halfSize,0,halfSize,halfSize,halfSize);

     	int [][] m1_second_part = sum(zeroMatrix,B,0,0,0,0,halfSize);

     	int [][] m1 = denseMatrixMult(m1_first_part,m1_second_part,halfSize);
         

     	int [][] m2_first_part = sum(zeroMatrix, A, 0, 0, 0, 0, halfSize);

     	int [][] m2_second_part= sub(B, B, 0, halfSize, halfSize, halfSize, halfSize);

     	int [] [] m2= denseMatrixMult(m2_first_part, m2_second_part, halfSize);
         

     	int [] [] m3_first_part = sum(zeroMatrix, A, 0, 0, halfSize, halfSize, halfSize);

     	int [] [] m3_second_part = sub(B,B, halfSize, 0, 0, 0, halfSize);

     	int [] [] m3= denseMatrixMult(m3_first_part, m3_second_part, halfSize);
         

     	int [] [] m4_first_part = sum(A,A, 0,0, 0,halfSize, halfSize);

     	int [] [] m4_second_part= sum(zeroMatrix,B, 0,0, halfSize,halfSize,halfSize);

     	int [] [] m4= denseMatrixMult(m4_first_part, m4_second_part, halfSize);
         

     	int [] [] m5_first_part = sub(A,A,halfSize, 0, 0,0,halfSize);

     	int [] [] m5_second_part=sum(B,B, 0,0,0, halfSize, halfSize);

     	int [] [] m5= denseMatrixMult(m5_first_part, m5_second_part, halfSize);


     	int [] [] m6_first_part= sub(A,A,0, halfSize, halfSize, halfSize, halfSize);

     	int [] [] m6_second_part= sum(B,B, halfSize, 0, halfSize, halfSize, halfSize);

     	int [] [] m6= denseMatrixMult(m6_first_part, m6_second_part, halfSize);
 

     	int [] [] first_top_leftC= sum(m0, m6, 0, 0, 0, 0, halfSize);

     	int [] [] second_top_leftC= sub(m4, m3, 0, 0, 0, 0, halfSize);

     	int [] [] top_leftC= sub(first_top_leftC, second_top_leftC, 0, 0, 0, 0, halfSize);

     	int [] [] top_rightC= sum(m2, m4, 0, 0, 0, 0, halfSize);

     	int [] [] bottom_leftC= sum(m1, m3, 0, 0, 0, 0, halfSize); 

    	int [] [] first_bottom_rightC= sum(m2, m5, 0, 0, 0, 0, halfSize);

     	int [] [] second_bottom_rightC= sub(m0, m1, 0, 0, 0, 0, halfSize);

     	int [] [] bottom_rightC= sum(first_bottom_rightC, second_bottom_rightC, 0, 0, 0, 0, halfSize);

       
      
       for(int i1 = 0, i2 = 0; i1 < size/2; i1++, i2++) {
           for(int j1 = 0, j2 = 0; j1 < size/2; j1++, j2++) {
               R[i2][j2] = top_leftC[i1][j1];
           }
       }
       for(int i1 = 0, i2 = 0; i1 < size/2; i1++, i2++) {
           for(int j1 = 0, j2 = size/2; j1 < size/2; j1++, j2++) {
               R[i2][j2] = top_rightC[i1][j1];
           }
       }
       for(int i1 = 0, i2 = size/2; i1 < size/2; i1++, i2++) {
           for(int j1 = 0, j2 = 0; j1 < size/2; j1++, j2++) {
               R[i2][j2] = bottom_leftC[i1][j1];
           }
       }
       for(int i1 = 0, i2 = size/2; i1 < size/2; i1++, i2++) {
           for(int j1 = 0, j2 = size/2; j1 < size/2; j1++, j2++) {
               R[i2][j2] = bottom_rightC[i1][j1];
           }
       }
    
       return R;
  }
  
  public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)	{
	  int[][] sum = new int[n][n];
	  for(int i = 0; i < n; i++) {
		  int yA = y1;
		  int yB = y2;
		  for(int j = 0; j < n; j++) {
			  sum[i][j] = A[x1][yA] + B[x2][yB];
			  yA++;
			  yB++;
		  }
		  x1++;
		  x2++;
	  }
	  return sum;
  }
  
  public int[][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)	{
	  int[][] sub = new int[n][n];

	  for(int i = 0; i < n; i++) {
		  int yA = y1;
		  int yB = y2;
		  for(int j = 0; j < n; j++) {
			  sub[i][j] = A[x1][yA] - B[x2][yB];
			  yA++;
			  yB++;
		  }
		  x1++;
		  x2++;
	  }
	  return sub;
  }
  
  
  public int[][] initMatrix(int n)	{
	  int[][] A = new int[n][n];
	  for(int i = 0; i < n; i++) {
		  for(int j = 0; j < n; j++) {
			  A[i][j] = 0;
		  }
	  }
	  return A;
  }
  
  public void printMatrix(int n, int[][] A)	{
	  for(int i = 0; i < n; i++) {
		  for(int j = 0; j < n; j++) {
			  System.out.print(A[i][j] + " ");
		  }
		  System.out.println("");
	  }
  }
  
  public int[][] readMatrix(String filename, int n) throws Exception	{
	  int[][] A = new int[n][n];
	  Scanner file = new Scanner(new BufferedReader(new FileReader(filename)));
	  while(file.hasNextLine()) {
		  for(int i = 0; i < n; i++) {
			String[] line = file.nextLine().trim().split(" ");
            for (int j=0; j<line.length; j++) {
               A[i][j] = Integer.parseInt(line[j]);
            }
		  }
	  }
	  file.close();
	  return A;
  }
  
}