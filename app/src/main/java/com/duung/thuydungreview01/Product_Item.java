package com.duung.thuydungreview01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Model.Product;

public class Product_Item extends AppCompatActivity {

    EditText etId, etProductCode, etProductName, etUnitPrice;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_item);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etId = findViewById(R.id.etId);
        etProductCode = findViewById(R.id.etProductCode);
        etProductName = findViewById(R.id.etProductName);
        etUnitPrice = findViewById(R.id.etUnitPrice);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String idStr = etId.getText().toString().trim();
            String productCode = etProductCode.getText().toString().trim();
            String productName = etProductName.getText().toString().trim();
            String unitPriceStr = etUnitPrice.getText().toString().trim();

            if (idStr.isEmpty() || productCode.isEmpty() || productName.isEmpty() || unitPriceStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int id;
            double unitPrice;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid ID", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                unitPrice = Double.parseDouble(unitPriceStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid price", Toast.LENGTH_SHORT).show();
                return;
            }

            // ImageLink không nhập vì yêu cầu đề bài
            Product newProduct = new Product(id, productCode, productName, unitPrice, "");

            Intent resultIntent = new Intent();
            resultIntent.putExtra("new_product", newProduct);
            setResult(RESULT_OK, resultIntent);

            finish();
        });
    }
}
