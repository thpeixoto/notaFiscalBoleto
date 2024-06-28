package br.com.exercicio.alpe.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cliente implements Serializable {

    @Id
    private Long id;

    private String nome;

    private String cpfCnpj;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<NotaFiscal> notas = new HashSet<>();

    public Cliente(Long id, String nome, String cpfCnpj) {
        this.id = id;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
    }
}
