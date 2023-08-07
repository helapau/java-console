package com.company.ds;

import java.util.*;
import java.util.stream.Collectors;

public class Hashing {

    private HashMap<String, String> tickets = new HashMap<>();

    public HashMap<String, String> getTickets() {
        return tickets;
    }

    public void modifyMap(HashMap<String, String> ticketsMap) {
        ticketsMap.put("hey", "B");
    }

    public int[] findGivenSumA(int[] array, int sum) {
        // consider each pair, return first that sums to sum
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length ; j++) {
                if (array[i] + array[j] == sum) {
                    return new int[] {array[i], array[j]};
                }
            }
        }
        System.out.println("No sum found");
        return null;
    }

    public int[] findGivenSumB(int[] array, int sum) {
        //
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer n : array) {
            int diff = sum - n;
            if (map.containsKey(diff) && map.get(diff).equals(n)) {
                return new int[] {n, diff};
            }
            map.put(n, diff);
        }
        System.out.println("No sum found");
        return null;
    }

    /**
     * Given a list of tickets, find itinerary (how the cities were visited in order)
     * ex.
     * Input:
     * "Chennai" -> "Banglore"
     * "Bombay" -> "Delhi"
     * "Goa"    -> "Chennai"
     * "Delhi"  -> "Goa"
     *
     * Output:
     * Bombay->Delhi, Delhi->Goa, Goa->Chennai, Chennai->Banglore,
     * @return
     */
    public void findItinerary(String[][] tickets) {
        HashMap<String, String> startToDest = new HashMap<>(tickets.length);
        for (String[] t : tickets) {
            startToDest.put(t[0], t[1]);
        }
        String start = null;

        for (String[] t : tickets) {
            if (!startToDest.containsValue(t[0])) {
                start = t[0];
                break;
            }
        }
        int i = 0;
        String next;
        while (i < tickets.length) {
            next = startToDest.get(start);
            System.out.println(start + " --> " + next);
            start = next;
            i++;
        }
    }

    public boolean isSubset(int[] arr1, int[] arr2) {
        // assume both arrays are sets
        boolean isArr1Smaller = arr1.length <= arr2.length;
        int hashSetCapacity = isArr1Smaller ? arr2.length : arr1.length;
        HashSet<Integer> biggerArrSet = new HashSet<>(hashSetCapacity);
        if (isArr1Smaller) {
            biggerArrSet = (HashSet<Integer>) Arrays.stream(arr2).boxed().collect(Collectors.toSet());
        } else {
            biggerArrSet = (HashSet<Integer>) Arrays.stream(arr1).boxed().collect(Collectors.toSet());
        }
        int maxIndex = isArr1Smaller ? arr1.length - 1 : arr2.length - 1;
        int[] smallerArray = isArr1Smaller ? arr1 : arr2;
        for (int i = 0; i <= maxIndex; i++) {
            if(!biggerArrSet.contains(smallerArray[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean areDisjoint(int[] arr1, int[] arr2) {
        // assume both arrays are sets
        Set<Integer> set2 = Arrays.stream(arr2).boxed().collect(Collectors.toSet());
        for (int i = 0; i < arr1.length; i++) {
            if (set2.contains(arr1[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * example: arr[][] = [{1, 2}, {3, 4}, {5, 9}, {4, 3}, {9, 5}]
     * out: "{3,4}{5,9}"
     * @param arr 2d array of pairs
     * @return
     */
    public String findSymmetricPairs(int[][] arr) {
        HashMap<Integer, Integer> pairs = new HashMap<>();
        StringBuilder result = new StringBuilder();
        for (int[] pair : arr) {
            int first = pair[0];
            int second = pair[1];
            Integer value = pairs.get(second);
            if (value != null && value == first) {
                result.append("{" + second + ", " + first + "}");
            } else {
                pairs.put(first, second);
            }
        }
        return result.toString();
    }

    public String itineraryTwo(Map<String, String> tickets) {
        String start = null;
        Collection<String> values = tickets.values();
        for (String key : tickets.keySet()) {
            if (!values.contains(key)) {
                start = key;
                break;
            }
        }
        if (start == null) {
            System.out.println("Cyclic route, or disconnected tickets");
        }
        String result = "";
        while (true) {
            String to = tickets.get(start);
            if (to == null) {
                break;
            }
            result += start + "->" + to +", ";
            start = to;
        }
        return result;
    }

    /**
     * Find out whether it's possible to distribute items of various types between numPeople people
     * if a particular person can take at most two items of the same type
     * @param items array[i] corresponds to type of item
     * @param numPeople
     * @return
     */
    public boolean distributeItems(int[] items, int numPeople) {
        Map<Integer, Integer> countByItemType = new HashMap<>();
        // number of repeated items should be <= 2 * numPeople for the distribution to be possible
        for (int i = 0; i < items.length; i++) {
            if (!countByItemType.containsKey(items[i])) {
                countByItemType.put(items[i], 1);
            } else {
                int count = countByItemType.get(items[i]) + 1;
                if (count > 2 * numPeople) {
                    return false;
                }
                countByItemType.put(items[i], count);
            }
        }
        return true;
    }
}
