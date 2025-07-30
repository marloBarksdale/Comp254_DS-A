package com.exercise1.lynden.flood;

import com.AbstractMap;
import com.Entry;

import java.util.ArrayList;
import java.util.Random;

/**
 * An abstract base class supporting Map implementations that use hash
 * tables with MAD compression and configurable load factor.
 */
public abstract class LF_AbstractHashMap<K,V> extends AbstractMap<K,V> {
  protected int n = 0;                 // number of entries in the dictionary
  protected int capacity;              // length of the table
  private int prime;                   // prime factor
  private long scale, shift;           // the shift and scaling factors
  private double maxLoadFactor;        // configurable maximum load factor

  /** Creates a hash table with the given capacity, prime factor, and load factor. */
  public LF_AbstractHashMap(int cap, int p, double loadFactor) {
    prime = p;
    capacity = cap;
    maxLoadFactor = loadFactor;
    Random rand = new Random();
    scale = rand.nextInt(prime-1) + 1;
    shift = rand.nextInt(prime);
    createTable();
  }

  /** Creates a hash table with given capacity, load factor, and prime factor 109345121. */
  public LF_AbstractHashMap(int cap, double loadFactor) { 
    this(cap, 109345121, loadFactor); 
  }

  /** Creates a hash table with capacity 17, load factor 0.5, and prime factor 109345121. */
  public LF_AbstractHashMap() { 
    this(17, 109345121, 0.5); 
  }

  /** Creates a hash table with given capacity and prime factor, default load factor 0.5. */
  public LF_AbstractHashMap(int cap, int p) { 
    this(cap, p, 0.5); 
  }

  /** Creates a hash table with given capacity, default load factor 0.5. */
  public LF_AbstractHashMap(int cap) { 
    this(cap, 109345121, 0.5); 
  }

  /** Sets the maximum load factor (must be called before adding entries). */
  public void setMaxLoadFactor(double loadFactor) {
    if (loadFactor <= 0 || loadFactor > 1.0) {
      throw new IllegalArgumentException("Load factor must be between 0 and 1");
    }
    this.maxLoadFactor = loadFactor;
  }

  /** Gets the current maximum load factor. */
  public double getMaxLoadFactor() {
    return maxLoadFactor;
  }

  /** Gets the current load factor. */
  public double getCurrentLoadFactor() {
    return (double) n / capacity;
  }

  // public methods
  @Override
  public int size() { return n; }

  @Override
  public V get(K key) { return bucketGet(hashValue(key), key); }

  @Override
  public V remove(K key) { return bucketRemove(hashValue(key), key); }

  @Override
  public V put(K key, V value) {
    V answer = bucketPut(hashValue(key), key, value);
    if (getCurrentLoadFactor() > maxLoadFactor)        // keep load factor <= maxLoadFactor
      resize(2 * capacity - 1);                        // (or find a nearby prime)
    return answer;
  }

  // private utilities
  /** Hash function applying MAD method to default hash code. */
  private int hashValue(K key) {
    return (int) ((Math.abs(key.hashCode()*scale + shift) % prime) % capacity);
  }

  /** Updates the size of the hash table and rehashes all entries. */
  private void resize(int newCap) {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
    for (Entry<K,V> e : entrySet())
      buffer.add(e);
    capacity = newCap;
    createTable();                     // based on updated capacity
    n = 0;                             // will be recomputed while reinserting entries
    for (Entry<K,V> e : buffer)
      put(e.getKey(), e.getValue());
  }

  // protected abstract methods to be implemented by subclasses
  protected abstract void createTable();
  protected abstract V bucketGet(int h, K k);
  protected abstract V bucketPut(int h, K k, V v);
  protected abstract V bucketRemove(int h, K k);
}