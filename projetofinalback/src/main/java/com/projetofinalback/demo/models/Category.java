package com.projetofinalback.demo.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.*;

 @Entity
 @NoArgsConstructor
 @Getter
 @Setter
 @ToString
 @Table(name="Categorias")
public class Category implements Serializable{
    
    @Id//gerado automaticamente não precisa preencher no crud
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categoryID")
    private Long categoryId;

    @Column(name="categoryName", nullable =false, unique=true, length = 25)
    private String categoryName;

       @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryId, category.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(categoryId);
    }
}
