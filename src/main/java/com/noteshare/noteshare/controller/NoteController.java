package com.noteshare.noteshare.controller;

import com.noteshare.noteshare.entity.Note;
import com.noteshare.noteshare.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes") // Ithu thaan base URL (e.g., localhost:8080/notes)
public class NoteController {

    @Autowired
    private NoteService noteService;

    // 1. Note Upload panrathuku (POST request)
    @PostMapping("/upload")
    public ResponseEntity<Note> uploadNote(@RequestBody Note note) {
        Note savedNote = noteService.uploadNote(note);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED); // HttpStatus.CREATED na 201 success code
    }

    // 2. Ellam notes-um pakka (GET request)
    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK); // HttpStatus.OK na 200 success code
    }

    // 3. Note Delete panna (DELETE request) - Uploader ID vachu check panrom
    @DeleteMapping("/delete/{noteId}/uploader/{uploaderId}")
    public ResponseEntity<String> deleteNote(@PathVariable Long noteId, @PathVariable Long uploaderId) {
        try {
            noteService.deleteNote(noteId, uploaderId);
            return new ResponseEntity<>("Note deleted successfully!", HttpStatus.OK);
        } catch (RuntimeException e) {
            // Namma service la ezhuthuna error message inga thaan catch aagi return aagum
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note) {
        Note updatedNote = noteService.updateNote(id, note);
        return new ResponseEntity<>(updatedNote, HttpStatus.OK);
    }
}