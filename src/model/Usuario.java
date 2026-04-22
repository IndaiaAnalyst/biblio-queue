package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Enum TipoUsuario [cite: 17]
 * Define as categorias autorizadas no sistema[cite: 18, 19, 20].
 */
enum TipoUsuario {
    ALUNO, PROFESSOR, BIBLIOTECARIO [cite: 18, 19, 20]
}

/**
 * Classe Usuario [cite: 4]
 * Representa a entidade base para todos os perfis de acesso.
 */
public abstract class Usuario {
    private String id; [cite: 5]
    private String nome; [cite: 6]
    private String email; [cite: 7]
    private String senha; [cite: 8]
    private TipoUsuario categoria; [cite: 9]
    private int limiteLivros; [cite: 10]
    private List<Emprestimo> emprestimos; [cite: 11]

    public Usuario(String id, String nome, String email, String senha, TipoUsuario categoria, int limiteLivros) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.categoria = categoria;
        this.limiteLivros = limiteLivros;
    }

    public String getId() { return id; } [cite: 13]
    public String getNome() { return nome; } [cite: 14]
    public TipoUsuario getCategoria() { return categoria; } [cite: 15]
    public List<Emprestimo> getEmprestimos() { return emprestimos; } [cite: 16]
}

/**
 * Classe Titulo [cite: 21]
 * Representa a OBRA no catálogo (informações bibliográficas)[cite: 22].
 */
public class Titulo {
    private String nome; [cite: 23]
    private String autor; [cite: 24]
    private String isbn; [cite: 25]
    private String genero; [cite: 26]
    private String descricao; [cite: 27]
    private LocalDate dataPublicacao; [cite: 28]
    private int quantidade; [cite: 29]
    private int quantidadeDisponivel; [cite: 30]
    private int quantidadeReservas; [cite: 31]

    public Titulo(String nome, String autor, String isbn, String genero) {
        this.nome = nome;
        this.autor = autor;
        this.isbn = isbn;
        this.genero = genero;
    }

    public String getNome() { return nome; } [cite: 33]
    public int getQuantidadeDisponivel() { return quantidadeDisponivel; } [cite: 34]
    public void diminuirDisponivel() { this.quantidadeDisponivel--; } [cite: 35]
    public void aumentarDisponivel() { this.quantidadeDisponivel++; } [cite: 36]
}

/**
 * Classe Livro [cite: 37]
 * Representa o EXEMPLAR físico disponível na estante[cite: 38].
 */
public class Livro {
    private String id; [cite: 39]
    private boolean disponivel; [cite: 40]
    private Titulo titulo; [cite: 41]

    public Livro(String id, Titulo titulo) {
        this.id = id;
        this.titulo = titulo;
        this.disponivel = true;
    }

    public boolean isDisponivel() { return disponivel; } [cite: 43]
    public void setDisponivel(boolean status) { this.disponivel = status; } [cite: 44]
    public Titulo getTitulo() { return titulo; } [cite: 45]
}

/**
 * Classe Emprestimo [cite: 46]
 * Registra a transação entre um Usuário e um Livro[cite: 48, 49].
 */
public class Emprestimo {
    private String id; [cite: 47]
    private Usuario usuario; [cite: 48]
    private Livro livro; [cite: 49]
    private LocalDate dataEmprestimo; [cite: 50]
    private LocalDate dataDevolucao; [cite: 51]
    private boolean atrasado; [cite: 52]

    public Emprestimo(String id, Usuario usuario, Livro livro) {
        this.id = id;
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.atrasado = false;
    }

    public boolean isAtrasado() { return atrasado; } [cite: 54]
    public Usuario getUsuario() { return usuario; } [cite: 55]
    public Livro getLivro() { return livro; } [cite: 56]
}

/**
 * Classe Reserva [cite: 57]
 * Gerencia a fila de espera para obras indisponíveis[cite: 59, 60].
 */
public class Reserva {
    private Usuario usuario; [cite: 58]
    private Titulo titulo; [cite: 59]
    private LocalDateTime dataReserva; [cite: 60]

    public Reserva(Usuario usuario, Titulo titulo) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.dataReserva = LocalDateTime.now();
    }

    public Usuario getUsuario() { return usuario; } [cite: 62]
    public LocalDateTime getDataReserva() { return dataReserva; } [cite: 63]
    public Titulo getTitulo() { return titulo; } [cite: 64]
}
