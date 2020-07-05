package model;

public class Fish {

    private Integer id;
    private String name;
    private Double length;
    private Double weight;

    public Fish(Integer id, String name, Double length, Double weight) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", weight=" + weight +
                '}';
    }
}
