package src.Others;

public class PalindromicProblems {

	public static void main(String[] args) {
		String stringForlongestPalindromicSubString = "forgeeksskeegfor";
		System.out.println("The longest possible palindromic substring length is: " + longestPalindromicSubStringLength(
				stringForlongestPalindromicSubString, 0, stringForlongestPalindromicSubString.length() - 1));
		System.out.println("The longest possible palindromic substring is: " + longestPalindromicSubString(
				stringForlongestPalindromicSubString, 0, stringForlongestPalindromicSubString.length() - 1));

		String stringForlongestPalindromicSubSequence = "GEEKSFORGEEKS";
		System.out.println("The longest possible palindromic sub sequence length is: "
				+ longestPalindromicSubSequenceLength(stringForlongestPalindromicSubSequence, 0,
						stringForlongestPalindromicSubSequence.length() - 1));

		String minInsertionsToFormPalindrome = "abcda";
		System.out.println("Minimum number of insertions required are: " + minInsertionsToFormPalindrome(
				minInsertionsToFormPalindrome, 0, minInsertionsToFormPalindrome.length() - 1));

	}

	public static int findMaximumPalindromeNumberInTheGivenRange(int lower, int upper) {
		return 0;
	}

	public static int printPalindromicNumbersLessThanTheGivenNumber() {
		return 0;
	}

	// working
	// https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
	public static int longestPalindromicSubStringLength(String input, int l, int h) {
		if (l <= h) {
			if (l == h)
				return 1;
			if (input.charAt(l) == input.charAt(h) && l + 1 == h)
				return 2;
			if (input.charAt(l) == input.charAt(h) && l + 2 == h)
				return 3;
			if (input.charAt(l) == input.charAt(h)) {
				if (l < input.length() - 1 && h > 0 && input.charAt(l + 1) == input.charAt(h - 1)) {
					return 2 + longestPalindromicSubStringLength(input, l + 1, h - 1);
				} else {
					return Math.max(longestPalindromicSubStringLength(input, l + 1, h),
							longestPalindromicSubStringLength(input, l, h - 1));
				}
			}
			if (input.charAt(l) != input.charAt(h))
				return Math.max(longestPalindromicSubStringLength(input, l + 1, h),
						longestPalindromicSubStringLength(input, l, h - 1));
		}
		return 0;
	}

	// working
	// https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
	// also learn about the matrix approach
	public static int longestPalindromicSubSequenceLength(String input, int l, int h) {
		if (l <= h) {
			if (l == h)
				return 1;
			if (input.charAt(l) == input.charAt(h) && l + 1 == h)
				return 2;
			if (input.charAt(l) == input.charAt(h) && l + 2 == h)
				return 3;
			if (input.charAt(l) == input.charAt(h)) {
				return 2 + longestPalindromicSubSequenceLength(input, l + 1, h - 1);
			}
			if (input.charAt(l) != input.charAt(h))
				return Math.max(longestPalindromicSubSequenceLength(input, l + 1, h),
						longestPalindromicSubSequenceLength(input, l, h - 1));
		}
		return 0;
	}

	// working
	// https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
	public static String longestPalindromicSubString(String input, int l, int h) {
		if (l <= h) {
			if (l == h) {
				return String.valueOf(input.charAt(l));
			}
			String result1 = "";
			String result2 = "";
			if (input.charAt(l) == input.charAt(h) && (l + 1 == h || l + 2 == h))
				return input.substring(l, h + 1);
			if (input.charAt(l) == input.charAt(h)) {
				if (l < input.length() - 1 && h > 0 && input.charAt(l + 1) == input.charAt(h - 1)) {
					return String.valueOf(input.charAt(l)) + longestPalindromicSubString(input, l + 1, h - 1)
							+ String.valueOf(input.charAt(h));
				} else {
					result1 = longestPalindromicSubString(input, l + 1, h);
					result2 = longestPalindromicSubString(input, l, h - 1);
					return result1.length() > result2.length() ? result1 : result2;
				}
			}
			if (input.charAt(l) != input.charAt(h))
				result1 = longestPalindromicSubString(input, l + 1, h);
			result2 = longestPalindromicSubString(input, l, h - 1);
			return result1.length() > result2.length() ? result1 : result2;
		}
		return "";
	}

	// https://www.geeksforgeeks.org/count-palindrome-sub-strings-string/
	public static int totalNumberOfPalindromicSubStrings(String input) {
		return 0;
	}

	public static int totalNumberOfPalindromsFromGivenCharacters(char[] input) {
		return 0;
	}

	// https://www.geeksforgeeks.org/minimum-insertions-to-form-a-palindrome-dp-28/
	public static int minInsertionsToFormPalindrome(String input, int low, int high) {

		if (low <= high) {
			if (low == high)
				return 0;
			if (low + 1 == high) {
				if (input.charAt(low) == input.charAt(high))
					return 0;
				return 1;
			}
			if (input.charAt(low) == input.charAt(high)) {
				return minInsertionsToFormPalindrome(input, low + 1, high - 1);

			}
			return 1 + Math.min(minInsertionsToFormPalindrome(input, low + 1, high),
					minInsertionsToFormPalindrome(input, low, high - 1));
		}
		return Integer.MAX_VALUE;
	}

	// go through thoroughly
	public static int minInsertionsToFormPalindromeWithouRecursion(String input, int low, int high) {
		return 0;
	}
}