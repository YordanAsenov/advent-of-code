package com.adventofcode.year2022.day7;

import java.util.List;

public class Game {

    private static boolean isCommand(String input) {
        return input.startsWith("$ ");
    }
    
    private static File initFileSystem(List<String> input) {
        File fileSystem = new File("/", null);
        File currentFile = fileSystem;
        
        for (String row : input) {
            // collect files and directories
            if (!isCommand(row)) {
                if (row.startsWith("dir")) {
                    currentFile.getFiles().add(
                            new File(row.substring(4), currentFile)
                    );
                } else {
                    String[] fileParts = row.split(" ");
                    currentFile.getFiles().add(
                            new File(fileParts[1], Long.parseLong(fileParts[0]), currentFile)
                    );
                }
            }
            
            String[] fullCommand = row.substring(2).split(" ");
            String command = fullCommand[0];
            String attributes = fullCommand.length > 1 ? fullCommand[1] : "";
            
            if (command.equals("ls")) {
                continue;
            }
            if (command.equals("cd")) {
                // move out
                if (attributes.equals("..")) {
                    currentFile = currentFile.getParent();
                    continue;
                }
                // move in
                for (File file : currentFile.getFiles()) {
                    if (file.getName().equals(attributes)) {
                        currentFile = file;
                    }
                }
            }
        }
        return fileSystem;
    }

    public static long solve(List<String> input) {
        File fileSystem = initFileSystem(input);
        fileSystem.print();
        return fileSystem.getDirectoriesLightestThanTotalSize(100_000);
    }
    
    
    public static long solve2(List<String> input) {
        File fileSystem = initFileSystem(input);
        return fileSystem.findBestChoiceToDelete();
    }
}
