package mengaji.firli.id.firlimengaji.model;

public class Juz {
    private int mJuz;
    private int mImage;
    private int mMulai;


    public Juz(int mJuz, int mImage, int mMulai) {
        this.mJuz = mJuz;
        this.mImage = mImage;
        this.mMulai = mMulai;

    }

    public int getmJuz() {
        return mJuz;
    }

    public void setmJuz(int mJuz) {
        this.mJuz = mJuz;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public int getmMulai() {
        return mMulai;
    }

    public void setmMulai(int mMulai) {
        this.mMulai = mMulai;
    }
}
