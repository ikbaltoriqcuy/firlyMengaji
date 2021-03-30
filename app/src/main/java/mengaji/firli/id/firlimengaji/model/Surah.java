package mengaji.firli.id.firlimengaji.model;

public class Surah {
    private int mArab;
    private int mIndonesia;
    private int mAudio;
    private int mAyat;
    private int mNumAyat;

    public Surah(int mArab, int mIndonesia, int mAudio, int mAyat, int mNumAyat) {
        this.mArab = mArab;
        this.mIndonesia = mIndonesia;
        this.mAudio = mAudio;
        this.mAyat = mAyat;
        this.mNumAyat = mNumAyat;
    }

    public int getmArab() {
        return mArab;
    }

    public void setmArab(int mArab) {
        this.mArab = mArab;
    }

    public int getmIndonesia() {
        return mIndonesia;
    }

    public void setmIndonesia(int mIndonesia) {
        this.mIndonesia = mIndonesia;
    }

    public int getmAudio() {
        return mAudio;
    }

    public void setmAudio(int mAudio) {
        this.mAudio = mAudio;
    }

    public int getmAyat() {
        return mAyat;
    }

    public void setmAyat(int mAyat) {
        this.mAyat = mAyat;
    }

    public int getmNumAyat() {
        return mNumAyat;
    }

    public void setmNumAyat(int mNumAyat) {
        this.mNumAyat = mNumAyat;
    }
}
