package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import src.Utilities.Count;
import src.Utilities.JavaUtility;

// yet to learn about the matrix approach of DP
public class DynamicProgramming {

	public static void main(String[] args) {
		final int[][] goldMine = { { 10, 33, 13, 15 }, { 22, 21, 04, 1 }, { 5, 0, 2, 3 }, { 0, 6, 14, 2 } };
		System.out
				.println("The maximum amount of the gold that can be collecetd is: " + goldMineProblem(goldMine, 4, 4));
		final int[] longestIncreasingSubSequence = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
		System.out.println("The length of the longest increasing sub sequence is:"
				+ longestIncreasingSubSequence(longestIncreasingSubSequence));

		final int[] longestIncreasingOddEvenSubSequence = { 5, 6, 9, 4, 7, 8 };
		System.out.println("The length of the longest increasing consecutive sub sequence is:"
				+ longestIncreasingOddEvenSubSequence(longestIncreasingOddEvenSubSequence));

		final int[][] minCostMatrix = { { 1, 2, 3 }, { 4, 8, 2 }, { 1, 5, 3 } };

		System.out.println("The cost of minimum path matrix is:" + minCostpathInMatrix(minCostMatrix, 3, 3, 0, 0));

		final int[][] minStepsToReachEndOfMatrix = { { 0, 0, 1, 1, 1 }, { 1, 1, 1, 0, 1 }, { 1, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1 } };
		boolean[][] visited = new boolean[4][5];
		final boolean[][] solution = new boolean[4][5];
		System.out.println("There exists a path:" + checkIfPathExists(minStepsToReachEndOfMatrix, 4, 5, 1, 2, visited));
		visited = new boolean[4][5];
		final Count count = new Count();
		noOfPossiblePaths(minStepsToReachEndOfMatrix, 4, 5, 1, 2, visited, count);
		System.out.println("No of possible paths are:" + count.value);

		visited = new boolean[4][5];
		System.out.println("Minimum steps to reach the end of the matrix:"
				+ minStepsToReachEndOfMatrix(minStepsToReachEndOfMatrix, 4, 5, 1, 2, visited));

		visited = new boolean[4][5];
		System.out.println("Minimum steps to reach the end of the matrix Using BFS:"
				+ minStepsToReachEndOfMatrixUsingBFS(minStepsToReachEndOfMatrix, 4, 5, 1, 2, visited));

		visited = new boolean[4][5];
		solution[1][2] = true;
		System.out.println("Minimum steps to reach the end of the matrix:"
				+ minStepsPathToReachEndOfMatrix(minStepsToReachEndOfMatrix, 4, 5, 1, 2, visited, solution));

		JavaUtility.printBooleanMatrix(solution, 4, 5);
		final int[] arrayForMinSteps = { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 };
		System.out.println("Number of steps to reach the end of the array");
		System.out.println(minNoOfSteps(arrayForMinSteps, 0, arrayForMinSteps.length - 1));

		final int[] coinDenominations = { 86, 7, 43, 67, 6 };
		System.out.println("Number of coins required for the given sum is:" + minCoinProblem(coinDenominations, 8777));

		final int[] points = { 3, 5, 10 };
		System.out.println("Number of ways to reach given sum are: " + noOfWaysToReachGivenSum(points, 13));

		final int[] maxSumOfNonAdjacentElements = { 1, 0, 3, 9, 2, 10 };
		System.out.println("The max sum of non adjancent elements is: "
				+ maxSumOfNonAdjacentElements(maxSumOfNonAdjacentElements, maxSumOfNonAdjacentElements.length - 1));

		final int nthStair = 3;
		System.out.println("No of ways reaching nth stair is: " + noOfWaysToReachGivenNumberOfSteps(nthStair));

		final int targetPositionToReach = 9;
		System.out.println("Minimum no of steps to reach the target:" + infineteLineProblem(targetPositionToReach));

		final int[][] matrixForLargestRegion = { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 1 },
				{ 0, 0, 0, 0, 0 }, { 1, 0, 1, 0, 1 } };
		System.out.println("The length of the largest region is:" + findLargestRegion(matrixForLargestRegion, 5, 5));

		final int[] ratingArrayForCandies = { 2, 4, 2, 6, 1, 7, 8, 9, 2, 1 };
		System.out.println("Minimum number of candies required are: " + minNoOfCandies(ratingArrayForCandies));

		final String firstSequence = "AGGTAB";
		final String secondSequence = "GXTXAYB";
		System.out.println("The length of the longest common subsequence is: "
				+ lengthOfLongestCommonSubSequence(firstSequence.toCharArray(), secondSequence.toCharArray()));

	}

	// https://www.geeksforgeeks.org/gold-mine-problem/
	// working
	public static int goldMineProblem(int[][] goldMine, int length, int breadth) {
		int maxGoldCollected = 0;
		for (int i = 0; i < length; i++) {
			maxGoldCollected = Math.max(maxGoldCollected, goldMineProblem(goldMine, i, 0, length, breadth));
		}
		return maxGoldCollected;
	}

	public static int goldMineProblem(int[][] goldMine, int i, int j, int length, int breadth) {
		if (JavaUtility.checkIfIndexAreValid(i, j, length, breadth)) {
			final int rightSum = goldMineProblem(goldMine, i - 1, j + 1, length, breadth);
			final int leftSum = goldMineProblem(goldMine, i + 1, j + 1, length, breadth);
			final int middleSum = goldMineProblem(goldMine, i, j + 1, length, breadth);
			return goldMine[i][j] + Math.max(middleSum, Math.max(leftSum, rightSum));
		}
		return 0;
	}

	// https://www.geeksforgeeks.org/puzzle-set-35-2-eggs-and-100-floors/
	public static int solveEggFloorProblem(int noOfFloors, int noOfEggs) {
		if (noOfEggs == 1) {
			return noOfFloors;
		}
		if (noOfFloors == 1 || noOfFloors == 0) {
			return noOfFloors;
		}
		int min = Integer.MAX_VALUE, localmin;
		for (int i = 1; i < noOfFloors; i++) {
			localmin = Math.max(solveEggFloorProblem(i - 1, noOfEggs - 1),
					solveEggFloorProblem(noOfFloors - i, noOfEggs));
			min = localmin < min ? localmin : min;
		}
		return min + 1;
	}

	// also check the below link for O(NlogN) solution in below link
	// https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
	// another application is connecting bridges -
	// https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
	public static int longestIncreasingSubSequence(int[] array) {
		final int length = array.length;
		if (length <= 1) {
			return length;
		}
		final int[] lis = new int[length];
		Arrays.fill(lis, 1);
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < i; j++) {
				if (array[i] > array[j] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < length; i++) {
			System.out.print(lis[i] + " ");
			if (lis[i] > max) {
				max = lis[i];
			}
		}
		return max;
	}

	public static int longestIncreasingOddEvenSubSequence(int[] array) {
		final int length = array.length;
		if (length <= 1) {
			return length;
		}
		final int[] lis = new int[length];
		Arrays.fill(lis, 1);
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < i; j++) {
				if (array[i] > array[j] && array[i] % 2 != array[j] % 2 && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < length; i++) {
			System.out.print(lis[i] + " ");
			if (lis[i] > max) {
				max = lis[i];
			}
		}
		return max;
	}

	// https://www.geeksforgeeks.org/min-cost-path-dp-6/
	// also check for matrix solution
	public static int minCostpathInMatrix(int[][] matrix, int length, int breadth, int i, int j) {
		if (i < 0 || j < 0 || i >= length || j >= breadth) {
			return Integer.MAX_VALUE;
		}
		if (i == length - 1 && j == breadth - 1) {
			return matrix[i][j];
		}
		return matrix[i][j] + Math.min(
				Math.min(minCostpathInMatrix(matrix, length, breadth, i, j + 1),
						minCostpathInMatrix(matrix, length, breadth, i + 1, j)),
				minCostpathInMatrix(matrix, length, breadth, i + 1, j + 1));

	}

	// also think of the matrix solution
	// DFS
	public static boolean checkIfPathExists(int[][] grid, int length, int breadth, int i, int j, boolean[][] visited) {
		if (i == length - 1 && j == breadth - 1) {
			return true;
		}
		if (!JavaUtility.checkIfIndexAreValid(i, j, length, breadth) || grid[i][j] == 0 || visited[i][j]) {
			return false;
		}
		visited[i][j] = true;
		final int[] xvalues = { 1, -1, 0, 0 };
		final int[] yvalues = { 0, 0, 1, -1 };
		int nextXValue = -1;
		int nextYValue = -1;
		for (int l = 0; l < 4; l++) {
			nextXValue = i + xvalues[l];
			nextYValue = j + yvalues[l];
			if (checkIfPathExists(grid, length, breadth, nextXValue, nextYValue, visited)) {
				return true;
			}
		}
		return false;
	}

	// also think of the matrix solution and is much simpler
	public static boolean noOfPossiblePaths(int[][] grid, int length, int breadth, int i, int j, boolean[][] visited,
			Count count) {
		if (i == length - 1 && j == breadth - 1) {
			count.value++;
			return true;
		}
		if (!JavaUtility.checkIfIndexAreValid(i, j, length, breadth) || grid[i][j] == 0 || visited[i][j]) {
			return false;
		}
		visited[i][j] = true;
		final int[] xvalues = { 1, -1, 0, 0 };
		final int[] yvalues = { 0, 0, 1, -1 };
		int nextXValue = -1;
		int nextYValue = -1;
		boolean pathFound = false;
		for (int l = 0; l < 4; l++) {
			nextXValue = i + xvalues[l];
			nextYValue = j + yvalues[l];
			if (noOfPossiblePaths(grid, length, breadth, nextXValue, nextYValue, visited, count)) {
				pathFound = true;
			}
		}
		return pathFound == true ? pathFound : false;
	}

	public static int minStepsToReachEndOfMatrix(int[][] grid, int length, int breadth, int i, int j,
			boolean[][] visited) {
		if (i == length - 1 && j == breadth - 1) {
			return 0;
		}
		if (!JavaUtility.checkIfIndexAreValid(i, j, length, breadth) || grid[i][j] == 0 || visited[i][j]) {
			return Integer.MAX_VALUE;
		}
		visited[i][j] = true;
		int localSum = 0;
		int actualSum = Integer.MAX_VALUE;
		final int[] xvalues = { 1, -1, 0, 0 };
		final int[] yvalues = { 0, 0, 1, -1 };
		int nextXValue = -1;
		int nextYValue = -1;
		for (int l = 0; l < 4; l++) {
			nextXValue = i + xvalues[l];
			nextYValue = j + yvalues[l];
			localSum = minStepsToReachEndOfMatrix(grid, length, breadth, nextXValue, nextYValue, visited);
			if (localSum != Integer.MAX_VALUE && localSum < actualSum) {
				actualSum = localSum;
			}
		}
		return actualSum != Integer.MAX_VALUE ? 1 + actualSum : Integer.MAX_VALUE;
	}

	// working
	public static int minStepsToReachEndOfMatrixUsingBFS(int[][] grid, int length, int breadth, int i, int j,
			boolean[][] visited) {
		if (i == length - 1 && j == breadth - 1) {
			return 0;
		}
		if (!JavaUtility.checkIfIndexAreValidForMatrix(i, j, length, breadth, grid, visited)) {
			return Integer.MAX_VALUE;
		}
		visited[i][j] = true;
		final Queue<Coordinate> points = new LinkedList<>();
		points.add(new Coordinate(i, j, 0));
		final int[] xvalues = { 1, -1, 0, 0 };
		final int[] yvalues = { 0, 0, 1, -1 };
		Coordinate currentPoint = null;
		int nextXValue = -1;
		int nextYValue = -1;
		int nextDistance = -1;
		while (!points.isEmpty()) {
			currentPoint = points.poll();
			for (int l = 0; l < 4; l++) {
				if (currentPoint.x == length - 1 && currentPoint.y == breadth - 1) {
					return currentPoint.lengthOfPathTillThisPoint;
				}
				nextXValue = currentPoint.x + xvalues[l];
				nextYValue = currentPoint.y + yvalues[l];
				nextDistance = currentPoint.lengthOfPathTillThisPoint + 1;
				if (JavaUtility.checkIfIndexAreValidForMatrix(nextXValue, nextYValue, length, breadth, grid, visited)) {
					points.add(new Coordinate(nextXValue, nextYValue, nextDistance));
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	// also think of the matrix solution
	public static int minStepsPathToReachEndOfMatrix(int[][] grid, int length, int breadth, int i, int j,
			boolean[][] visited, boolean[][] solution) {
		if (i == length - 1 && j == breadth - 1) {
			return 0;
		}
		if (!JavaUtility.checkIfIndexAreValid(i, j, length, breadth) || grid[i][j] == 0 || visited[i][j]) {
			return Integer.MAX_VALUE;
		}
		visited[i][j] = true;
		int localSum = 0;
		int actualSum = Integer.MAX_VALUE;
		final int[] xvalues = { 1, -1, 0, 0 };
		final int[] yvalues = { 0, 0, 1, -1 };
		int nextXValue = -1;
		int nextYValue = -1;
		boolean toggled = false;

		for (int l = 0; l < 4; l++) {
			nextXValue = i + xvalues[l];
			nextYValue = j + yvalues[l];
			if (JavaUtility.checkIfIndexAreValid(nextXValue, nextYValue, length, breadth)
					&& grid[nextXValue][nextYValue] == 1 && !visited[nextXValue][nextYValue]) {

				solution[nextXValue][nextYValue] = true;
				localSum = minStepsPathToReachEndOfMatrix(grid, length, breadth, nextXValue, nextYValue, visited,
						solution);
				if (localSum != Integer.MAX_VALUE && localSum < actualSum) {
					actualSum = localSum;
				} else {

					solution[nextXValue][nextYValue] = false;

				}
			}
		}
		return actualSum != Integer.MAX_VALUE ? 1 + actualSum : Integer.MAX_VALUE;
	}

	// do this for sure --
	// https://www.geeksforgeeks.org/minimum-number-jumps-reach-endset-2on-solution/
	public static int minNoOfSteps(int[] array, int currentIndex, int upperIndex) {
		if (currentIndex > upperIndex) {
			return 0;
		}
		if (currentIndex == upperIndex) {
			return array[currentIndex];
		}
		int localSum = 0;
		int actualSum = Integer.MAX_VALUE;
		for (int l = 1; l <= array[currentIndex]; l++) {
			localSum = minNoOfSteps(array, currentIndex + l, upperIndex);
			if (localSum < actualSum) {
				actualSum = localSum;
			}
		}
		return localSum != Integer.MAX_VALUE ? localSum + 1 : actualSum;
	}

	public static int minCoinProblem(int[] coinValues, int sum) {
		Arrays.sort(coinValues);
		final Map<Integer, Integer> map = new HashMap<>();
		return minCoinProblem(coinValues, sum, map);
	}

	// working
	public static int minCoinProblem(int[] coinValues, int sum, Map<Integer, Integer> map) {
		if (sum == 0) {
			return 0;
		}
		Integer localCoins = 0;
		Integer globalCount = Integer.MAX_VALUE;
		final int length = coinValues.length;
		for (int i = 0; i < length; i++) {
			if (sum < coinValues[i]) {
				continue;
			} else if (map.containsKey(sum)) {
				return map.get(sum);
			} else {
				localCoins = minCoinProblem(coinValues, sum - coinValues[i], map);
				if (localCoins < globalCount) {
					globalCount = localCoins;
				}
			}
		}
		globalCount = globalCount == Integer.MAX_VALUE ? globalCount : globalCount + 1;
		map.put(sum, globalCount);
		return globalCount;
	}

	// https://www.geeksforgeeks.org/count-number-ways-reach-given-score-game/
	// v.v.v.imp read again
	// also read for minimum no of coins required for getting the given sum (the
	// above method)
	public static int noOfWaysToReachGivenSum(int[] points, int sum) {
		final int[] array = new int[sum + 1];
		final int length = points.length;
		array[0] = 1;
		for (int i = 0; i < length; i++) {
			for (int j = points[i]; j <= sum; j++) {
				array[j] = array[j] + array[j - points[i]];
			}
		}
		return array[sum];
	}

	// https://www.geeksforgeeks.org/count-ways-reach-nth-stair-using-step-1-2-3/
	// assume a scenario where possible steps can be an array list and difference
	// between steps is very large
	// try to print those possible paths also - imp
	public static int noOfWaysToReachGivenNumberOfSteps(int totalSteps) {
		if (totalSteps == 0) {
			return 0;
		} else if (totalSteps == 1) {
			return 1;
		} else if (totalSteps == 2) {
			return 2;
		} else if (totalSteps == 3) {
			return 4;
		} else {
			return noOfWaysToReachGivenNumberOfSteps(totalSteps - 1) + noOfWaysToReachGivenNumberOfSteps(totalSteps - 2)
					+ noOfWaysToReachGivenNumberOfSteps(totalSteps - 3);
		}

	}

	// https://practice.geeksforgeeks.org/problems/word-boggle/0
	public static int noOfWordsFromDictionary(int[][] boggle, String[] words) {
		return 0;
	}

	public static int minNoStepsForKnightToReachDestination() {
		return 0;
	}

	// http://blog.gainlo.co/index.php/2016/12/02/uber-interview-question-maximum-sum-non-adjacent-elements/
	public static int maxSumOfNonAdjacentElements(int[] array, int index) {
		if (index >= 0 && index < array.length) {
			if (index == 0) {
				if (array[0] >= 0) {
					return array[0];
				} else {
					return 0;
				}
			} else {
				return Math.max(array[index] + maxSumOfNonAdjacentElements(array, index - 2),
						maxSumOfNonAdjacentElements(array, index - 1));
			}
		}
		return 0;
	}

	// https://www.geeksforgeeks.org/c-program-for-tower-of-hanoi/
	// also think of the iterative approach
	public static void towerOfHanoi(int n, char from, char to, char aux) {
		if (n == 1) {
			System.out.println("Move disk from: " + from + " to " + to);
		} else {
			towerOfHanoi(n - 1, from, aux, to);
			System.out.println("Move disk from: " + from + " to " + to);
			towerOfHanoi(n - 1, aux, to, from);
		}
	}

	public static void fibonacciNumbers() {

	}

	// https://www.geeksforgeeks.org/find-minimum-moves-reach-target-infinite-line/
	// -- imp
	public static int infineteLineProblem(int currentPost, int target, int step) {
		if (currentPost == target) {
			return step;
		}
		if (Math.abs(currentPost) > Math.abs(target)) {
			return Integer.MAX_VALUE;
		}
		final int leftSteps = infineteLineProblem(currentPost - step - 1, target, step + 1);
		final int rightSteps = infineteLineProblem(currentPost + step + 1, target, step + 1);
		return Math.min(leftSteps, rightSteps);
	}

	public static int infineteLineProblem(int target) {
		// starting from origin and with prev steps =0;
		return infineteLineProblem(0, target, 0);
	}

	// // https://www.geeksforgeeks.org/find-length-largest-region-boolean-matrix/
	// slight variation of the above problem -
	// https://www.geeksforgeeks.org/find-number-of-islands/
	public static int findLargestRegion(int[][] matrix, int length, int breadth) {
		int localMax = Integer.MIN_VALUE;
		int globalMax = Integer.MIN_VALUE;
		boolean visited[][];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < breadth; j++) {
				visited = new boolean[length][breadth];
				localMax = findLargestRegion(matrix, length, breadth, i, j, visited);
				globalMax = Math.max(localMax, globalMax);
			}
		}
		return globalMax;
	}

	public static int findLargestRegion(int[][] matrix, int length, int breadth, int i, int j, boolean[][] visited) {
		if (!JavaUtility.checkIfIndexAreValid(i, j, length, breadth)) {
			return 0;
		}
		if (visited[i][j] || matrix[i][j] == 0) {
			return 0;
		}
		visited[i][j] = true;
		int sum = 1;
		final int[] xaxis = { -1, -1, -1, 0, 0, 1, 1, 1, };
		final int[] yaxis = { -1, 0, 1, -1, 1, -1, 0, 1 };
		for (int index = 0; index < 8; index++) {
			sum = sum + findLargestRegion(matrix, length, breadth, i + xaxis[index], j + yaxis[index], visited);
		}
		return sum;
	}

	// https://www.geeksforgeeks.org/find-rectangle-binary-matrix-corners-1/
	public static boolean isRectangularMatrixPresent() {
		return false;
	}

	// https://www.hackerrank.com/challenges/candies/problem
	// working
	public static int minNoOfCandies(int[] ratings) {
		final int length = ratings.length;
		final int[] candies = new int[length];
		for (int i = 0; i < length; i++) {
			candies[i] = 1;
		}
		for (int i = 1; i < length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candies[i] = candies[i - 1] + 1;
			}
		}

		for (int i = length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
				candies[i] = candies[i + 1] + 1;
			}
		}
		int totalCandies = 0;
		for (int i = 0; i < length; i++) {
			totalCandies = totalCandies + candies[i];
		}
		return totalCandies;
	}

	// https://www.geeksforgeeks.org/paper-cut-minimum-number-squares-set-2/
	public static void minNoOfSqaures() {

	}

	// https://www.geeksforgeeks.org/count-number-ways-tile-floor-size-n-x-m-using-1-x-m-size-tiles/
	// also check this out -
	// https://www.geeksforgeeks.org/minimum-number-of-square-tiles-required-to-fill-the-rectangular-floor/
	public static int noOfTiles(int n, int m) {
		final int[] tiles = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			if (n < m) {
				tiles[i] = 1;
			} else if (n > m) {
				tiles[i] = tiles[i - 1] + tiles[i - m];
			} else {
				tiles[i] = 2;
			}
		}
		return tiles[n];
	}

	// https://leetcode.com/problems/regular-expression-matching/
	// think of wild card and regular expresion
	public static void regularExpressionMatching() {

	}

	// https://leetcode.com/problems/word-break/submissions/
	public static boolean wordBreak() {
		return false;

	}

	// https://www.careercup.com/page?pid=google-interview-questions
	public static void colorFlipFlop() {

	}

	// https://www.geeksforgeeks.org/painters-partition-problem/ - check for both
	// DP, matrix and binary search approach
	// also check this - https://www.geeksforgeeks.org/painting-fence-algorithm/
	public void paintersProblem() {

	}

	// https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
	boolean solveNQueensProblem(int board[][], int col) {
		int length = board.length;
		/*
		 * base case: If all queens are placed then return true
		 */
		if (col >= length)
			return true;

		/*
		 * Consider this column and try placing this queen in all rows one by one
		 */
		for (int i = 0; i < length; i++) {
			/*
			 * Check if the queen can be placed on board[i][col]
			 */
			if (JavaUtility.isSafeForNQueens(board, i, col)) {
				/* Place this queen in board[i][col] */
				board[i][col] = 1;

				/* recur to place rest of the queens */
				if (solveNQueensProblem(board, col + 1) == true)
					return true;

				/*
				 * If placing queen in board[i][col] doesn't lead to a solution then remove
				 * queen from board[i][col]
				 */
				board[i][col] = 0; // BACKTRACK
			}
		}

		/*
		 * If the queen can not be placed in any row in this colum col, then return
		 * false
		 */
		return false;
	}

	// https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/
	public static void knightsTourProblem() {

	}

	// https://www.geeksforgeeks.org/find-number-of-islands/
	// time complexity - O(MN)
	public static void islandProblem() {

	}

	// https://www.careercup.com/question?id=5757216146587648
	public static void maxWordLengthPossibleFromInput() {

	}

	// https://www.youtube.com/watch?v=yCQN096CwWM
	// https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/SubRectangularMatrixWithMaximumSum.java
	public static void maxRectangleInArray() {

	}

	// will not work if there are repeated elements (one case handeled for AGGTAB,
	// GXTXAYB)
	public static int lengthOfLongestCommonSubSequence(char[] firstSequnce, char[] secondSequence) {
		int firstLength = firstSequnce.length;
		int secondLength = secondSequence.length;
		int[][] matrix = new int[firstLength][secondLength];
		for (int i = 0; i < secondLength; i++) {
			if (secondSequence[i] == firstSequnce[0] || (i > 0 && matrix[0][i - 1] == 1)) {
				matrix[0][i] = 1;
			}
		}
		int max_value = 0;
		// A, B, C, D, G, H and A, E, D, F, H, R
		for (int i = 1; i < firstLength; i++) {
			for (int j = 0; j < secondLength; j++) {
				max_value = j > 0 ? Math.max(matrix[i - 1][j], matrix[i][j - 1]) : matrix[i - 1][j];
				matrix[i][j] = secondSequence[j] == firstSequnce[i] ? j > 0 ? 1 + max_value : 1 : max_value;
			}
		}
		JavaUtility.printMatrix(matrix);
		return matrix[firstLength - 1][secondLength - 1];
	}

}

class Coordinate {
	int x;

	int y;

	int lengthOfPathTillThisPoint;

	public Coordinate(int x, int y, int lengthOfPathTillThisPoint) {
		this.x = x;
		this.y = y;
		this.lengthOfPathTillThisPoint = lengthOfPathTillThisPoint;
	}
}

/**
 * https://leetcode.com/discuss/interview-experience/188224/Google-Software-Engineer-Interview-Experience
 * pinball game board = [ . . . \ . ] [ . \ . / . ] ball_pos = (0,0), ball_dir =
 * (0,1) exit_pos = (0,1), exit_dir = (-1,0)
 **/

/**
 * Yet to do: 1.
 * https://www.geeksforgeeks.org/minimum-cost-make-two-strings-identical/ total
 * no of path from top left to bottom right of a matrix
 *
 * nimsum problem :
 * https://www.geeksforgeeks.org/combinatorial-game-theory-set-2-game-nim/
 * https://www.geeksforgeeks.org/painters-partition-problem-set-2/ longest
 * common subsequence -
 * https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/ minimum coin
 * change problem -
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/CoinChangingMinimumCoin.java
 * https://www.geeksforgeeks.org/hungarian-algorithm-assignment-problem-set-1-introduction/
 */
