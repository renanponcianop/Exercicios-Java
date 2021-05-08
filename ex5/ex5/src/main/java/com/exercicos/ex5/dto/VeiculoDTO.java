package com.exercicos.ex5.dto;

import com.exercicos.ex5.entities.Veiculo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class VeiculoDTO{

    private Integer id;
    @NotEmpty(message = "Este campo não pode ser vazio")
    private String veiculo;
    @NotEmpty(message = "Este campo não pode ser vazio")
    private String marca;
    @NotNull(message = "Este campo não pode ser nullo")
    private Integer ano;
    @NotEmpty(message = "Este campo não pode ser vazio")
    private String descricao;
    @NotNull(message = "Este campo não pode ser nullo")
    private Boolean vendido;
    private Date created;
    private Date updated;

    public VeiculoDTO(){}

    public VeiculoDTO(Integer id, String veiculo, String marca, Integer ano, String descricao, Boolean vendido, Date created, Date updated) {
        this.id = id;
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.descricao = descricao;
        this.vendido = vendido;
        this.created = created;
        this.updated = updated;
    }

    public VeiculoDTO(Veiculo v){
        this.id = v.getId();
        this.veiculo = v.getVeiculo();
        this.marca = v.getMarca();
        this.ano = v.getAno();
        this.descricao = v.getDescricao();
        this.vendido = v.getVendido();
        this.created = v.getCreated();
        this.updated = v.getUpdated();
    }

    public Integer getId() {
        return id;
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
}
