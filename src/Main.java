//The main class implementation with all the testing scenarios required for this assignment.
//Petra Miková, ID: 120852, summer term 22/23 - DSA

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random(); //to generate random strings and numbers

        AVLTree AVLTree = new AVLTree();
        RedBlackTree RedBlackTree = new RedBlackTree();
        ChainingHashTable<String, Integer> ChainingHashTable = new ChainingHashTable<>();
        QuadraticProbingHashTable<String, Integer> QuadraticProbingHashTable = new QuadraticProbingHashTable<>();

        int[] numbers = new int[10000000];
        int[] numbers2 = new int[10000000];
        String[] strings = new String[10000000];


        for (int i = 0; i < 10000000; i++) {
            numbers[i] = random.nextInt(10000000);
            numbers2[i] = i;
            strings[i] = randomString(random.nextInt(10) + 1);
        }

        /*                                  AVL TREE TESTING:
        * The test measures the time complexity of insert from 0 to 10M nodes in following intervals:
        * 100, 500, 1K, 5K, 10K, 25K, 50K, 100K, 250K, 500K, 750K, 1M, 2.5M, 5M, 7.5M, 10M
        * The time will be displayed in milliseconds.
        */
        System.out.println("AVL Tree Insert Test:");

        Instant startAVL100 = Instant.now();

        for (int j = 0; j < 100; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL100 = Instant.now();
        Duration timeElapsedAVL100 = Duration.between(startAVL100, endAVL100);
        System.out.println("Time elapsed 100: " + timeElapsedAVL100.toMillis() + " milliseconds");

        Instant startAVL500 = Instant.now();

        for (int j = 100; j < 500; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL500 = Instant.now();
        Duration timeElapsedAVL500 = Duration.between(startAVL500, endAVL500);
        System.out.println("Time elapsed 500: " + timeElapsedAVL500.toMillis() + " milliseconds");

        Instant startAVL1K = Instant.now();

        for (int j = 500; j < 1000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL1K = Instant.now();
        Duration timeElapsedAVL1K = Duration.between(startAVL1K, endAVL1K);
        System.out.println("Time elapsed 1K: " + timeElapsedAVL1K.toMillis() + " milliseconds");

        Instant startAVL5K = Instant.now();

        for (int j = 1000; j < 5000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL5K = Instant.now();
        Duration timeElapsedAVL5K = Duration.between(startAVL5K, endAVL5K);
        System.out.println("Time elapsed 5K: " + timeElapsedAVL5K.toMillis() + " milliseconds");

        Instant startAVL10K = Instant.now();

        for (int j = 5000; j < 10000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL10K = Instant.now();
        Duration timeElapsedAVL10K = Duration.between(startAVL10K, endAVL10K);
        System.out.println("Time elapsed 10K: " + timeElapsedAVL10K.toMillis() + " milliseconds");

        Instant startAVL25K = Instant.now();

        for (int j = 10000; j < 25000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL25K = Instant.now();
        Duration timeElapsedAVL25K = Duration.between(startAVL25K, endAVL25K);
        System.out.println("Time elapsed 25K: " + timeElapsedAVL25K.toMillis() + " milliseconds");

        Instant startAVL50K = Instant.now();

        for (int j = 25000; j < 50000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL50K = Instant.now();
        Duration timeElapsedAVL50K = Duration.between(startAVL50K, endAVL50K);
        System.out.println("Time elapsed 5OK: " + timeElapsedAVL50K.toMillis() + " milliseconds");

        Instant startAVL100K = Instant.now();

        for (int j = 75000; j < 100000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL100K = Instant.now();
        Duration timeElapsedAVL100K = Duration.between(startAVL100K, endAVL100K);
        System.out.println("Time elapsed 100K: " + timeElapsedAVL100K.toMillis() + " milliseconds");

        Instant startAVL250K = Instant.now();

        for (int j = 100000; j < 250000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL250K = Instant.now();
        Duration timeElapsedAVL250K = Duration.between(startAVL250K, endAVL250K);
        System.out.println("Time elapsed 250K: " + timeElapsedAVL250K.toMillis() + " milliseconds");

        Instant startAVL500K = Instant.now();

        for (int j = 250000; j < 500000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL500K = Instant.now();
        Duration timeElapsedAVL500K = Duration.between(startAVL500K, endAVL500K);
        System.out.println("Time elapsed 500K: " + timeElapsedAVL500K.toMillis() + " milliseconds");

        Instant startAVL750K = Instant.now();

        for (int j = 500000; j < 750000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL750K = Instant.now();
        Duration timeElapsedAVL750K = Duration.between(startAVL750K, endAVL750K);
        System.out.println("Time elapsed 750K: " + timeElapsedAVL750K.toMillis() + " milliseconds");

        Instant startAVL1M = Instant.now();

        for (int j = 750000; j < 1000000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL1M = Instant.now();
        Duration timeElapsedAVL1M = Duration.between(startAVL1M, endAVL1M);
        System.out.println("Time elapsed 1M: " + timeElapsedAVL1M.toMillis() + " milliseconds");

        Instant startAVL2_5M = Instant.now();

        for (int j = 1000000; j < 2500000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL2_5M = Instant.now();
        Duration timeElapsedAVL2_5M = Duration.between(startAVL2_5M, endAVL2_5M);
        System.out.println("Time elapsed 2.5M: " + timeElapsedAVL2_5M.toMillis() + " milliseconds");

        Instant startAVL5M = Instant.now();

        for (int j = 2500000; j < 5000000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL5M = Instant.now();
        Duration timeElapsedAVL5M = Duration.between(startAVL5M, endAVL5M);
        System.out.println("Time elapsed 5M: " + timeElapsedAVL5M.toMillis() + " milliseconds");

        Instant startAVL7_5M = Instant.now();

        for (int j = 5000000; j < 7500000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL7_5M = Instant.now();
        Duration timeElapsedAVL7_5M = Duration.between(startAVL7_5M, endAVL7_5M);
        System.out.println("Time elapsed 7.5M: " + timeElapsedAVL7_5M.toMillis() + " milliseconds");

        Instant startAVL10M = Instant.now();

        for (int j = 7500000; j < 10000000; j++){
            AVLTree.insert(numbers[j]);
        }

        Instant endAVL10M = Instant.now();
        Duration timeElapsedAVL10M = Duration.between(startAVL10M, endAVL10M);
        System.out.println("Time elapsed 10M: " + timeElapsedAVL10M.toMillis() + " milliseconds\n");


        /*                                  RED BLACK TREE TESTING:
         * The test measures the time complexity of insert from 0 to 10M nodes in following intervals:
         * 100, 500, 1K, 5K, 10K, 25K, 50K, 100K, 250K, 500K, 750K, 1M, 2.5M, 5M, 7.5M, 10M
         * The time will be displayed in milliseconds.
         */

        System.out.println("Red Black Tree Insert Test:");
        Instant startRB100 = Instant.now();

        for (int j = 0; j < 100; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB100 = Instant.now();
        Duration timeElapsedRB100 = Duration.between(startRB100, endRB100);
        System.out.println("Time elapsed 100: " + timeElapsedRB100.toMillis() + " milliseconds");

        Instant startRB500 = Instant.now();

        for (int j = 100; j < 500; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB500 = Instant.now();
        Duration timeElapsedRB500 = Duration.between(startRB500, endRB500);
        System.out.println("Time elapsed 500: " + timeElapsedRB500.toMillis() + " milliseconds");

        Instant startRB1K = Instant.now();

        for (int j = 500; j < 1000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB1K = Instant.now();
        Duration timeElapsedRB1K = Duration.between(startRB1K, endRB1K);
        System.out.println("Time elapsed 1K: " + timeElapsedRB1K.toMillis() + " milliseconds");

        Instant startRB5K = Instant.now();

        for (int j = 1000; j < 5000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB5K = Instant.now();
        Duration timeElapsedRB5K = Duration.between(startRB5K, endRB5K);
        System.out.println("Time elapsed 5K: " + timeElapsedRB5K.toMillis() + " milliseconds");

        Instant startRB10K = Instant.now();

        for (int j = 5000; j < 10000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB10K = Instant.now();
        Duration timeElapsedRB10K = Duration.between(startRB10K, endRB10K);
        System.out.println("Time elapsed 10K: " + timeElapsedRB10K.toMillis() + " milliseconds");

        Instant startRB25K = Instant.now();

        for (int j = 10000; j < 25000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB25K = Instant.now();
        Duration timeElapsedRB25K = Duration.between(startRB25K, endRB25K);
        System.out.println("Time elapsed 25K: " + timeElapsedRB25K.toMillis() + " milliseconds");

        Instant startRB50K = Instant.now();

        for (int j = 25000; j < 50000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB50K = Instant.now();
        Duration timeElapsedRB50K = Duration.between(startRB50K, endRB50K);
        System.out.println("Time elapsed 5OK: " + timeElapsedRB50K.toMillis() + " milliseconds");

        Instant startRB100K = Instant.now();

        for (int j = 75000; j < 100000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB100K = Instant.now();
        Duration timeElapsedRB100K = Duration.between(startRB100K, endRB100K);
        System.out.println("Time elapsed 100K: " + timeElapsedRB100K.toMillis() + " milliseconds");

        Instant startRB250K = Instant.now();

        for (int j = 100000; j < 250000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB250K = Instant.now();
        Duration timeElapsedRB250K = Duration.between(startRB250K, endRB250K);
        System.out.println("Time elapsed 250K: " + timeElapsedRB250K.toMillis() + " milliseconds");

        Instant startRB500K = Instant.now();

        for (int j = 250000; j < 500000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB500K = Instant.now();
        Duration timeElapsedRB500K = Duration.between(startRB500K, endRB500K);
        System.out.println("Time elapsed 500K: " + timeElapsedRB500K.toMillis() + " milliseconds");

        Instant startRB750K = Instant.now();

        for (int j = 500000; j < 750000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB750K = Instant.now();
        Duration timeElapsedRB750K = Duration.between(startRB750K, endRB750K);
        System.out.println("Time elapsed 750K: " + timeElapsedRB750K.toMillis() + " milliseconds");

        Instant startRB1M = Instant.now();

        for (int j = 750000; j < 1000000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB1M = Instant.now();
        Duration timeElapsedRB1M = Duration.between(startRB1M, endRB1M);
        System.out.println("Time elapsed 1M: " + timeElapsedRB1M.toMillis() + " milliseconds");

        Instant startRB2_5M = Instant.now();

        for (int j = 1000000; j < 2500000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB2_5M = Instant.now();
        Duration timeElapsedRB2_5M = Duration.between(startRB2_5M, endRB2_5M);
        System.out.println("Time elapsed 2.5M: " + timeElapsedRB2_5M.toMillis() + " milliseconds");

        Instant startRB5M = Instant.now();

        for (int j = 2500000; j < 5000000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB5M = Instant.now();
        Duration timeElapsedRB5M = Duration.between(startRB5M, endRB5M);
        System.out.println("Time elapsed 5M: " + timeElapsedRB5M.toMillis() + " milliseconds");

        Instant startRB7_5M = Instant.now();

        for (int j = 5000000; j < 7500000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB7_5M = Instant.now();
        Duration timeElapsedRB7_5M = Duration.between(startRB7_5M, endRB7_5M);
        System.out.println("Time elapsed 7.5M: " + timeElapsedRB7_5M.toMillis() + " milliseconds");

        Instant startRB10M = Instant.now();

        for (int j = 7500000; j < 10000000; j++){
            RedBlackTree.insert(numbers[j]);
        }

        Instant endRB10M = Instant.now();
        Duration timeElapsedRB10M = Duration.between(startRB10M, endRB10M);
        System.out.println("Time elapsed 10M: " + timeElapsedRB10M.toMillis() + " milliseconds\n");

        /*                                  HASH TABLE (SEPARATE CHAINING) TESTING:
         * The test measures the time complexity of insert from 0 to 10M nodes in following intervals:
         * 100, 500, 1K, 5K, 10K, 25K, 50K, 100K, 250K, 500K, 750K, 1M, 2.5M, 5M, 7.5M, 10M
         * The time will be displayed in milliseconds.
         */

        System.out.println("Hash Table Separate Chaining Insert Test:");

        Instant startHTS100 = Instant.now();

        for (int j = 0; j < 100; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS100 = Instant.now();
        Duration timeElapsedHTS100 = Duration.between(startHTS100, endHTS100);
        System.out.println("Time elapsed 100: " + timeElapsedHTS100.toMillis() + " milliseconds");

        Instant startHTS500 = Instant.now();

        for (int j = 100; j < 500; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS500 = Instant.now();
        Duration timeElapsedHTS500 = Duration.between(startHTS500, endHTS500);
        System.out.println("Time elapsed 500: " + timeElapsedHTS500.toMillis() + " milliseconds");

        Instant startHTS1K = Instant.now();

        for (int j = 500; j < 1000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS1K = Instant.now();
        Duration timeElapsedHTS1K = Duration.between(startHTS1K, endHTS1K);
        System.out.println("Time elapsed 1K: " + timeElapsedHTS1K.toMillis() + " milliseconds");

        Instant startHTS5K = Instant.now();

        for (int j = 1000; j < 5000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS5K = Instant.now();
        Duration timeElapsedHTS5K = Duration.between(startHTS5K, endHTS5K);
        System.out.println("Time elapsed 5K: " + timeElapsedHTS5K.toMillis() + " milliseconds");

        Instant startHTS10K = Instant.now();

        for (int j = 5000; j < 10000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS10K = Instant.now();
        Duration timeElapsedHTS10K = Duration.between(startHTS10K, endHTS10K);
        System.out.println("Time elapsed 10K: " + timeElapsedHTS10K.toMillis() + " milliseconds");

        Instant startHTS25K = Instant.now();

        for (int j = 10000; j < 25000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS25K = Instant.now();
        Duration timeElapsedHTS25K = Duration.between(startHTS25K, endHTS25K);
        System.out.println("Time elapsed 25K: " + timeElapsedHTS25K.toMillis() + " milliseconds");

        Instant startHTS50K = Instant.now();

        for (int j = 25000; j < 50000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS50K = Instant.now();
        Duration timeElapsedHTS50K = Duration.between(startHTS50K, endHTS50K);
        System.out.println("Time elapsed 5OK: " + timeElapsedHTS50K.toMillis() + " milliseconds");

        Instant startHTS100K = Instant.now();

        for (int j = 75000; j < 100000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS100K = Instant.now();
        Duration timeElapsedHTS100K = Duration.between(startHTS100K, endHTS100K);
        System.out.println("Time elapsed 100K: " + timeElapsedHTS100K.toMillis() + " milliseconds");

        Instant startHTS250K = Instant.now();

        for (int j = 100000; j < 250000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS250K = Instant.now();
        Duration timeElapsedHTS250K = Duration.between(startHTS250K, endHTS250K);
        System.out.println("Time elapsed 250K: " + timeElapsedHTS250K.toMillis() + " milliseconds");

        Instant startHTS500K = Instant.now();

        for (int j = 250000; j < 500000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS500K = Instant.now();
        Duration timeElapsedHTS500K = Duration.between(startHTS500K, endHTS500K);
        System.out.println("Time elapsed 500K: " + timeElapsedHTS500K.toMillis() + " milliseconds");

        Instant startHTS750K = Instant.now();

        for (int j = 500000; j < 750000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS750K = Instant.now();
        Duration timeElapsedHTS750K = Duration.between(startHTS750K, endHTS750K);
        System.out.println("Time elapsed 750K: " + timeElapsedHTS750K.toMillis() + " milliseconds");

        Instant startHTS1M = Instant.now();

        for (int j = 750000; j < 1000000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS1M = Instant.now();
        Duration timeElapsedHTS1M = Duration.between(startHTS1M, endHTS1M);
        System.out.println("Time elapsed 1M: " + timeElapsedHTS1M.toMillis() + " milliseconds");

        Instant startHTS2_5M = Instant.now();

        for (int j = 1000000; j < 2500000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS2_5M = Instant.now();
        Duration timeElapsedHTS2_5M = Duration.between(startHTS2_5M, endHTS2_5M);
        System.out.println("Time elapsed 2.5M: " + timeElapsedHTS2_5M.toMillis() + " milliseconds");

        Instant startHTS5M = Instant.now();

        for (int j = 2500000; j < 5000000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS5M = Instant.now();
        Duration timeElapsedHTS5M = Duration.between(startHTS5M, endHTS5M);
        System.out.println("Time elapsed 5M: " + timeElapsedHTS5M.toMillis() + " milliseconds");

        Instant startHTS7_5M = Instant.now();

        for (int j = 5000000; j < 7500000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS7_5M = Instant.now();
        Duration timeElapsedHTS7_5M = Duration.between(startHTS7_5M, endHTS7_5M);
        System.out.println("Time elapsed 7.5M: " + timeElapsedHTS7_5M.toMillis() + " milliseconds");

        Instant startHTS10M = Instant.now();

        for (int j = 7500000; j < 10000000; j++){
            ChainingHashTable.put(strings[j], numbers[j]);
        }

        Instant endHTS10M = Instant.now();
        Duration timeElapsedHTS10M = Duration.between(startHTS10M, endHTS10M);
        System.out.println("Time elapsed 10M: " + timeElapsedHTS10M.toMillis() + " milliseconds\n");


    }

    private static String randomString(int length) { //helper function to generate random strings
        final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }




}
