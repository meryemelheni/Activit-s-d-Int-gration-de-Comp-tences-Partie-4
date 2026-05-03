package com.example.grading.service;

import com.example.grading.client.EtudiantServiceClient;
import com.example.grading.dto.NoteDTO;
import com.example.grading.entity.Note;
import com.example.grading.exception.ResourceNotFoundException;
import com.example.grading.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository repository;
    private final EtudiantServiceClient etudiantServiceClient;

    public NoteService(NoteRepository repository, EtudiantServiceClient etudiantServiceClient) {
        this.repository = repository;
        this.etudiantServiceClient = etudiantServiceClient;
    }

    public List<NoteDTO> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public NoteDTO findById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Note introuvable avec id: " + id));
    }

    public NoteDTO create(NoteDTO dto) {
        validateStudent(dto.getStudentId());
        validateNote(dto.getValeur());
        Note saved = repository.save(toEntity(dto));
        return toDto(saved);
    }

    public NoteDTO update(Long id, NoteDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Note introuvable avec id: " + id);
        }
        validateStudent(dto.getStudentId());
        validateNote(dto.getValeur());
        Note entity = toEntity(dto);
        entity.setId(id);
        return toDto(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Note introuvable avec id: " + id);
        }
        repository.deleteById(id);
    }

    private void validateStudent(Long studentId) {
        etudiantServiceClient.getEtudiantById(studentId);
    }

    private void validateNote(Double valeur) {
        if (valeur == null || valeur < 0 || valeur > 20) {
            throw new IllegalArgumentException("La valeur de la note doit etre comprise entre 0 et 20");
        }
    }

    private NoteDTO toDto(Note entity) {
        return new NoteDTO(
            entity.getId(),
            entity.getStudentId(),
            entity.getMatiere(),
            entity.getValeur()
        );
    }

    private Note toEntity(NoteDTO dto) {
        return new Note(
            dto.getId(),
            dto.getStudentId(),
            dto.getMatiere(),
            dto.getValeur()
        );
    }
}