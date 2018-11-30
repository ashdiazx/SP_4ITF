package marian.diaz.com.sp_4itf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Activity2 extends AppCompatActivity {
    TextView toGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        toGreeting = findViewById(R.id.toGreeting);
    }

    public void next (View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    public void greet(View v){
        SharedPreferences pref = getSharedPreferences("Data1", MODE_PRIVATE);
        String uname = pref.getString("user", null);
        String pass = pref.getString("pass", null);
        toGreeting.setText("Hello " + uname + "Your Password is " + pass);
    }

    public void greetCache(View v){
        File folder = getExternalCacheDir();
        File file = new File(folder, "myCache.txt");
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
            int c;
            StringBuffer buffer = new StringBuffer();
            while ((c=fin.read()) != -1){
                buffer.append((char)c);
            }
            String msg = "Good morning" + buffer;
            toGreeting.setText(msg);
        } catch (java.io.IOException e) {
            Toast.makeText(this, "Error in reading cache...", Toast.LENGTH_LONG).show();

        }
    }
}
