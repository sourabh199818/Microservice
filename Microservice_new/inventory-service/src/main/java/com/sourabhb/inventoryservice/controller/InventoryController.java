package com.sourabhb.inventoryservice.controller;


import com.sourabhb.inventoryservice.dto.InventoryResponse;
import com.sourabhb.inventoryservice.model.Inventory;
import com.sourabhb.inventoryservice.repository.InventoryRepository;
import com.sourabhb.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

      private  final InventoryService inventoryService;

      private final  InventoryRepository inventoryRepository;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse>  isInStock(@RequestParam List<String>  skuCode)
    {

        return inventoryService.isInStock(skuCode);
    }

//    @GetMapping("/1")
//   public List<Inventory> innStock()
//   {
//
//       return inventoryRepository.findAll();
//   }
}
