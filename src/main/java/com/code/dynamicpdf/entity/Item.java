package com.code.dynamicpdf.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String quantity;
    private double rate;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "seller_buyer_info_id")
    private SellerBuyerInfo sellerBuyerInfo;

    public Item() {
    }

    public Item(Long id, String name, String quantity, double rate, double amount, SellerBuyerInfo sellerBuyerInfo) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
        this.sellerBuyerInfo = sellerBuyerInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public SellerBuyerInfo getSellerBuyerInfo() {
        return sellerBuyerInfo;
    }

    public void setSellerBuyerInfo(SellerBuyerInfo sellerBuyerInfo) {
        this.sellerBuyerInfo = sellerBuyerInfo;
    }



    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", rate=" + rate +
                ", amount=" + amount +
                ", sellerBuyerInfo=" + sellerBuyerInfo +
                '}';
    }
}
