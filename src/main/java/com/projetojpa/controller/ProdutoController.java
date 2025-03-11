package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Produto;
import com.projetojpa.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	private final ProdutoService produtoService;
	
	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProductById(@PathVariable Long id) {
		Produto produto = produtoService.getProdutoById(id);
		if (produto != null) {
			return ResponseEntity.ok(produto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	} 
	
	@GetMapping("/")
	public ResponseEntity<List<Produto>> getAllProdutos() {
		List<Produto> produtos = produtoService.getAllProdutos();
		return ResponseEntity.ok(produtos);
	}
	
	@PostMapping("/")
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
		Produto criarProduto = produtoService.salvarProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
		Produto updatedProduto =produtoService.updateProduto(id, produto);
		if (updatedProduto != null) {
			return ResponseEntity.ok(updatedProduto);
		}
		else {
			return ResponseEntity.notFound().build();
			}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deleteProduto(@PathVariable Long id) {
		boolean deleted = produtoService.deleteProduto(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
     }
}
