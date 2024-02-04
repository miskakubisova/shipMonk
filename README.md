# SortedLinkedList Project For ShipMonk

## Overview

The SortedLinkedList project provides a Java implementation of a sorted linked list. This list maintains its elements in sorted order, according to their natural ordering or an optional `Comparator`. The list supports generic types, provided they implement the `Comparable` interface. It offers basic operations such as add, remove, and contains, as well as utility functions like size, isEmpty, get, first, and last.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 1.8 or higher

### Installation

Clone the repository to your local machine:

```bash
git clone https://github.com/miskakubisova/shipMonk
```

Navigate to the project directory:
```bash
cd shipMonk
```

Run the unit test provided to show usage of functionality [SortedLinkedListTest](src/test/java/SortedLinkedListTest.java)

## Usage
### Creating a SortedLinkedList
```java
SortedLinkedList<Integer> intList = new SortedLinkedList<>();
SortedLinkedList<String> stringList = new SortedLinkedList<>();
```

### Adding Elements
```java
intList.add(3);
intList.add(1);
intList.add(2);

stringList.add("banana");
stringList.add("apple");
stringList.add("cherry");
```

### Removing Elements
```java
intList.remove(2); // Removes the element '2'
stringList.remove("banana"); // Removes the element "banana"
```

### Checking for Element Presence
```java
boolean contains3 = intList.contains(3); // true
boolean containsGrape = stringList.contains("grape"); // false
```

### Iterating Over the List
```java
for (Integer value : intList) {
System.out.println(value);
}

for (String value : stringList) {
System.out.println(value);
}
```
