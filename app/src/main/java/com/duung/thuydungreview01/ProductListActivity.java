package com.duung.thuydungreview01;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import Adapter.ProductAdapter;
import Model.Product;
import Model.ProductList;

public class ProductListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_PRODUCT = 100;

    ListView lvProducts;
    ProductAdapter productAdapter;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvProducts = findViewById(R.id.lvProducts);

        productList = ProductList.getInstance().getProductList();
        productAdapter = new ProductAdapter(this, productList);
        lvProducts.setAdapter(productAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_product) {
            // Mở Activity thêm sản phẩm
            Intent intent = new Intent(this, ProductAddActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_PRODUCT);
            return true;
        } else if (id == R.id.action_profile) {
            // Mở About/Profile Activity
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_PRODUCT && resultCode == RESULT_OK && data != null) {
            Product newProduct = (Product) data.getSerializableExtra("new_product");
            if (newProduct != null) {
                ProductList.getInstance().addProduct(newProduct);
                productAdapter.notifyDataSetChanged();

                Toast.makeText(this, "Product added: " + newProduct.getProductName(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
