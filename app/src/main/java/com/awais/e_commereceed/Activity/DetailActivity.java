package com.awais.e_commereceed.Activity;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.awais.e_commereceed.Adapter.SliderAdapter;
import com.awais.e_commereceed.Domain.ItemsDomain;
import com.awais.e_commereceed.Domain.SliderItems;
import com.awais.e_commereceed.Fragment.DescriptionFragment;
import com.awais.e_commereceed.Fragment.ReviewFragment;
import com.awais.e_commereceed.Fragment.SoldFragment;
import com.awais.e_commereceed.Helper.ManagmentCart;

import com.awais.e_commereceed.databinding.ActivityDetailBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    private ItemsDomain object;
    private int numberOrder=1;
    private ManagmentCart managmentCart;
    private Handler slideHandle=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managmentCart=new ManagmentCart(this);


        getBundle();
        banners();
        setupViewPager();


    }

    private void banners() {
        ArrayList<SliderItems> sliderItems=new ArrayList<>();
        for(int i=0;i< object.getPicUrl().size();i++){
            sliderItems.add(new SliderItems(object.getPicUrl().get(i)));
        }
        binding.viewpageSlider.setAdapter(new SliderAdapter(sliderItems,binding.viewpageSlider));
        binding.viewpageSlider.setClipToPadding(false);
        binding.viewpageSlider.setClipChildren(false);
        binding.viewpageSlider.setOffscreenPageLimit(3);
        binding.viewpageSlider.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    private void getBundle() {
        object=(ItemsDomain) getIntent().getSerializableExtra("object");
        binding.titleTxt.setText(object.getTitle());
        binding.priceTxt.setText("Rs"+object.getPrice());
        binding.ratingBar.setRating((float) object.getRating());
        binding.ratingTxt.setText(object.getRating() +"Rating");
        binding.addTocartBtn.setOnClickListener(v -> {
                object.setNumberinCart(numberOrder);
                managmentCart.insertItem(object);

        });
        binding.backBtn.setOnClickListener(v -> {
            finish();
        });
    }
    private void setupViewPager(){
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        DescriptionFragment tab1 = new DescriptionFragment();
        ReviewFragment tab2 = new ReviewFragment();
        SoldFragment tab3 = new SoldFragment();
        Bundle bundle1 = new Bundle();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = new Bundle();
        bundle1.putString("description",object.getDescription());
        tab1.setArguments(bundle1);
        tab2.setArguments(bundle2);
        tab3.setArguments(bundle3);
        adapter.addFragment(tab1,"Descriptions");
        adapter.addFragment(tab2,"Reviews");
        adapter.addFragment(tab3,"Sold");
        binding.viewpager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewpager);




    }
    private class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList=new ArrayList<>();
        private final List<String > mFragmentTitleList=new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment,String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}