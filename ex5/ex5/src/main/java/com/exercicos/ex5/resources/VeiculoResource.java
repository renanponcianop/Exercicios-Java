package com.exercicos.ex5.resources;

import com.exercicos.ex5.dto.BaseReturn;
import com.exercicos.ex5.dto.VeiculoDTO;
import com.exercicos.ex5.entities.Veiculo;
import com.exercicos.ex5.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoResource {

    @Autowired
    private VeiculoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BaseReturn> findAll(){
        List<Veiculo> list = service.findAll();
        List<VeiculoDTO> listDto = list.stream().map(VeiculoDTO::new).collect(Collectors.toList());
        BaseReturn baseReturn = new BaseReturn(listDto.size(), listDto);
        return ResponseEntity.ok().body(baseReturn);
    }

    @RequestMapping(value="/find", method = RequestMethod.GET)
    public ResponseEntity<BaseReturn> search(@RequestParam(value="vendidos", required = false) Boolean vendidos,
                                             @RequestParam(value="decada", required = false) Integer decada,
                                             @RequestParam(value="fabricante", required = false) String fabricante,
                                             @RequestParam(value="recentes", required = false) Boolean recentes){
        List<Veiculo> list = service.search(vendidos,decada,fabricante,recentes);
        List<VeiculoDTO> listDto = list.stream().map(VeiculoDTO::new).collect(Collectors.toList());
        BaseReturn baseReturn = new BaseReturn(listDto.size(), listDto);
        return ResponseEntity.ok().body(baseReturn);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Veiculo> find(@PathVariable Integer id) {
        Veiculo obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody VeiculoDTO objDTO) {
        Veiculo obj = service.convertIntoEntitie(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id,@Valid @RequestBody VeiculoDTO objDTO) {
        Veiculo obj = service.convertIntoEntitie(objDTO);
        service.update(obj);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<Void> updatePatch(@PathVariable Integer id, HttpServletRequest request) throws IOException {
        service.patchUpdate(request, id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Veiculo> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
