package pl.adriankurek.quicknotegreendao.ui.main.api;

import java.util.List;

import pl.adriankurek.quicknotegreendao.data.Note;

public interface Contract {
    interface Presenter {
        void onAddButtonClick();
        void onLongNoteClick(Note note);
        void reloadData();
    }
    interface View {
        void setUpAdapter(List<Note> notes);
        String getNewNoteContents();
        void showToast(String message);
        void startNoteDetailsActivity(String date, String contents);
        void refreshData(List<Note> notes);
        void clearTextField();
    }
}
