package com.projetofinalback.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import com.projetofinalback.demo.models.Product;
import com.projetofinalback.demo.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

        /*******************************************************************************************************/

       // public void addProductToCategory(Product productId, Category categoryId);//Adicionar um produto a uma categoria


}
