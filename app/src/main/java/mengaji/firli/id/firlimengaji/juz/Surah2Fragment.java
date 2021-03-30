package mengaji.firli.id.firlimengaji.juz;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
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
public class Surah2Fragment extends Fragment {
    private RecyclerView recyclerView;
    private SurahAdapter mAdapter;
    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;

    private ArrayList<Surah> models = new ArrayList<>();

    private Context mContext;

    private int mNumSurah;

    public Surah2Fragment(Context context, int mNumSurah) {
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

        List<SurahFormat> data =  readJSON.queryJSON(getActivity(),mNumSurah);
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
                        Toast.makeText(getContext(), "Share", Toast.LENGTH_SHORT).show();
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
