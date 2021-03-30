package mengaji.firli.id.firlimengaji.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import mengaji.firli.id.firlimengaji.R;
import mengaji.firli.id.firlimengaji.model.Juz;

public class JuzAdapter extends RecyclerView.Adapter<JuzAdapter.ViewHolder> {
    private Context context;
    private List<Juz> data;
    private int mResourceId;
    private JuzAdapter.ClickHandler mClickHandler;
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudiManager;

//    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
//        @Override
//        public void onCompletion(MediaPlayer mediaPlayer) {
//            releaseMediaPlayer();
//        }
//    };


    public JuzAdapter(Context context,
                      List<Juz> data,
                      JuzAdapter.ClickHandler mClickHandler) {
        this.context = context;
        this.data = data;
        this.mClickHandler = mClickHandler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_juz, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.tvNumJuz.setText(String.valueOf(position+1));
            holder.tvJuz.setText(data.get(position).getmJuz());
            holder.tvMulai.setText(data.get(position).getmMulai());
            //holder.imageView.setImageResource(data.get(position).getmImage());
    }

//    private void releaseMediaPlayer(){
//        if (mMediaPlayer != null){
//            mMediaPlayer.release();
//            mMediaPlayer = null;
//            mAudiManager.abandonAudioFocus(mOnAudioFocusChangeListener);
//        }
//    }

//    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
//        @Override
//        public void onAudioFocusChange(int focusChange) {
//            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
//                mMediaPlayer.pause();
//                mMediaPlayer.seekTo(0);
//            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
//                mMediaPlayer.start();
//            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
//                releaseMediaPlayer();
//            }
//        }
//    };

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements
            View.OnClickListener,
            View.OnLongClickListener{
        TextView tvJuz;
        TextView tvMulai;
        TextView tvNumJuz;
        ImageView imageView;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvJuz = (TextView) itemView.findViewById(R.id.juz);
            tvMulai = (TextView) itemView.findViewById(R.id.mulai);
            tvNumJuz = (TextView) itemView.findViewById(R.id.num_juz);
            imageView = (ImageView) itemView.findViewById(R.id.angka);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.background_juz);
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
