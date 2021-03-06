package src;

//https://algorithms.tutorialhorizon.com/all-elements-appears-thrice-and-one-element-appears-once-find-that-element-in-on-time-and-o1-space/

// https://www.geeksforgeeks.org/bitwise-algorithms/
// https://www.geeksforgeeks.org/bitwise-hacks-for-competitive-programming/
// vvv imp
// https://www.hackerearth.com/practice/basic-programming/bit-manipulation/basics-of-bit-manipulation/tutorial/
public class MyBinaryOperators {

	public static void main(String args[]) {
		final int[] arrayForRepeatingNumber = { 1, 2, 1, 3, 5, 4 };
		findRepeatingAndMissingNumber(arrayForRepeatingNumber);

		final int[] arrayForFinidngRepeatedNumberInSortedArray = { 3, 4, 4, 5, 6 };
		System.out.println("The index of the repeated number is:" + findRepeatingNumber(
				arrayForFinidngRepeatedNumberInSortedArray, 0, arrayForFinidngRepeatedNumberInSortedArray.length - 1));

		System.out.println("The sum of given two numbers is: " + findSumOfTwoNumbersWithoutUsingAddOperator(41, 4));

		int[] arrayForMultipleRepeatingNumbers = { 10, 1, 2, 3, 5, 4, 9, 8, 5, 6, 4 };
		System.out.println("One of the multiple repeating numbers is:"
				+ findOneOfMultipleRepeatingNumber(arrayForMultipleRepeatingNumbers));

		System.out.println("Given number is a power of 2 " + checkIfGivenNumberIsPowerOfTwo(0));

	}

	// not working - go thorugh the below link for better approach
	// https://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/
	// use a bucker sort approach or
	// try counting the bits at each position -
	// https://algorithms.tutorialhorizon.com/all-elements-appears-thrice-and-one-element-appears-once-find-that-element-in-on-time-and-o1-space/
	public static void findRepeatingAndMissingNumber(int[] array) {
		int result = 0;

	}

	// working
	public static int findMissingNumber(int[] array) {
		int result = 0;
		final int length = array.length;
		for (int index = 0; index < length; index++) {
			result = result ^ index;
		}
		for (int index = 0; index < length; index++) {
			result = result ^ array[index];
		}
		return result;
	}

	// find repeating number in sorted array
	// https://www.geeksforgeeks.org/find-repeating-element-sorted-array-size-n/
	// not exactly similar as finding first instance of the repeating element in
	// sorted array
	public static int findRepeatingNumber(int[] array, int lowerIndex, int upperIndex) {
		if (lowerIndex > upperIndex) {
			return -1;
		} else {
			int mid = (lowerIndex + upperIndex) / 2;
			if (mid > 0 && array[mid] == array[mid - 1])
				return mid - 1;
			else {
				int leftIndex = findRepeatingNumber(array, lowerIndex, mid - 1);
				if (leftIndex != -1) {
					return leftIndex;
				}
				return findRepeatingNumber(array, mid + 1, upperIndex);
			}
		}
	}

	// https://www.geeksforgeeks.org/find-one-multiple-repeating-elements-read-array/
	// working
	public static int findOneOfMultipleRepeatingNumber(int[] array) {
		int maxElement = array.length + 1;
		int length = array.length;
		for (int i = 0; i < length; i++) {
			array[array[i] % maxElement] = array[array[i] % maxElement] + maxElement;
		}
		for (int i = length - 1; i > 0; i--) {
			if (array[i] / maxElement > 1)
				return i;
		}
		return -1;
	}

	// working
	public static String findSumOfTwoNumbersWithoutUsingAddOperator(int a, int b) {
		StringBuilder actualSum = new StringBuilder("");
		int localSum = 0;
		int prevCarry = 0;
		while (a > 0 || b > 0) {
			localSum = a % 2 ^ b % 2 ^ prevCarry % 2;
			prevCarry = a % 2 & b % 2 | prevCarry % 2 & (a % 2 ^ b % 2);
			actualSum = actualSum.append(localSum);
			a = a >> 1;
			b = b >> 1;

		}
		actualSum = actualSum.append(prevCarry != 0 ? String.valueOf(prevCarry) : "");
		return actualSum.reverse().toString();
	}

	public static int findNextPowerOfTwo(int number) {
		return -1;
	}

	// working
	public static boolean checkIfGivenNumberIsPowerOfTwo(int number) {
		if (number == 0)
			return false;
		return (number & (number - 1)) == 0 ? true : false;
	}

	// https://www.geeksforgeeks.org/find-two-non-repeating-elements-in-an-array-of-repeating-elements/
	public static void minValueToAddToMakeArrayBalanced() {

	}

	// https://www.geeksforgeeks.org/find-sum-non-repeating-distinct-elements-array/
	public static void sumOfDistinctElementsInArray() {

	}

	// https://www.geeksforgeeks.org/find-first-repeating-element-array-integers/
	public static void findFirstRepeatingElementInArray() {

	}

	// https://www.geeksforgeeks.org/find-the-first-missing-number/
	public static void findFirstMissingElementInArray() {

	}

	// https://github.com/mission-peace/interview/tree/master/src/com/interview/bits
	public static void swapOddEvenBits() {

	}

	public static String numberToBinaryString() {
		return "";
	}

	// https://www.geeksforgeeks.org/closest-next-smaller-greater-numbers-number-set-bits/
	public static int nearestNumberWithSameSetOfBits() {
		return 0;
	}

	/***
	 * Bit Manipulation hacks num |= (1 << pos); --> Setting a bit at a given
	 * position num &= (~(1 << pos)); --> Un set a bit at a given position num ^= (1
	 * << pos); --> Toggling bit a nth position ~num --> inverting all bits
	 */
}
