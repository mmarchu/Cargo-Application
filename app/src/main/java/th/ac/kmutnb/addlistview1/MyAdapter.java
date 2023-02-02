package th.ac.kmutnb.addlistview1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Product> {

    Context context;
    List<Product> arrayListProduct;

    public MyAdapter(@NonNull Context context, List<Product> arrayListProduct) {

        super(context, R.layout.custom_list_item,arrayListProduct);

        this.context = context;
        this.arrayListProduct = arrayListProduct;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);

        tvID.setText(arrayListProduct.get(position).getId());
        tvName.setText(arrayListProduct.get(position).getName());

        return view;
    }
}
