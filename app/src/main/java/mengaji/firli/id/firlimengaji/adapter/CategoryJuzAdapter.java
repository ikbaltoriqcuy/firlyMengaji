package mengaji.firli.id.firlimengaji.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mengaji.firli.id.firlimengaji.juz.Juz1Fragment;
import mengaji.firli.id.firlimengaji.juz.Surah1Fragment;
import mengaji.firli.id.firlimengaji.juz.Surah2Fragment;

public class CategoryJuzAdapter extends FragmentPagerAdapter {
    private String[] TabTitle = new String[]{
            "Juz 1", "Juz 2", "Juz 3", "Juz 4", "Juz 5", "Juz 6", "Juz 7", "Juz 8", "Juz 9", "Juz 10",
            "Juz 11", "Juz 12", "Juz 13", "Juz 14", "Juz 15", "Juz 16", "Juz 17", "Juz 18", "Juz 19", "Juz 20",
            "Juz 21", "Juz 22", "Juz 23", "Juz 24", "Juz 25", "Juz 26", "Juz 27", "Juz 28", "Juz 29", "Juz 30"
    };

    private int pageCount = 30;

    private Context mContext;

    public CategoryJuzAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Juz1Fragment fragment = new Juz1Fragment(mContext,position+1);
        return fragment;
    }


    @Override
    public int getCount() {
        return pageCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TabTitle[position];
//        if (position ==0){
//            return "Juz 1";
//        }else if(position == 1) {
//            return "Juz 2";
//        }else if(position == 2) {
//            return "Juz 3";
//        }else if(position == 3) {
//            return "Juz 4";
//        }else if(position == 4) {
//            return "Juz 5";
//        }else if(position == 5) {
//            return "Juz 6";
//        }else if(position == 6) {
//            return "Juz 7";
//        }else if(position == 7) {
//            return "Juz 8";
//        }else if(position == 8) {
//            return "Juz 9";
//        }else if(position == 9) {
//            return "Juz 10";
//        }else if(position == 10) {
//            return "Juz 11";
//        }else if(position == 11) {
//            return "Juz 12";
//        }else if(position == 12) {
//            return "Juz 13";
//        }else if(position == 13) {
//            return "Juz 14";
//        }else if(position == 14) {
//            return "Juz 15";
//        }else if(position == 15) {
//            return "Juz 16";
//        }else if(position == 16) {
//            return "Juz 17";
//        }else if(position == 17) {
//            return "Juz 18";
//        }else if(position == 18) {
//            return "Juz 19";
//        }else if(position == 19) {
//            return "Juz 20";
//        }else if(position == 20) {
//            return "Juz 21";
//        }else if(position == 21) {
//            return "Juz 22";
//        }else if(position == 22) {
//            return "Juz 23";
//        }else if(position == 23) {
//            return "Juz 24";
//        }else if(position == 24) {
//            return "Juz 25";
//        }else if(position == 25) {
//            return "Juz 26";
//        }else if(position == 26) {
//            return "Juz 27";
//        }else if(position == 27) {
//            return "Juz 28";
//        }else if(position == 28) {
//            return "Juz 29";
//        }else  {
//            return "Juz 30";
//        }
    }
}
