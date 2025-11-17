package com.renata.crudapi.service;

import com.renata.crudapi.exception.ResourceNotFoundException;
import com.renata.crudapi.model.Produto;
import com.renata.crudapi.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repo;

    @InjectMocks
    private ProdutoService service;

    @Test
    void criar_deveSalvarProduto() {
        Produto p = new Produto();
        p.setNome("Caneca");
        p.setDescricao("Caneca porcelana");
        p.setPreco(BigDecimal.valueOf(29.90));
        p.setQuantidade(10);

        Produto salvo = new Produto();
        salvo.setId(1L);
        salvo.setNome(p.getNome());
        salvo.setDescricao(p.getDescricao());
        salvo.setPreco(p.getPreco());
        salvo.setQuantidade(p.getQuantidade());

        when(repo.save(p)).thenReturn(salvo);

        Produto resultado = service.criar(p);
        assertNotNull(resultado.getId());
        assertEquals("Caneca", resultado.getNome());
        verify(repo, times(1)).save(p);
    }

    @Test
    void listarTodos_retornaLista() {
        Produto p = new Produto();
        p.setId(1L);
        p.setNome("Produto A");
        when(repo.findAll()).thenReturn(List.of(p));

        var lista = service.listarTodos();
        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
        verify(repo).findAll();
    }

    @Test
    void buscarPorId_quandoExiste_retorna() {
        Produto p = new Produto();
        p.setId(1L);
        p.setNome("Produto A");
        when(repo.findById(1L)).thenReturn(Optional.of(p));

        Produto encontrado = service.buscarPorId(1L);
        assertEquals(1L, encontrado.getId());
    }

    @Test
    void buscarPorId_quandoNaoExiste_lancaException() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void atualizar_deveSalvarAlteracoes() {
        Produto existente = new Produto();
        existente.setId(1L);
        existente.setNome("A");
        existente.setDescricao("D");
        existente.setPreco(BigDecimal.TEN);
        existente.setQuantidade(1);

        Produto novo = new Produto();
        novo.setNome("B");
        novo.setDescricao("D2");
        novo.setPreco(BigDecimal.valueOf(20));
        novo.setQuantidade(5);

        when(repo.findById(1L)).thenReturn(Optional.of(existente));
        when(repo.save(any(Produto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Produto updated = service.atualizar(1L, novo);
        assertEquals("B", updated.getNome());
        assertEquals(5, updated.getQuantidade());
        verify(repo).save(existente);
    }

    @Test
    void excluir_deveChamarDelete() {
        Produto p = new Produto();
        p.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(p));
        doNothing().when(repo).delete(p);

        service.excluir(1L);
        verify(repo).delete(p);
    }
}
