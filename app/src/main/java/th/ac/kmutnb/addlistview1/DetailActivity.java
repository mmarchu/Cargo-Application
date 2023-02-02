package th.ac.kmutnb.addlistview1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    TextView tvid,tvname,tvposition,tvamount;
    Button BTdelete;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gray)));

        //Initializing Views
        tvid = findViewById(R.id.txtid);
        tvname = findViewById(R.id.txtname);
        tvposition = findViewById(R.id.txtposition);
        tvamount = findViewById(R.id.txtamount);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: "+MainActivity.productArrayList.get(position).getId());
        tvname.setText("Name: "+MainActivity.productArrayList.get(position).getName());
        tvposition.setText("Position: "+MainActivity.productArrayList.get(position).getPosition());
        tvamount.setText("Amount: "+MainActivity.productArrayList.get(position).getAmount());

    }


}