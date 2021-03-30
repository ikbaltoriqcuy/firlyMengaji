package mengaji.firli.id.firlimengaji.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mengaji.firli.id.firlimengaji.Fragment.JuzFragment;
import mengaji.firli.id.firlimengaji.Fragment.SurahFragment;

public class CategoryAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new SurahFragment(mContext);
        }else {
            return new JuzFragment(mContext);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position ==0){
            return "Surah";
        }else {
            return "Juz";
        }
    }
}
