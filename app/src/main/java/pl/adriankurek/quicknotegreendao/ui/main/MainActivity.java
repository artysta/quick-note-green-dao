package pl.adriankurek.quicknotegreendao.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.adriankurek.quicknotegreendao.R;
import pl.adriankurek.quicknotegreendao.data.database.DbController;
import pl.adriankurek.quicknotegreendao.data.model.Note;
import pl.adriankurek.quicknotegreendao.ui.main.api.Contract;

public class MainActivity extends AppCompatActivity implements Contract.View {
    private Button btnAdd;
    private EditText editContents;

    private RecyclerView recyclerNotes;
    private List<Note> notes;
    private MyAdapter adapter;
    private Contract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerNotes = findViewById(R.id.recycler_notes);

        presenter = new Presenter(this, new DbController(this));

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(e -> {
            presenter.onAddButtonClick();
            presenter.reloadData();
            adapter.notifyItemInserted(0);
        });

        editContents = findViewById(R.id.edit_contents);
    }

    @Override
    public void refreshData(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String getNewNoteContents() {
        return editContents.getText().toString();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setUpAdapter(List<Note> notes) {
        this.notes = notes;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerNotes.setLayoutManager(layoutManager);

        adapter = new MyAdapter();
        recyclerNotes.setAdapter(adapter);

        Toast.makeText(this, "Notes array size: " + notes.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startNoteDetailsActivity(String date, String contents) {
        Intent intent = new Intent(getApplicationContext(), NoteDetailsActivity.class);
        intent.putExtra("CREATION_DATE", date);
        intent.putExtra("CONTENTS", contents);
        startActivity(intent);
    }

    //RecyclerView custom adapter.
    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        class MyViewHolder extends RecyclerView.ViewHolder {
            private ViewGroup viewGroup;
            private TextView txtDate;
            private TextView txtContents;

            MyViewHolder(View v) {
                super(v);
                txtDate = v.findViewById(R.id.item_note_date);
                txtContents = v.findViewById(R.id.item_note_contents);
                viewGroup = v.findViewById(R.id.note_card);

                viewGroup.setOnClickListener(e -> {
                    Note note = notes.get(this.getAdapterPosition());
                    String date = note.getCreationDate().toString();
                    String contents = note.getContents();
                    startNoteDetailsActivity(date, contents);
                });

                // Remove item.
                viewGroup.setOnLongClickListener(e -> {
                    Note note = notes.get(this.getAdapterPosition());
                    presenter.onLongNoteClick(note);
                    adapter.notifyItemRemoved(this.getAdapterPosition());
                    presenter.reloadData();
                    return true;
                });
            }
        }

        MyAdapter() {
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.note_item, parent, false);

            return new MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
            Note current = notes.get(position);

            holder.txtDate.setText(current.getCreationDate().toString());
            holder.txtContents.setText(current.getContents());
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }
    }

    @Override
    public void clearTextField() {
        editContents.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
