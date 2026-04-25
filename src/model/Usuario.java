package model;

import repository.EmprestimoDaoLista;
import java.util.List;
import java.util.ArrayList;

public class Usuario {
    private String id; 
    private String nome; 
    private String email; 
    private String senha; 
    private TipoUsuario categoria; 
    private int limiteLivros; 
    private List<Emprestimo> listaEmprestimos = new ArrayList<>(); 

    // Construtor padrão
    public Usuario() {}

    // Construtor parametrizado
    public Usuario(String id, String nome, String email, TipoUsuario categoria, int limiteLivros) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.categoria = categoria;
        this.limiteLivros = limiteLivros;
    }

    // Getters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public TipoUsuario getCategoria() { return categoria; }
    public int getLimiteLivros() { return limiteLivros; }
    public List<Emprestimo> getEmprestimos() { return listaEmprestimos; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setCategoria(TipoUsuario categoria) { this.categoria = categoria; }
    public void setLimiteLivros(int limiteLivros) { this.limiteLivros = limiteLivros; }

    public void mostrarDados() {
        System.out.println("ID: " + id + " | Nome: " + nome + " | Categoria: " + categoria);
    }

    // CORREÇÃO: Método necessário para a classe Biblioteca realizar o empréstimo
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        this.listaEmprestimos.add(emprestimo);
    }

    public boolean pegarLivro(Livro livro) {
        if (listaEmprestimos.size() < limiteLivros) {
            Emprestimo novoEmprestimo = new Emprestimo();
            novoEmprestimo.setLivro(livro);
            listaEmprestimos.add(novoEmprestimo);
            return true;
        }
        return false; 
    }

    public boolean devolverLivro(Livro livro) {
        return listaEmprestimos.removeIf(emp -> emp.getLivro().equals(livro));
    }
}
