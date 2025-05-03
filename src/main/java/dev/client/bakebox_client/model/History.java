package dev.client.bakebox_client.model;

import java.time.LocalDateTime;

public class History {
    private Long id;
    private String itemName;
    private String boxName;
    private LocalDateTime purchasedAt;

    public History() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getBoxName() { return boxName; }
    public void setBoxName(String boxName) { this.boxName = boxName; }

    public LocalDateTime getPurchasedAt() { return purchasedAt; }
    public void setPurchasedAt(LocalDateTime purchasedAt) { this.purchasedAt = purchasedAt; }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", boxName='" + boxName + '\'' +
                ", purchasedAt=" + purchasedAt +
                '}';
    }
}
