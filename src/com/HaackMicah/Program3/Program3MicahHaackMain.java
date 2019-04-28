package com.HaackMicah.Program3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

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

	private final static int NUM_RUNS = 10;
	
	public static void main(String[] args) {
		
		
		// construct large int array
		int[] orgArr = genRandArray(40000);
		
		System.out.println("--- NOW TESTING LARGE ARRAY ---");
		
		System.out.println("Array Sorted? (Before Sort): " + checkSort(orgArr));
		
		int[] mergeSortArr = mergeSort(orgArr);
		System.out.println("Array Sorted? (Merge): " + checkSort(mergeSortArr));
		
		int[] heapSortArr = heapSort(orgArr);
		System.out.println("Array Sorted? (Heap): " + checkSort(heapSortArr));
		
		int[] insertionSortArr = insertionSort(orgArr);
		System.out.println("Array Sorted? (Insertion): " + checkSort(insertionSortArr));
		
		int[] selectionSortArr = selectionSort(orgArr);
		System.out.println("Array Sorted? (Selection): " + checkSort(selectionSortArr));
		
		int[] bubbleSortArr = bubbleSort(orgArr);
		System.out.println("Array Sorted? (Bubble): " + checkSort(bubbleSortArr));
		
		System.out.println("---------------------------");
		
		System.out.println("--- NOW TESTING VARIOUS SIZE ARRAYS AND TIMING ---\n");
		
		testArray(10);
		testArray(100);
		testArray(500);
		testArray(1000);
		testArray(5000);
		testArray(10000);
		testArray(20000);
		
		System.out.println("\n---------------------------");
		
		
	}
	
	/**
	 * Helper method to automatically test the timing on an array of size size
	 * @param size The size of array to test
	 */
	public static void testArray(int size) {
		
		int[] arr = genRandArray(size);
		System.out.println("... Testing Array Size " + size + " ...");
		long[] times = timeSorts(arr);
		printResults(size, times);
		System.out.println("... Array Size " + size + " Testing Complete! ...");
		
	}
	
	
	/**
	 * Helper method to make printing time results easier.
	 * @param size The size of the array tested
	 * @param times The timing values
	 */
	public static void printResults(int size, long[] times) {
		
		System.out.printf("Array size %d results:\nMerge Time: %d ms\nHeap Time: %d ms\nInsertion Time: %d ms\nSelection Time: %d ms\nBubble Time: %d ms\n", size, times[0], times[1], times[2], times[3], times[4]);
		
	}
	
	/**
	 * Helper method to obtain a random array of any size
	 * @param size The size of the array desired
	 * @return An array of size size with random ints
	 */
	public static int[] genRandArray(int size) {
		
		int[] returnArr = new int[size];
		Random rand = new Random();
		
		for( int i = 0; i < returnArr.length; i++ ) {
			
			returnArr[i] = rand.nextInt(size);
		}
		
		return returnArr;
		
	}
	

	/**
	 * This method is used to find the averages for an array with each sorting method
	 * format of return is -> avg time of [ merge, heap, insertion, selection, bubble ]
	 * @param a The array to test on
	 * @return The average timing of a varitey of sorting methods
	 */
	public static long[] timeSorts(int[] a) {
		
		// MERGE
		long mergeTime = 0;
		
		for( int i = 0; i < NUM_RUNS; i++ ) {
			
			int[] copy = copyArray(a);
			
			long start = System.nanoTime();
			int[] newCopy = mergeSort(copy);
			long finish = System.nanoTime();
			long timeInMSec = (finish - start) / 1000;
			
			mergeTime += timeInMSec;
			
		}
		
		mergeTime /= NUM_RUNS;
		
		// HEAP
		long heapTime = 0;
		
		for( int i = 0; i < NUM_RUNS; i++ ) {
			
			int[] copy = copyArray(a);
			
			long start = System.nanoTime();
			int[] newCopy = heapSort(copy);
			long finish = System.nanoTime();
			long timeInMSec = (finish - start) / 1000;
			
			heapTime += timeInMSec;
			
		}
		
		heapTime /= NUM_RUNS;
		
		// INSERTION
		long insertionTime = 0;
		
		for( int i = 0; i < NUM_RUNS; i++ ) {
			
			int[] copy = copyArray(a);
			
			long start = System.nanoTime();
			int[] newCopy = insertionSort(copy);
			long finish = System.nanoTime();
			long timeInMSec = (finish - start) / 1000;
			
			insertionTime += timeInMSec;
			
		}
		
		insertionTime /= NUM_RUNS;
		
		// SELECTION
		long selectionTime = 0;
		
		for( int i = 0; i < NUM_RUNS; i++ ) {
			
			int[] copy = copyArray(a);
			
			long start = System.nanoTime();
			int[] newCopy = selectionSort(copy);
			long finish = System.nanoTime();
			long timeInMSec = (finish - start) / 1000;
			
			selectionTime += timeInMSec;
			
		}
		
		selectionTime /= NUM_RUNS;
		
		// BUBBLE
		long bubbleTime = 0;
		
		for( int i = 0; i < NUM_RUNS; i++ ) {
			
			int[] copy = copyArray(a);
			
			long start = System.nanoTime();
			int[] newCopy = bubbleSort(copy);
			long finish = System.nanoTime();
			long timeInMSec = (finish - start) / 1000;
			
			bubbleTime += timeInMSec;
			
		}
		
		bubbleTime /= NUM_RUNS;
		
		long[] returnArr = {mergeTime, heapTime, insertionTime, selectionTime, bubbleTime};
		return returnArr;
		
	}

	/**
	 * This method implements merge sort based on the textbook description
	 * @param a The array to (copy) then sort
	 * @return The sorted copy array
	 */
	public static int[] mergeSort(int[] a) {
		
		int[] copy = copyArray(a);
		
		mergeS(copy);
		
		return copy;
		
	}
	
	private static void mergeS(int[] a) {
		
		int n = a.length;
		
		// check trivial sort
		if( n < 2 )
			return;
		
		// divide
		int mid = n / 2;
		int[] s1 = Arrays.copyOfRange(a, 0, mid); // copy first half
		int[] s2 = Arrays.copyOfRange(a, mid, n); // copy second half
		
		// conquer
		mergeS(s1);
		mergeS(s2);
		
		// merge results
		merge(s1, s2, a);
		
		
	}
	
	
	/**
	 * This method implements heap sort based on the textbook description
	 * @param a The array to (copy) then sort
	 * @return The sorted copy array
	 */
	public static int[] heapSort(int[] a) {
		
		// Utilize a heap data structure
		// Implementation can be seen in the HeapPriorityQueue class and is based on the textbook example
		HeapPriorityQueue<Integer, Integer> heap = new HeapPriorityQueue<>();
		
		int[] copy = copyArray(a);
		
		// add all the values into the heap
		for( int i = 0; i < copy.length; i++ ) {
			heap.insert(copy[i], i);
		}
		
		// remove the min values and replace the copy vals with it
		for( int i = 0; i < copy.length; i++ ) {
			
			copy[i] = heap.removeMin().getKey();
		}
		
		return copy;
	}
	
	/**
	 * This method implements insertionSort based on the textbook description
	 * @param a The array to (copy) then sort
	 * @return The sorted copy array
	 */
	public static int[] insertionSort(int[] a) {
		
		// Utilize a sorted list implementation of priority queue
		// Implementation can bee seen in the SortedPriorityQueue class and is based on the textbook example
		SortedPriorityQueue<Integer, Integer> spq = new SortedPriorityQueue<>();
		
		int[] copy = copyArray(a);
		
		// add all the values into the spq
		for( int i = 0; i < copy.length; i++ ) {
			spq.insert(copy[i], i);
		}
		
		// remove the min vals and replace copy vals with it
		for( int i = 0; i < copy.length; i++ ) {
			copy[i] = spq.removeMin().getKey();
		}
		
		return copy;
		
		
	}
	
	/**
	 * This method implements selectionSort based on the textbook description
	 * @param a The array to (copy) then sort
	 * @return The sorted copy array
	 */
	public static int[] selectionSort(int[] a) {
		
		// Utilize a unsorted list implementation of priority queue
		// Implementation can bee seen in the UnsortedPriorityQueue class and is based on the textbook example
		UnsortedPriorityQueue<Integer, Integer> spq = new UnsortedPriorityQueue<>();
		
		int[] copy = copyArray(a);
		
		// add all the values into the spq
		for( int i = 0; i < copy.length; i++ ) {
			spq.insert(copy[i], i);
		}
		
		// remove the min vals and replace copy vals with it
		for( int i = 0; i < copy.length; i++ ) {
			copy[i] = spq.removeMin().getKey();
		}
		
		return copy;
	}
	
	/**
	 * This method implements bubbleSort based on the textbook description
	 * @param a The array to (copy) then sort
	 * @return The sorted copy array
	 */
	public static int[] bubbleSort(int[] a) {
		
		// create copy array
		int[] copy = copyArray(a);
		
		for( int i = 0; i < copy.length - 1; i++ ) {
			
			for( int j = 0; j < copy.length - i - 1; j++ ) {
				if( copy[j] > copy[j+1] ) {
					int temp = copy[j];
					copy[j] = copy[j+1];
					copy[j+1] = temp;
					
				}
			}
			
		}
		
		return copy;
		
		
	}
	
	/**
	 * This method returns a copy of a
	 * @param a The array to copy
	 * @return A copy of a
	 */
	private static int[] copyArray(int[] a) {
		
		int[] copy = new int[a.length];
		for( int i = 0; i < a.length; i++ )
			copy[i] = a[i];
		return copy;
		
	}
	
	/**
	 * Helper method to merge two seperate arrays, s1 and s2, into an array of proper size S based on the Comparator
	 * @param s1 one array to merge
	 * @param s2 second array to merge
	 * @param S the array to merge into
	 */
	private static void merge(int[] s1, int[] s2, int[] S) {
		
		int i = 0;
		int j = 0;
		
		while( i + j < S.length ) {
			
			if( j == s2.length || (i < s1.length && s1[i] < s2[j]) )
				S[i+j] = s1[i++];
			else
				S[i+j] = s2[j++];
			
		}
		
		
	}
	
	
	/**
	 * This is a helper method to determine if the array is sorted properly
	 * @param a The array to check proper sort
	 * @return true if the array is sorted, false otherwise
	 */
	private static boolean checkSort(int[] a) {
		
		for( int i = 0; i < a.length - 1; i++ ) {
			
			if( a[i] > a[i+1] )
				return false;
			
		}
		
		return true;
		
	}
	
	
}
