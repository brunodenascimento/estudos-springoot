package com.projetofinalback.demo.controllers;
import com.projetofinalback.demo.models.Product;
import com.projetofinalback.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/products")
public class productController {

    private final ProductService productService;

     //método para adicionar um novo produto ao sistema do banco de dados
     @PostMapping //anotação método de adicionar
     public ResponseEntity<Product> create(@RequestBody Product product){
        Product produto = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
     }
     //método para buscar um novo produto que esteja cadastrado ao sistema do banco de dados pelo id
     @GetMapping("/{idproduct}")
     public ResponseEntity<Product> getById(@PathVariable Long idproduct){
         Product produto = productService.searchById(idproduct);
         return ResponseEntity.ok(produto);
     }
 
     //método para alterar o nome de um produto
     @PatchMapping("/{idproduct}/updateproductname")
     public ResponseEntity<Product> updateProductName(@PathVariable Long idproduct, @RequestBody Product productname){
         Product produto = productService.updateProductName(idproduct, productname.getProductName());
         return ResponseEntity.ok(produto);
     }
     
     //método para alterar a descrição de um produto
     @PatchMapping("/{idproduct}/updateproductdescription")
     public ResponseEntity<Product> updateProductDescription(@PathVariable Long idproduct, @RequestBody Product productdescription){
         Product produto = productService.updateProductDescription(idproduct, productdescription.getProductDescription());
         return ResponseEntity.ok(produto);
     }
     
     //método para alterar o preço de um produto
     @PatchMapping("/{idproduct}/updateproductprice")
     public ResponseEntity<Product> updateProductPrice(@PathVariable Long idproduct, @RequestBody Product productprice){
         Product produto = productService.updateProductPrice(idproduct, productprice.getProductPrice());
         return ResponseEntity.ok(produto);
     }

     //método para deletar um produto
    @DeleteMapping("/{idproduct}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long idproduct){
    productService.deleteProduct(idproduct);
    return ResponseEntity.status(HttpStatus.OK).body("Produto selecionado foi deletada com sucesso.");}
 
     //método para listar todos os produtos cadastrados
     @GetMapping
     public ResponseEntity<List<Product>> getAll(){
         List<Product> produtos = productService.searchAll();
         return ResponseEntity.ok(produtos);
     }

}
