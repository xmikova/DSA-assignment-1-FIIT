//The implementation of the hash table with separate chaining collision resolution and its functionalities: put, remove, and get.
//Petra Miková, ID: 120852, summer term 22/23 - DSA

public class ChainingHashTable<Key, Value> {
     static class ChainingHashTableNode<Key, Value> {
        Key key;
        Value value;
        ChainingHashTableNode<Key, Value> next;


         public ChainingHashTableNode(Key key, Value value) { //Constructor for a "node" in a hash table.
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private static final int def_capacity = 16;
    private static final float def_lf = 0.75f;
    private static final float min_lf = 0.25f;
    private ChainingHashTableNode<Key, Value>[] table;
    private int size;
    private int capacity;
    private float loadFactor;
    private int threshold;

    public ChainingHashTable() { //constructor
        this.loadFactor = def_lf;
        this.capacity = def_capacity;
        this.threshold = (int) (capacity * loadFactor);
        this.table = (ChainingHashTableNode<Key, Value>[]) new ChainingHashTableNode[capacity]; //Create a hash table with the nodes and a capacity given.
        this.size = 0;
    }


    private int hash(String key, int capacity) { //A hash method that takes the hashcode computed and modulo it with capacity to return the index for key.
        int hashCode = hashFunction(key);
        int index = hashCode % capacity;
        return index < 0 ? index + capacity : index; //To overcome getting outbound index errors.
    }

    private int hashFunction(String key) { //A hashing function inspired with FNV1 hashing function.
        int hash = 0x811c9dc5;
        for (int i = 0; i < key.length(); i++) {
            hash ^= key.charAt(i); //XOR the hash with character at ith position in key.
            hash *= 0x01000193; //Multiply the hash with a huge prime.
        }
        return hash;
    }

    private int getIndex(Key key, int capacity){ //Index getter method - simply returns the index.
        int index = hash(key.toString(),capacity);
        return index;
    }

    public void put(Key key, Value value) {
        int index = getIndex(key, capacity);
        ChainingHashTableNode<Key, Value> node = table[index];
        while (node != null && !node.key.equals(key)) { //Separate chaining, traverse the linked list and add the new key at the end of it.
            node = node.next;
        }
        if (node != null) { //If the key was found in chain, update its value.
            node.value = value;
        } else { //If the key was not found in chain, add new node to front of chain.
            ChainingHashTableNode<Key, Value> newNode = new ChainingHashTableNode<>(key, value);
            newNode.next = table[index];
            table[index] = newNode;
            size++;
            if (size >= threshold) { //Upsize if the size of current table exceeds the threshold.
                Upsize();
            }
        }
    }
    public void remove(Key key) {
        int index = getIndex(key,capacity);
        ChainingHashTableNode<Key,Value> node = table[index];
        ChainingHashTableNode<Key, Value> prevNode = null;
        while (node != null) { //Traverse the linked list in slot at computed index.
            if (node.key.equals(key)) { //If the key to be deleted was found.
                if (prevNode == null) table[index] = node.next; //Case where key is the root of linked list at the slot.
                else {
                    prevNode.next = node.next; //Else skip over the current node in each iteration with the previous node's pointer.
                }
                size--;
                if (size < min_lf * capacity) {
                    Downsize(); //Downsize the table if its size is less than the minimal load factor multiplied by capacity of current table.
                }
            }
            prevNode = node; //Update the pointers in each iteration.
            node = node.next;
        }
    }

    public Value get(Key key) {
        int index = getIndex(key,capacity);
        ChainingHashTableNode<Key, Value> node = table[index];
        while (node != null) { //Loop through the "chain" at given index and return the key's value found or null if there was no such key found.
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }


    private void Upsize() {
        int newCapacity = capacity * 2; //Double the current capacity.
        threshold = (int) (newCapacity * loadFactor);
        ChainingHashTableNode<Key, Value>[] newTable = new ChainingHashTableNode[newCapacity]; //Create the new, bigger table.

        //Rehashing of all the current keys.
        for (int i = 0; i < this.capacity; i++) {
            ChainingHashTableNode<Key, Value> node = table[i];
            while (node != null) {
                ChainingHashTableNode<Key, Value> next = node.next;
                int newIndex = getIndex(node.key, capacity);
                node.next = newTable[newIndex];
                newTable[newIndex] = node;
                node = next;
            }
        }

        //Update the variables table and capacity.
        table = newTable;
        capacity = newCapacity;
    }

    private void Downsize() {
        int newCapacity = capacity / 2; //Halve the current capacity.
        threshold = (int) (newCapacity * loadFactor);
        ChainingHashTableNode<Key, Value>[] newTable = new ChainingHashTableNode[newCapacity]; //Create the new, smaller table.

        //Rehashing of all the current keys.
        for (int i = 0; i < this.capacity; i++) {
            ChainingHashTableNode<Key, Value> node = table[i];
            while (node != null) {
                ChainingHashTableNode<Key, Value> next = node.next;
                int newIndex = getIndex(node.key, capacity);
                node.next = newTable[newIndex];
                newTable[newIndex] = node;
                node = next;
            }
        }

        //Update the variables table and capacity.
        table = newTable;
        capacity = newCapacity;
    }
}