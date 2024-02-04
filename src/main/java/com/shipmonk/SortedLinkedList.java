package com.shipmonk;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A sorted linked list implementation that maintains its elements in a sorted order
 * based on their natural ordering. This class only accepts elements that implement
 * the Comparable interface like Integer and String.
 *
 * @param <T> the type of elements held in this list, which must implement Comparable.
 */
public class SortedLinkedList<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> head; // The first node of the list
    private Node<T> tail; // The last node of the list, for efficient adds at the end
    private int size = 0;  // The number of elements in the list

    /**
     * A node represents an element in the list, holding the value and a reference
     * to the next node in the list.
     */
    private static class Node<T> {
        T value; // The value stored in the node
        Node<T> next; // Reference to the next node

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    /**
     * Adds a new element to the list in its sorted position.
     *
     * @param value the value to be added to the list.
     * @throws IllegalArgumentException if the value is null.
     */
    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Null values are not allowed in this list.");
        }

        Node<T> newNode = new Node<>(value);
        // If the list is empty or the new value goes before the head, adjust the head reference.
        if (head == null || head.value.compareTo(value) > 0) {
            newNode.next = head;
            head = newNode;
            if (tail == null) {
                tail = head;
            }
        } else {
            // Otherwise, find the correct position for the new value.
            Node<T> current = head;
            while (current.next != null && current.next.value.compareTo(value) < 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            // If the new node is added at the end, update the tail reference.
            if (current == tail) {
                tail = newNode;
            }
        }
        size++;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param value the element to be removed from the list
     * @return true if the list contained the specified element
     */
    public boolean remove(T value) {
        if (head == null) return false;

        if (head.value.compareTo(value) == 0) {
            head = head.next;
            if (head == null) {
                tail = null; // List is now empty
            }
            size--;
            return true;
        }

        Node<T> current = head;
        while (current.next != null && current.next.value.compareTo(value) != 0) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            if (current.next == null) {
                tail = current; // Updated the tail if the last element was removed
            }
            size--;
            return true;
        }

        return false;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param value the element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns a string representation of the elements in this list, in the order they appear.
     *
     * @return a string representation of the list contents
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list contains no elements.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Retrieves an element at a specified position in the list.
     *
     * @param index the position of the element to retrieve.
     * @return the element at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    /**
     * Returns the first element of the list.
     *
     * @return the first element in the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public T first() {
        if (head == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        return head.value;
    }

    /**
     * Returns the last element of the list.
     *
     * @return the last element in the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public T last() {
        if (tail == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        return tail.value;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new SortedLinkedListIterator();
    }

    /**
     * An iterator for the SortedLinkedList.
     */
    private class SortedLinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T value = current.value;
            current = current.next;
            return value;
        }
    }
}
