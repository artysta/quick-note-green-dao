package pl.adriankurek.quicknotegreendao.ui.main;

import java.util.Calendar;

import pl.adriankurek.quicknotegreendao.data.api.DataProvider;
import pl.adriankurek.quicknotegreendao.data.Note;
import pl.adriankurek.quicknotegreendao.ui.main.api.Contract;

public class Presenter implements Contract.Presenter {
    private Contract.View view;
    private DataProvider provider;

    public Presenter(Contract.View view, DataProvider provider) {
        this.view = view;
        this.provider = provider;

        view.setUpAdapter(provider.getAllNotesDescById());
    }

    @Override
    public void onAddButtonClick() {
        String contents = view.getNewNoteContents();

        if (contents.trim().equals("")) {
            view.showToast("Text field can not be empty!");
            return;
        }

        Note note = new Note();
        note.setCreationDate(Calendar.getInstance().getTime());
        note.setContents(contents);
        provider.addNewNote(note);

        view.showToast("Note added!");
        view.clearTextField();
    }

    @Override
    public void onLongNoteClick(Note note) {
        provider.deleteNote(note);
        view.showToast("Note deleted!");
    }

    @Override
    public void reloadData() {
        view.refreshData(provider.getAllNotesDescById());
    }
}
