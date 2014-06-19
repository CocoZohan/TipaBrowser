package com.example.TipaBrowser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends Activity implements View.OnClickListener{
    /**
     * Called when the activity is first created.
     */
    EditText addressEditText;
    public static ProgressBar progressBar;
    Button btnGo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        addressEditText = (EditText)findViewById(R.id.addressMain);

        // progress bar is initialized, but set invisible
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        btnGo = (Button)findViewById(R.id.buttonGo);
        btnGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + addressEditText.getText().toString() + "/")));
    }
}
