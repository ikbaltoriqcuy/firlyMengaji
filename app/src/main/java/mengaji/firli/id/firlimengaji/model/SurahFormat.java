package mengaji.firli.id.firlimengaji.model;

public class SurahFormat {

    private int id;
    private int surahId;
    private int verseID;
    private String ayahText;
    private String indoText;
    private String mSound;
    private int rawSound;

    public SurahFormat(int id, int surahId, int verseID, String ayahText, String indoText, String mSound) {
        this.id = id;
        this.surahId = surahId;
        this.verseID = verseID;
        this.ayahText = ayahText;
        this.indoText = indoText;
        this.mSound = mSound;
    }

    public SurahFormat(int id, int surahId, int verseID, String ayahText, String indoText, int rawSound) {
        this.id = id;
        this.surahId = surahId;
        this.verseID = verseID;
        this.ayahText = ayahText;
        this.indoText = indoText;
        this.rawSound = rawSound;
    }

    public int getRawSound() {
        return rawSound;
    }

    public void setRawSound(int rawSound) {
        this.rawSound = rawSound;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSurahId() {
        return surahId;
    }

    public void setSurahId(int surahId) {
        this.surahId = surahId;
    }

    public int getVerseID() {
        return verseID;
    }

    public void setVerseID(int verseID) {
        this.verseID = verseID;
    }

    public String getAyahText() {
        return ayahText;
    }

    public void setAyahText(String ayahText) {
        this.ayahText = ayahText;
    }

    public String getIndoText() {
        return indoText;
    }

    public void setIndoText(String indoText) {
        this.indoText = indoText;
    }

    public String getmSound() {
        return mSound;
    }

    public void setmSound(String mSound) {
        this.mSound = mSound;
    }

    @Override
    public String toString() {
        return "SurahFormat{" +
                "id=" + id +
                ", surahId=" + surahId +
                ", verseID=" + verseID +
                ", ayahText='" + ayahText + '\'' +
                ", indoText='" + indoText + '\'' +
                '}';
    }
}
