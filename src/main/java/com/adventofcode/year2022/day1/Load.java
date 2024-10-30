package com.adventofcode.year2022.day1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.Data;

@Data
public class Load {
    private List<Long> inventories = new ArrayList<>();
    
    public Load(List<String> input) {
        long inventory = 0;
        for (String line : input) {
            if (!line.trim().isEmpty()) {
                inventory += Long.parseLong(line);
            } else {
                this.inventories.add(inventory);
                inventory = 0;
            }
        }
        this.inventories.add(inventory);
    }
    
    public Long findBiggerInventory() {
        Long max = null;
        
        for (Long inventory : this.inventories) {
            if (max == null)
                max = inventory;
            if (inventory > max)
                max = inventory;
        }
        
        return max;
    }
    
    public Long findThreeBiggestInventories() {
        this.inventories.sort(Long::compareTo);
        this.inventories.sort(Comparator.reverseOrder());
        return inventories.get(0) + inventories.get(1) + inventories.get(2);
    }
}
