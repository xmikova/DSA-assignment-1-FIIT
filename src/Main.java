import java.time.Duration;
import java.time.Instant;
import java.util.Random;




public class Main {
    public static void main(String[] args) {
        AVLTree AVLTree = new AVLTree();
        RedBlackTree RedBlackTree = new RedBlackTree();

        Random random = new Random();

        int[] numbers = new int[10000000];
        int[] numbers2 = new int[10000000];

        for (int i = 0; i < 10000000; i++) {
            numbers[i] = random.nextInt(10000000);
            numbers2[i] = random.nextInt(10000000);

        }
//        AVLTree.insert(5);
//        AVLTree.insert(45);
//        AVLTree.insert(10);
//        AVLTree.insert(7);
//        AVLTree.insert(12);
//        AVLTree.insert(6);
//        AVLTree.insert(95);
//        AVLTree.insert(19);
//        AVLTree.insert(37);
//
        AVLTree.insert(1);
        AVLTree.insert(2);
        AVLTree.insert(3);
        AVLTree.insert(4);
        AVLTree.insert(5);
//
        AVLTree.printPreorder();
        System.out.println("");

        AVLTree.delete(4);
        AVLTree.printPreorder();
        System.out.println("");
//
//        Instant start = Instant.now();
//
//        for (int i = 0; i < 10000000; i++){
//            //AVLTree.insert(ThreadLocalRandom.current().nextInt(0, 1000000 + 1));
//            //AVLTree.insert(random.nextInt(1000000) + 1);
//            AVLTree.insert(numbers[i]);
//
//        }
//        Instant end = Instant.now();
//        Duration timeElapsed = Duration.between(start, end);
//        System.out.println("Time elapsed for 10M insert: " + timeElapsed.toMillis() + " milliseconds\n");
//
//        //AVLTree.printPreorder();
//
//        Instant start11 = Instant.now();
//
//        for (int k = 0; k < 10000000; k++){
//
//            //AVLTree.insert(ThreadLocalRandom.current().nextInt(0, 1000000 + 1));
//            //AVLTree.insert(random.nextInt(1000000) + 1);
//            AVLTree.search(numbers2[k]);
//
//        }
//
//
//        Instant end11 = Instant.now();
//        Duration timeElapsed11 = Duration.between(start11, end11);
//        System.out.println("Time elapsed for 10M search: " + timeElapsed11.toMillis() + " milliseconds");
//
//
//        Instant start2 = Instant.now();
//
//        for (int j = 0; j < 10000000; j++){
//
//            //AVLTree.insert(ThreadLocalRandom.current().nextInt(0, 1000000 + 1));
//            //AVLTree.insert(random.nextInt(1000000) + 1);
//            AVLTree.delete(numbers[j]);
//
//        }


//        Instant end2 = Instant.now();
//        Duration timeElapsed2 = Duration.between(start2, end2);
//        System.out.println("Time elapsed for 10M delete: " + timeElapsed2.toMillis() + " milliseconds");

        Instant start3 = Instant.now();

        for (int i = 0; i < 10000000; i++){
            //AVLTree.insert(ThreadLocalRandom.current().nextInt(0, 1000000 + 1));
            //AVLTree.insert(random.nextInt(1000000) + 1);
            RedBlackTree.insert(numbers[i]);

        }
        Instant end3 = Instant.now();
        Duration timeElapsed3 = Duration.between(start3, end3);
        System.out.println("Time elapsed for 10M insert: " + timeElapsed3.toMillis() + " milliseconds\n");

        Instant start11 = Instant.now();

        for (int k = 0; k < 10000000; k++){

            //AVLTree.insert(ThreadLocalRandom.current().nextInt(0, 1000000 + 1));
            //AVLTree.insert(random.nextInt(1000000) + 1);
            RedBlackTree.search(numbers2[k]);

        }


        Instant end11 = Instant.now();
        Duration timeElapsed11 = Duration.between(start11, end11);
        System.out.println("Time elapsed for 10M search: " + timeElapsed11.toMillis() + " milliseconds");


        Instant start4 = Instant.now();

        for (int j = 0; j < 10000000; j++){

            //AVLTree.insert(ThreadLocalRandom.current().nextInt(0, 1000000 + 1));
            //AVLTree.insert(random.nextInt(1000000) + 1);
            RedBlackTree.delete(numbers[j]);

        }


        Instant end4 = Instant.now();
        Duration timeElapsed4 = Duration.between(start4, end4);
        System.out.println("Time elapsed for 10M delete: " + timeElapsed4.toMillis() + " milliseconds");
//
//        AVLTree.inorder();
//        System.out.println("\n");
//
//        for (int i = 0; i < 100000; i++){
//            AVLTree.delete((int)Math.floor(Math.random() * (100000 - 2 + 1) + 2));
//        }        //AVLTree.inorder();
//        AVLTree.search(9);
//        RedBlackTree RedBlackTree = new RedBlackTree();
//        RedBlackTree.insert(5);
//        RedBlackTree.insert(45);
//        RedBlackTree.insert(10);
//        RedBlackTree.insert(7);
//        RedBlackTree.insert(12);
//        RedBlackTree.insert(6);
//        RedBlackTree.insert(95);
//        RedBlackTree.insert(19);
//        RedBlackTree.insert(37);
//
////        RedBlackTree.insert(1);
////        RedBlackTree.insert(2);
////        RedBlackTree.insert(3);
////        RedBlackTree.insert(4);
////        RedBlackTree.insert(5);
////        RedBlackTree.insert(6);
//
//
//
//
//        //RedBlackTree.inorder();
////        long startTime = System.currentTimeMillis();
////
//       RedBlackTree.printPreorder();
////        for (int i = 0; i < 10000000; i++){
////            RedBlackTree.insert((int)Math.floor(Math.random() * (10000000 - 2 + 1) + 2));
////        }
////
////        long endTime = System.currentTimeMillis();
////        long duration = (endTime - startTime);
////        System.out.format("Milli = %s, ( S_Start : %s, S_End : %s ) \n", duration, startTime, endTime );
//
//       //RedBlackTree.printPostorder();

    }



}
