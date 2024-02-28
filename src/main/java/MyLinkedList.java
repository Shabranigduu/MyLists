import java.util.Arrays;
import java.util.Comparator;

public class MyLinkedList<T> implements MyList<T> {

    private Node<T> head;

    private int size;

    /**
     * Размер списка
     *
     * @return Количество элементов списка
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Добавление элемента в конец списка
     *
     * @param t Элемент для добавления в список
     */
    @Override
    public void add(T t) {
        Node<T> newNode = new Node<>(t);
        if (size == 0) {
            head = newNode;
        } else {

            Node<T> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        size++;
    }

    /**
     * Добавление элемента в список по индексу, со сдвигом элементов после индекса
     *
     * @param t     Элемент для добавления в список
     * @param index Индекс добавляемого элемента
     * @throws IndexOutOfBoundsException Если индекс меньше 0 или больше размера списка
     */
    @Override
    public void add(T t, int index) {
        if (index == size) {
            add(t);
        } else {
            checkIndex(index);
            Node<T> newNode = new Node<>(t);
            Node<T> currentNode = head;
            if (index == 0) {
                head = newNode;
                head.next = currentNode;
            } else {
                for (int i = 0; i < index - 1; i++) {
                    currentNode = currentNode.next;
                }
                newNode.next = currentNode.next;
                currentNode.next = newNode;
            }
            size++;
        }
    }

    /**
     * Получение элемента списка по индексу
     *
     * @param index Индекс элемента
     * @return Элемент списка
     * @throws IndexOutOfBoundsException Если индекс меньше 0 или строго больше размера списка
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        if (index == 0) {
            return head.obj;
        }
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.obj;
    }

    /**
     * Удалние элемента по индексу
     *
     * @param index Индекс элемента
     * @throws IndexOutOfBoundsException Если индекс меньше 0 или строго больше размера списка
     */
    @Override
    public void remove(int index) {
        checkIndex(index);
        Node<T> currentNode = head;
        if (index == 0) {
            head = currentNode.next;
        } else {
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            currentNode.next = currentNode.next.next;
        }
        size--;
    }

    /**
     * Удаление первого соответсвующего объекту элемента, в том числе null
     *
     * @param o Объект для удаления из списка
     * @return true если объект удален, false если объект в списке отсутствует
     */
    @Override
    public boolean remove(Object o) {
        Node<T> currentNode = head;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (currentNode.obj == null) {
                    remove(i);
                    return true;
                }
                currentNode = currentNode.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(currentNode.obj)) {
                    remove(i);
                    return true;
                }
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    /**
     * Полноя очистка списка
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Сортировка списка по естественному порядку. Параметризованный класс должен реализовывать интерфейс Comparable и не содержать пустых элементов(null).
     *
     * @throws IllegalArgumentException Если параметризированный класс не имеет реализации интерфейса Comparable
     */
    @Override
    public void sort() {
        T[] array = (T[]) new Object[size];
        Node<T> currentNode = head;
        for (int i = 0; i < size; i++) {
            if (currentNode.obj == null) {
                throw new RuntimeException("Для сортировки список не должен содержать null");
            }
            if (!(currentNode.obj instanceof Comparable)) {
                throw new IllegalArgumentException("Тип " + currentNode.obj.getClass().getName() + " должен реализовывать интерфейс Comparable");
            }
            array[i] = currentNode.obj;
            currentNode = currentNode.next;
        }
        Arrays.sort(array);
        clear();
        for (T t : array) {
            add(t);
        }
    }

    /**
     * Сортировка списка по переданному компаратору. Список не должен содержать пустых элементов (null).
     *
     * @param comparator Компаратор для сортировки
     */
    @Override
    public void sort(Comparator<? super T> comparator) {
        T[] array = (T[]) new Object[size];
        Node<T> currentNode = head;
        for (int i = 0; i < size; i++) {
            if (currentNode.obj == null) {
                throw new RuntimeException("Для сортировки список не должен содержать null");
            }
            array[i] = currentNode.obj;
            currentNode = currentNode.next;
        }
        Arrays.sort(array, comparator);
        clear();
        for (T t : array) {
            add(t);
        }
    }

    /**
     * Проверка индека на допустимость значния.
     *
     * @param index Передаваемый индекс
     * @throws IndexOutOfBoundsException Если индекс меньше 0 или строго больше размера списка
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> currentNode = head;
        if (head == null) {
            return sb.append("]").toString();
        } else {
            sb.append(currentNode.obj == null ? "null" : currentNode.obj.toString());
        }
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            sb.append(currentNode.obj == null ? ", null" : ", " + currentNode.obj);
        }
        return sb.append("]").toString();
    }

    private static class Node<T> {
        T obj;
        Node<T> next;

        public Node(T obj) {
            this.obj = obj;
            this.next = null;
        }
    }
}
