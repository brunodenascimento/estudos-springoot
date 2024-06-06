package com.projetofinalback.demo.models;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

 @Entity
 @NoArgsConstructor
 @Getter
 @Setter
 @ToString
 @Table(name="Produtos")
public class Product implements Serializable{
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_ID")
    private Long productId;

    @Column(name = "product_NAME", nullable =false)
    private String productName;

    @Column(name="product_DESCRIPTION", nullable =false)
    private String productDescription;

    @Column(name="product_PRICE", nullable =false, unique=true)
    private double productPrice;

    @ManyToOne//muitos para um
    @JoinColumn(name="product_category", nullable = false, referencedColumnName ="categoryID" ) //categoria do produto
    private Category productCategory;

    @ManyToOne//muitos para um
    @JoinColumn(name="sale_by", nullable = false, referencedColumnName = "PartnerStore_ID")
    private PartnerStore saleBy; //produto vendido por loja X
}
