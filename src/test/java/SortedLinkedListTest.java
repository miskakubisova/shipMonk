import com.shipmonk.SortedLinkedList;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SortedLinkedListTest {

    @Test
    void addElementsAndCheckOrder() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.add(3);
        list.add(1);
        list.add(2);

        assertEquals(1, list.first());
        assertEquals(2, list.get(1));
        assertEquals(3, list.last());
        assertEquals(3, list.size());
    }

    @Test
    void isEmptyOnNewList() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        assertTrue(list.isEmpty());
    }

    @Test
    void sizeAfterAddingElements() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.add(10);
        list.add(20);
        assertEquals(2, list.size());
    }

    @Test
    void getElementsByIndex() {
        SortedLinkedList<String> list = new SortedLinkedList<>();
        list.add("banana");
        list.add("apple");
        list.add("cherry");

        assertEquals("apple", list.get(0));
        assertEquals("banana", list.get(1));
        assertEquals("cherry", list.get(2));
    }

    @Test
    void throwsExceptionForInvalidIndex() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.add(1);

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(1); // Should throw IndexOutOfBoundsException
        });

        String expectedMessage = "Index: 1, Size: 1";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void checkOrderAfterAddingMultipleElements() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.add(5);
        list.add(3);
        list.add(8);
        list.add(1);

        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
        assertEquals(8, list.get(3));
    }

    @Test
    void testAdditionOfNulls() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> list.add(null));
        assertEquals("Null values are not allowed in this list.", exception.getMessage());
    }

    @Test
    void testAddingDuplicates() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.add(10);
        list.add(5);
        list.add(10);

        assertEquals(3, list.size(), "List should allow duplicates and have a size of 3.");
        assertEquals(5, list.first(), "First element should be the smallest.");
        assertEquals(10, list.last(), "Last element should be the largest, considering duplicates.");
    }

    @Test
    void testOutOfBoundsAccess() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(2);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1), "Accessing an index below 0 should throw IndexOutOfBoundsException");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2), "Accessing an index equal to size should throw IndexOutOfBoundsException");
    }

    @Test
    void testListIsEmpty() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        assertTrue(list.isEmpty(), "Newly created list should be empty.");

        list.add(1);
        assertFalse(list.isEmpty(), "List should not be empty after adding an element.");
    }

    @Test
    void testRemoveElements() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.remove(Integer.valueOf(2)), "Should successfully remove existing element.");
        assertFalse(list.contains(2), "List should not contain the removed element.");
        assertEquals(2, list.size(), "List size should decrease after removal.");
    }

    @Test
    void addAndIterateCustomType() {
        class CustomType implements Comparable<CustomType> {
            int value;

            CustomType(int value) {
                this.value = value;
            }

            @Override
            public int compareTo(CustomType o) {
                return Integer.compare(this.value, o.value);
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                CustomType that = (CustomType) obj;
                return value == that.value;
            }
        }

        SortedLinkedList<CustomType> customList = new SortedLinkedList<>();
        customList.add(new CustomType(5));
        customList.add(new CustomType(1));
        customList.add(new CustomType(3));

        Iterator<CustomType> iterator = customList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next().value, "First element in iteration should have value 1.");
        assertEquals(3, iterator.next().value, "Second element in iteration should have value 3.");
        assertEquals(5, iterator.next().value, "Third element in iteration should have value 5.");
    }
}
