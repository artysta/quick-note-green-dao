package pl.adriankurek.quicknotegreendao.data.api;

import java.util.List;

import pl.adriankurek.quicknotegreendao.data.Note;

public interface DataProvider {
    void addNewNote(Note note);
    void deleteNote(Note note);
    List<Note> getAllNotes();
    List<Note> getAllNotesDescById();
}
