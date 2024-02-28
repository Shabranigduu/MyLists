import java.util.Arrays;
import java.util.Comparator;

/**
 * Кастомная реализация ArrayList
 *
 * @param <T> Тип принимаемых объектов
 * @author Dmitriy Karpievich
 */
public class MyArrayList<T> implements MyList<T> {

    private Object[] array = {};

    private int size;

    public MyArrayList() {
    }

    /**
     * Создание списка с заданным размером внутреннего массива. Используется если заранее известно количество элементов списка.
     *
     * @param capacitySize Размер внутреннего массива
     * @throws IllegalArgumentException Если переданный размер меньше нуля.
     */
    public MyArrayList(int capacitySize) {
        if (capacitySize >= 0) {
            this.array = new Object[capacitySize];
        } else {
            throw new IllegalArgumentException("Некорректная ёмкость: " + capacitySize);
        }
    }

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
        checkLength();
        array[size] = t;
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
        checkLength();
        System.arraycopy(array, index,
                array, index + 1,
                size - index);
        array[index] = t;
        size++;
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
        return (T) array[index];
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
        System.arraycopy(array, index + 1,
                array, index,
                size - (index + 1));
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
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(array[i])) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Полноя очистка списка
     */
    @Override
    public void clear() {
        array = new Object[]{};
        size = 0;
    }

    /**
     * Сортировка списка по естественному порядку. Параметризованный класс должен реализовывать интерфейс Comparable и не содержать пустых элементов(null).
     *
     * @throws IllegalArgumentException Если параметризированный класс не имеет реализации интерфейса Comparable
     */
    @Override
    public void sort() {
        for (int i = 0; i < size; i++) {
            if (array[i] != null) {
                if (!(array[i] instanceof Comparable)) {
                    throw new IllegalArgumentException("Тип " + array[i].getClass().getName() + " должен реализовывать интерфейс Comparable");
                }
            } else throw new RuntimeException("Для сортировки список не должен содержать null");
        }
        Arrays.sort(array, 0, size);
    }

    /**
     * Сортировка списка по переданному компаратору. Список не должен содержать пустых элементов (null).
     *
     * @param comparator Компаратор для сортировки
     */
    @Override
    public void sort(Comparator<? super T> comparator) {
        for (int i = 0; i < size; i++) {
            if (array[i] == null) {
                throw new RuntimeException("Для сортировки список не должен содержать null");
            }
        }
        Arrays.sort((T[]) array, 0, size, comparator);
    }

    /**
     * Инициализация начального внутреннего массива в 10 элементов. Иначе увлечение размера внутреннего массива в два раза.
     */
    private void increaseCapacity() {
        if (array.length == 0) {
            array = new Object[10];
        } else {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    /**
     * Проверка размера внутренного массива. В случае если размер внутреннего массива равен размеру списка - увеличение размера внутреннго массива.
     */
    private void checkLength() {
        if (array.length == size) {
            increaseCapacity();
        }
    }

    /**
     * Проверка индека на допустимость значния.
     *
     * @param index Передаваемый индекс
     * @throws IndexOutOfBoundsException Если индекс меньше 0 или строго больше размера списка
     */
    private void checkIndex(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                if (array[i] == null) {
                    sb.append("null");
                } else {
                    sb.append(array[i].toString());
                }
            } else {
                if (array[i] == null) {
                    sb.append("null").append(", ");
                } else {
                    sb.append(array[i].toString()).append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
