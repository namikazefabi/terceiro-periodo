package br.com.terceiroperiodo.controller;

import br.com.terceiroperiodo.model.Cidade;

import br.com.terceiroperiodo.model.Estado;
import br.com.terceiroperiodo.service.CidadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/cidade")
public class CidadeController {
    @Autowired
    CidadeService cidadeService;



    @PostMapping
    public ResponseEntity<Cidade> salvarEstado(@RequestBody Cidade cidade) {
        Cidade response = cidadeService.salvar(cidade);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}" )
    public ResponseEntity<Cidade> buscaPorId(@PathVariable Long id) {
        Optional<Cidade> response = cidadeService.buscarPorId(id);
        if(response.isPresent()) {
            return ResponseEntity.ok(response.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<Cidade> update(@RequestBody Cidade cidade) {
        if (!cidadeService.buscarPorId(cidade.getId()).isPresent()) {
        }
        return ResponseEntity.ok(cidadeService.atualizar(cidade));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {

        if (!cidadeService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
