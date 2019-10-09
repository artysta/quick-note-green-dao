package pl.adriankurek.quicknotegreendao.data.database;

import android.app.Activity;

import java.util.List;

import pl.adriankurek.quicknotegreendao.data.api.DataProvider;
import pl.adriankurek.quicknotegreendao.data.Note;
import pl.adriankurek.quicknotegreendao.data.NoteDao;

public class DbController implements DataProvider {
    private NoteDao noteDao;
    private Activity activity;

    public DbController(Activity activity) {
        this.activity = activity;
        this.noteDao = getNoteDao();
    }

    private NoteDao getNoteDao() {
        return ((GreenDao) activity.getApplication()).getDaoSession().getNoteDao();
    }

    public List<Note> getAllNotesDescById() {
        return noteDao.queryBuilder().orderDesc(NoteDao.Properties.Id).list();
    }

    @Override
    public List<Note> getAllNotes() {
        return noteDao.loadAll();
    }

    @Override
    public void addNewNote(Note note) {
        noteDao.insert(note);
    }


    @Override
    public void deleteNote(Note note) {
        noteDao.delete(note);
    }

    /*public void updateNote(Note note) {
        noteDao.update(note);
    }

    public void deleteAll() {
        noteDao.deleteAll();
    }

    public long getCount() {
        return noteDao.queryBuilder().count();
    }*/
}
