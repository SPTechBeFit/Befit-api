package sptech.befitapi.resources.repository.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import sptech.befitapi.resources.repository.entity.types.FuncaoType;
import sptech.befitapi.resources.repository.entity.types.NivelType;
import sptech.befitapi.resources.repository.entity.types.ObjetivoType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String email;

    @Getter(AccessLevel.NONE)
    private String senha;

    private String personId = UUID.randomUUID().toString();

    private Integer xp = 0;

    private Boolean logado = false;

    public Usuario(Integer id, String nome, String email, String senha, String personId, Integer xp, Boolean logado) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.personId = personId;
        this.xp = xp;
        this.logado = logado;
    }

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }
}
