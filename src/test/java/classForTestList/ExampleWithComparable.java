package classForTestList;

public class ExampleWithComparable implements Comparable<ExampleWithComparable>{
    private int id;

    public ExampleWithComparable(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Example{" +
                "id=" + id +
                '}';
    }



    @Override
    public int compareTo(ExampleWithComparable o) {
        return id - o.id;
    }
}
