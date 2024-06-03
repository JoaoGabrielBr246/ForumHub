package com.alura.ForumHub.domain.topico;

import com.alura.ForumHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String topico;
    @Setter
    private String mensagem;
    @Setter
    private String curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;


    public Topico(DadosSalvarTopico dados) {
        this.topico = dados.topico();
        this.mensagem = dados.mensagem();
        this.curso = dados.curso();
    }

}
