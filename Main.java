import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1
//        System.out.println(sameLetterPattern("ABAB", "CDCD")); // true
//        System.out.println(sameLetterPattern("ABCBA", "BCDCB")); // true
//        System.out.println(sameLetterPattern("FFGG", "CDCD")); // false
//        System.out.println(sameLetterPattern("FFFF", "ABCD")); // false
        // 2
//        System.out.println(memeSum(26, 39));     // 515
//        System.out.println(memeSum(122, 81));    // 1103
//        System.out.println(memeSum(1222, 30277)); // 31499
        // 3
//        System.out.println(digitsCount(4666)); // 4
//        System.out.println(digitsCount(544)); // 3
//        System.out.println(digitsCount(121317)); // 6
//        System.out.println(digitsCount(0)); // 1
//        System.out.println(digitsCount(12345)); // 5
//        System.out.println(digitsCount(1289396387328L)); // 13
        // 4
//        System.out.println(totalPoints(new String[]{"cat", "create", "sat"}, "caster")); // 2
//        System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant")); // 108
//        System.out.println(totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed")); // 13
        // 5
//        System.out.println(longestRun(new int[]{1, 2, 3, 5, 6, 7, 8, 9})); // 5
//        System.out.println(longestRun(new int[]{1, 2, 3, 10, 11, 15})); // 3
//        System.out.println(longestRun(new int[]{5, 4, 2, 1})); // 2
//        System.out.println(longestRun(new int[]{3, 5, 7, 10, 15})); // 1
        // 6
//        System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"})); // "54%"
//        System.out.println(takeDownAverage(new String[]{"10%"})); // "0%"
//        System.out.println(takeDownAverage(new String[]{"53%", "79%"})); // "51%"
        // 7
//        System.out.println(canMove("Rook", "A8", "H8")); // true
//        System.out.println(canMove("Bishop", "A7", "G1")); // true
//        System.out.println(canMove("Queen", "C4", "D6")); // false
        // 8
//        System.out.println(maxPossible(9328, 456)); // 9658
//        System.out.println(maxPossible(523, 76)); // 763
//        System.out.println(maxPossible(9132, 5564)); // 9655
//        System.out.println(maxPossible(8732, 91255)); // 9755
        // 9
//        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra")); // "2011-4-2 17:23"
//        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome")); // "1983-8-1 00:01"
//        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing")); // "1971-1-1 02:40"
        // 10
        System.out.println(isNew(3)); // true
        System.out.println(isNew(30)); // true
        System.out.println(isNew(321)); // false
        System.out.println(isNew(123)); // true

    }
    // 1
    public static boolean sameLetterPattern(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        return getPattern(str1).equals(getPattern(str2));
    }
    private static String getPattern(String str) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder pattern = new StringBuilder();

        int count = 0;
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, count++);
            }
            pattern.append(map.get(c));
        }

        return pattern.toString();
    }
    // 2
    public static int memeSum(int a, int b) {
        StringBuilder result = new StringBuilder();

        String strA = Integer.toString(a);
        String strB = Integer.toString(b);

        int maxLength = Math.max(strA.length(), strB.length());
        strA = String.format("%" + maxLength + "s", strA).replace(' ', '0');
        strB = String.format("%" + maxLength + "s", strB).replace(' ', '0');

        for (int i = 0; i < maxLength; i++) {
            int digitA = strA.charAt(i) - '0';
            int digitB = strB.charAt(i) - '0';
            result.append(digitA + digitB);
        }

        return Integer.parseInt(result.toString());
    }
    // 3
    public static int digitsCount(long num){
        if (num < 0){
            num = -num;
        }
        if (num < 10){
            return 1;
        }
        return 1 + digitsCount(num / 10);
    }
    // 4
    public static int totalPoints(String[] words, String baseWord) {
        int totalPoints = 0;

        char[] baseChars = baseWord.toCharArray();
        Arrays.sort(baseChars);
        String sortedBaseWord = new String(baseChars);

        Set<String> countedSixLetterWords = new HashSet<>();

        for (String word : words) {
            if (!canFormWord(word, sortedBaseWord)) {
                continue;
            }

            int wordLength = word.length();
            int points = 0;

            switch (wordLength) {
                case 3: points = 1; break;
                case 4: points = 2; break;
                case 5: points = 3; break;
                case 6:
                    points = 4;
                    if (!countedSixLetterWords.contains(word)) {
                        points += 50;
                        countedSixLetterWords.add(word);
                    }
                    break;
            }
            totalPoints += points;
        }

        return totalPoints;
    }
    private static boolean canFormWord(String word, String sortedBaseWord) {
        char[] wordChars = word.toCharArray();
        Arrays.sort(wordChars);
        String sortedWord = new String(wordChars);

        int i = 0, j = 0;
        while (i < sortedWord.length() && j < sortedBaseWord.length()) {
            if (sortedWord.charAt(i) == sortedBaseWord.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == sortedWord.length();
    }
    // 5
    public static int longestRun(int[] nums){
        int longRun = 1;
        int run = 1;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[i-1] + 1 || nums[i] == nums[i-1] - 1){
                run += 1;
                longRun = Math.max(longRun, run);
            }else{
                run = 1;
            }
        }
        return longRun;
    }
    // 6
    public static String takeDownAverage(String[] scores) {
        int total = 0;

        for (String score : scores) {
            total += Integer.parseInt(score.replace("%", ""));
        }
        int n = scores.length;
        double average = (double) total / n;
        int delta = 5;
        double newAverage = average - delta;
        long result = Math.round(newAverage * (n+1) - total);

        return result + "%";
    }
    // 7
    public static boolean canMove(String piece, String start, String end) {
        int startX = start.charAt(0) - 'A';
        int startY = start.charAt(1) - '1';
        int endX = end.charAt(0) - 'A';
        int endY = end.charAt(1) - '1';

        switch (piece) {
            case "Rook": // Ладья
                return startX == endX || startY == endY;

            case "Bishop": // слон
                return Math.abs(startX - endX) == Math.abs(startY - endY);

            case "Queen": // ферзь
                return (startX == endX || startY == endY) || (Math.abs(startX - endX) == Math.abs(startY - endY));

            case "King": // король
                return Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1;

            case "Knight": // конь
                int dx = Math.abs(startX - endX);
                int dy = Math.abs(startY - endY);
                return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);

            case "Pawn": // пешка
                return (startX == endX) && (endY - startY == 1);
            default:
                return false;
        }
    }
    // 8
    public static int maxPossible(int num1, int num2) {
        char[] digits1 = String.valueOf(num1).toCharArray();

        List<Integer> digits2 = String.valueOf(num2).chars()
                .mapToObj(c -> c - '0')
                .sorted(Collections.reverseOrder())
                .toList();

        int index = 0;

        for (int i = 0; i < digits1.length; i++) {
            if (index < digits2.size() && digits2.get(index) > (digits1[i] - '0')) {
                digits1[i] = (char) (digits2.get(index) + '0');
                index++;
            }
        }
        return Integer.parseInt(new String(digits1));
    }
    // 9
    public static String timeDifference(String cityA, String date, String cityB){
        Map<String, Double> cityOffsets = new HashMap<>();

        cityOffsets.put("Los Angeles", -8.0);
        cityOffsets.put("New York", -5.0);
        cityOffsets.put("Caracas", -4.5);
        cityOffsets.put("Buenos Aires", -3.0);
        cityOffsets.put("London", 0.0);
        cityOffsets.put("Rome", 1.0);
        cityOffsets.put("Moscow", 3.0);
        cityOffsets.put("Tehran", 3.5);
        cityOffsets.put("New Delhi", 5.5);
        cityOffsets.put("Beijing", 8.0);
        cityOffsets.put("Canberra", 10.0);

        String[] timestamp = date.replace(",", "").split(" ");
        if (timestamp[1].length() == 1){
            timestamp[1] = "0" + timestamp[1];
        }
        if (timestamp[3].length() != 5){
            timestamp[3] = "0" + timestamp[3];
        }
        // yyyy-MM-dd HH:mm date in cityA
        String dateA = timestamp[2] + '-' + monthNameToNumber(timestamp[0]) + '-' + timestamp[1] + " " + timestamp[3];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dateTimeA = LocalDateTime.parse(dateA, formatter);
        LocalDateTime result;

        double deltaTime = Math.abs(cityOffsets.get(cityA) - cityOffsets.get(cityB));

        if (deltaTime != Math.round(deltaTime)){
            result = dateTimeA.plusHours((long) Math.ceil(deltaTime)).plusMinutes(30);
        } else{
            result = dateTimeA.plusHours((long) Math.ceil(deltaTime));
        }
        return result.toString().replace("T", " ");
    }
    private static String monthNameToNumber(String monthName) {
        return switch (monthName.toLowerCase()) {
            case "january" -> "01";
            case "february" -> "02";
            case "march" -> "03";
            case "april" -> "04";
            case "may" -> "05";
            case "june" -> "06";
            case "july" -> "07";
            case "august" -> "08";
            case "september" -> "09";
            case "october" -> "10";
            case "november" -> "11";
            case "december" -> "12";
            default -> "-1";
        };
    }
    // 10
    public static boolean isNew(int num) {
        String numStr = Integer.toString(num);
        char[] digits = numStr.toCharArray();
        Arrays.sort(digits);

        if (digits[0] == '0') {
            for (int i = 1; i < digits.length; i++) {
                if (digits[i] != '0') {
                    char temp = digits[0];
                    digits[0] = digits[i];
                    digits[i] = temp;
                    break;
                }
            }
        }
        String sortedNumStr = new String(digits);

        return sortedNumStr.equals(numStr);
    }
}
