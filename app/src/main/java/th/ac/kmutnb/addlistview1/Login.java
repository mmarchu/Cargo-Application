package th.ac.kmutnb.addlistview1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Login extends AppCompatActivity {

    private EditText enterUsername, enterPassword;
    private String username, password;
    private String URL = "http://10.0.2.2:80/pjmobile/login.php";
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new Session(this);
        if (session.isLoggin()){
            finish();
            startActivity(new Intent(Login.this, MainActivity.class));
        }
        getSupportActionBar().hide();



        username = password = "";
        enterUsername = findViewById(R.id.enterUsername);
        enterPassword = findViewById(R.id.enterPassword);
    }
    public void login(View view) {
        username = enterUsername.getText().toString().trim();
        password = enterPassword.getText().toString().trim();
        if(!username.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("res", response);
                    if (response.equals("success")) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        session.createSession(username);
                        Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    } else if (response.equals("failed")) {
                        Toast.makeText(Login.this, "Email or Password was wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Login.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("username", username);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }
    public void Create(View V) {
        Intent itn = new Intent(this, RegisterActivity.class);
        startActivity(itn);
    }
}