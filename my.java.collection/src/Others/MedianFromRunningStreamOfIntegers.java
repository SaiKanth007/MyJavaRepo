package src.Others;

import java.util.Comparator;
import java.util.PriorityQueue;

import src.Utilities.JavaUtility;

public class MedianFromRunningStreamOfIntegers {

	public static int[] leftArray = new int[100];
	public static int leftArrayLength = 0;
	public static int rightArrayLength = 0;

	public static int[] rightArray = new int[100];

	public static void main(String[] args) {
		int[] array = { 3, 1, 4, 2, -1, 0 };
		// System.out.println(Arrays.toString(array));
		heapSort(array);
		// System.out.println(Arrays.toString(array));

		PriorityQueue<Integer> minQueue = new PriorityQueue();
		PriorityQueue<Integer> maxQueue = new PriorityQueue(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2.compareTo(o1);
			}
		});

		int[] runningStreamOfIntegers = {5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4}; 

		for (int i = 0; i < runningStreamOfIntegers.length; i++) {
			System.out.print(medianOfRunningStreamOfIntegers(runningStreamOfIntegers[i], minQueue, maxQueue) + " ");
		}

	}

	//working
	public static int medianOfRunningStreamOfIntegers(int input, PriorityQueue<Integer> minQueue,
			PriorityQueue<Integer> maxQueue) {
		if (maxQueue.size() == 0) {
			maxQueue.add(input);
			return input;
		} 
		if (input < maxQueue.peek()) {
			maxQueue.add(input);
		} else {
			minQueue.add(input);
		}
		if (maxQueue.size() - minQueue.size() >= 2) {
			minQueue.add(maxQueue.poll());
		} else if (minQueue.size() - maxQueue.size() >= 2) {
			maxQueue.add(minQueue.poll());
		}
		if (maxQueue.size() == minQueue.size()) {
			return (maxQueue.peek() + minQueue.peek()) / 2;
		} else if (maxQueue.size() > minQueue.size()) {
			return maxQueue.peek();
		} else {
			return minQueue.peek();
		}

	}

	public static void heapSort(int[] array) {
		int length = array.length;
		minHeapConstruction(array);
		for (int index = length - 1; index > 0; index--) {
			JavaUtility.swap(array, 0, index);
			minHeapify(array, 0, index);
		}
	}

	public static void minHeapConstruction(int[] array) {
		int length = array.length;
		for (int index = length / 2; index >= 0; index--) {
			minHeapify(array, index, length);
		}
	}

	public static void minHeapify(int[] array, int index, int length) {
		if (index >= length)
			return;
		int i = 2 * index + 1;
		int j = 2 * index + 2;
		int min = index;
		if (i < length && array[i] < array[min])
			min = i;
		if (j < length && array[j] < array[min])
			min = j;
		if (min != index) {
			JavaUtility.swap(array, min, index);
			minHeapify(array, min, length);
		}
	}

	public static void minMapHeapify(int[] array, boolean isMinHeap) {
		int length = array.length;
		for (int index = length / 2; index >= 0; index--) {
			minMaxHeapifyUtil(array, index, length, isMinHeap);
		}
	}

	public static void minMaxHeapifyUtil(int[] array, int index, int length, boolean isMinHeap) {
		if (index >= length)
			return;
		int i = 2 * index + 1;
		int j = 2 * index + 2;
		int min = index;
		if (i < length && ((isMinHeap && array[i] < array[min])) || (!isMinHeap && array[i] > array[min]))
			min = i;
		if (j < length && ((isMinHeap && array[j] < array[min])) || (!isMinHeap && array[j] > array[min]))
			min = j;
		if (min != index) {
			JavaUtility.swap(array, min, index);
			minHeapify(array, min, length);
		}
	}
}