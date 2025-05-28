package com.duung.thuydungreview01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import Adapter.ProductAdapter;
import Model.Product;
import Model.ProductListActivity;

public class ProductListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_PRODUCT = 100;

    ListView lvProducts;
    Toolbar toolbar;

    List<Product> productList;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // Setup Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup ListView
        lvProducts = findViewById(R.id.lvProducts);

        // ✅ Lấy danh sách sản phẩm từ ProductList singleton
        productList = ProductList.getInstance().getProductList();

        // ✅ Custom Adapter hiển thị
        productAdapter = new ProductAdapter(this, productList);
        lvProducts.setAdapter(productAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.top_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_product) {
            Intent intent = new Intent(this, Product_Item.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_PRODUCT);
            return true;
        } else if (id == R.id.action_profile) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // ✅ Nhận kết quả khi thêm sản phẩm
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_PRODUCT && resultCode == RESULT_OK && data != null) {
            Product newProduct = (Product) data.getSerializableExtra("new_product");
            if (newProduct != null) {
                ProductList.getInstance().addProduct(newProduct); // Thêm vào Singleton
                productAdapter.notifyDataSetChanged();             // Cập nhật giao diện
                Toast.makeText(this, "Added: " + newProduct.getProductName(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
