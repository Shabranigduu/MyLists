import classForTestList.Example;
import classForTestList.ExampleWithComparable;
import classForTestList.SortById;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    MyList<Integer> emptyIntegerList = new MyArrayList<>();
    int emptyIntegerListSize;
    MyList<Integer> integerList = new MyArrayList<>();
    int integerListSize;
    MyList<Integer> integerListWithNullElements = new MyArrayList<>();
    int integerListWithNullElementsSize;
    MyList<Example> exampleList = new MyArrayList<>();
    int exampleListSize;
    MyList<ExampleWithComparable> exampleListWithComparable = new MyArrayList<>();
    int exampleWithComparableSize;

    @BeforeEach
    void prepare() {
        integerList.add(3);
        integerList.add(1);
        integerList.add(8);
        integerList.add(2);
        integerListSize = 4;

        integerListWithNullElements.add(3);
        integerListWithNullElements.add(null);
        integerListWithNullElements.add(5);
        integerListWithNullElements.add(1);
        integerListWithNullElements.add(null);
        integerListWithNullElementsSize = 5;

        exampleList.add(new Example(3));
        exampleList.add(new Example(1));
        exampleList.add(new Example(8));
        exampleList.add(new Example(2));
        exampleListSize = 4;

        exampleListWithComparable.add(new ExampleWithComparable(3));
        exampleListWithComparable.add(new ExampleWithComparable(1));
        exampleListWithComparable.add(new ExampleWithComparable(8));
        exampleListWithComparable.add(new ExampleWithComparable(2));
        exampleWithComparableSize = 4;
    }


    @Test
    void size() {
        assertAll(() -> assertEquals(emptyIntegerListSize, emptyIntegerList.size(), "Размер пустого списка должен быть равен 0"),
                () -> assertEquals(integerListSize, integerList.size()),
                () -> assertEquals(integerListWithNullElementsSize, integerListWithNullElements.size()),
                () -> assertEquals(exampleListSize, exampleList.size()));
    }

    @Test
    void add() {
        integerList.add(9);
        assertAll(() -> assertEquals(integerListSize + 1, integerList.size()),
                () -> assertEquals(9, integerList.get(integerList.size() - 1)));
    }

    @Test
    void addWithIndexZero() {
        integerList.add(9, 0);
        assertAll(() -> assertEquals(integerListSize + 1, integerList.size()),
                () -> assertEquals(9, integerList.get(0)));
    }

    @Test
    void addWithIndexOutOfBound() {
        assertAll(() -> assertThrows(IndexOutOfBoundsException.class, () -> integerList.add(9, 7)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> integerList.add(9, -1)));

    }

    @Test
    void get() {
        assertAll(() -> assertEquals(3, integerList.get(0)),
                () -> assertEquals(2, integerList.get(3)),
                () -> assertSame(null, integerListWithNullElements.get(1)),
                () -> assertEquals(5, integerListWithNullElements.get(2)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> integerList.get(10)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> emptyIntegerList.get(0)),
                () -> assertInstanceOf(Example.class, exampleList.get(0)));
    }

    @Test
    void remove() {
        integerList.remove(0);
        integerListWithNullElements.remove(1);
        exampleList.remove(2);

        assertAll(() -> assertEquals(integerListSize - 1, integerList.size()),
                () -> assertEquals(integerListWithNullElementsSize - 1, integerListWithNullElements.size()),
                () -> assertEquals(exampleListSize - 1, exampleList.size()),
                () -> assertNotEquals(3, integerList.get(0)));

    }

    @Test
    void removeObject() {
        integerList.remove(Integer.valueOf(8));
        integerListWithNullElements.remove(null);
        Example elementToRemove = exampleList.get(2);
        exampleList.remove(elementToRemove);

        assertAll(() -> assertEquals(integerListSize - 1, integerList.size()),
                () -> assertEquals(integerListWithNullElementsSize - 1, integerListWithNullElements.size()),
                () -> assertEquals(exampleListSize - 1, exampleList.size()),
                () -> assertNotEquals(elementToRemove, exampleList.get(2)),
                () -> assertTrue(integerList.remove(Integer.valueOf(2))),
                () -> assertFalse(integerList.remove(Integer.valueOf(11))));
    }

    @Test
    void clear() {
        integerList.clear();
        integerListWithNullElements.clear();
        exampleList.clear();

        assertAll(() -> assertEquals(emptyIntegerList.size(), integerList.size()),
                () -> assertEquals(0, integerListWithNullElements.size()),
                () -> assertEquals(0, exampleList.size()),
                () -> assertEquals("[]", integerList.toString()),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> integerList.get(0)));
    }

    @Test
    void sort() {
        integerList.sort();
        exampleListWithComparable.sort();

        assertAll(()->assertEquals(1, integerList.get(0)),
                ()-> assertEquals("[1, 2, 3, 8]", integerList.toString()),
                ()->assertEquals(1, exampleListWithComparable.get(0).getId()),
                ()-> assertEquals("[Example{id=1}, Example{id=2}, Example{id=3}, Example{id=8}]", exampleListWithComparable.toString()),
                ()->assertThrows(RuntimeException.class, ()-> integerListWithNullElements.sort()),
                ()-> assertThrows(IllegalArgumentException.class, ()-> exampleList.sort()));
    }

    @Test
    void sortWithComparator() {
        exampleList.sort(new SortById());

        assertAll(()->assertEquals(1, exampleList.get(0).getId()),
                ()-> assertEquals("[Example{id=1}, Example{id=2}, Example{id=3}, Example{id=8}]", exampleList.toString()));
    }
}