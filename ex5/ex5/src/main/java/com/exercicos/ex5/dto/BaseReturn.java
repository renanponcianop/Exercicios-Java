package com.exercicos.ex5.dto;

import java.util.List;

public class BaseReturn {

    private Integer quantidade;
    private List<VeiculoDTO> veiculos;

    public BaseReturn(){}

    public BaseReturn(Integer quantidade, List<VeiculoDTO> veiculos) {
        this.quantidade = quantidade;
        this.veiculos = veiculos;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public List<VeiculoDTO> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<VeiculoDTO> veiculos) {
        this.veiculos = veiculos;
    }
}
