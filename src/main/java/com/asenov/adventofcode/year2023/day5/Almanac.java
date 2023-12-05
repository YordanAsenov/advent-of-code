package com.asenov.adventofcode.year2023.day5;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

@Data
public class Almanac {
    private String seeds;
    private final List<Range> seedToSoil;
    private final List<Range> soilToFertilizer;
    private final List<Range> fertilizerToWater;
    private final List<Range> waterToLight;
    private final List<Range> lightToTemperature;
    private final List<Range> temperatureToHumidity;
    private final List<Range> humidityToLocation;

    private static List<Range> initRange(String key, List<String> input) {
        List<Range> rangeList = new ArrayList<>();

        int rowNum = input.indexOf(key);
        for (int i = rowNum + 1; i < input.size(); i++) {
            String row = input.get(i);
            if (row.isEmpty() || row.isBlank()) {
                break;
            }
            rangeList.add(new Range(row));
        }

        return rangeList;
    }

    public Almanac(List<String> input) {
        this.seeds = input.get(0);
        this.seedToSoil = initRange("seed-to-soil map:", input);
        this.soilToFertilizer = initRange("soil-to-fertilizer map:", input);
        this.fertilizerToWater = initRange("fertilizer-to-water map:", input);
        this.waterToLight = initRange("water-to-light map:", input);
        this.lightToTemperature = initRange("light-to-temperature map:", input);
        this.temperatureToHumidity = initRange("temperature-to-humidity map:", input);
        this.humidityToLocation = initRange("humidity-to-location map:", input);
    }

    public Long getDestinationValue(Long sourceValue, List<Range> rangeList) {
        return rangeList.stream()
            .filter(range -> range.contains(sourceValue))
            .map(range -> sourceValue - range.offset())
            .findFirst()
            .orElse(sourceValue);
    }

    public Long getLocation(Long seed) {
        // System.out.println("/*************************************/");
        // System.out.println("Seed: " + seed);
        Long soil = getDestinationValue(seed, seedToSoil);
        // System.out.println("Soil: " + soil);
        Long fertilizer = getDestinationValue(soil, soilToFertilizer);
        // System.out.println("Fertilizer: " + fertilizer);
        Long water = getDestinationValue(fertilizer, fertilizerToWater);
        // System.out.println("Water: " + water);
        Long light = getDestinationValue(water, waterToLight);
        // System.out.println("Light: " + light);
        Long temperature = getDestinationValue(light, lightToTemperature);
        // System.out.println("Temperature: " + temperature);
        Long humidity = getDestinationValue(temperature, temperatureToHumidity);
        // System.out.println("Humidity: " + humidity);
        Long location = getDestinationValue(humidity, humidityToLocation);
        // System.out.println("Location: " + location);
        // System.out.println("/*************************************/");
        return location;
    }

    public Long getLowestLocation() {
        List<String> list = Arrays.stream(this.seeds.substring(6)
            .split(" "))
            .filter(n -> !n.isEmpty() && !n.isBlank())
            .toList();

        Long lowestLocation = null;

        for (int i = 0; i < list.size(); i+=2) {
            long from = Long.parseLong(list.get(i));
            long rangeSize = Long.parseLong(list.get(i + 1));

            Optional<Long> location = LongStream.range(from, from + rangeSize)
                .mapToObj(this::getLocation)
                .min(Long::compareTo);

            if (location.isPresent()) {
                Long lowerLocation = location.get();
                if (lowestLocation == null || lowestLocation > lowerLocation) {
                    lowestLocation = lowerLocation;
                }
            }
        }

        return lowestLocation;
    }
}
