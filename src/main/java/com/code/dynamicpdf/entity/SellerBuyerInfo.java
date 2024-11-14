package com.code.dynamicpdf.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "seller_buyer_info")
public class SellerBuyerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seller;
    private String sellerGstin;
    private String sellerAddress;
    private String buyer;
    private String buyerGstin;
    private String buyerAddress;

    @OneToMany(mappedBy = "sellerBuyerInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;

    public SellerBuyerInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerGstin() {
        return sellerGstin;
    }

    public void setSellerGstin(String sellerGstin) {
        this.sellerGstin = sellerGstin;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyerGstin() {
        return buyerGstin;
    }

    public void setBuyerGstin(String buyerGstin) {
        this.buyerGstin = buyerGstin;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "SellerBuyerInfo{" +
                "id=" + id +
                ", seller='" + seller + '\'' +
                ", sellerGstin='" + sellerGstin + '\'' +
                ", sellerAddress='" + sellerAddress + '\'' +
                ", buyer='" + buyer + '\'' +
                ", buyerGstin='" + buyerGstin + '\'' +
                ", buyerAddress='" + buyerAddress + '\'' +
                ", items=" + items +
                '}';
    }

    public SellerBuyerInfo(Long id, String seller, String sellerGstin, String sellerAddress, String buyer, String buyerGstin, String buyerAddress, List<Item> items) {
        this.id = id;
        this.seller = seller;
        this.sellerGstin = sellerGstin;
        this.sellerAddress = sellerAddress;
        this.buyer = buyer;
        this.buyerGstin = buyerGstin;
        this.buyerAddress = buyerAddress;
        this.items = items;


    }
}
