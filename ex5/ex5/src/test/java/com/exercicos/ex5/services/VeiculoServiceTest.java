package com.exercicos.ex5.services;

import com.exercicos.ex5.entities.Veiculo;
import com.exercicos.ex5.repositories.VeiculoRepository;
import com.exercicos.ex5.services.exceptions.MarcaNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;


@SpringBootTest
public class VeiculoServiceTest {

    @Mock
    VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoService veiculoService;

    public List<Veiculo> listVeiculos = new ArrayList<Veiculo>();

    @BeforeEach
    public void setUp() {
        listVeiculos.clear();
        Veiculo v1 = new Veiculo(1,"Model 3", "Tesla", 2021, "Carro elétrico da marca Tesla", false);
        listVeiculos.add(v1);
        Veiculo v2 = new Veiculo(2,"Model X", "Tesla", 2019, "Carro elétrico da marca Tesla", false);
        listVeiculos.add(v2);
        Veiculo v3 = new Veiculo(3,"Model Y", "Tesla", 2020, "Carro elétrico da marca Tesla", true);
        listVeiculos.add(v3);
        Veiculo v4 = new Veiculo(4,"Model S", "Tesla", 2019, "Carro elétrico da marca Tesla", true);
        listVeiculos.add(v4);
    }


    @Test
    public void testSave() {
        doReturn(listVeiculos).when(veiculoRepository).findAll();

        //confirma quantos carros tem pré adicao
        List<Veiculo> carrosPreAdicao = veiculoService.findAll();
        assertEquals(4, carrosPreAdicao.size());

        Veiculo v1 = new Veiculo(5,"Argo", "Fiat", 2021, "Carro Subcompacto", false);

        doReturn(Optional.of(v1)).when(veiculoRepository).findById(5);
        listVeiculos.add(v1);

        Veiculo inserted = veiculoService.insert(v1);

        assertNotNull(inserted);

        Integer id = inserted.getId();
        assertNotNull(id);
        //confirma que teve carros adicionado
        List<Veiculo> carrosPosAdicao = veiculoService.findAll();
        assertEquals(5, carrosPosAdicao.size());

        // Buscar o objeto
        Veiculo veiculoInserido = veiculoService.find(id);
        assertNotNull(veiculoInserido);

        assertEquals("Argo",veiculoInserido.getVeiculo());
        assertEquals("Fiat",veiculoInserido.getMarca());
    }

    @Test
    public void testLista() {
        doReturn(listVeiculos).when(veiculoRepository).findAll();
        List<Veiculo> carros = veiculoService.findAll();
        assertEquals(4, carros.size());
    }

    @Test
    public void testDeleteCheckMetod() {
        doReturn(Optional.of(listVeiculos.get(0))).when(veiculoRepository).findById(1);
        veiculoService.delete(1);
        Mockito.verify(veiculoRepository, Mockito.times(1)).deleteById(1);
    }

    @Test
    public void testSearchVariacoesDeFiltros() throws ParseException {
        //teste Search vendidos
        List<Veiculo> carrosvendidos = veiculoService.search(true,null,null,null);
        Mockito.verify(veiculoRepository, Mockito.times(1)).findByFilters(true,null,null,null);

        //teste Search disponível para venda
        List<Veiculo> carrosdisponíveis = veiculoService.search(false,null,null,null);
        Mockito.verify(veiculoRepository, Mockito.times(1)).findByFilters(false,null,null,null);

        //teste search por decada
        List<Veiculo> carros2010 = veiculoService.search(null,2010,null,null);
        Mockito.verify(veiculoRepository, Mockito.times(1)).findByFilters(null,2010,null,null);

        //teste Search por decada disponível para venda
        List<Veiculo> carros2010Vendido = veiculoService.search(false,2010,null,null);
        Mockito.verify(veiculoRepository, Mockito.times(1)).findByFilters(false,2010,null,null);

        //teste Search por decada vendido
        List<Veiculo> carros2010Disponível = veiculoService.search(true,2010,null,null);
        Mockito.verify(veiculoRepository, Mockito.times(1)).findByFilters(true,2010,null,null);

        //teste Search com marca existente
        List<Veiculo> carrosTesla = veiculoService.search(null,null,"Tesla",null);
        Mockito.verify(veiculoRepository, Mockito.times(1)).findByFilters(null,null,"Tesla",null);

        //teste Search com marca que não existe
        List<Veiculo> carrosQueNaoTemCarro = veiculoService.search(null,null,"Marca que nao tem carro",null);
        Mockito.verify(veiculoRepository, Mockito.times(1)).findByFilters(null,null,"Marca que nao tem carro",null);

        //teste Search buscando os mais recentes cadastrados
        List<Veiculo> carrosRecentes = veiculoService.search(null,null,null,true);
        Mockito.verify(veiculoRepository, Mockito.times(1)).findByFilters(null,null,null,true);
    }

    @Test
    public void testFindAndValidateSpecificCar() {
        doReturn(Optional.of(listVeiculos.get(0))).when(veiculoRepository).findById(1);
        Veiculo expected = veiculoService.find(1);
        assertEquals("Model 3",expected.getVeiculo());
        assertEquals("Tesla",expected.getMarca());
        assertEquals(2021,expected.getAno());
        assertEquals("Carro elétrico da marca Tesla",expected.getDescricao());
        assertEquals(false,expected.getVendido());

        Veiculo v1 = new Veiculo(1,"Model 3", "Tesla", 2021, "Carro elétrico da marca Tesla", true);
        doReturn(Optional.of(v1)).when(veiculoRepository).findById(1);

        expected.setVendido(true);
        veiculoService.update(expected);

        assertEquals("Model 3",expected.getVeiculo());
        assertEquals("Tesla",expected.getMarca());
        assertEquals(2021,expected.getAno());
        assertEquals("Carro elétrico da marca Tesla",expected.getDescricao());
        assertEquals(true,expected.getVendido());
        //voltando ao padrão
        expected.setVendido(false);
        veiculoService.update(expected);
        Mockito.verify(veiculoRepository, Mockito.times(2)).save(expected);
    }

    @Test
    public void testUpdateFailed() throws Exception  {
        doReturn(Optional.of(listVeiculos.get(0))).when(veiculoRepository).findById(1);
        Veiculo expectedErrado = veiculoService.find(1);

        expectedErrado.setMarca("marcaqualquernaoregistrada");
        try{
            veiculoService.update(expectedErrado);
        } catch (MarcaNotFoundException e) {
            assertEquals("A marca marcaqualquernaoregistrada não foi encontrada, verifique se ela foi digitada corretamente",e.getMessage());
        }
    }
}
