package mengaji.firli.id.firlimengaji.juz;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mengaji.firli.id.firlimengaji.R;
import mengaji.firli.id.firlimengaji.ReadJSON;
import mengaji.firli.id.firlimengaji.adapter.SurahAdapter;
import mengaji.firli.id.firlimengaji.model.Surah;
import mengaji.firli.id.firlimengaji.model.SurahFormat;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Juz1Fragment extends Fragment {
    private RecyclerView recyclerView;
    private SurahAdapter mAdapter;
    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;

    private ArrayList<Surah> models = new ArrayList<>();

    private Context mContext;

    List<SurahFormat> data;

    private int mNumSurah;

    private String [] nameSurah = new String[]{
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

    public Juz1Fragment(Context context, int mNumSurah) {
        mContext = context;
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        this.mNumSurah = mNumSurah;
        // Required empty public constructor
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_juz1, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclewViewQuantityJuz);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);

        prepareQuantityJuz();

        return view;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void prepareQuantityJuz() {

        ReadJSON readJSON = new ReadJSON();

         data =  readJSON.queryJuzJSON(getActivity(),mNumSurah);



        Log.d("noSurah : " , String.valueOf(mNumSurah));


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SurahAdapter(getContext(), data, new SurahAdapter.ClickHandler() {
            @Override
            public void onItemClick(final int position) {
                //Log.d("goblok : " , position + " ");
                //Toast.makeText(getContext(), "Berhasil Masuk " + position, Toast.LENGTH_SHORT).show();
                Dialog d = new BottomSheetDialog(getContext());
                View view = getLayoutInflater().inflate(R.layout.dialog_layout,null);

                LinearLayout play = view.findViewById(R.id.play);
                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                            mMediaPlayer = MediaPlayer.create(mContext, models.get(position).getmAudio());
                            mMediaPlayer.start();
                            mMediaPlayer.setOnCompletionListener(mCompleteListener);
                        }
                    }
                });

                LinearLayout play_custom = view.findViewById(R.id.play_custom);
                play_custom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "Play Custom", Toast.LENGTH_SHORT).show();
                    }
                });

                LinearLayout share = view.findViewById(R.id.share);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(data.get(position).getAyahText() + " \n "
                                + data.get(position).getIndoText() + "\n" +
                                "(" + nameSurah[data.get(position).getSurahId()-1] + ":" + data.get(position).getVerseID() + ")");
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString());
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                    }
                });

                d.setContentView(view);
                d.setCancelable(true);
                d.show();


            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });

        recyclerView.setAdapter(mAdapter);
    }

    private void releaseMediaPlayer(){
        if (mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mMediaPlayer.start();
            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompleteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
}
