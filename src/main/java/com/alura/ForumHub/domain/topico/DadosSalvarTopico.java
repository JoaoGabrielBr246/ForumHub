package com.alura.ForumHub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosSalvarTopico(
        @NotBlank
        String topico,
        @NotBlank
        String curso,
        @NotBlank
        String mensagem
) {
}
