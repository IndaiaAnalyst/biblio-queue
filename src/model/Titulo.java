package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import repository.LivroDaoLista;
import ed.FilaPrioridadeReserva; 

public class Titulo {
    private String nome;
    private String autor;
    private String isbn;
    private String genero;
    private String descricao;
    private LocalDate dataPublicacao;
    private int quantidade;
    private int quantidadeDisponivel;
    private int quantidadeReservas;

    private LivroDaoLista listaDeExemplares = new LivroDaoLista();
    
    private FilaPrioridadeReserva filaReservas; 

    public Titulo() {}

    public Titulo(String nome, String autor, String isbn) {
        this.nome = nome;
        this.autor = autor;
        this.isbn = isbn;
    }

    public Livro[] allExemplares() {
        if (listaDeExemplares.getListLivro() == null) return new Livro[0];
        return listaDeExemplares.getListLivro().toArray(new Livro[0]);
    }

    public Livro[] getExemplaresIndisponiveis() {
        return listaDeExemplares.getListLivro().stream()
                .filter(l -> !l.isEstaDisponivel())
                .toArray(size -> new Livro[size]);
    }

    public Livro[] getExemplaresDisponiveis() {
        return listaDeExemplares.getListLivro().stream()
                .filter(l -> l.isEstaDisponivel())
                .toArray(size -> new Livro[size]);
    }

    public Usuario[] getUsuarioNaListaDeReservas() {
        return new Usuario[0]; 
    }

    // --- Getters ---
    public String getNome() { return nome; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public String getGenero() { return genero; }
    public String getDescricao() { return descricao; }
    public LocalDate getDataPublicacao() { return dataPublicacao; }
    public int getQuantidade() { return quantidade; }
    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public int getQuantidadeReservas() { return quantidadeReservas; }
    public FilaPrioridadeReserva getFilaReservas() { return filaReservas; }

    // --- Setters ---
    public void setNome(String nome) { this.nome = nome; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setDataPublicacao(LocalDate dataPublicacao) { this.dataPublicacao = dataPublicacao; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setQuantidadeDisponivel(int quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }
    public void setQuantidadeReservas(int quantidadeReservas) { this.quantidadeReservas = quantidadeReservas; }
    public void setFilaReservas(FilaPrioridadeReserva filaReservas) { this.filaReservas = filaReservas; }

    public void diminuirDisponivel() {
        if (this.quantidadeDisponivel > 0) {
            this.quantidadeDisponivel--;
        }
    }

    public void aumentarDisponivel() {
        if (this.quantidadeDisponivel < quantidade) {
            this.quantidadeDisponivel++;
        }
    }

    public void mostrarDados() {
        System.out.println("Título: " + nome + " | Autor: " + autor + " | ISBN: " + isbn);
    }
}
