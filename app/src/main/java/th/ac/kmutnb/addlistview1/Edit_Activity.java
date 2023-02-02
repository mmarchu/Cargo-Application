package th.ac.kmutnb.addlistview1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class Edit_Activity extends AppCompatActivity {

    EditText edId,edName,edPosition,edAmount;
    private int position;

    //load
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gray)));

        edId = findViewById(R.id.ed_id);
        edName = findViewById(R.id.ed_name);
        edPosition = findViewById(R.id.ed_position);
        edAmount = findViewById(R.id.ed_amount);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        edId.setText(MainActivity.productArrayList.get(position).getId());
        edName.setText(MainActivity.productArrayList.get(position).getName());
        edPosition.setText(MainActivity.productArrayList.get(position).getPosition());
        edAmount.setText(MainActivity.productArrayList.get(position).getAmount());




    }

    public void btn_updateData(View view) {

        String id = edId.getText().toString();
        String name = edName.getText().toString();
        String position = edPosition.getText().toString();
        String amount = edAmount.getText().toString();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "https://surasee.000webhostapp.com/update.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Edit_Activity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Edit_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("id", id);
                params.put("name", name);
                params.put("position", position);
                params.put("amount", amount);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Edit_Activity.this);
        requestQueue.add(request);

    }


}