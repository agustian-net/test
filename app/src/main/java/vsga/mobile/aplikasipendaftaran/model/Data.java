package vsga.mobile.aplikasipendaftaran.model;
//class ini berfungsi untuk membuat object
public class Data {
    private String id, nama, alamat, jeniskelamin, lokasi, foto ;
    private Integer nohp;
    public Data(){

    }

    public Data(String id, String nama, String alamat,  int nohp, String jeniskelamin, String lokasi, String foto){
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.nohp = nohp;
        this.jeniskelamin = jeniskelamin;
        this.lokasi = lokasi;
        this.foto = foto;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getAlamat(){
        return alamat;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public Integer getNohp(){
        return nohp;
    }

    public  void setNohp(Integer nohp){
        this.nohp = nohp;
    }

    public String getJeniskelamin(){
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin){
        this.jeniskelamin = jeniskelamin;
    }

    public String getLokasi(){
        return lokasi;
    }

    public void setLokasi(String lokasi){
        this.lokasi = lokasi;
    }

    public String getFoto(){
        return foto;
    }

    public  void setFoto(String foto){
        this.foto = foto;
    }
}
