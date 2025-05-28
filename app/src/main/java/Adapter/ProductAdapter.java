package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.duung.thuydungreview01.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.Product;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return productList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.activity_product_item, parent, false);

        Product p = productList.get(i);

        TextView tvName = view.findViewById(R.id.etProductName);
        TextView tvCode = view.findViewById(R.id.etProductCode);
        TextView tvPrice = view.findViewById(R.id.etUnitPrice);
        ImageView imgProduct = view.findViewById(R.id.imgProduct);

        tvName.setText(p.getProductName());
        tvCode.setText("Code: " + p.getProductCode());
        tvPrice.setText("$" + p.getUnitPrice());

        String imgLink = p.getImageLink();
        if (imgLink != null && !imgLink.isEmpty()) {
            Picasso.get()
                    .load(imgLink)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .into(imgProduct);
        } else {
            imgProduct.setImageResource(R.drawable.ic_default_image);
        }

        return view;
    }
}
