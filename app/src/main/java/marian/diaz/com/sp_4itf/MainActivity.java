package marian.diaz.com.sp_4itf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText eName, ePwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eName = findViewById(R.id.etName);
        ePwd = findViewById(R.id.etPassword);
    }

    public void next (View v){
        Intent i = new Intent(this, Activity2.class);
        startActivity(i);
    }

    public void saveData(View v){
        String uname = eName.getText().toString();
        String pwd = ePwd.getText().toString();
        SharedPreferences sp = getSharedPreferences("Data1", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("user", uname);
        editor.putString("pass", pwd);
        editor.commit();
        Toast.makeText(this, "Data Send...", Toast.LENGTH_LONG).show();
    }

    public void saveInternal(View v){
        FileOutputStream fos = null;
        String uname = eName.getText().toString();
        String pwd = ePwd.getText().toString();
        byte[] buf1 = uname.getBytes();
        byte[] buf2 = pwd.getBytes();

        try {
            fos = openFileOutput("Data2.txt", MODE_PRIVATE);
            fos.write(buf1);
            fos.write(buf2);
        } catch (Exception e) {
            Log.d("4ITF", "File not existing...");

        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Data written in internal storage", Toast.LENGTH_LONG).show();

    }

    public void saveExternal(View v){
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "internal.txt");
        String uname = eName.getText().toString();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(uname.getBytes());
            Toast.makeText(this, "Save in internal storage...", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(this, "Error writing data...", Toast.LENGTH_LONG).show();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveEC(View v){
        File folder = getExternalCacheDir();
        File file = new File(folder, "myCache.txt");
        String uname = eName.getText().toString();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(uname.getBytes());
            Toast.makeText(this, "Save in cache storage...", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(this, "Error writing data...", Toast.LENGTH_LONG).show();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
