package dev.client.bakebox_client.model;

import java.util.List;

public class Box {
    private Long id;
    private String boxName;
    private int boxPrice;
    private List<Item> items;

    public Box() {}

    public Box(String boxName, int boxPrice, List<Item> items) {
        this.boxName = boxName;
        this.boxPrice = boxPrice;
        this.items = items;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBoxName() { return boxName; }
    public void setBoxName(String boxName) { this.boxName = boxName; }

    public int getBoxPrice() { return boxPrice; }
    public void setBoxPrice(int boxPrice) { this.boxPrice = boxPrice; }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", boxName='" + boxName + '\'' +
                ", boxPrice=" + boxPrice +
                ", items=" + items +
                '}';
    }
}
