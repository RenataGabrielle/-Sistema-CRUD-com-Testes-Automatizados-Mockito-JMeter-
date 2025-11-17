package com.renata.crudapi.service;

import com.renata.crudapi.exception.ResourceNotFoundException;
import com.renata.crudapi.model.Produto;
import com.renata.crudapi.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository repo;

    public ProdutoService(ProdutoRepository repo) { this.repo = repo; }

    public Produto criar(Produto produto) { return repo.save(produto); }

    public List<Produto> listarTodos() { return repo.findAll(); }

    public Produto buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Produto não encontrado com id: " + id));
    }

    public Produto atualizar(Long id, Produto novo) {
        Produto existente = buscarPorId(id);
        existente.setNome(novo.getNome());
        existente.setDescricao(novo.getDescricao());
        existente.setPreco(novo.getPreco());
        existente.setQuantidade(novo.getQuantidade());
        return repo.save(existente);
    }

    public void excluir(Long id) {
        Produto p = buscarPorId(id);
        repo.delete(p);
    }
}
