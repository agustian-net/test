package vsga.mobile.aplikasipendaftaran.adapter;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vsga.mobile.aplikasipendaftaran.R;
import vsga.mobile.aplikasipendaftaran.model.Data;
//class ini berfungsi untuk menampilkan data kdalam listview
public class MyAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public MyAdapter(Activity activity, List<Data> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView id = convertView.findViewById(R.id.txtid);
        TextView nama = convertView.findViewById(R.id.txtnama);
        TextView alamat = convertView.findViewById(R.id.txtalamat);
        TextView nohp = convertView.findViewById(R.id.txtnohp);
        TextView jeniskelamin = convertView.findViewById(R.id.txtJK);
        TextView lokasi = convertView.findViewById(R.id.txtlokasi);
        TextView foto = convertView.findViewById(R.id.txtfoto);
        Data data = items.get(position);

        id.setText(data.getId());
        nama.setText(data.getNama());
        alamat.setText(data.getAlamat());
        nohp.setText(data.getNohp());
        jeniskelamin.setText(data.getJeniskelamin());
        lokasi.setText(data.getLokasi());
        foto.setText(data.getFoto());

        return convertView;
    }
}
