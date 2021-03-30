package mengaji.firli.id.firlimengaji.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import mengaji.firli.id.firlimengaji.R;
import mengaji.firli.id.firlimengaji.model.Surah;
import mengaji.firli.id.firlimengaji.model.SurahFormat;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.ViewHolder> {
    private Context mContext;
    private ClickHandler mClickHandler;
    private List<SurahFormat> data;
    private int mColorResourceId;
    private BottomSheetDialog bottomSheetDialog;


    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;

    public SurahAdapter(Context mContext,
                        List<SurahFormat> data,
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surah_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(data.get(position) !=null) {
            if(data.get(position).getVerseID() == 1) {
                holder.bismillahreltiveLayout.setVisibility(View.VISIBLE);

                if(data.get(position).getSurahId() == 1) {
                    holder.textBismillah.setText("Pembukaaan");
                    holder.textBismillah.setTextSize(16);
                }

                holder.numayah.setText(String.valueOf(data.get(position).getVerseID()));
                holder.ayah.setText(data.get(position).getAyahText());
                holder.arti.setText(data.get(position).getIndoText());
            }else{
                holder.bismillahreltiveLayout.setVisibility(View.GONE);
                holder.numayah.setText(String.valueOf(data.get(position).getVerseID()));
                holder.ayah.setText(data.get(position).getAyahText());
                holder.arti.setText(data.get(position).getIndoText());
            }
        }
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
        TextView ayah;
        TextView numayah;
        TextView arti;
        TextView textBismillah;
        RelativeLayout bismillahreltiveLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ayah = (TextView) itemView.findViewById(R.id.ayah);
            numayah = (TextView) itemView.findViewById(R.id.num_ayah);
            arti = (TextView) itemView.findViewById(R.id.arti);

            textBismillah = (TextView) itemView.findViewById(R.id.textBismillah);
            bismillahreltiveLayout = (RelativeLayout) itemView.findViewById(R.id.bismillah);

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
