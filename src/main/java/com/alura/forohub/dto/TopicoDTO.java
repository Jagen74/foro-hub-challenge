package com.alura.forohub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoDTO {
    private String titulo;
    private String mensaje;
    private Long autorId;
    private Long cursoId;
}
