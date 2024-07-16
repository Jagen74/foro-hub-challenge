package com.alura.forohub.service;

import com.alura.forohub.dto.TopicoDTO;
import com.alura.forohub.model.Curso;
import com.alura.forohub.model.Topico;
import com.alura.forohub.model.Usuario;
import com.alura.forohub.repository.CursoRepository;
import com.alura.forohub.repository.TopicoRepository;
import com.alura.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public List<Topico> obtenerTodos() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> obtenerPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public Topico crearTopico(TopicoDTO topicoDTO) {
        Usuario autor = usuarioRepository.findById(topicoDTO.getAutorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(topicoDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(topicoDTO.getTitulo());
        topico.setMensaje(topicoDTO.getMensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        return topicoRepository.save(topico);
    }

    public Optional<Topico> actualizar(Long id, TopicoDTO topicoActualizado) {
        return topicoRepository.findById(id)
                .map(topico -> {
                    topico.setTitulo(topicoActualizado.getTitulo());
                    topico.setMensaje(topicoActualizado.getMensaje());
                    // Actualizar autor y curso si es necesario
                    return topicoRepository.save(topico);
                });
    }

    public boolean eliminar(Long id) {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}