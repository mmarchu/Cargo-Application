package th.ac.kmutnb.addlistview1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myapp_listview";
    ListView listView;
    MyAdapter adapter;
    Session session;

    public static ArrayList<Product> productArrayList = new ArrayList<>();
    String url = "https://surasee.000webhostapp.com/retrieve.php";

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new Session(this);
        session.checkLogin();
        HashMap<String,String> user=session.getUserDetail();
        String name=user.get(session.NAME);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gray)));

        listView = findViewById(R.id.myListView);
        adapter = new MyAdapter(this,productArrayList);
        listView.setAdapter(adapter);

        //listview Click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Details","Edit Product","Delete Product"};
                builder.setTitle(productArrayList.get(position).getName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(),DetailActivity.class)
                                .putExtra("position",position));

                                break;

                            case 1:

                                startActivity(new Intent(getApplicationContext(),Edit_Activity.class)
                                .putExtra("position",position));

                                break;

                            case 2:

                                deleteData(productArrayList.get(position).getId());

                                break;
                        }


                    }
                });

                builder.create().show();





            }
        });

        retrieveData();
    }
    //delete listview
    private void deleteData(final String id) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://surasee.000webhostapp.com/delete.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Data Deleted")){

                            Toast.makeText(MainActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("id", id);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    //listview
    public  void retrieveData(){
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        productArrayList.clear();
                        try {

                            JSONObject jasonObject = new JSONObject(response);
                            String success = jasonObject.getString("success");
                            JSONArray jasonArray = jasonObject.getJSONArray("product");

                            if(success.equals("1")){

                                for(int i=0; i<jasonArray.length(); i++){

                                    JSONObject object = jasonArray.getJSONObject(i);

                                    String id = object.getString("id");
                                    String name = object.getString("name");
                                    String position = object.getString("position");
                                    String amount = object.getString("amount");

                                    product = new Product(id,name,position,amount);
                                    productArrayList.add(product);
                                    adapter.notifyDataSetChanged();


                                }
                            }

                        }

                        catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    public void btn_add_activity(View view){
        startActivity(new Intent(getApplicationContext(),Add_Data_Activity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mnu_logout, menu); //load item from XML
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.mnu_logout:
                session.logout();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}