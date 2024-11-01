package com.adventofcode.year2022.day7;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class File {
    private Type type;
    private String name;
    private long size;
    private int level;
    
    private File parent;
    private List<File> files = new ArrayList<>();
    
    public File(String name, File parent) {
        this.name = name;
        this.type = Type.DIRECTORY;
        this.parent = parent;
        
        if (this.parent == null) {
            this.level = 0;
        } else {
            this.level = this.parent.level + 1;
        }
    }
    
    public File(String name, long size, File parent) {
        this.name = name;
        this.type = Type.FILE;
        this.size = size;
        this.parent = parent;
        
        if (this.parent == null) {
            this.level = 0;
        } else {
            this.level = this.parent.level + 1;
        }
    }
    
    public long getSize() {
        if (Type.FILE.equals(type)) {
            return this.size;
        }
        
        long totalSize = 0;
        for (File file : this.files) {
            totalSize += file.getSize();
        }
        return totalSize;
    }
    
    public void print() {
        if (Type.DIRECTORY == this.type) {
            System.out.println("  ".repeat(this.level) + "- " + this.name + " (" + this.type + ", totalSize=" + this.getSize() + ")");
        }
        if (Type.FILE.equals(this.type)) {
            System.out.println("  ".repeat(this.level) + "- " + this.name + " (" + this.type + ", size=" + this.size + ")");
        }
        
        for (File file : this.files) {
            file.print();
        }
    }

    public Long getDirectoriesLightestThanTotalSize(long maxSize) {
        List<Long> sizes = getDirectoriesLightestThanTotalSize(maxSize,  this.files);
        return sizes.stream().mapToLong(Long::longValue).sum();
    }
    
    public List<Long> getDirectoriesLightestThanTotalSize(long maxSize, List<File> files) {
        List<Long> sizes = new ArrayList<>();
        
        for (File file : files) {
            if (Type.DIRECTORY == file.getType()) {
                if (file.getSize() < maxSize) {
                    sizes.add(file.getSize());
                }
                
                List<Long> directories = getDirectoriesLightestThanTotalSize(maxSize, file.getFiles());
                if (directories != null && !directories.isEmpty()) {
                    sizes.addAll(directories);
                }
            }
        }

        return sizes;
    }
    
    public Long findBestChoiceToDelete() {
        long totalAvailableSpace = 70_000_000;
        long currentUsedSpace = this.getSize();
        long requiredFreeSpace = 30_000_000;
        
        long currentFreeSpace = totalAvailableSpace - currentUsedSpace;
        
        List<Long> sizes = getDirectoriesLightestThanTotalSize(totalAvailableSpace,  this.files);
        return sizes.stream()
                .filter(s -> requiredFreeSpace < (currentFreeSpace + s))
                .min(Long::compareTo)
                .orElse(null);
        
    }
}