package com.exercicos.ex5.services;

import com.exercicos.ex5.dto.VeiculoDTO;
import com.exercicos.ex5.entities.Marca;
import com.exercicos.ex5.entities.Veiculo;
import com.exercicos.ex5.repositories.VeiculoRepository;
import com.exercicos.ex5.services.exceptions.MarcaNotFoundException;
import com.exercicos.ex5.services.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    VeiculoRepository repo;

    public List<Veiculo> findAll(){
        return repo.findAll();
    }

    public Veiculo find(Integer id){
        Optional<Veiculo> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Veiculo.class.getName()));
    }

    public List<Veiculo> search(Boolean vendido, Integer decada, String fabricante,Boolean recentes){
        return repo.findByFilters(vendido, decada, fabricante, recentes);
    }

    public Veiculo insert(Veiculo obj){
        validateMarca(obj.getMarca());
        repo.save(obj);
        return find(obj.getId());
    }

    public Veiculo update(Veiculo obj){
        find(obj.getId());
        validateMarca(obj.getMarca());
        return repo.save(obj);
    }

    public Veiculo patchUpdate(HttpServletRequest request, Integer id) throws IOException {
        Veiculo v = find(id);
        Veiculo updatedUser = objectMapper.readerForUpdating(v).readValue(request.getReader());
        validateMarca(updatedUser.getMarca());
        return repo.save(updatedUser);
    }

    public void validateMarca(String marca){
        Boolean achou = false;
        for (Marca c : Marca.values()) {
            if (c.name().toLowerCase().trim().equals(marca.toLowerCase().trim())) {
                achou = true;
            }
        }
        if (achou == Boolean.FALSE)
            throw new MarcaNotFoundException("A marca "+marca+" não foi encontrada, verifique se ela foi digitada corretamente");
    }

    public void delete(Integer id){
        find(id);
        repo.deleteById(id);
    }

    public Veiculo convertIntoEntitie(VeiculoDTO dto){
        return new Veiculo(dto);
    }
}
