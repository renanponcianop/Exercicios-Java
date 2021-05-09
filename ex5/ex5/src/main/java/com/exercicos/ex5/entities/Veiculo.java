package com.exercicos.ex5.entities;


import com.exercicos.ex5.dto.VeiculoDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String veiculo;
    private String marca;
    private Integer ano;
    @Column(columnDefinition="TEXT")
    private String descricao;
    private Boolean vendido;
    @Basic(optional = false)
    @CreationTimestamp
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date created;

    @UpdateTimestamp
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date updated;

    public Veiculo(){}

    public Veiculo(Integer id, String veiculo, String marca, Integer ano, String descricao, Boolean vendido) {
        this.id = id;
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.descricao = descricao;
        this.vendido = vendido;
    }

    public Veiculo(Integer id, String veiculo, String marca, Integer ano, String descricao, Boolean vendido, Date created, Date updated) {
        this.id = id;
        this.veiculo = veiculo;
        this.marca = capitalizeString(marca);
        this.ano = ano;
        this.descricao = descricao;
        this.vendido = vendido;
        this.created = created;
        this.updated = updated;
    }

    public Veiculo(VeiculoDTO dto){
        this.id = dto.getId();
        this.veiculo = dto.getVeiculo();
        this.marca = capitalizeString(dto.getMarca());
        this.ano = dto.getAno();
        this.descricao = dto.getDescricao();
        this.vendido = dto.getVendido();
        this.created = dto.getCreated();
        this.updated = dto.getUpdated();
    }

    public String capitalizeString(String str){
        str = str.toLowerCase();
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getVendido() {
        return vendido;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return id.equals(veiculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
