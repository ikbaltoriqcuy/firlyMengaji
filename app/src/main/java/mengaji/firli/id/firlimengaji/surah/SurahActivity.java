package mengaji.firli.id.firlimengaji.surah;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import mengaji.firli.id.firlimengaji.R;
import mengaji.firli.id.firlimengaji.adapter.CategoryJuzAdapter;
import mengaji.firli.id.firlimengaji.adapter.CategorySurahAdapter;

public class SurahActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private static ViewPager viewPager;
    private static TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah2);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set viewpager
         viewPager = (ViewPager) findViewById(R.id.viewpagerSurah);

        //set adapter call
        CategorySurahAdapter adapter = new CategorySurahAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //set Tab
        tabLayout = (TabLayout)findViewById(R.id.tabsSurah);

        try{

            tabLayout.getTabAt(9).select();
        }catch (Exception e) {

        }
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#ffffff"));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(getIntent().getExtras().getInt("tab"));
        viewPager.setOffscreenPageLimit(1);

    }

    public static void setTabIndex(){
        int pos = tabLayout.getSelectedTabPosition()  +1;
         viewPager.setCurrentItem(pos);
    }
    public static void setTabIndex(int position){
         viewPager.setCurrentItem(position);
    }
}
