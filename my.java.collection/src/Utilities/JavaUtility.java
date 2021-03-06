package src.Utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.print.attribute.standard.MediaSize.Other;

import src.MyLinkedList;
import src.MyLinkedList.Node;
import src.Others.SingleTon;

public class JavaUtility {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println(System.getProperty("os.arch"));

		Penny[] objectList = new Penny[4];
		SingleTon s1 = SingleTon.getSingleTon();
		SingleTon s2 = SingleTon.getSingleTon();
		if (s1 == s2)
			System.out.println("SingleTon Implemented");

		for (Constructor constructor : Arrays.asList(SingleTon.class.getDeclaredConstructors())) {
			// Below code will destroy the singleton pattern
			constructor.setAccessible(true);
			SingleTon s3 = (SingleTon) constructor.newInstance();
			if (s1 != s3)
				System.out.println("SingleTon Implementation is broken through reflections");

			break;
		}
		Map<String, String> testMap = new HashMap<>();
		testMap.put("1", "abc");
		String result = testMap.remove("1");
		System.out.println(result);
		System.out.println(testMap.size());

		sortString("ai34oA");
	}

	EnumSet<Values> enumSet = EnumSet.of(Values.HIGH, Values.MEDIUM);

	public static boolean checkIfIndexAreValid(int i, int j, int length, int breadth) {
		if (i >= length || j >= breadth || i < 0 || j < 0) {
			return false;
		}
		return true;
	}

	public static boolean checkIfIndexAreValid(int[][] matrix, int i, int j) {
		int breadth = matrix[0].length;
		int length = matrix.length;
		if (i >= length || j >= breadth || i < 0 || j < 0) {
			return false;
		}
		return true;
	}

	public static boolean checkIfIndexAreValidForMatrix(int i, int j, int length, int breadth, int[][] grid,
			boolean[][] visited) {
		return checkIfIndexAreValid(i, j, length, breadth) && grid[i][j] == 1 && !visited[i][j];
	}

	public static void printMatrix(int[][] matrix) {
		int rowSize = matrix.length;
		if (rowSize > 0) {
			int columnSize = matrix[0].length;
			for (int i = 0; i < rowSize; i++) {
				for (int j = 0; j < columnSize; j++) {
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

	public static void swap(int[] array, int i, int j) {
		int k = array[i];
		array[i] = array[j];
		array[j] = k;
	}

	public static void swap(char[] array, int i, int j) {
		char k = array[i];
		array[i] = array[j];
		array[j] = k;
	}

	public static void printBeforeSorting(int[] array) {
		System.out.println("The values of the array before sorting are:");
		int length = array.length;
		for (int index = 0; index < length; index++)
			System.out.print(array[index] + " ");
	}

	public static void printAfterSorting(int[] array, String sortName) {
		System.out.println("The values of the array after " + sortName + "sorting are:");
		int length = array.length;
		for (int index = 0; index < length; index++)
			System.out.print(array[index] + " ");
	}

	public static void print(int[] array) {
		int length = array.length;
		for (int i = 0; i < length; i++) {
			System.out.print(array[i] + " ");
		}
	}

	public static Map<Integer, String> NumbersToWordsMap() {
		Map<Integer, String> numberWordMap = new HashMap<>();
		numberWordMap.put(0, "zero");
		numberWordMap.put(1, "one");
		numberWordMap.put(2, "two");
		numberWordMap.put(3, "three");
		numberWordMap.put(4, "four");
		numberWordMap.put(5, "five");
		numberWordMap.put(6, "six");
		numberWordMap.put(7, "seven");
		numberWordMap.put(8, "eight");
		numberWordMap.put(9, "nine");
		numberWordMap.put(10, "ten");
		numberWordMap.put(11, "eleven");
		numberWordMap.put(12, "twelve");
		numberWordMap.put(13, "thirteen");
		numberWordMap.put(14, "fourteen");
		numberWordMap.put(15, "fifteen");
		numberWordMap.put(16, "sixteen");
		numberWordMap.put(17, "seventeen");
		numberWordMap.put(18, "eighteen");
		numberWordMap.put(19, "ninteen");
		numberWordMap.put(20, "twenty");
		numberWordMap.put(30, "thirty");
		numberWordMap.put(40, "forty");
		numberWordMap.put(50, "fifty");
		numberWordMap.put(60, "sixty");
		numberWordMap.put(70, "seventy");
		numberWordMap.put(80, "eighty");
		numberWordMap.put(90, "ninty");
		return numberWordMap;
	}

	public static Map<Integer, Integer> getRomansToNumberMap() {
		Map<Integer, Integer> map = new HashMap<>();
		map.put((int) 'I', 1);
		map.put((int) 'V', 5);
		map.put((int) 'X', 10);
		map.put((int) 'L', 50);
		map.put((int) 'C', 100);
		map.put((int) 'D', 500);
		map.put((int) 'M', 1000);
		return map;
	}

	public static MyLinkedList prepareLinkedListForGivenData(String input) {
		String[] inputs = input.split(",");
		final MyLinkedList linkedList = new MyLinkedList(new Node(Integer.parseInt(inputs[0])));
		for (int i = 1; i < inputs.length; i++) {
			linkedList.addNode(Integer.parseInt(inputs[i]));
		}
		return linkedList;
	}

	public static int findMaxElement(int[] array) {
		int length = array.length;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < length; i++) {
			max = Math.max(max, array[i]);
		}
		return max;
	}

	public static void reverseArray(int[] array) {
		int length = array.length;
		for (int index = 0; index < length / 2; index++) {
			swap(array, index, length - index - 1);
		}
	}

	public static void reverseArray(int[] array, int lowerIndex, int upperIndex) {
		for (int index = lowerIndex; index < (upperIndex + lowerIndex) / 2; index++) {
			swap(array, index, upperIndex - index - 1);
		}
	}

	public static String NumberToWordsUtil(int input) {
		Map<Integer, String> map = NumbersToWordsMap();
		if (input <= 20)
			return map.get(input);
		else
			return map.get((input / 10) * 10).concat("and").concat(map.get(input % 10));
	}

	public static <T> void swap(T[] array, T firstElement, T secondElement) {
		T buffer = firstElement;
		firstElement = secondElement;
		secondElement = buffer;
		int value = Values.HIGH.getLeveCode();
		String ValuestringLowerCase = Values.MEDIUM.asLowerCase();
	}

	public static void printBooleanMatrix(boolean[][] matrix, int rowSize, int columnSize) {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	// working
	public static String sortString(String input) {
		int[] charArray = new int[256];
		int length = input.length();

		for (int i = 0; i < length; i++) {
			charArray[input.charAt(i)]++;
		}
		char[] output = new char[length];

		for (int i = 1; i < 256; i++) {
			charArray[i] = charArray[i] + charArray[i - 1];
		}

		for (int i = length - 1; i >= 0; i--) {
			// the last index at which input.charAt(i) can occur is at
			// charArray[input.charAt(i)] - 1
			output[charArray[input.charAt(i)] - 1] = input.charAt(i);
			--charArray[input.charAt(i)];
		}

		System.out.println("The reverse value of the string is:" + String.valueOf(output));
		return String.valueOf(output);

	}

	public static boolean isSafeForNQueens(int board[][], int row, int col) {
		int i, j;

		int length = board.length;
		/* Check this row on left side */
		for (i = 0; i < col; i++)
			if (board[row][i] == 1)
				return false;

		/* Check upper diagonal on left side */
		for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
			if (board[i][j] == 1)
				return false;

		/* Check lower diagonal on left side */
		for (i = row, j = col; j >= 0 && i < length; i++, j--)
			if (board[i][j] == 1)
				return false;

		return true;
	}

}

// http://tutorials.jenkov.com/java/enums.html#enum-fields
enum Values {
	HIGH(3) {
		@Override
		public String asLowerCase() {
			return HIGH.toString().toLowerCase();
		}
	},
	MEDIUM(2);

	private int levelCode;

	private Values(int levelCode) {
		this.levelCode = levelCode;
	}

	public String asLowerCase() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLeveCode() {
		return levelCode;
	}

	public static void scannerInput() {
		Scanner in = new Scanner(System.in);
		int noOfTestCases = in.nextInt();
		int arraySize = -1;
		int[] array;
		int sum = -1;
		for (int i = 0; i < noOfTestCases; i++) {
			arraySize = in.nextInt();
			array = new int[arraySize];
			for (int j = 0; j < arraySize; j++) {
				array[j] = in.nextInt();
			}
			sum = in.nextInt();
			// System.out.println(minCoinProblem(array, sum));
		}
	}
}
