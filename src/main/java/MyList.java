import java.util.Comparator;

public interface MyList<T> {
    /**
     * Размер списка
     *
     * @return Количество элементов списка
     */
    int size();

    /**
     * Добавление элемента в конец списка
     *
     * @param t Элемент для добавления в список
     */
    void add(T t);

    /**
     * Добавление элемента в список по индексу, со сдвигом элементов после индекса
     *
     * @param t Элемент для добавления в список
     * @param index Индекс добавляемого элемента
     * @throws IndexOutOfBoundsException Если индекс меньше 0 или больше размера списка
     */
    void add(T t, int index);

    /**
     * Получение элемента списка по индексу
     *
     * @param index Индекс элемента
     * @return Элемент списка
     * @throws IndexOutOfBoundsException Если индекс меньше 0 или строго больше размера списка
     */
    T get(int index);

    /**
     * Удалние элемента по индексу
     *
     * @param index Индекс элемента
     * @throws IndexOutOfBoundsException Если индекс меньше 0 или строго больше размера списка
     */
    void remove(int index);

    /**
     * Удаление первого соответсвующего объекту элемента, в том числе null
     *
     * @param o Объект для удаления из списка
     * @return true если объект удален, false если объект в списке отсутствует
     */
    boolean remove(Object o);

    /**
     * Полноя очистка списка
     */
    void clear();

    /**
     * Сортировка списка по естественному порядку. Параметризованный класс должен реализовывать интерфейс Comparable и не содержать пустых элементов(null).
     *
     * @throws IllegalArgumentException Если параметризированный класс не имеет реализации интерфейса Comparable
     */
    void sort();

    /**
     * Сортировка списка по переданному компаратору. Список не должен содержать пустых элементов (null).
     *
     * @param comparator Компаратор для сортировки
     */
    void sort(Comparator<? super T> comparator);

}
