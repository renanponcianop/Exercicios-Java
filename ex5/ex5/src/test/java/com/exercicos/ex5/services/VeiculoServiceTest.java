package com.exercicos.ex5.services;

import com.exercicos.ex5.entities.Veiculo;
import com.exercicos.ex5.services.exceptions.MarcaNotFoundException;
import com.exercicos.ex5.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class VeiculoServiceTest {

    @Autowired
    private VeiculoService veiculoService;

    @Test
    public void testSave() {
        //confirma quantos carros tem pré adicao
        List<Veiculo> carrosPreAdicao = veiculoService.findAll();
        assertEquals(4, carrosPreAdicao.size());

        Veiculo v1 = new Veiculo(null,"Argo", "Fiat", 2021, "Carro Subcompacto", false);

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

        // Deletar o objeto
        veiculoService.delete(id);

        // Verificar se deletou
        try {
            veiculoService.find(id);
            fail("O carro não foi excluído");
        } catch (ObjectNotFoundException e) {
            // OK
        }
        //confirma quantos carros tem salvo
        List<Veiculo> carrosPosExclusao = veiculoService.findAll();
        assertEquals(4, carrosPosExclusao.size());
    }

    @Test
    public void testLista() {
        List<Veiculo> carros = veiculoService.findAll();
        assertEquals(4, carros.size());
    }

    @Test
    public void testSearchVariacoesDeFiltros() throws ParseException {
        //teste Search vendidos
        List<Veiculo> carrosvendidos = veiculoService.search(true,null,null,null);
        assertEquals(2, carrosvendidos.size());

        //teste Search disponível para venda
        List<Veiculo> carrosdisponíveis = veiculoService.search(false,null,null,null);
        assertEquals(2, carrosdisponíveis.size());

        //teste search por decada
        List<Veiculo> carros2010 = veiculoService.search(null,2010,null,null);
        assertEquals(2, carros2010.size());

        //teste Search por decada disponível para venda
        List<Veiculo> carros2010Vendido = veiculoService.search(false,2010,null,null);
        assertEquals(1, carros2010Vendido.size());

        //teste Search por decada vendido
        List<Veiculo> carros2010Disponível = veiculoService.search(true,2010,null,null);
        assertEquals(1, carros2010Disponível.size());

        //teste Search com marca existente
        List<Veiculo> carrosTesla = veiculoService.search(null,null,"Tesla",null);
        assertEquals(4, carrosTesla.size());

        //teste Search com marca que não existe
        List<Veiculo> carrosQueNaoTemCarro = veiculoService.search(null,null,"Marca que nao tem carro",null);
        assertEquals(0, carrosQueNaoTemCarro.size());

        //teste Search buscando os mais recentes cadastrados
        List<Veiculo> carrosRecentes = veiculoService.search(null,null,null,true);
        assertEquals(4, carrosRecentes.size());

        Veiculo v3 = veiculoService.find(3);
        Veiculo v4 = veiculoService.find(4);
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/03/2021");
        Date dataAtualv3 = v3.getCreated();
        Date dataAtualv4 = v4.getCreated();
        v3.setCreated(date1);
        v4.setCreated(date1);
        veiculoService.update(v3);
        veiculoService.update(v4);

        //teste Search buscando os mais recentes cadastrados só que após jogar 2 paradatas retrasadas
        List<Veiculo> carrosRecentesPosAlteracao = veiculoService.search(null,null,null,true);
        assertEquals(2, carrosRecentesPosAlteracao.size());

        v3.setCreated(dataAtualv3);
        v4.setCreated(dataAtualv4);
        veiculoService.update(v3);
        veiculoService.update(v4);
        //teste Search buscando os mais recentes voltando as datas dos alterados
        List<Veiculo> carrosRecentesDataAtual = veiculoService.search(null,null,null,true);
        assertEquals(4, carrosRecentesDataAtual.size());
    }

    @Test
    public void testFindAndValidateSpecificCar() {
        Veiculo expected = veiculoService.find(1);
        assertEquals("Model 3",expected.getVeiculo());
        assertEquals("Tesla",expected.getMarca());
        assertEquals(2021,expected.getAno());
        assertEquals("Carro elétrico da marca Tesla",expected.getDescricao());
        assertEquals(false,expected.getVendido());

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
    }

    @Test
    public void testUpdateFailed() {
        Veiculo expectedErrado = veiculoService.find(1);

        expectedErrado.setMarca("marcaqualquernaoregistrada");
        try{
            veiculoService.update(expectedErrado);
        } catch (MarcaNotFoundException e) {
            Veiculo expectedCorreto = veiculoService.find(1);
            //valida se valores continuam iguais
            assertEquals("Model 3",expectedCorreto.getVeiculo());
            assertEquals("Tesla",expectedCorreto.getMarca());
            assertEquals(2021,expectedCorreto.getAno());
            assertEquals("Carro elétrico da marca Tesla",expectedCorreto.getDescricao());
            assertEquals(false,expectedCorreto.getVendido());
        }
    }
}
