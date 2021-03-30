package mengaji.firli.id.firlimengaji.juz;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import mengaji.firli.id.firlimengaji.R;
import mengaji.firli.id.firlimengaji.adapter.CategoryJuzAdapter;

public class Juz1Activity extends AppCompatActivity {

    private Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juz2);

        toolbar = (Toolbar)findViewById(R.id.toolbarJuz);
        setSupportActionBar(toolbar);

        //set viewpager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpagerJuz);

        //set adapter call
        CategoryJuzAdapter adapter = new CategoryJuzAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //set Tab
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabsJuz);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#ffffff"));
        viewPager.setCurrentItem(getIntent().getExtras().getInt("tab"));
        viewPager.setOffscreenPageLimit(1);



        Log.d("Al-Quran", "decodeValue = " + new String("بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّحِيْمِ"));

    }
}
