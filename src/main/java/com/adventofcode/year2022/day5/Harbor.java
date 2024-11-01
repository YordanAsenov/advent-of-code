package com.adventofcode.year2022.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Harbor {
    
    public record Action(int count, int from, int to) {
        public Action(String input) {
            this(
                Integer.parseInt(input.substring(input.indexOf("move") + 5, input.indexOf("from")).trim()),
                Integer.parseInt(input.substring(input.indexOf("from") + 5, input.indexOf("to")).trim()),
                Integer.parseInt(input.substring(input.indexOf("to") + 3).trim())
            );
        }
    }
    
    private final List<Stack<String>> containers;
    private final List<Action> actions;
    
    private static List<Stack<String>> initContainers(List<String> input) {
        List<Stack<String>> stacks = new ArrayList<>();
        
        String baseRow = input.stream()
                .filter(line -> line.startsWith(" 1"))
                .findFirst()
                .orElse(null);
        
        int rowNum = input.indexOf(baseRow);
   
        int colIndex = 0;
        while(colIndex < input.get(input.indexOf(baseRow) - 1).length()) {
            Stack<String> stack = new Stack<>();
            for (int i = rowNum - 1; i >= 0; i--) {
                String currentRow = input.get(i);
                if (colIndex > currentRow.length()) {
                    break;
                }
                
                String currentColumn = currentRow.substring(colIndex, colIndex + 3);
                if (currentColumn.isBlank()) {
                    break;
                }
                
                String content = currentColumn.substring(currentColumn.indexOf("[") + 1, currentColumn.indexOf("]")).trim();
                stack.add(content);
            }
            stacks.add(stack);
            colIndex += 4;
        }
        
        return stacks;
    }
    
    private static List<Action> initActions(List<String> input) {
        List<Action> actions = new ArrayList<>();
        
        String firstActionRow = input.stream()
                .filter(line -> line.startsWith("move"))
                .findFirst()
                .orElse(null);
        int rowNum = input.indexOf(firstActionRow);
        for (int i = rowNum; i < input.size(); i++) {
            String row = input.get(i);
            if (row.isEmpty() || row.isBlank()) {
                break;
            }
            actions.add(new Action(row));
        }
        
        return actions;
    }
    
    public Harbor(List<String> input) {
        this.containers = initContainers(input);
        this.actions = initActions(input);
    }
    
    public void moveContainers() {
        this.actions.forEach(action -> {
            int count = action.count;
            while (count > 0) {
                Stack<String> fromStack = this.containers.get(action.from - 1);
                String content = fromStack.pop();
                Stack<String> toStack = this.containers.get(action.to - 1);
                toStack.push(content);
                count--;
            }
        });
    }
    
    public void moveContainersWithNewCrane() {
        this.actions.forEach(action -> {
            int count = action.count;
            
            List<String> pickedContainers = new ArrayList<>();
            while (count > 0) {
                Stack<String> fromStack = this.containers.get(action.from - 1);
                pickedContainers.add(fromStack.pop());
                count--;
            }
            Stack<String> toStack = this.containers.get(action.to - 1);
            pickedContainers.reversed().forEach(toStack::push);
        });
    }
    
    public String getContentInUpperContainers() {
        StringBuilder content = new StringBuilder();
        this.containers.forEach(stack -> content.append(stack.peek()));
        return content.toString();
    }
}
