package main;


public class Algos {
	public static int binarySearch(int[] arr, int waldo) {
		int start = 0;
		int end = arr.length-1;
		int mid = 0;
		
		while (end >= start) {
			 mid = (int)(start + end)/2;
			 
			 if (arr[mid] == waldo) {
				 return mid;
			 }
			 
			 if (arr[mid] < waldo) {
				 start = mid + 1;
			 } else {
				 end = mid - 1;
			 }
		}
		return -1;
	}
	
	public static int  bubbleSort(int[] arr) {
		boolean hasSwaps = false;
		int scans = 0;
		
		do {
			hasSwaps = false;
			for (int i = 0; i < arr.length - scans - 1; i++) {
				if (arr[i] > arr[i+1]) {
					hasSwaps = true;
					
					swap(arr, i, i+1);
				}
			}
			scans++;
		} while (hasSwaps);
		
		return scans;
	}
	
	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++ ) {
			int minSpot = i;
			for (int j = i + 1; j< arr.length; j++) {
				if (arr[j] < arr[minSpot]) {
					minSpot = j;
				}
			}
			swap(arr, i, minSpot);
		}
	}
	
	public static void insertionSort(int[] arr) {
		for (int i = 1; i<arr.length; i++) {
			int temp = arr[i];
			int j = i-1;
			while (j > -1 && temp < arr[j]) {
				arr[j+1] = arr[j];
				j--;
				
			}
			arr[j+1] = temp;
		}
	}
	
	public static void insertionSort(Comparable[] arr) {
		for (int i = 1; i<arr.length; i++) {
			Comparable temp = arr[i];
			int j = i-1;
			while (j > -1 && temp.compareTo(arr[j]) < 0) {
				arr[j+1] = arr[j];
				j--;
				
			}
			arr[j+1] = temp;
		}
	}
	
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
