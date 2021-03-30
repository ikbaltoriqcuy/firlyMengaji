package mengaji.firli.id.firlimengaji.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mengaji.firli.id.firlimengaji.juz.Surah1Fragment;
import mengaji.firli.id.firlimengaji.juz.Surah2Fragment;

public class CategorySurahAdapter extends FragmentPagerAdapter {
    private String [] TabTitle = new String[]{
            "1. Al-Fathah", "2. Al-Baqarah", "3. Ali imran", "4. An-Nisa'", "5. Al-Maidah", "6. Al-An'am", "7. Al-A'raf",
            "8. Al-Anfal", "9. At-Taubah", "10. Yunus", "11. Hud", "12. Yusuf", "13. Ar-Rad'", "14. Ibrahim",
            "15. Al-Hijr", "16. An-Nahl", "17. Al-Isra'", "18. Al-Kahf", "19. Maryam", "20. Ta-Ha", "21. Al-Anbiya",
            "22. Al-Hajj", "23. Al-Mu'minun", "24. An-Nur", "25. Al-Furqan", "26. Asy-Syur'ara'", "27. An-Naml", "28. Al-Qasas",
            "29. Al-'Ankabut", "30. Ar-Rum", "31. Luqman", "32. As-Sajdah", "33. Al-Ahzab", "34. Saba'", "35. Fatir",
            "36. Ya-Sin", "37. As-Saffat", "38. Sad", "39. Az-Zumar", "40. Ghafir", "41. Fussilat","42. Asy-Syura",
            "43. Az-Zukhruf", "44. Ad-Dukhan", "45. Al-Jasiyah", "46. Al-Ahqaf", "47. Muhammad", "48. Al-Fath", "49. Al-Hujurat",
            "50. Qaf", "51. Az-Zariyat", "52. At-Tur", "53. An-Najm", "54. Al-Qamar", "55. Ar-Rahman", "56. Al-Waqi'ah",
            "57. Al-Hadid", "58. Al-Mujadilah", "59. Al-Hasyr", "60. Al-Mumtahanah", "61. As-Saff", "62. Al-Jumu'ah", "63. Al-Munafiqun",
            "64. At-Taghabun", "65. At-Talaq", "66. At-Tahrim", "67. Al-Mulk", "68. Al-Qalam", "69. Al-Haqqah", "70. Al-Ma'arij",
            "71. Nuh", "72. Al-Jinn", "73. Al-Muzzammil", "74. Al-Muddassir", "75. Al-Qiyamah", "76. Al-Insan", "77. Al-Mursalat",
            "78. An-Naba'", "79. An-Nazi'at", "80. 'Abasa", "81. At-Takwir", "82. Al-Infitar", "83. Al- Muthaffifiin", "84. Al-Insyiqaq",
            "85. Al-Buruj", "86. At-Tariq", "87. Al-A'la", "88. Al-Ghasyiyah", "89. Al-Fajr", "90. Al- Balad", "91. Asy-Syams",
            "92. Al-Lail", "93. Ad-Duha", "94. Al-Insyirah", "95. At-Tin", "96. Al-'Alaq", "97. Al-Qadr", "98. Al-Bayyinah",
            "99. Az-Zalzalah", "100. Al-'Adiyat", "101. Al-Qari'ah", "102. At-Takasur", "103. Al-'Asr", "104. Al-Humazah", "105. Al-Fil",
            "106. Quraisy", "107. Al-Ma'un", "108. Al-Kausar", "109. Al-Kafirun", "110. An-Nasr", "111. Al-Lahab",
            "112. Al-Ikhlas", "113. Al-Falaq", "114. An-Nas"
    };

    private int pageCount = 114;

    private Context mContext;

    public CategorySurahAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Surah1Fragment fragment = new Surah1Fragment(mContext,position+1, TabTitle[position]);
        return fragment;

//        switch(position) {
//            case 0 :
//                Surah1Fragment fragment = new Surah1Fragment(mContext,position+1);
//                return fragment;
//            case 1 :
//                Surah2Fragment fragment1 = new Surah2Fragment(mContext,position+1);
//                return fragment1;
//        }
//
//        return  null;

    }

    @Override
    public int getCount() {
        return 114;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TabTitle[position];
    }
}
