package dji.com.challenge.takehome.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dji.challenge.R;
import dji.com.challenge.takehome.network.model.Product;
import dji.com.challenge.takehome.utils.LogUtil;

import java.util.List;

public class ProductsListAdapter extends AbsBaseAdapter<Product>{

    public ProductsListAdapter(List<Product> listOfProducts) {
        super(listOfProducts);
    }

    @Override
    protected View newView(LayoutInflater inflater, ViewGroup parent, int position) {
        return LayoutInflater.from(parent.getContext())
                             .inflate(R.layout.single_row, parent, false);
    }

    @Override
    protected void bindView(View view, int position) {

        Product product = this.getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.product_name);
        textView.setText(product.name);
        textView = (TextView) view.findViewById(R.id.product_description);
        textView.setText(product.description);
    }
}