package mengaji.firli.id.firlimengaji.juz;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mengaji.firli.id.firlimengaji.R;
import mengaji.firli.id.firlimengaji.ReadJSON;
import mengaji.firli.id.firlimengaji.model.SurahFormat;
import mengaji.firli.id.firlimengaji.adapter.SurahAdapter;
import mengaji.firli.id.firlimengaji.model.Surah;
import mengaji.firli.id.firlimengaji.surah.SurahActivity;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Surah1Fragment extends Fragment {
    private RecyclerView recyclerView;
    private SurahAdapter mAdapter;
    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;

    private RelativeLayout relativeLayout;
    private ImageButton playOrPause;
    private ImageButton next;
    private ImageButton previous;
    private ImageButton stop;

    private boolean isPlay;

    CurrentPositionAyah currentPositionAyah = new CurrentPositionAyah();


    List<SurahFormat> data;

    private Context mContext;

    private int mNumSurah;
   private String namaSurah;
    public Surah1Fragment(Context context, int mNumSurah , String namaSurah) {
        mContext = context;
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        this.mNumSurah = mNumSurah;
        this.namaSurah = namaSurah;
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
        View view = inflater.inflate(R.layout.fragment_surah1, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclewViewQuantitySurah);
        recyclerView.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);


        //for music Controller
        relativeLayout = (RelativeLayout) view.findViewById(R.id.muscontroller);
        playOrPause = (ImageButton) view.findViewById(R.id.playorpause);
        next = (ImageButton) view.findViewById(R.id.next);
        previous = (ImageButton) view.findViewById(R.id.previous);
        stop = (ImageButton) view.findViewById(R.id.stop);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous();
            }
        });

        playOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlay) {
                    Pause();
                }else{
                    Play();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stop();
            }
        });
        if(DataTemp.isPLay){
            relativeLayout.setVisibility(View.VISIBLE);
        }else{
            relativeLayout.setVisibility(View.GONE);
        }

        ///

        prepareQuantityJuz();

        return view;

    }

    private void Play(){
        isPlay = true;
        playOrPause.setBackgroundResource(R.drawable.ic_pause_black_24dp);
        mMediaPlayer.start();
    }

    private void Pause(){
        isPlay = false;
        try {
            playOrPause.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
        }catch (Exception e) {

        }
        mMediaPlayer.pause();
    }


    private void Stop(){
        mMediaPlayer.stop();
        relativeLayout.setVisibility(View.GONE);
    }

    private void next(){

        if(currentPositionAyah.getVerseId() < currentPositionAyah.getSumVerse()) {
            currentPositionAyah.inc();
            mMediaPlayer.stop();
            mMediaPlayer = MediaPlayer.create(mContext, data.get(currentPositionAyah.getVerseId()-1).getRawSound());
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mCompleteListener);

        }else{

        }

    }

    private void previous(){

        if(currentPositionAyah.getVerseId() > 1) {
            currentPositionAyah.dec();

            mMediaPlayer.stop();

            mMediaPlayer = MediaPlayer.create(mContext, data.get(currentPositionAyah.getVerseId()-1).getRawSound());
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mCompleteListener);
        }else{

        }
    }


    @Override
    public void onDestroy() {
        try{
            DataTemp.isPLay = false;
            releaseMediaPlayer();
            Stop();
        }catch (Exception e) {

        }
        super.onDestroy();
    }

    public void prepareToPlay(int position, Dialog d){
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            if(isPlay) {
                mMediaPlayer.stop();
                isPlay = false;
            }

            if(data.get(position).getSurahId() ==1) {

                mMediaPlayer = MediaPlayer.create(mContext, data.get(position).getRawSound());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompleteListener);
                isPlay = true;

                currentPositionAyah.setSurahId(data.get(position).getSurahId());
                currentPositionAyah.setVerseId(data.get(position).getVerseID());


            }else{
                //access in SD CARD
                mMediaPlayer = MediaPlayer.create(mContext, R.raw.none_sound);
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompleteListener);

                currentPositionAyah.setSurahId(data.get(position).getSurahId());
                currentPositionAyah.setVerseId(data.get(position).getVerseID());
                isPlay = true;


                Log.d("Play :" , String.valueOf(data.get(position).getSurahId()) + " "+
                        String.valueOf(data.get(position).getVerseID())
                );
            }


            relativeLayout.setVisibility(View.VISIBLE);
            playOrPause.setBackgroundResource(R.drawable.ic_pause_black_24dp);

            if(d !=null)
                d.dismiss();

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void prepareQuantityJuz() {

        ReadJSON readJSON = new ReadJSON();

        data =  readJSON.queryJSON(getActivity(),mNumSurah);
        currentPositionAyah.setSumVerse(data.size());
        Log.d("SumVerse : " , String.valueOf(data.size()));
        Log.d("noSurah : " , String.valueOf(mNumSurah));


//        if(DataTemp.isPLay == true && DataTemp.numSurah+1 == mNumSurah) {
//            prepareToPlay(0 ,null);
//        }

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
                final Dialog d = new BottomSheetDialog(getContext());

                View view = getLayoutInflater().inflate(R.layout.dialog_layout,null);

                LinearLayout play = view.findViewById(R.id.play);
                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DataTemp.numSurah  = mNumSurah;
                        prepareToPlay(position ,d);
                    }
                });
                LinearLayout play_custom = view.findViewById(R.id.play_custom);
                play_custom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog();
                    }
                });

                LinearLayout share = view.findViewById(R.id.share);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(data.get(position).getAyahText() + " \n "
                                + data.get(position).getIndoText() + "\n" +
                        "(" + namaSurah + ":" + data.get(position).getVerseID() + ")");
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

    @Override
    public void onResume() {
        if(DataTemp.isPLay && DataTemp.numSurah == mNumSurah-1){
            relativeLayout.setVisibility(View.VISIBLE);
        }else {
            relativeLayout.setVisibility(View.GONE);
        }

        if(DataTemp.isPLay == true && DataTemp.numSurah == mNumSurah-1) {
            prepareToPlay(0 ,null);
        }

        Log.d("Banding " , String.valueOf(DataTemp.numSurah+2) +" "+ String.valueOf(mNumSurah) );

        super.onResume();
    }

    private MediaPlayer.OnCompletionListener mCompleteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();

            currentPositionAyah.inc();

            if(currentPositionAyah.getVerseId() <= data.size()){
                if(data.get(currentPositionAyah.getVerseId()-1).getRawSound() !=0) {
                    recyclerView.scrollToPosition(currentPositionAyah.getVerseId()-1);
                    mMediaPlayer = MediaPlayer.create(mContext, data.get(currentPositionAyah.getVerseId()-1).getRawSound());
                    mMediaPlayer.start();
                }else{
                    recyclerView.scrollToPosition(currentPositionAyah.getVerseId()-1);
                    mMediaPlayer = MediaPlayer.create(mContext, R.raw.none_sound);
                    mMediaPlayer.start();
                }
                mMediaPlayer.setOnCompletionListener(mCompleteListener);
                int pos = mNumSurah-1;
                SurahActivity.setTabIndex(pos);

            }else{
                try{
                    releaseMediaPlayer();
                    Stop();
                }catch (Exception e) {

                }
                relativeLayout.setVisibility(View.GONE);
                isPlay = false;

                DataTemp.isPLay = true;
                DataTemp.numSurah  = mNumSurah;
                Log.d("surah : ", String.valueOf(mNumSurah) );
                SurahActivity.setTabIndex(mNumSurah);
            }

        }
    };

    private void alertDialog(){
        View view = getLayoutInflater().inflate(R.layout.custom_alert_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.pengulangan)
                .setView(view)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    class CurrentPositionAyah{
        int surahId;
        int verseId;
        int sumVerse;

        public CurrentPositionAyah() {
            surahId =1;
            verseId =1;
        }

        public void inc(){
            verseId++;

            if(surahId > sumVerse) {
                surahId = 1;
                //surahId++;
            }

            if(surahId == 114 && verseId ==6 ) {
                surahId = 1;
                verseId = 1;
            }

            Log.d("data : " , toString());
        }
        //perlu diperbaiki lagi
        public void dec(){
            verseId--;


            if(surahId == 1 && verseId == 1 ) {
                surahId = 114;
                verseId = 1;
            }
        }

        public int getSumVerse() {
            return sumVerse;
        }

        public int getSurahId() {
            return surahId;
        }

        public void setSumVerse(int sumVerse) {
            this.sumVerse = sumVerse;
        }

        public void setSurahId(int surahId) {
            this.surahId = surahId;
        }

        public int getVerseId() {
            return verseId;
        }

        public void setVerseId(int verseId) {
            this.verseId = verseId;
        }

        @Override
        public String toString() {
            return "CurrentPositionAyah{" +
                    "surahId=" + surahId +
                    ", verseId=" + verseId +
                    ", sumVerse=" + sumVerse +
                    '}';
        }
    }
}
