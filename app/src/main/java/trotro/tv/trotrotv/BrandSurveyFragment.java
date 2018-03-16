package trotro.tv.trotrotv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import trotro.tv.trotrotv.adapter.BrandListAdapter;
import trotro.tv.trotrotv.adapter.StationListAdapter;
import trotro.tv.trotrotv.model.Brand;
import trotro.tv.trotrotv.model.Station;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrandSurveyFragment extends Fragment {
    ListView lv;

    public BrandSurveyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout layout = (FrameLayout) inflater.inflate(R.layout.fragment_brand_survey, container, false);
        lv = (ListView) layout.findViewById(R.id.list_brands);

        getBrands();

        return layout;
    }

    private List<Brand> getBrands() {
        List<Brand> list = new ArrayList();

        Brand brand = new Brand(getContext());
        brand.setLocation("STARBITE");
        brand.setName("STARBITE");
        list.add(brand);

        brand = new Brand(getContext());
        brand.setLocation("GOLDEN TULIP");
        brand.setName("GOLDEN TULIP");
        list.add(brand);

        brand = new Brand(getContext());
        brand.setLocation("SHELL");
        brand.setName("SHELL");
        list.add(brand);

        BrandListAdapter adapter = new BrandListAdapter(getActivity(), list);
        lv.setAdapter(adapter);

        return list;

    }

}
