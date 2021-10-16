package vsga.mobile.aplikasipendaftaran;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import vsga.mobile.aplikasipendaftaran.adapter.MyAdapter;
import vsga.mobile.aplikasipendaftaran.helper.DBHelper;
import vsga.mobile.aplikasipendaftaran.model.Data;

public class MainActivity extends AppCompatActivity {

    //mendeklarasikan variabel
    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> itemList = new ArrayList<>();
    MyAdapter adapter;
    DBHelper SQLite = new DBHelper(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_NOHP = "nohp";
    public static final String TAG_JK = "jeniskelamin";
    public static final String TAG_LOKASI = "lokasi";
    public static final String TAG_FOTO = "foto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tambah SQLite
        SQLite = new DBHelper(getApplicationContext());

        //Tambah ListView
        listView = findViewById(R.id.list_view);

        //Tambah adapter dan listview
        adapter = new MyAdapter(MainActivity.this, itemList);
        listView.setAdapter((ListAdapter) adapter);

        //tekan lama untuk menampilkan edit dan hapus
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            /* Todo Auto-generated method stud */
            final String idx = itemList.get(position).getId();
            final String nama = itemList.get(position).getNama();
            final String alamat = itemList.get(position).getAlamat();
            final Integer nohp = itemList.get(position).getNohp();
            final String jeniskelamin = itemList.get(position).getJeniskelamin();
            final String lokasi = itemList.get(position).getLokasi();
            final String foto = itemList.get(position).getFoto();

            final CharSequence[] dialogitem = {"Edit", "Delete"};
            dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setCancelable(true);
            dialog.setItems(dialogitem, (dialog, which) -> {
                /* TODO Auto-generated method stud */
                switch (which) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, AddEdit.class);
                        intent.putExtra(TAG_ID, idx);
                        intent.putExtra(TAG_NAMA, nama);
                        intent.putExtra(TAG_ALAMAT, alamat);
                        intent.putExtra(TAG_NOHP, nohp);
                        intent.putExtra(TAG_JK, jeniskelamin);
                        intent.putExtra(TAG_LOKASI, lokasi);
                        intent.putExtra(TAG_FOTO, foto);
                        startActivity(intent);
                        break;
                    case 1:
                        SQLite.delete(Integer.parseInt(idx));
                        itemList.clear();
                        getAllData();
                        break;

                }
            }).show();
            return false;
        });
        getAllData();
    }

    //Fungsi ini digunakan untuk mengambil semua data yang ada pada database
    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String poster = row.get(i).get(TAG_NAMA);
            String title = row.get(i).get(TAG_ALAMAT);
            String title1 = row.get(i).get(TAG_JK);
            String poster2 = row.get(i).get(TAG_LOKASI);
            String title2 = row.get(i).get(TAG_FOTO);

            Data data = new Data();

            data.setId(id);
            data.setNama(poster);
            data.setAlamat(title);
            data.setJeniskelamin(title1);
            data.setLokasi(poster2);
            data.setFoto(title2);

            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }
}
