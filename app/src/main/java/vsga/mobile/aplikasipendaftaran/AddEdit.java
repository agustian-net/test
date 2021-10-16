package vsga.mobile.aplikasipendaftaran;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import vsga.mobile.aplikasipendaftaran.helper.DBHelper;

public class AddEdit extends AppCompatActivity {
    //Deklarasikan variabel
    EditText txt_id, txt_nama, txt_alamat, txt_nohp, txt_jeniskelamin, txt_lokasi, txt_foto  ;
    Button btn_submit;
    DBHelper SQLite = new DBHelper(this);
    String id, nama, alamat, nohp, jeniskelamin, lokasi, foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //mengambil value berdasarkan id
        txt_id = findViewById(R.id.eid);
        txt_nama = findViewById(R.id.enama);
        txt_alamat = findViewById(R.id.ealamat);
        txt_nohp = findViewById(R.id.enohp);
        txt_jeniskelamin = findViewById(R.id.rgjeniskelamin);
        txt_lokasi = findViewById(R.id.elokasi);
        txt_foto = findViewById(R.id.efoto);
        btn_submit = findViewById(R.id.bsubmit);

        //menampilkan value yang diinput
        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        nama = getIntent().getStringExtra(MainActivity.TAG_NAMA);
        alamat = getIntent().getStringExtra(MainActivity.TAG_ALAMAT);
        nohp = getIntent().getStringExtra(MainActivity.TAG_NOHP);
        jeniskelamin = getIntent().getStringExtra(MainActivity.TAG_JK);
        lokasi = getIntent().getStringExtra(MainActivity.TAG_LOKASI);
        foto = getIntent().getStringExtra(MainActivity.TAG_FOTO);


        //membuat kondisi untuk add data dan edit
        if (id == null || id == ""){
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_nama.setText(nama);
            txt_alamat.setText(alamat);
            txt_nohp.setText(nohp);
            txt_jeniskelamin.setText(jeniskelamin);
            txt_lokasi.setText(lokasi);
            txt_foto.setText(foto);
        }

        //fungsi simpan
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txt_id.getText().toString().equals("")){
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e){
                    Log.e("Submit", e.toString());
                }
            }
        });

//        //fungsi cancel
//        btn_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                blank();
//                finish();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    //memilih item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //mengosongkan value pada form
    private void blank(){
        txt_nama.requestFocus();
        txt_id.setText(null);
        txt_nama.setText(null);
        txt_alamat.setText(null);
        txt_nohp.setText(null);
        txt_lokasi.setText(null);
        txt_foto.setText(null);
    }

    //menyimpan data ke database
    private void save(){
        if (String.valueOf(txt_nama.getText()).equals(null) || String.valueOf(txt_nama.getText()).equals("") ||
                String.valueOf(txt_alamat.getText()).equals(null) || String.valueOf(txt_alamat.getText()).equals("") ||
                String.valueOf(txt_nohp.getText()).equals(null) || String.valueOf(txt_nohp.getText()).equals("") ||
                String.valueOf(txt_jeniskelamin.getText()).equals(null) || String.valueOf(txt_jeniskelamin.getText()).equals("") ||
                String.valueOf(txt_lokasi.getText()).equals(null) || String.valueOf(txt_lokasi.getText()).equals("") ||
                String.valueOf(txt_foto.getText()).equals(null) || String.valueOf(txt_foto.getText()).equals(""))
        {
            Toast.makeText(getApplicationContext(),
                    "Please input field completely..", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(txt_nama.getText().toString().trim(),
                    txt_alamat.getText().toString().trim(),
                    txt_nohp.getText().toString().trim(),
                    txt_jeniskelamin.getText().toString().trim(),
                    txt_lokasi.getText().toString().trim(),
                    txt_foto.getText().toString().trim());
            blank();
            finish();
        }
    }

    //update data kedalam database
    private void edit(){
        if ((String.valueOf(txt_nama.getText()).equals(null) || String.valueOf(txt_nama.getText()).equals("")) ||
                String.valueOf(txt_alamat.getText()).equals(null) || String.valueOf(txt_alamat.getText()).equals("") ||
                String.valueOf(txt_nohp.getText()).equals(null) || String.valueOf(txt_nohp.getText()).equals("") ||
                String.valueOf(txt_jeniskelamin.getText()).equals(null) || String.valueOf(txt_jeniskelamin.getText()).equals("") ||
                String.valueOf(txt_lokasi.getText()).equals(null) || String.valueOf(txt_lokasi.getText()).equals("") ||
                String.valueOf(txt_foto.getText()).equals(null) || String.valueOf(txt_foto.getText()).equals(""))
        {
            Toast.makeText(getApplicationContext(),
                    "Please input name or address..", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(txt_nama.getText().toString().trim(),
                    txt_alamat.getText().toString().trim(),
                    txt_nohp.getText().toString().trim(),
                    txt_jeniskelamin.getText().toString().trim(),
                    txt_lokasi.getText().toString().trim(),
                    txt_foto.getText().toString().trim());
            blank();
            finish();
        }
    }
}
