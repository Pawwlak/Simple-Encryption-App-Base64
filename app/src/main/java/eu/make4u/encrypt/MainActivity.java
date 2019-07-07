package eu.make4u.encrypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etInput, etOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = (EditText) findViewById(R.id.etInput);
        etOutput =(EditText) findViewById(R.id.etOutput);

    }

    public void encrypt(View v){
        byte[] base64Data = etInput.getText().toString().getBytes();
        String base64Str = "";

        try {
            base64Str = Base.encryptBASE64(base64Data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        etOutput.setText(base64Str);
    }

    public void decrypt(View v){

        String base64Str = etInput.getText().toString();
        byte[] base64Byte = null;
        try {
            base64Byte = Base.decryptBASE64(base64Str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String output = new String(base64Byte);
        etOutput.setText(output);

    }

    public void copy(View v){

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copy", etOutput.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied", Toast.LENGTH_SHORT).show();

    }

    public void past(View v){

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String text = clipboard.getText().toString();
        etInput.setText(text);
        Toast.makeText(getApplicationContext(), "Pased", Toast.LENGTH_SHORT).show();
    }
}
