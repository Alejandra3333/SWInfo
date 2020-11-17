package mx.uv.fiee.iinfo.swinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import mx.uv.fiee.iinf.tyam.API.Fragments.Fragments;
import mx.uv.fiee.iinf.tyam.R;

public class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager2 viewPager2 = findViewById(R.id.vpSW);
        MyPagerAdapter vpAdapter = new MyPagerAdapter (getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(vpAdapter);
        TabLayout tabLayout = findViewById(R.id.pagerHeader);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position){
                case 0: tab.setText("Planets");
                    break;
                case 1: tab.setText("People");
                    break;
                case 2: tab.setText("Vehicles");
                    break;
            }
        }).attach();
    }
}
class MyPagerAdapter extends FragmentStateAdapter {

    public MyPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragments swFragment = new Fragments();
        Bundle args = new Bundle();
        args.putInt("pos", position);
        swFragment.setArguments(args);
        return swFragment;
    }
    @Override
    public int getItemCount() {
        return 3;
    }
}
