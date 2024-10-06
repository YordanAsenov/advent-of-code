package com.adventofcode.year2015.day2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Box {
    private int length;
    private int width;
    private int height;

    private int getLengthSurfaceArea() {
        return length * width;
    }

    private int getLengthSurfacePerimeter() {
        return length + width;
    }

    private int getWidthSurfaceArea() {
        return width * height;
    }

    private int getWidthSurfacePerimeter() {
        return width + height;
    }

    private int getHeightSurfaceArea() {
        return height * length;
    }

    private int getHeightSurfacePerimeter() {
        return height + length;
    }

    public int getArea(){
        return (
            (2 * getLengthSurfaceArea()) +
            (2 * getWidthSurfaceArea()) +
            (2 * getHeightSurfaceArea())
        );
    }

    public int getMinimumSideSurfaceArea() {
        int lengthSurfaceArea = getLengthSurfaceArea();
        int widthSurfaceArea = getWidthSurfaceArea();
        int heightSurfaceArea = getHeightSurfaceArea();

        if (lengthSurfaceArea <= widthSurfaceArea && lengthSurfaceArea <= heightSurfaceArea)
            return lengthSurfaceArea;
        if (widthSurfaceArea <= lengthSurfaceArea && widthSurfaceArea <= heightSurfaceArea)
            return widthSurfaceArea;
        return heightSurfaceArea;
    }

    public int getMinimumSidePerimeter() {
        int lengthSurfacePerimeter = (2 * getLengthSurfacePerimeter());
        int widthSurfacePerimeter = (2 * getWidthSurfacePerimeter());
        int heightSurfacePerimeter = (2 * getHeightSurfacePerimeter());

        if (lengthSurfacePerimeter <= widthSurfacePerimeter && lengthSurfacePerimeter <= heightSurfacePerimeter)
            return lengthSurfacePerimeter;
        if (widthSurfacePerimeter <= lengthSurfacePerimeter && widthSurfacePerimeter <= heightSurfacePerimeter)
            return widthSurfacePerimeter;
        return heightSurfacePerimeter;
    }

    public int getVolume() {
        return length * width * height;
    }
}