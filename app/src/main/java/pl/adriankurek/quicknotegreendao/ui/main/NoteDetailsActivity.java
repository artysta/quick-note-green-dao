package pl.adriankurek.quicknotegreendao.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pl.adriankurek.quicknotegreendao.R;

public class NoteDetailsActivity extends AppCompatActivity {
    private TextView txtDate;
    private TextView txtContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        txtDate = findViewById(R.id.details_note_date);
        txtContents = findViewById(R.id.details_note_contents);

        Intent intent = getIntent();

        String creationDate = intent.getStringExtra("CREATION_DATE");
        String contents = intent.getStringExtra("CONTENTS");

        txtDate.setText(creationDate);
        txtContents.setText(contents);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
