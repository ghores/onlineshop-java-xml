package com.example.onlineshop.mock;

import com.example.onlineshop.R;
import com.example.onlineshop.models.Color;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductCategory;
import com.example.onlineshop.models.Size;

import java.util.ArrayList;
import java.util.List;

public class MockDataHandler {

    private static List<Color> colors;
    private static List<Size> sizes;

    static {
        fillColors();
        fillSizes();
    }

    private static void fillColors() {
        colors = new ArrayList<>();
    }

    private static void fillSizes() {
        sizes = new ArrayList<>();
    }

    public static List<Color> getColors() {
        return colors;
    }

    public static List<Size> getSizes() {
        return sizes;
    }

    public static Color getColorById(long id) {
        for (Color color : colors) {
            if (color.getId() == id)
                return color;
        }
        return null;
    }

    public static Size getSizeById(long id) {
        for (Size size : sizes) {
            if (size.getId() == id)
                return size;
        }
        return null;
    }
}
