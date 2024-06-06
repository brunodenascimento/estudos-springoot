package com.projetofinalback.demo.controllers;
import com.projetofinalback.demo.models.PartnerStore;
import com.projetofinalback.demo.services.PartnerStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/partnerstores") //caminho para acessar o controller e as suas funções das lojas parceiras. para administrar os dados.
public class partnerStoreController {

    private final PartnerStoreService partnerStoreService;


    //método para adicionar uma nova loja parceira
    @PostMapping
    public ResponseEntity<PartnerStore> create(@RequestBody PartnerStore partnerStore){
       PartnerStore lojaparceira = partnerStoreService.save(partnerStore);
       return ResponseEntity.status(HttpStatus.CREATED).body(lojaparceira);
    }

    //método para buscar uma loja cadastrada e parceira nossa
    @GetMapping("/{partnerStoreId}")
    public ResponseEntity<PartnerStore> getById(@PathVariable Long partnerStoreId){
        PartnerStore lojaparceira = partnerStoreService.searchById(partnerStoreId);
        return ResponseEntity.ok(lojaparceira);
    }

    //método para alterar o nome de alguma loja parceira
    @PatchMapping("/{partnerStoreId}/updatenamepartnerstore")
    public ResponseEntity<PartnerStore> updateNamePartnerStore(@PathVariable Long partnerStoreId, @RequestBody PartnerStore partnerStore){
        PartnerStore lojaparceira = partnerStoreService.updateNamePartnerStore(partnerStoreId, partnerStore.getPartnerStoreName());
        return ResponseEntity.ok(lojaparceira);
    }

        //método para alterar a hrl de alguma loja parceira
        @PatchMapping("/{partnerStoreId}/updateurlpartnerstore")
        public ResponseEntity<PartnerStore> updateURLPartnerStore(@PathVariable Long partnerStoreId, @RequestBody PartnerStore partnerStore){
            PartnerStore lojaparceira = partnerStoreService.updateURLPartnerStore(partnerStoreId, partnerStore.getPartnerStoreUrl());
            return ResponseEntity.ok(lojaparceira);
        }

    //método para listar todas as lojas parceiras cadastradas
    @GetMapping
    public ResponseEntity<List<PartnerStore>> getAll(){
        List<PartnerStore> lojasparceiras = partnerStoreService.searchAll();
        return ResponseEntity.ok(lojasparceiras);
    }
     //método para deletar uma partner store
     @DeleteMapping("/{partnerStoreId}")
     public ResponseEntity<String> deletePartnerStore(@PathVariable Long partnerStoreId){
     partnerStoreService.deletePartnerStore(partnerStoreId);
     return ResponseEntity.status(HttpStatus.OK).body("Partner Store foi deletada com sucesso.");}
}
