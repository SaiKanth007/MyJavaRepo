package src.Others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//think of scenario of negative numbers as well
// think of knapsack with repeated elements as well
// matrix appraoch might not work for an array with negative numbers (check this once)
public class KnapsackProblems {

	public static void main(String[] args) {

		int[] subSetWithSum = { 1, 4, 2, 3, 6 };
		System.out.println("There exists a subset with the given sum :"
				+ checkSubSetWithGivenSum(subSetWithSum, 10, subSetWithSum.length - 1));
		System.out.println("There exists a subset with the given sum :"
				+ checkSubSetWithGivenSumUsingMatrix(subSetWithSum, 10, subSetWithSum.length - 1));

		int[] printSubSetWithSum = { 4, 8, 1, 4, 2, 1, 7 };
		List<Integer> result = new ArrayList();
		System.out.println("There exists a subset with the given sum :"
				+ checkSubSetWithGivenSum(printSubSetWithSum, 10, printSubSetWithSum.length - 1, result));
		System.out.println("The length of the result set is: " + result.size());
		System.out.println("The values are: ");
		for (Integer value : result) {
			System.out.println(value + " ");
		}

		System.out.println("There exists a subset with the given sum for a given count:"
				+ checkSubSetWithGivenSumAndGivenCount(printSubSetWithSum, 10, printSubSetWithSum.length - 1, 3));

		result = new ArrayList();
		System.out.println("There exists a subset with the given sum for a given count:"
				+ checkSubSetWithGivenSumWithCount(printSubSetWithSum, 10, printSubSetWithSum.length - 1, result, 3));
		System.out.println("The length of the result set is: " + result.size());
		System.out.println("The values are: ");
		for (Integer value : result) {
			System.out.print(value + " ");
		}

		int[] arrayForCountSubArrays = { 10, 2, -2, -20 };
		System.out.println("Number of Sub Sequence is:"
				+ countNoOfSetsWithGivenSum(arrayForCountSubArrays, -20, arrayForCountSubArrays.length - 1));
	}

	// exponential time complextity
	// https://www.geeksforgeeks.org/subset-sum-problem-osum-space/
	public static boolean checkSubSetWithGivenSum(int[] inputArray, int sum, int index) {
		if (sum == 0)
			return true;
		if (index >= inputArray.length)
			return false;
		if (index < 0 && sum != 0)
			return false;
		if (inputArray[index] > sum)
			return checkSubSetWithGivenSum(inputArray, sum, index - 1);
		return checkSubSetWithGivenSum(inputArray, sum - inputArray[index], index - 1)
				|| checkSubSetWithGivenSum(inputArray, sum, index - 1);

	}

	// working
	// think of solve this using matrix appraoch as well
	public static boolean checkSubSetWithGivenSumAndGivenCount(int[] inputArray, int sum, int index, int count) {
		if (sum == 0) {
			if (count == 0)
				return true;
			return false;
		}
		if (index >= inputArray.length)
			return false;
		if ((count == 0 || index < 0) && sum != 0)
			return false;

		if (inputArray[index] > sum)
			return checkSubSetWithGivenSumAndGivenCount(inputArray, sum, index - 1, count);
		return checkSubSetWithGivenSumAndGivenCount(inputArray, sum - inputArray[index], index - 1, count - 1)
				|| checkSubSetWithGivenSumAndGivenCount(inputArray, sum, index - 1, count);
	}

	// https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/SubsetSum.java
	// O(sum.n) --> where n is the size of the array
	// we can have a separate count matrix to have the no of ways to find the subset
	// with the given sum or to find minimum size subset
	public static boolean checkSubSetWithGivenSumUsingMatrix(int[] inputArray, int sum, int index) {
		boolean sumMatrix[][] = new boolean[inputArray.length + 1][sum + 1];
		for (int i = 0; i <= inputArray.length; i++) {
			sumMatrix[i][0] = true;
		}
		for (int i = 1; i <= inputArray.length; i++) {
			for (int j = 1; j <= sum; j++) {
				if (j - inputArray[i - 1] >= 0) {
					sumMatrix[i][j] = sumMatrix[i - 1][j] || sumMatrix[i - 1][j - inputArray[i - 1]];
				} else {
					sumMatrix[i][j] = sumMatrix[i - 1][j];
				}
			}
		}

		// we can use this matrix and now find out the elements that are included in the
		// result
		// https://stackoverflow.com/questions/7489398/how-to-find-which-elements-are-in-the-bag-using-knapsack-algorithm-and-not-onl
		// only if this condition true, we mean that there is a set with the given sum

		// working, but we need to think of the solution for printing minimum number of
		// elements required to print the sum
		if (sumMatrix[inputArray.length][index]) {
			int j = sum;
			int i = inputArray.length;
			while (i > 0 && j > 0) {
				if (j - inputArray[i - 1] >= 0) {
					if (sumMatrix[i - 1][j - inputArray[i - 1]])
						System.out.println(inputArray[i - 1] + " is included");
					j = j - inputArray[i - 1];
				}
				i = i - 1;
			}
		}

		return sumMatrix[inputArray.length][index];
	}

	// https://www.geeksforgeeks.org/maximum-size-subset-given-sum/
	// think of matrix approach as well
	public static boolean checkSubSetWithGivenSum(int[] inputArray, int sum, int index, List<Integer> result) {
		if (sum == 0) {
			return true;
		}
		if (index < 0 && sum != 0)
			return false;
		if (inputArray[index] > sum) {
			List<Integer> tempList = new ArrayList();
			if (checkSubSetWithGivenSum(inputArray, sum, index - 1, tempList)) {
				result.addAll(tempList);
				return true;
			}
			return false;

		} else {
			List<Integer> firstList = new ArrayList();
			List<Integer> secondList = new ArrayList();
			Boolean firstResult = checkSubSetWithGivenSum(inputArray, sum - inputArray[index], index - 1, firstList);
			Boolean secondResult = checkSubSetWithGivenSum(inputArray, sum, index - 1, secondList);

			if (firstResult && secondResult) {
				// uncomment this out if u do not want minimum numbers to get the required sum
				if (firstList.size() < secondList.size()) {
					result.addAll(firstList);
					if (firstResult && inputArray[index] != 0) {
						result.add(inputArray[index]);
					}
				} else {
					result.addAll(secondList);
				}
			} else if (firstResult) {
				result.addAll(firstList);
				if (inputArray[index] != 0) {
					result.add(inputArray[index]);
				}

			} else if (secondResult) {
				result.addAll(secondList);
			}
			return firstResult || secondResult;
		}

	}

	// https://www.geeksforgeeks.org/maximum-size-subset-given-sum/
	// returns sub set of given size for the given sum
	public static boolean checkSubSetWithGivenSumWithCount(int[] inputArray, int sum, int index, List<Integer> result,
			int count) {
		if (sum == 0) {
			if (count == 0)
				return true;
			return false;
		}
		if (count == 0 && sum != 0)
			return false;
		if (index < 0 && sum != 0)
			return false;
		if (inputArray[index] > sum) {
			List<Integer> tempList = new ArrayList();
			if (checkSubSetWithGivenSumWithCount(inputArray, sum, index - 1, tempList, count)) {
				result.addAll(tempList);
				return true;
			}
			return false;

		} else {
			List<Integer> firstList = new ArrayList();
			List<Integer> secondList = new ArrayList();
			Boolean firstResult = checkSubSetWithGivenSumWithCount(inputArray, sum - inputArray[index], index - 1,
					firstList, count - 1);
			Boolean secondResult = checkSubSetWithGivenSumWithCount(inputArray, sum, index - 1, secondList, count);

			if (firstResult && secondResult) {
				// uncomment this out if u do not want minimum numbers to get the required sum
				if (firstList.size() < secondList.size()) {
					result.addAll(firstList);
					if (firstResult && inputArray[index] != 0) {
						result.add(inputArray[index]);
					}
				} else {
					result.addAll(secondList);
				}
			} else if (firstResult) {
				result.addAll(firstList);
				if (firstResult && inputArray[index] != 0) {
					result.add(inputArray[index]);
				}

			} else if (secondResult) {
				result.addAll(secondList);
			}
			return firstResult || secondResult;
		}
	}

	// works fine for all positives, has to check for negative numbers as well
	public static int countNoOfSetsWithGivenSum(int[] inputArray, int sum, int index) {
		if (index < 0)
			return 0;
		if (sum == 0) {
			return 1;
			// return 1 + countNoOfSetsWithGivenSum(inputArray, sum, index - 1);
		}

		if (index == 0)
			if (sum != inputArray[index])
				return 0;
			else
				return 1;

		Integer a = countNoOfSetsWithGivenSum(inputArray, sum, index - 1);
		Integer b = countNoOfSetsWithGivenSum(inputArray, sum - inputArray[index], index - 1);
		System.out.println("For index" + index + " and sum " + sum + " values are	 " + a + " " + b);
		return a + b;
	}

	// https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
	public static void knapsackWithWieghtAndValues() {

	}

}
