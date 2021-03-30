package mengaji.firli.id.firlimengaji.model;

public class ListSurah {
    private String namaIndo ;
    private String namaArab ;
    private String diturunkan ;
    private String jumlahayat ;
    private String nomersurat;

    public ListSurah(String namaIndo, String namaArab, String diturunkan, String jumlahayat, String nomersurat) {
        this.namaIndo = namaIndo;
        this.namaArab = namaArab;
        this.diturunkan = diturunkan;
        this.jumlahayat = jumlahayat;
        this.nomersurat = nomersurat;
    }

    @Override
    public String toString() {
        return "ListSurah{" +
                "namaIndo='" + namaIndo + '\'' +
                ", namaArab='" + namaArab + '\'' +
                ", diturunkan='" + diturunkan + '\'' +
                ", jumlahayat='" + jumlahayat + '\'' +
                ", nomersurat='" + nomersurat + '\'' +
                '}';
    }

    public String getNamaIndo() {
        return namaIndo;
    }

    public void setNamaIndo(String namaIndo) {
        this.namaIndo = namaIndo;
    }

    public String getNamaArab() {
        return namaArab;
    }

    public void setNamaArab(String namaArab) {
        this.namaArab = namaArab;
    }

    public String getDiturunkan() {
        return diturunkan;
    }

    public void setDiturunkan(String diturunkan) {
        this.diturunkan = diturunkan;
    }

    public String getJumlahayat() {
        return jumlahayat;
    }

    public void setJumlahayat(String jumlahayat) {
        this.jumlahayat = jumlahayat;
    }

    public String getNomersurat() {
        return nomersurat;
    }

    public void setNomersurat(String nomersurat) {
        this.nomersurat = nomersurat;
    }
}
