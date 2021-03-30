package mengaji.firli.id.firlimengaji.adapter;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import mengaji.firli.id.firlimengaji.R;
import mengaji.firli.id.firlimengaji.model.ListSurah;
import mengaji.firli.id.firlimengaji.model.SurahFormat;

public class ListSurahAdapter extends RecyclerView.Adapter<ListSurahAdapter.ViewHolder> {
    private Context mContext;
    private ClickHandler mClickHandler;
    private List<ListSurah> data;
    private int mColorResourceId;
    private BottomSheetDialog bottomSheetDialog;


    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;

    public ListSurahAdapter(Context mContext,
                            List<ListSurah> data,
                            ClickHandler handler) {
        this.mContext = mContext;
        this.mClickHandler = handler;
        this.data = data;
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    private MediaPlayer.OnCompletionListener mCompleteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
          holder.arab.setText(data.get(position).getNamaArab());
          holder.indo.setText(data.get(position).getNamaIndo());
          holder.diturunkan.setText(data.get(position).getDiturunkan());
          holder.jumlahAyat.setText(data.get(position).getJumlahayat());
          holder.noSurah.setText(data.get(position).getNomersurat());

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
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements
            View.OnClickListener,
            View.OnLongClickListener{

        TextView arab;
        TextView indo;
        TextView diturunkan;
        TextView jumlahAyat;
        TextView noSurah;



        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            arab = (TextView) itemView.findViewById(R.id.arab);
            indo = (TextView) itemView.findViewById(R.id.textViewJudulSurah);
            diturunkan = (TextView) itemView.findViewById(R.id.textViewIndonesia);
            jumlahAyat = (TextView) itemView.findViewById(R.id.ayat);
            noSurah = (TextView) itemView.findViewById(R.id.num_surah);


        }

        @Override
        public void onClick(View view) {
            mClickHandler.onItemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            return mClickHandler.onItemLongClick(getAdapterPosition());
        }
    }



    public interface ClickHandler{
        void onItemClick(int position);
        boolean onItemLongClick(int position);

    }
}
