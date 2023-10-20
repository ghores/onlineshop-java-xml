package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshop.models.Color;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.Size;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private Product product;
    private TextView price, name, description;
    private ImageView image;
    private Button btnAddToCard;
    private LinearLayout mainView;
    private ChipGroup sizeChips, colorChips;
    private List<Size> sizes;
    private List<Color> colors;

    private Size selectedSize = null;
    private Color selectedColor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        init();
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        //region bind views
        bindViews();
        //endregion

        //region product details
        product = (Product) getIntent().getExtras().get("product");
        price.setText(product.getPrice() + "$");
        name.setText(product.getName());
        image.setImageDrawable(getResources().getDrawable(product.getImage(), null));
        //endregion

        //region fill colors and sizes
        fillColors();
        fillSizes();
        //endregion

        //region handle sizes
        for (Size size : sizes) {
            Chip chip = new Chip(this);
            chip.setText(size.getName());
            chip.setCheckable(true);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        selectedSize = size;
                    } else {
                        selectedSize = null;
                    }
                }
            });
            sizeChips.addView(chip);
        }
        //endregion

        //region handle colors
        for (Color color :
                colors) {
            Chip chip = new Chip(this);
            chip.setText(color.getName());
            chip.setCheckable(true);
            chip.setTextColor(android.graphics.Color.parseColor(color.getValue()));
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        selectedColor = color;
                    } else {
                        selectedColor = null;
                    }
                }
            });
            colorChips.addView(chip);
        }
        //endregion

        //region handle add to card
        btnAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedSize == null) {
                    Snackbar.make(mainView, "please selected a size", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (selectedColor == null) {
                    Snackbar.make(mainView, "please selected a color", Snackbar.LENGTH_LONG).show();
                    return;
                }
                String result = "Selected Items : \nColor : " + selectedColor.getName() + "\nSize : " + selectedSize.getName();
                Toast.makeText(ProductDetailsActivity.this, result, Toast.LENGTH_LONG).show();
                finish();
                ProductDetailsActivity.this.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        //endregion
    }

    private void bindViews() {
        price = findViewById(R.id.product_price);
        name = findViewById(R.id.product_name);
        image = findViewById(R.id.product_image);
        description = findViewById(R.id.product_description);
        sizeChips = findViewById(R.id.chip_product_sizes);
        colorChips = findViewById(R.id.chip_product_colors);
        btnAddToCard = findViewById(R.id.btn_add_to_card);
        mainView = findViewById(R.id.product_detail_view);
    }

    private void fillColors() {
        colors = new ArrayList<>();
        colors.add(new Color(1,"Pink", "#f78fb3"));
        colors.add(new Color(2,"Blue", "#546de5"));
        colors.add(new Color(3,"Aqua", "#3dc1d3"));
        colors.add(new Color(4,"Purple", "#574b90"));
    }

    private void fillSizes() {
        sizes = new ArrayList<>();
        sizes.add(new Size(1,"38"));
        sizes.add(new Size(2,"39"));
        sizes.add(new Size(3,"40"));
        sizes.add(new Size(4,"41"));
        sizes.add(new Size(5,"42"));
        sizes.add(new Size(6,"43"));
        sizes.add(new Size(7,"44"));
        sizes.add(new Size(8,"45"));
        sizes.add(new Size(9,"46"));
        sizes.add(new Size(10,"47"));
        sizes.add(new Size(11,"48"));
    }
}