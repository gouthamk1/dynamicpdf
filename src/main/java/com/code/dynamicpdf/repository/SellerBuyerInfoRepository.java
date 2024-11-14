package com.code.dynamicpdf.repository;


import com.code.dynamicpdf.entity.SellerBuyerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerBuyerInfoRepository extends JpaRepository<SellerBuyerInfo, Long> {
    SellerBuyerInfo findBySellerAndBuyer(String seller, String buyer);
}
