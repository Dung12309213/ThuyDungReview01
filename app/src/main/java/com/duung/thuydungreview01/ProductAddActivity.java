package com.duung.thuydungreview01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import Model.Product;
import Model.ProductList;

public class ProductAddActivity extends AppCompatActivity {

    EditText etProductCode, etProductName, etUnitPrice, etImageLink;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);

        etProductCode = findViewById(R.id.etProductCode);
        etProductName = findViewById(R.id.etProductName);
        etUnitPrice = findViewById(R.id.etUnitPrice);
        etImageLink = findViewById(R.id.etImageLink);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String code = etProductCode.getText().toString().trim();
            String name = etProductName.getText().toString().trim();
            String priceStr = etUnitPrice.getText().toString().trim();
            String imageLink = etImageLink.getText().toString().trim();

            if (code.isEmpty() || name.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(ProductAddActivity.this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double price;
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                Toast.makeText(ProductAddActivity.this, "Invalid price", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tạo sản phẩm mới với id tự sinh (ví dụ: timestamp)
            int id = ProductList.getInstance().getProductList().size() + 1;
            Product newProduct = new Product(id, code, name, price, imageLink);

            Intent intent = new Intent();
            intent.putExtra("new_product", (Serializable) newProduct);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
