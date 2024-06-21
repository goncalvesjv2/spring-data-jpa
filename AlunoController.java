package com.academia.controller;

import com.academia.model.Aluno;
import com.academia.repository.AlunoRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno getAlunoById(@PathVariable Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluno not found"));
    }

    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno updateAluno(@PathVariable Long id, @RequestBody Aluno alunoDetails) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluno not found"));
        aluno.setNome(alunoDetails.getNome());
        aluno.setIdade(alunoDetails.getIdade());
        return alunoRepository.save(aluno);
    }

    @DeleteMapping("/{id}")
    public void deleteAluno(@PathVariable Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluno not found"));
        alunoRepository.delete(aluno);
    }
}
