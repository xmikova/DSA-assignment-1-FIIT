//The implementation of the hash table with quadratic probing (open addressing) collision resolution and its functionalities: put, remove, and get.
//Petra Miková, ID: 120852, summer term 22/23 - DSA

public class QuadraticProbingHashTable<Key, Value> {
    static class QuadraticProbingHashTableNode<Key, Value> {
        Key key;
        Value value;
        QuadraticProbingHashTableNode<Key, Value> next;


        public QuadraticProbingHashTableNode(Key key, Value value) { //Constructor for a "node" in a hash table.
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private static final int def_capacity = 16;
    private static final float def_lf = 0.75f;
    private static final float min_lf = 0.25f;
    private QuadraticProbingHashTableNode<Key, Value>[] table;
    private int size;
    private int capacity;
    private float loadFactor;
    private int threshold;

    public QuadraticProbingHashTable() { //constructor
        this.loadFactor = def_lf;
        this.capacity = def_capacity;
        this.threshold = (int) (capacity * loadFactor);
        this.table = (QuadraticProbingHashTableNode<Key, Value>[]) new QuadraticProbingHashTableNode[capacity]; //Create a hash table with the nodes and a capacity given.
        this.size = 0;
    }

    private int hash(String key, int capacity) { //A hash method that takes the hashcode computed and modulo it with capacity to return the index for key.
        int hashCode = hashFunction(key);
        int index = hashCode % capacity;
        return index < 0 ? (index + capacity) % capacity : index; //To overcome getting outbound index errors.
    }

    private int hashFunction(String key) { //A hashing function inspired with FNV1 hashing function.
        int hash = 0x811c9dc5;
        for (int i = 0; i < key.length(); i++) {
            hash ^= key.charAt(i);
            hash *= 0x01000193;
        }
        return hash;
    }

    private int getIndex(Key key, int capacity) { //Index getter method - simply returns the index.
        int index = hash(key.toString(), capacity);
        return index;
    }

    public void put(Key key, Value value) {
        int index = getIndex(key, capacity);
        int i = 0; //The initial probe number is set to 0.

        while (table[index] != null && !table[index].key.equals(key)) { //Case where the slot is not empty and the key does not match the key present in this slot.
            i++;
            index = (index + i * i) % capacity; //Quadratic probing - adding the square of probe number to current index and modulo this with capacity.
        }

        if (table[index] != null && table[index].key.equals(key)) {
            table[index].value = value; //Update the value if the key is equal to the one in the slot.
        } else {
            table[index] = new QuadraticProbingHashTableNode<>(key, value); //Put the key into an empty slot at computed index.
            size++;

            if (size >= threshold) { //Upsize if the size of current table exceeds the threshold.
                Upsize();
            }
        }
    }

    public void remove(Key key) {
        //Firstly, compute the index.
        int index = getIndex(key, capacity);
        int i = 0;

        while (table[index] != null && !table[index].key.equals(key)) {
            i++;
            index = (index + i * i) % capacity;
        }

        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null; //Set the slot to null if the key to be deleted was found.
            size--;

            //Reinsert all the other elements to ensure quadratic probing still works correctly.
            int nextIndex = (index + 1) % capacity;
            while (table[nextIndex] != null) {
                QuadraticProbingHashTableNode<Key, Value> node = table[nextIndex];
                table[nextIndex] = null; //Set the current slot where the key is to null.
                size--;
                put(node.key, node.value); //Put it into table with its new index as the size changed.
                nextIndex = (nextIndex + 1) % capacity;
                if (size < min_lf * capacity) {
                    Downsize(); //Downsize the table if its size is less than the minimal load factor multiplied by capacity of current table.
                }
            }
        }
    }


    public Value get(Key key) {
        int index = getIndex(key, capacity);
        int i = 0;

        while (table[index] != null && !table[index].key.equals(key)) {
            i++;
            index = (index + i * i) % capacity;
        }

        //After the index is computed, search for the key and return its value, else return null.
        if (table[index] != null && table[index].key.equals(key)) {
            return table[index].value;
        } else {
            return null;
        }
    }


    private void Upsize() {
        int newCapacity = capacity * 2; //Double the current capacity.
        threshold = (int) (newCapacity * loadFactor);
        QuadraticProbingHashTableNode<Key, Value>[] newTable = new QuadraticProbingHashTableNode[newCapacity]; //Create the new, bigger table.

        //Rehashing of all the current elements.
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                int index = getIndex(table[i].key, newCapacity);
                int j = 1;
                while (newTable[index] != null) {
                    index = (index + j * j) % newCapacity;
                    j++;
                }
                newTable[index] = table[i];
            }
        }

        //Update the variables table and capacity.
        table = newTable;
        capacity = newCapacity;
    }

    private void Downsize() {
        int newCapacity = capacity / 2; //Halve the current capacity.
        threshold = (int) (newCapacity * loadFactor);
        QuadraticProbingHashTableNode<Key, Value>[] newTable = (QuadraticProbingHashTableNode<Key, Value>[]) new QuadraticProbingHashTableNode[newCapacity];

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                int index = getIndex(table[i].key, newCapacity);
                int j = 1;
                while (newTable[index] != null) {
                    index = (index + j * j) % newCapacity;
                    j++;
                }
                newTable[index] = table[i];
            }
        }

        //Update the variables table and capacity.
        table = newTable;
        capacity = newCapacity;
    }
}