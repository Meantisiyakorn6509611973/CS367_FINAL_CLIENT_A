package dev.client.bakebox_client.model;

public class Item {
    private Long itemId;
    private String itemName;
    private int itemPrice;
    private int itemAmount;
    private Box box; // you can remove this if you don’t need nested box info

    public Item() {}

    public Item(String itemName, int itemPrice, int itemAmount) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemAmount = itemAmount;
    }
    
    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getItemPrice() { return itemPrice; }
    public void setItemPrice(int itemPrice) { this.itemPrice = itemPrice; }

    public int getItemAmount() { return itemAmount; }
    public void setItemAmount(int itemAmount) { this.itemAmount = itemAmount; }

    public Box getBox() { return box; }
    public void setBox(Box box) { this.box = box; }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemAmount=" + itemAmount +
                '}';
    }
}
