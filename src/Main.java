//The main class implementation with all the testing scenarios required for this assignment.
//Petra Miková, ID: 120852, summer term 22/23 - DSA

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random(); //To generate random strings and numbers for datasets.

        AVLTree AVLTree = new AVLTree();
        RedBlackTree RedBlackTree = new RedBlackTree();
        ChainingHashTable<String, Integer> ChainingHashTable = new ChainingHashTable<>();
        QuadraticProbingHashTable<String, Integer> QuadraticProbingHashTable = new QuadraticProbingHashTable<>();

        int[] numbers = new int[20000000];
        int[] numbers2 = new int[20000000];
        String[] strings = new String[20000000];


        for (int i = 0; i < 10000000; i++) {
            numbers[i] = random.nextInt(10000000);
            numbers2[i] = random.nextInt(10000000);
            strings[i] = randomString(random.nextInt(10) + 1);
        }

        /*//AVL tree:
        long startAVL, endAVL, totalAVL;
        int intervalAVL = 50000;
        int numOfNodesAVL = 0;

        for (int i = intervalAVL; i <= 10000000; i += intervalAVL) {
            startAVL = System.currentTimeMillis();
            for (int j = numOfNodesAVL; j < i; j++) {
                AVLTree.insert(numbers[j]);
            }
            endAVL = System.currentTimeMillis();
            totalAVL = endAVL - startAVL;
            //System.out.println(totalAVL);

            long startTime1 = System.nanoTime();
            for (int s = 0; s <= 10000; s++){
                int randomIndex = random.nextInt(intervalAVL);
                int randomElement = numbers[randomIndex];
                AVLTree.search(numbers[randomElement]);
            }
            long endTime1 = System.nanoTime();
            long totalTime1 = endTime1 - startTime1;
            //System.out.println(totalTime1);

            long startTime2 = System.nanoTime();
            for (int s = 0; s <= 10000; s++){
                int randomIndex = random.nextInt(i);
                int randomElement = numbers[randomIndex];
                AVLTree.delete(numbers[randomElement]);
            }
            long endTime2 = System.nanoTime();
            long totalTime2 = endTime2 - startTime2;
            System.out.println(totalTime2);

            numOfNodesAVL = i;
        }
*/


        /*                                  RED BLACK TREE TESTING:
         * The test measures the time complexity of insert from 0 to 10M nodes in following intervals:
         * 100, 500, 1K, 5K, 10K, 25K, 50K, 100K, 250K, 500K, 750K, 1M, 2.5M, 5M, 7.5M, 10M
         * The time will be displayed in milliseconds.
         */
//
//        long startRBT, endRBT, totalRBT;
//        int interval = 50000;
//        int numNodes = 0;
//
//        for (int i = interval; i <= 10000000; i += interval) {
//            startRBT = System.currentTimeMillis();
//            for (int j = numNodes; j < i; j++) {
//                RedBlackTree.insert(numbers[j]);
//            }
//            endRBT = System.currentTimeMillis();
//            totalRBT = endRBT - startRBT;
//            System.out.println(totalRBT);
//
//            long startTime1 = System.nanoTime();
//            for (int s = 0; s <= 10000; s++){
//                int randomIndex = random.nextInt(i);
//                int randomElement = numbers[randomIndex];
//                AVLTree.search(numbers[randomElement]);
//            }
//            long endTime1 = System.nanoTime();
//            long totalTime1 = endTime1 - startTime1;
//            //System.out.println(totalTime1);
//
//            long startTime2 = System.nanoTime();
//            for (int s = 0; s <= 10000; s++){
//                int randomIndex = random.nextInt(i);
//                int randomElement = numbers[randomIndex];
//                //AVLTree.delete(numbers[randomElement]);
//            }
//            long endTime2 = System.nanoTime();
//            long totalTime2 = endTime2 - startTime2;
//            //System.out.println(totalTime2);
//
//            numNodes = i;
//
//        }




        /*                                  HASH TABLE (SEPARATE CHAINING) TESTING:
         * The test measures the time complexity of insert from 0 to 10M nodes in following intervals:
         * 100, 500, 1K, 5K, 10K, 25K, 50K, 100K, 250K, 500K, 750K, 1M, 2.5M, 5M, 7.5M, 10M
         * The time will be displayed in milliseconds.
         */

        System.out.println("__________________________________");
        long startHTSCH, endHTSCH, totalHTSCH;
        int intervalHTSCH = 50000;
        int numOfNodesHTSCH = 0;

        for (int i = intervalHTSCH; i <= 10000000; i += intervalHTSCH) {
            startHTSCH = System.currentTimeMillis();
            for (int j = numOfNodesHTSCH; j < i; j++) {
                ChainingHashTable.put(strings[j],numbers[j]);
            }
            endHTSCH = System.currentTimeMillis();
            totalHTSCH = endHTSCH - startHTSCH;
            System.out.println(totalHTSCH);

            long startTime1 = System.nanoTime();
            for (int s = 0; s <= 10000; s++){
                int randomIndex = random.nextInt(i);
                int randomElement = numbers[randomIndex];
                AVLTree.search(numbers[randomElement]);
            }
            long endTime1 = System.nanoTime();
            long totalTime1 = endTime1 - startTime1;
            //System.out.println(totalTime1);

            long startTime2 = System.nanoTime();
            for (int s = 0; s <= 10000; s++){
                int randomIndex = random.nextInt(i);
                int randomElement = numbers[randomIndex];
                //AVLTree.delete(numbers[randomElement]);
            }
            long endTime2 = System.nanoTime();
            long totalTime2 = endTime2 - startTime2;
            //System.out.println(totalTime2);

            numOfNodesHTSCH = i;

        }




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
