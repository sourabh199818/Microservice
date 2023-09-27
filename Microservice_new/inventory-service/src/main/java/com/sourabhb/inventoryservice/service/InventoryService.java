package com.sourabhb.inventoryservice.service;

import com.sourabhb.inventoryservice.dto.InventoryResponse;
import com.sourabhb.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

      @Transactional(readOnly = true)
      @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode)
    {

        log.info("Main Started");
        Thread.sleep(10000);
        log.info("wait Ended");
          return  inventoryRepository.findBySkuCodeIn(skuCode).stream()
                   .map(inventory ->
            InventoryResponse.builder().skuCode(inventory.getSkuCode())
                    .isInStock(inventory.getQuantity() >0).build()
        ).collect(Collectors.toList());

    }
}
