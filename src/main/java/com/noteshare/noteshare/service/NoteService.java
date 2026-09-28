package com.noteshare.noteshare.service;

import com.noteshare.noteshare.entity.Note;
import com.noteshare.noteshare.repository.NoteRepository;
import com.noteshare.noteshare.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public Note uploadNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public void deleteNote(Long noteId, Long uploaderId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note kedaikala!"));

        // Business Rule: Yaaru upload pannangalo avanga mattum thaan delete panna mudiyum
        if (!note.getUploader().getId().equals(uploaderId)) {
            throw new RuntimeException("Error: You did not upload this note, so you cannot delete it. Only the original uploader can delete a note.");
        }

        // FIX: Note-ah delete panrathuku munnadi, athoda ratings-ah delete panrom
        ratingRepository.deleteByNoteId(noteId);

        noteRepository.deleteById(noteId);
    }
    public Note updateNote(Long id, Note updatedNote) {
        Note existingNote = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note kedaikala!"));

        // Puthu details-ah existing note-la update panrom
        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setUnit(updatedNote.getUnit());
        existingNote.setFileReference(updatedNote.getFileReference());
        existingNote.setSubject(updatedNote.getSubject());

        return noteRepository.save(existingNote);
    }
}