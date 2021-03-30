package mengaji.firli.id.firlimengaji.Fragment;


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
import android.support.design.widget.BottomSheetDialogFragment;
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
import mengaji.firli.id.firlimengaji.ReadListSurahJSON;
import mengaji.firli.id.firlimengaji.adapter.ListSurahAdapter;
import mengaji.firli.id.firlimengaji.adapter.SurahAdapter;
import mengaji.firli.id.firlimengaji.model.ListSurah;
import mengaji.firli.id.firlimengaji.model.Surah;
import mengaji.firli.id.firlimengaji.model.SurahFormat;
import mengaji.firli.id.firlimengaji.surah.SurahActivity;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class SurahFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListSurahAdapter mAdapter;
    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;
    private Context mContext;
    private BottomSheetDialog bottomSheetDialog;
    private ArrayList<Surah> models = new ArrayList<>();
    private ArrayList mSelectedId;

    @SuppressLint("ValidFragment")
    public SurahFragment(Context context) {
        mContext = context;
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

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

        View view = inflater.inflate(R.layout.fragment_surah, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclewViewSurah);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);


//
//        View playView = bottomSheetDialogView.findViewById(R.id.play);
//        View shareView = bottomSheetDialogView.findViewById(R.id.share);
//        View copyView = bottomSheetDialogView.findViewById(R.id.copy);
//        View bookmarkView = bottomSheetDialogView.findViewById(R.id.bookmark);
//        View lastSeenView = bottomSheetDialogView.findViewById(R.id.last_seen);

//        playView.setOnClickListener(this);
//        shareView.setOnClickListener(this);
//        copyView.setOnClickListener(this);
//        bookmarkView.setOnClickListener(this);
//        lastSeenView.setOnClickListener(this);
        prepareSurahData();




        // Inflate the layout for this fragment
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void prepareSurahData(){

        List<ListSurah> listSurahs = ReadListSurahJSON.queryJSON(getActivity());


        mAdapter = new ListSurahAdapter(getContext(), listSurahs, new ListSurahAdapter.ClickHandler() {
            @Override
            public void onItemClick(final int position) {
                Intent intent = new Intent(getContext(), SurahActivity.class);
                intent.putExtra("tab", position);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(int position) {
//                Toast.makeText(getContext(), "Benar ditekan", Toast.LENGTH_SHORT).show();
//                String message;
//                if (models.size() == 1)
//                    message = getString(R.string.pengulangan);
//                else {
//                    message = getString(R.string.pengulangan);
//                }
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
//                        .setMessage(message)
//                        .setPositiveButton(R.string.pengulangan, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        })
//                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                                //mActionMode.finish();
//                            }
//                        });
//                builder.create().show();
                        return false;
                    }
                });

                recyclerView.setAdapter(mAdapter);
            }
        }

//    private void createDialog(){
//        mSelectedId = new ArrayList();
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setTitle(R.string.pengulangan)
//                .setIcon(R.drawable.ic_warning_black_24dp)
//                        .setMultiChoiceItems(R.array.jenis_pengulangan, null,
//                                new DialogInterface.OnMultiChoiceClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
//                                        if (isChecked){
//                                            mSelectedId.add(i);
//                                        }else if (mSelectedId.contains(i)){
//                                            mSelectedId.remove(Integer.valueOf(i));
//                                        }
//
//                                    }
//                                })
//                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(getContext(), "Anda berhasil tekan save", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(getContext(), "Anda berhasil tekan cancel", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//    }
//
//    private void releaseMediaPlayer(){
//        if (mMediaPlayer != null){
//            mMediaPlayer.release();
//            mMediaPlayer = null;
//            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
//        }
//    }

//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        switch (id) {
//            case R.id.recyclewViewSurah:
//                bottomSheetDialog.show();
//                break;
//            case R.id.play:
//                Toast.makeText(getContext(), "Play Murottal", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.share:
//                Toast.makeText(getContext(), "Bagikan Ayat", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.copy:
//                Toast.makeText(getContext(), "Salin Ayat", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.bookmark:
//                Toast.makeText(getContext(), "Tambahkan ke Bookmark", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.last_seen:
//                Toast.makeText(getContext(), "Tandai Terakhir Baca", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }

//    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
//        @Override
//        public void onAudioFocusChange(int focusChange) {
//            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
//                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
//                mMediaPlayer.pause();
//                mMediaPlayer.seekTo(0);
//            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
//                mMediaPlayer.start();
//            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
//                releaseMediaPlayer();
//            }
//        }
//    };
//
//    private MediaPlayer.OnCompletionListener mCompleteListener = new MediaPlayer.OnCompletionListener() {
//        @Override
//        public void onCompletion(MediaPlayer mediaPlayer) {
//            releaseMediaPlayer();
//        }
//    };
