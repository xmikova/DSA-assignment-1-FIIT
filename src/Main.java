//The main class implementation with all the testing scenarios for this assignment.
//Petra Miková, ID: 120852, summer term 22/23 - DSA

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random(); //To generate random strings and numbers for datasets.

        int[] numbers = new int[10000000];
        String[] strings = new String[10000000];


        for (int i = 0; i < 10000000; i++) {
            numbers[i] = random.nextInt(10000000); //Generating random integers dataset.
            strings[i] = randomString(random.nextInt(10) + 1); //Generating random strings dataset.
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("DATA STRUCTURES TESTING - AVL TREE, RED BLACK TREE, SEPARATE CHAINING HASH TABLE, QUADRATIC PROBING HASH TABLE");
        System.out.println("TIME COMPLEXITY TEST: enter 1 for AVL tree, 2 for Red Black tree, 3 for Separate Chaining hash table and 4 for Quadratic Probing hash table.");
        System.out.print("Enter a number:");
        int input = scanner.nextInt();

        switch (input){
            case 1: //AVL tree time complexity test.
                AVLTree AVLTree = new AVLTree();
                System.out.println("AVL tree insertion of 10M nodes in 50K nodes intervals:");
                long startAVLinsert1, endAVLinsert1, totalAVLinsert1;
                int intervalAVL1 = 50000;
                int numOfNodesAVL1 = 0;

                for (int i = intervalAVL1; i <= 10000000; i += intervalAVL1) {
                    startAVLinsert1 = System.currentTimeMillis();
                    for (int j = numOfNodesAVL1; j < i; j++) {
                        AVLTree.insert(numbers[j]);
                    }
                    endAVLinsert1 = System.currentTimeMillis();
                    totalAVLinsert1 = endAVLinsert1 - startAVLinsert1;
                    System.out.println("Insert of "+ i + " nodes: "+ totalAVLinsert1 + "ms");

                    numOfNodesAVL1 = i;
                }

                AVLTree AVLTree2 = new AVLTree();
                System.out.println("AVL tree insertion of 10M nodes in 50K nodes intervals, then searching 50K nodes in each interval and then deleting 50K nodes in each interval:");
                long startAVLinsert2, endAVLinsert2, totalAVLinsert2,startAVLsearch2, endAVLsearch2, totalAVLsearch2, startAVLdelete2, endAVLdelete2, totalAVLdelete2;
                int intervalAVL2 = 50000;
                int numOfNodesAVL2 = 0;

                for (int i = intervalAVL2; i <= 10000000; i += intervalAVL2) {
                    System.out.println("Interval " + i + ": ");
                    startAVLinsert2 = System.currentTimeMillis();
                    for (int j = numOfNodesAVL2; j < i; j++) {
                        AVLTree2.insert(numbers[j]);
                    }
                    endAVLinsert2 = System.currentTimeMillis();
                    totalAVLinsert2 = endAVLinsert2 - startAVLinsert2;
                    System.out.println("Insert: " + totalAVLinsert2);

                    startAVLsearch2 = System.currentTimeMillis();
                    for (int s = 0; s <= 50000; s++){
                        int randomIndexAVL = random.nextInt(i);
                        int randomElementAVL = numbers[randomIndexAVL];
                        AVLTree2.search(randomElementAVL);
                    }
                    endAVLsearch2 = System.currentTimeMillis();
                    totalAVLsearch2 = endAVLsearch2 - startAVLsearch2;
                    System.out.println("Search: " + totalAVLsearch2);


                    startAVLdelete2= System.currentTimeMillis();
                    for (int s = 0; s <= 50000; s++){
                        int randomIndexAVL = random.nextInt(i);
                        int randomElementAVL = numbers[randomIndexAVL];
                        AVLTree2.delete(randomElementAVL);
                }
                    endAVLdelete2 = System.currentTimeMillis();
                    totalAVLdelete2 = endAVLdelete2 - startAVLdelete2;
                    System.out.println("Delete: " + totalAVLdelete2);

                    for (int j = numOfNodesAVL2; j < i; j++) {
                        AVLTree2.insert(numbers[j]); //Reinsert the nodes to test the delete properly (done in all the other scenarios like this one as well).
                    }

                    numOfNodesAVL2 = i;
                }
                break;

            case 2: //RB tree time complexity test.
                RedBlackTree RedBlackTree = new RedBlackTree();
                System.out.println("Red Black tree insertion of 10M nodes in 50K nodes intervals:");
                long startRBTinsert1, endRBTinsert1, totalRBTinsert1;
                int intervalRBT1 = 50000;
                int numOfNodesRBT1 = 0;

                for (int i = intervalRBT1; i <= 10000000; i += intervalRBT1) {
                    startRBTinsert1 = System.currentTimeMillis();
                    for (int j = numOfNodesRBT1; j < i; j++) {
                        RedBlackTree.insert(numbers[j]);
                    }
                    endRBTinsert1 = System.currentTimeMillis();
                    totalRBTinsert1 = endRBTinsert1 - startRBTinsert1;
                    System.out.println("Insert of "+ i + " nodes: "+ totalRBTinsert1 + "ms");

                    numOfNodesRBT1 = i;
                }

                RedBlackTree RedBlackTree2 = new RedBlackTree();
                System.out.println("Red Black tree insertion of 10M nodes in 50K nodes intervals, then searching 50K nodes in each interval and then deleting 50K nodes in each interval:");
                long startRBTinsert2, endRBTinsert2, totalRBTinsert2, startRBTsearch2, endRBTsearch2, totalRBTsearch2, startRBTdelete2, endRBTdelete2, totalRBTdelete2;
                int intervalRBT2 = 50000;
                int numOfNodesRBT2 = 0;

                for (int i = intervalRBT2; i <= 10000000; i += intervalRBT2) {
                    System.out.println("Interval " + i + ": ");
                    startRBTinsert2 = System.currentTimeMillis();
                    for (int j = numOfNodesRBT2; j < i; j++) {
                        RedBlackTree2.insert(numbers[j]);
                    }
                    endRBTinsert2 = System.currentTimeMillis();
                    totalRBTinsert2 = endRBTinsert2 - startRBTinsert2;
                    System.out.println("Insert: " + totalRBTinsert2);

                    startRBTsearch2 = System.currentTimeMillis();
                    for (int s = 0; s <= 50000; s++){
                        int randomIndexRBT = random.nextInt(i);
                        int randomElementRBT = numbers[randomIndexRBT];
                        RedBlackTree2.search(randomElementRBT);
                    }
                    endRBTsearch2 = System.currentTimeMillis();
                    totalRBTsearch2 = endRBTsearch2 - startRBTsearch2;
                    System.out.println("Search:" + totalRBTsearch2);

                    startRBTdelete2 = System.currentTimeMillis();
                    for (int s = 0; s <= 50000; s++){
                        int randomIndexRBT = random.nextInt(i);
                        int randomElementRBT = numbers[randomIndexRBT];
                        RedBlackTree2.delete(randomElementRBT);
                    }
                    endRBTdelete2 = System.currentTimeMillis();
                    totalRBTdelete2 = endRBTdelete2 - startRBTdelete2;
                    System.out.println("Delete: " + totalRBTdelete2);

                    for (int j = numOfNodesRBT2; j < i; j++) {
                        RedBlackTree2.insert(numbers[j]);
                    }
                    numOfNodesRBT2 = i;
                }
                break;

            case 3: //Separate Chaining hash table time complexity test.
                ChainingHashTable<String,Integer> ChainingHashTable = new ChainingHashTable<>();
                System.out.println("Hash Table with separate chaining collision resolution insertion of 10M keys in 50K keys intervals:");
                long startHTCHSput1, endHTSCHput1, totalHTSCHput1;
                int intervalHTSCH1 = 50000;
                int numOfNodesHTSCH1 = 0;

                for (int i = intervalHTSCH1; i <= 10000000; i += intervalHTSCH1) {
                    startHTCHSput1 = System.currentTimeMillis();
                    for (int j = numOfNodesHTSCH1; j < i; j++) {
                       ChainingHashTable.put(strings[j],numbers[j]);
                    }
                    endHTSCHput1 = System.currentTimeMillis();
                    totalHTSCHput1 = endHTSCHput1 - startHTCHSput1;
                    System.out.println("Put of "+ i + " elements: "+ totalHTSCHput1 + "ms");

                    numOfNodesHTSCH1 = i;
                }

                ChainingHashTable<String,Integer> ChainingHashTable2 = new ChainingHashTable<>();
                System.out.println("Hash Table with separate chaining collision resolution insertion of 10M keys in 50K keys intervals, then searching 50K keys in each interval and then deleting 50K keys in each interval:");
                long startHTSCHput2, endHTSCHput2, totalHTSCHput2, startHTSCHget2, endHTSCHget2, totalHTSCHget2, startHTSCHremove2, endHTSCHremove2, totalHTSCHremove2;
                int intervalHTSCH2 = 50000;
                int numOfNodesHTSCH2 = 0;

                for (int i = intervalHTSCH2; i <= 10000000; i += intervalHTSCH2) {
                    System.out.println("Interval " + i + ": ");
                    startHTSCHput2 = System.currentTimeMillis();
                    for (int j = numOfNodesHTSCH2; j < i; j++) {
                        ChainingHashTable2.put(strings[j], numbers[j]);
                    }
                    endHTSCHput2 = System.currentTimeMillis();
                    totalHTSCHput2 = endHTSCHput2 - startHTSCHput2;
                    System.out.println("Put: " + totalHTSCHput2);

                    startHTSCHget2 = System.currentTimeMillis();
                    for (int s = 0; s <= 50000; s++){
                        int randomIndexHTSCH = random.nextInt(i);
                        String randomElementHTSCH = strings[randomIndexHTSCH];
                        ChainingHashTable2.get(randomElementHTSCH);
                    }
                    endHTSCHget2 = System.currentTimeMillis();
                    totalHTSCHget2 = endHTSCHget2 - startHTSCHget2;
                    System.out.println("Get: " + totalHTSCHget2);

                    startHTSCHremove2 = System.currentTimeMillis();
                    for (int s = 0; s <= 50000; s++){
                        int randomIndexHTSCH = random.nextInt(i);
                        String randomElementHTSCH = strings[randomIndexHTSCH];
                        ChainingHashTable2.remove(randomElementHTSCH);
                    }
                    endHTSCHremove2 = System.currentTimeMillis();
                    totalHTSCHremove2 = endHTSCHremove2 - startHTSCHremove2;
                    System.out.println("Remove: "+ totalHTSCHremove2);


                    for (int j = numOfNodesHTSCH2; j < i; j++) {
                        ChainingHashTable2.put(strings[j], numbers[j]);
                    }
                    numOfNodesHTSCH2 = i;
                }
                break;

            case 4: //Quadratic Probing hash table time complexity test.
                QuadraticProbingHashTable<String,Integer> QuadraticProbingHashTable = new QuadraticProbingHashTable<>();
                System.out.println("Hash table with quadratic probing collision resolution insertion of 10M keys in 50K keys intervals:");
                long startHTQPput1, endHTQPput1, totalHTQPput1;
                int intervalHTQP1 = 50000;
                int numOfNodesHTQP1 = 0;

                for (int i = intervalHTQP1; i <= 10000000; i += intervalHTQP1) {
                    startHTQPput1 = System.currentTimeMillis();
                    for (int j = numOfNodesHTQP1; j < i; j++) {
                        QuadraticProbingHashTable.put(strings[j],numbers[j]);
                    }
                    endHTQPput1 = System.currentTimeMillis();
                    totalHTQPput1 = endHTQPput1 - startHTQPput1;
                    System.out.println("Put of "+ i + " elements: "+ totalHTQPput1 + "ms");

                    numOfNodesHTQP1 = i;
                }

                QuadraticProbingHashTable<String,Integer> QuadraticProbingHashTable2 = new QuadraticProbingHashTable<>();
                System.out.println("Hash table with quadratic probing collision resolution insertion of 1OM keys in 50K keys intervals, then searching 50K keys in each interval and then deleting 50K keys in each interval:");
                long startHTQPput2, endHTQPput2, totalHTQPput2, startHTQPget2, endHTQPget2, totalHTQPget2, startHTQPremove2, endHTQPremove2, totalHTQPremove2;
                int intervalHTQP2 = 50000;
                int numOfNodesHTQP2 = 0;

                for (int i = intervalHTQP2; i <= 10000000; i += intervalHTQP2) {
                    System.out.println("Interval " + i + ": ");
                    startHTQPput2 = System.currentTimeMillis();
                    for (int j = numOfNodesHTQP2; j < i; j++) {
                        QuadraticProbingHashTable2.put(strings[j], numbers[j]);
                    }
                    endHTQPput2 = System.currentTimeMillis();
                    totalHTQPput2 = endHTQPput2 - startHTQPput2;
                    System.out.println("Put: " + totalHTQPput2);

                    startHTQPget2 = System.currentTimeMillis();
                    for (int s = 0; s <= 50000; s++){
                        int randomIndexHTQP = random.nextInt(i);
                        String randomElementHTQP = strings[randomIndexHTQP];
                        QuadraticProbingHashTable2.get(randomElementHTQP);
                    }
                    endHTQPget2 = System.currentTimeMillis();
                    totalHTQPget2 = endHTQPget2 - startHTQPget2;
                    System.out.println("Get: " + totalHTQPget2);

                    startHTQPremove2 = System.currentTimeMillis();
                    for (int s = 0; s <= 50000; s++){
                        int randomIndexHTQP = random.nextInt(i);
                        String randomElementHTQP = strings[randomIndexHTQP];
                        QuadraticProbingHashTable2.remove(randomElementHTQP);
                    }
                    endHTQPremove2 = System.currentTimeMillis();
                    totalHTQPremove2 = endHTQPremove2 - startHTQPremove2;
                    System.out.println("Remove: " + totalHTQPremove2);

                    for (int j = numOfNodesHTQP2; j < i; j++) {
                        QuadraticProbingHashTable2.put(strings[j], numbers[j]);
                    }
                    numOfNodesHTQP2 = i;
                }
                break;
        }
    }

    private static String randomString(int length) { //Helper function to generate random strings.
        final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();

        StringBuilder string = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(chars.length());
            string.append(chars.charAt(randomIndex));
        }

        return string.toString();
    }
}