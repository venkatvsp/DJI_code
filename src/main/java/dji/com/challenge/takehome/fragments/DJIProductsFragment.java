package dji.com.challenge.takehome.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dji.challenge.R;
import com.google.gson.Gson;
import dji.com.challenge.takehome.adapters.ProductsListAdapter;
import dji.com.challenge.takehome.lib.BaseFragment;
import dji.com.challenge.takehome.network.ApiClient;
import dji.com.challenge.takehome.network.model.Product;
import dji.com.challenge.takehome.utils.LogUtil;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;

/**
 * Product fragment
 */
public class DJIProductsFragment extends BaseFragment {

    @Inject
    Gson gson;
    ProductsListAdapter singleSelectAdapter;
    private List<Product> productList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_with_network, container, false);
        ButterKnife.inject(this, view);

        ListView listView = (ListView) view.findViewById(R.id.listOfProducts);
        singleSelectAdapter = new ProductsListAdapter(productList);
        listView.setAdapter(singleSelectAdapter);
        return view;
    }

    /*
     * Get products list from server
     */
    @OnClick(R.id.Button_fragment_with_network)
    public void getProduct() {
        Subscriber<List<Product>> observer = new Subscriber<List<Product>>() {
            @Override
            public void onCompleted() {
                LogUtil.logError(" Downloaded data from the server");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Product> listOfProducts) {

                if (!listOfProducts.isEmpty()) {

                    parseAndDisplayUI(listOfProducts);
                } else {
                    LogUtil.logError("Couldn't find list of products! Please try later");
                }
            }
        };
        final Subscription subscription = ApiClient.getProducts().subscribe(observer);
        addSubscription(subscription);
    }

     private void parseAndDisplayUI(List<Product> listOfProducts) {
         productList = listOfProducts;
         singleSelectAdapter.notifyDataSetChanged();
         singleSelectAdapter.add(productList);
     }

}
