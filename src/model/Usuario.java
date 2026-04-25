package model;

import repository.LivroDaoLista;
import repository.TituloDaoLista;
import repository.EmprestimoDaoLista;
import repository.ReservaDaoLista;

public class Biblioteca {
    public LivroDaoLista livroDao = new LivroDaoLista();
    public TituloDaoLista tituloDao = new TituloDaoLista();
    public EmprestimoDaoLista emprestimoDao = new EmprestimoDaoLista();
    public ReservaDaoLista reservaDao = new ReservaDaoLista();

    public boolean realizarEmprestimo(String idEmprestimo, Usuario usuario, Titulo titulo) {
        
        int limite = (usuario.getCategoria() == TipoUsuario.PROFESSOR) ? 7 : 3;

        if (usuario.getEmprestimos().size() >= limite) {
            System.out.println("Erro: Limite de " + limite + " livros atingido para " + usuario.getCategoria());
            return false;
        }

        Livro[] disponiveis = titulo.getExemplaresDisponiveis();
        
        if (disponiveis.length > 0) {
            Livro exemplar = disponiveis[0];

            Emprestimo emprestimo = new Emprestimo(idEmprestimo, usuario, exemplar, 7);
            
            exemplar.setDisponivel(false);
            titulo.diminuirDisponivel();
            
            emprestimoDao.addEmprestimo(emprestimo);
            usuario.adicionarEmprestimo(emprestimo);
            
            System.out.println("Empréstimo [" + idEmprestimo + "] registrado com sucesso!");
            return true;
        } else {
            System.out.println("Não há exemplares disponíveis para: " + titulo.getNome());
            return false;
        }
    }

    public void adicionarExemplar(String isbn, String idExemplar) {
        Titulo titulo = tituloDao.buscarPorIsbn(isbn);
        
        if (titulo != null) {
            Livro novoLivro = new Livro(idExemplar, titulo);
            livroDao.addLivro(novoLivro);
            
            titulo.setQuantidade(titulo.getQuantidade() + 1);
            titulo.aumentarDisponivel();
            
            System.out.println("Exemplar " + idExemplar + " adicionado ao título: " + titulo.getNome());
        } else {
            System.out.println("Erro: ISBN " + isbn + " não encontrado no sistema.");
        }
    }
}
