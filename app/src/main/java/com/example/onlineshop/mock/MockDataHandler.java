package com.example.onlineshop.mock;

import com.example.onlineshop.R;
import com.example.onlineshop.models.Color;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductCategory;
import com.example.onlineshop.models.Size;

import java.util.ArrayList;
import java.util.List;

public class MockDataHandler {

    private static List<Product> products;
    private static List<ProductCategory> productCategories;
    private static List<Color> colors;
    private static List<Size> sizes;

    static {
        fillCategories();
        fillProducts();
        fillColors();
        fillSizes();
    }

    private static void fillProducts() {
        products = new ArrayList<>();
        products.add(new Product(1, "New Balance", R.drawable.shoes, 3400));
        products.add(new Product(2, "Nike", R.drawable.nike, 2600));
        products.add(new Product(3, "Adidas", R.drawable.adidas, 1900));
        products.add(new Product(4, "Sorel", R.drawable.sorel, 2800));
        products.add(new Product(5, "Skechers", R.drawable.skechers, 3200));
        products.add(new Product(6, "Underarmour", R.drawable.underarmour, 4400));
    }

    private static void fillCategories() {
        productCategories = new ArrayList<>();
        productCategories.add(new ProductCategory("New Balance", R.drawable.shoes));
        productCategories.add(new ProductCategory("Nike", R.drawable.nike));
        productCategories.add(new ProductCategory("Adidas", R.drawable.adidas));
        productCategories.add(new ProductCategory("Sorel", R.drawable.sorel));
        productCategories.add(new ProductCategory("Skechers", R.drawable.skechers));
        productCategories.add(new ProductCategory("Underarmour", R.drawable.underarmour));
    }


    private static void fillColors() {
        colors = new ArrayList<>();
        colors.add(new Color(1, "Pink", "#f78fb3"));
        colors.add(new Color(2, "Blue", "#546de5"));
        colors.add(new Color(3, "Aqua", "#3dc1d3"));
        colors.add(new Color(4, "Purple", "#574b90"));
    }

    private static void fillSizes() {
        sizes = new ArrayList<>();
        sizes.add(new Size(1, "38"));
        sizes.add(new Size(2, "39"));
        sizes.add(new Size(3, "40"));
        sizes.add(new Size(4, "41"));
        sizes.add(new Size(5, "42"));
        sizes.add(new Size(6, "43"));
        sizes.add(new Size(7, "44"));
        sizes.add(new Size(8, "45"));
        sizes.add(new Size(9, "46"));
        sizes.add(new Size(10, "47"));
        sizes.add(new Size(11, "48"));
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public static List<Color> getColors() {
        return colors;
    }

    public static List<Size> getSizes() {
        return sizes;
    }

    public static Product getById(long id) {
        for (Product product : products) {
            if (product.getId() == id)
                return product;
        }
        return null;
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
