package com.HaackMicah.Program3;

import java.util.Random;

/**
 * 
 * @author Micah Haack
 *
 * Program 3 COMP222
 * This project deals with implementing a few types
 * of sorting algorithms, and analyzing the runtimes
 * for lists of various sizes.
 */
public class Program3MicahHaackMain {

	
	public static void main(String[] args) {
		
		
		// construct large int array
		int[] orgArr = new int[10000];
		Random rand = new Random();
		
		for( int i = 0; i < orgArr.length; i++ ) {
			
			orgArr[i] = rand.nextInt();
		}
		
		
	}
	
	/**
	 * TODO
	 * @param a
	 * @return
	 */
	public static int[] mergeSort(int[] a) {
		return null;
	}
	
	/**
	 * TODO
	 * @param a
	 * @return
	 */
	public static int[] heapSort(int[] a) {
		return null;
	}
	
	/**
	 * TODO
	 * @param a
	 * @return
	 */
	public static int[] insertionSort(int[] a) {
		return null;
	}
	
	/**
	 * TODO
	 * @param a
	 * @return
	 */
	public static int[] selectionSort(int[] a) {
		return null;
	}
	
	/**
	 * TODO
	 * @param a
	 * @return
	 */
	public static int[] bubbleSort(int[] a) {
		return null;
	}
}
