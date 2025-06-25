package br.inatel;

import br.inatel.DAO.ConnectionDAO.*;
import br.inatel.Model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AutorDAO autorDAO = new AutorDAO();
        autorDAO.testConnection();

        CopiaLivroDAO copiaLivroDAO = new CopiaLivroDAO();
        copiaLivroDAO.testConnection();

        EditoraDAO editoraDAO = new EditoraDAO();
        editoraDAO.testConnection();

        EmprestimosDAO emprestimosDAO = new EmprestimosDAO();
        emprestimosDAO.testConnection();

        GeneroLiterarioDAO generoLiterarioDAO = new GeneroLiterarioDAO();
        generoLiterarioDAO.testConnection();

        Livro_has_editoraDAO livroHasEditoraDAO = new Livro_has_editoraDAO();
        livroHasEditoraDAO.testConnection();

        LivroDAO livroDAO = new LivroDAO();
        livroDAO.testConnection();

        MembroDAO membroDAO = new MembroDAO();
        membroDAO.testConnection();

        ResenhaDAO resenhaDAO = new ResenhaDAO();
        resenhaDAO.testConnection();

        boolean menuPrincipal = true;
        Scanner scanner = new Scanner(System.in);
        int t;
        int op;
        int x,id,id2,numero,id3,idMembro,idEditora,idLivro,nota,idAutor, idGenero;
        do {
            System.out.println("Escolha uma das tabelas a seguir para manipular: ");
            System.out.println( " (0) - sair\n (1) - autor\n (2) - copia\n (3) - editora\n (4) - emprestimos\n (5) - genero\n " +
                    "(6) - livro\n (7) - membro\n (8) - resenha\n (9) - relacao de livros e editoras\n (10) - relacao de membros e resenhas\n" +
                    "(11) - relacao de livros a serem devolvidos\n(12) - relacao de autores e editoras (obras publicadas)");
            try {
                t = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número inteiro.");
                continue; // pula para a proxima iteracao
            }
            switch (t) {
                case 0:
                    menuPrincipal = false;
                    break;
                case 1:
                    System.out.println("Qual operação deseja realizar?");
                    System.out.println(" (1) - inserir novo autor\n (2) - atualizar registro\n (3) - deletar registro\n (4) - visualizar todos autores\n" +
                            " (5) - buscar autor");
                    try {
                        op = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida! Digite um número inteiro.");
                        continue; // Skip to next loop iteration
                    }
                    switch(op){
                        case 1:
                            System.out.println("Digite o nome do autor: ");
                            String nome = scanner.nextLine();
                            System.out.println();
                            System.out.println("Digite a nacionalidade do autor: ");
                            String nacionalidade = scanner.nextLine();
                            System.out.println("Inserindo autor");

                            Autor autor = new Autor(nome, nacionalidade);
                            autorDAO.insertAutor(autor);
                            System.out.println("\n \n");
                            break;
                        case 2:
                            System.out.println("Informe o ID do autor que deseja atualizar: ");
                            autorDAO.selectAutor();
                            try {
                                x = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Insira as informações do autor que deseja atualizar: ");
                            System.out.println("Digite o nome do autor: ");
                            nome = scanner.nextLine();
                            System.out.println();
                            System.out.println("Digite a nacionalidade do autor: ");
                            nacionalidade = scanner.nextLine();
                            System.out.println("Inserindo autor");
                            autorDAO.updateAutor(x,new Autor(nome, nacionalidade));

                            break;
                        case 3:
                            System.out.println("Digite o ID do autor que deseja excluir: ");
                            try {
                                id = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }

                            autorDAO.deleteAutor(id);
                            System.out.println("Autor excluido com sucesso!");
                            System.out.println("\n \n");
                            break;
                        case 4:
                            System.out.println("Vizualizando todos os autores: ");
                            autorDAO.selectAutor();
                            System.out.println("\n \n");
                            break;
                        case 5:
                            System.out.println("Insira o ID do autor que deseja visualizar: ");
                            try {
                                id = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            autorDAO.selectUnicoAutor(id);
                            break;

                    }
                    break;
                case 2:
                    System.out.println("Qual operação deseja realizar?");
                    System.out.println(" (1) - inserir nova copia\n (2) - atualizar registro\n (3) - deletar registro\n (4) - visualizar todas as copias\n");
                    try {
                        op = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida! Digite um número inteiro.");
                        continue; // Skip to next loop iteration
                    }
                    switch(op){
                        case 1:
                            System.out.println("Digite o ID do livro original: ");
                            try {
                                id2 = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Digite o nome da editora: ");
                            String nome = scanner.nextLine();
                            System.out.println("Digite o numero da edicao: ");
                            try {
                                numero = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Digite o ID da editora: ");
                            editoraDAO.selectEditora();
                            try {
                                id3 = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("A cópia está disponível (Sim ou Nao)");
                            String disponivel = scanner.nextLine();

                            CopiaLivro copia = new CopiaLivro(id2,nome,numero,id3,disponivel);
                            copiaLivroDAO.insertCopia(copia);
                            System.out.println("\n \n");
                            break;
                        case 2:
                            System.out.println("Informe o ID da copia que deseja atualizar: ");
                            copiaLivroDAO.selectCopia();
                            try {
                                 x = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Insira as informações da copia que deseja atualizar: ");
                            System.out.println("Digite o ID do livro original: ");
                            livroDAO.selectLivro();
                            try {
                                id2 = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Digite o nome da editora: ");
                            nome = scanner.nextLine();
                            System.out.println("Digite o numero da edicao: ");
                            try {
                                numero = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Digite o ID da editora: ");
                            editoraDAO.selectEditora();
                            try {
                                id3 = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("A cópia está disponível (Sim ou Nao)");
                            disponivel = scanner.nextLine();

                            copiaLivroDAO.updateCopia(x,new CopiaLivro(id2,nome,numero,id3,disponivel));
                            break;
                        case 3:
                            System.out.println("Digite o ID da copia que deseja excluir: ");
                            try {
                                id = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            copiaLivroDAO.deleteCopia(id);
                            System.out.println("Copia excluida com sucesso!");
                            System.out.println("\n \n");
                            break;
                        case 4:
                            System.out.println("Vizualizando todas as copias: ");
                            copiaLivroDAO.selectCopia();
                            System.out.println("\n \n ");
                            break;

                    }
                    break;
                case 3:
                    System.out.println("Qual operação deseja realizar?");
                    System.out.println(" (1) - inserir nova editora\n (2) - atualizar registro\n (3) - deletar registro\n (4) - visualizar todas editoras\n" +
                            " (5) - buscar editora");
                    try {
                        op = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida! Digite um número inteiro.");
                        continue; // pula para a proxima iteracao
                    }
                    switch(op){
                        case 1:
                            System.out.println("Digite o nome da editora: ");
                            String nome = scanner.nextLine();
                            System.out.println("Digite o numero de telefone: ");
                            String tel = scanner.nextLine();
                            System.out.println("Digite o e-mail da editora: ");
                            String email = scanner.nextLine();

                            Editora editora = new Editora(nome,tel,email);
                            editoraDAO.insertEditora(editora);
                            System.out.println("\n \n ");
                            break;
                        case 2:
                            System.out.println("Informe o ID da editora que deseja atualizar: ");
                            editoraDAO.selectEditora();
                            try {
                                x = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Insira as informações da editora que deseja atualizar: ");
                            System.out.println("Digite o nome da editora: ");
                            nome = scanner.nextLine();
                            System.out.println("Digite o numero de telefone: ");
                            tel = scanner.nextLine();
                            System.out.println("Digite o e-mail da editora: ");
                            email = scanner.nextLine();

                            editoraDAO.updateEditora(x,new Editora(nome,tel,email));
                            break;
                        case 3:
                            System.out.println("Digite o ID da editora que deseja excluir: ");
                            try {
                                id = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            editoraDAO.deleteEditora(id);
                            System.out.println("Editora excluida com sucesso!");
                            System.out.println("\n \n ");
                            break;
                        case 4:
                            System.out.println("Vizualizando todas as editoras: ");
                            editoraDAO.selectEditora();
                            System.out.println("\n \n ");
                            break;
                        case 5:
                            System.out.println("Insira o ID da editora que deseja visualizar: ");
                            id = Integer.parseInt(scanner.nextLine());
                            editoraDAO.selectUnicaEditora(id);
                            break;


                    }
                    break;
                case 4:
                    System.out.println("Qual operação deseja realizar?");
                    System.out.println(" (1) - inserir novo emprestimo\n (2) - atualizar emprestimo\n (3) - deletar emprestimo\n (4) - visualizar todos emprestimos\n");
                    try {
                        op = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida! Digite um número inteiro.");
                        continue; // pula para a proxima iteracao
                    }
                    switch(op){
                        case 1:
                            System.out.println("Digite a data de retirada: ");
                            String dataRetirada = scanner.nextLine();
                            System.out.println("Digite a data de devolucao: ");
                            String dataDevolucao = scanner.nextLine();
                            System.out.println("Digite o ID do membro: ");
                            membroDAO.selectMembro();
                            try {
                                idMembro = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Digite o ID do livro (cópia): ");
                            copiaLivroDAO.selectCopia();
                            try {
                                idLivro = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }

                            Emprestimos emprestimos = new Emprestimos(dataRetirada,dataDevolucao,idMembro,idLivro);
                            emprestimosDAO.insertEmprestimo(emprestimos);
                            System.out.println("\n \n ");
                            break;
                        case 2:
                            System.out.println("Informe o ID do emprestimo que deseja atualizar: ");
                            emprestimosDAO.selectEmprestimos();
                            try {
                                x = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Insira as informações do emprestimo que deseja atualizar: ");
                            System.out.println("Digite a data de retirada: ");
                            dataRetirada = scanner.nextLine();
                            System.out.println("Digite a data de devolucao: ");
                            dataDevolucao = scanner.nextLine();
                            System.out.println("Digite o ID do membro: ");
                            membroDAO.selectMembro();
                            try {
                                idMembro = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Digite o ID do livro (cópia): ");
                            copiaLivroDAO.selectCopia();
                            try {
                                idLivro = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            emprestimosDAO.updateEmprestimo(x,new Emprestimos(dataRetirada,dataDevolucao,idMembro,idLivro));
                            break;
                        case 3:
                            System.out.println("Digite o ID do emprestimo que deseja excluir: ");
                            try {
                                id = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            emprestimosDAO.deleteEmprestimos(id);
                            System.out.println("Emprestimo excluido com sucesso!");
                            System.out.println("\n \n ");
                            break;
                        case 4:
                            System.out.println("Vizualizando todos os emprestimos: ");
                            emprestimosDAO.selectEmprestimos();
                            System.out.println("\n \n ");
                            break;

                    }
                    break;
                case 5:
                    System.out.println("Qual operação deseja realizar?");
                    System.out.println(" (1) - inserir novo genero literario\n (2) - atualizar registro\n (3) - deletar registro\n (4) - visualizar todos generos literarios\n" +
                            " (5) - buscar genero literario");
                    try {
                        op = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida! Digite um número inteiro.");
                        continue; // pula para a proxima iteracao
                    }
                    switch(op){
                        case 1:
                            System.out.println("Digite o nome do gênero literário: ");
                            String genero = scanner.nextLine();
                            GeneroLiterario generoLiterario = new GeneroLiterario(genero);
                            generoLiterarioDAO.insertGenero(generoLiterario);
                            System.out.println("\n \n ");
                            break;
                        case 2:
                            System.out.println("Informe o ID do genero literario que deseja atualizar: ");
                            generoLiterarioDAO.selectGeneroLiterario();
                            try {
                                x = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Insira as informações do genero literario que deseja atualizar: ");
                            System.out.println("Digite o nome do gênero literário: ");
                            genero = scanner.nextLine();
                            generoLiterarioDAO.updateGenero(x,new GeneroLiterario(genero));

                            break;
                        case 3:
                            System.out.println("Digite o ID do genero literario que deseja excluir: ");
                            try {
                                id = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            generoLiterarioDAO.deleteGenero(id);
                            System.out.println("Genero literario excluido com sucesso!");
                            System.out.println("\n \n ");
                            break;
                        case 4:
                            System.out.println("Vizualizando todos os generos literarios: ");
                            generoLiterarioDAO.selectGeneroLiterario();
                            System.out.println("\n \n ");
                            break;
                        case 5:
                            System.out.println("Insira o ID do genero literario que deseja visualizar: ");
                            id = Integer.parseInt(scanner.nextLine());
                            generoLiterarioDAO.selectUnicoGenero(id);
                            break;

                    }
                    break;
                case 6:
                    System.out.println("Qual operação deseja realizar?");
                    System.out.println(" (1) - inserir novo livro\n (2) - atualizar registro\n (3) - deletar registro\n (4) - visualizar todos livros\n" +
                            " (5) - buscar livro");
                    try {
                        op = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida! Digite um número inteiro.");
                        continue; // pula para a proxima iteracao
                    }
                    switch(op){
                        case 1:
                            System.out.println("Digite o título da obra: ");
                            String tituloObra = scanner.nextLine();
                            System.out.println("Digite o ID do autor: ");
                            autorDAO.selectAutor();
                            try {
                                idAutor = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Digite o ID do gênero literário: ");
                            generoLiterarioDAO.selectGeneroLiterario();
                            try {
                                idGenero = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Digite o ID da lista de interesse: ");
                            membroDAO.selectMembro();
                            int idListaInteresse = Integer.parseInt(scanner.nextLine());

                            Livro livro = new Livro(tituloObra, idAutor, idGenero, idListaInteresse);
                            livroDAO.insertLivro(livro);
                            System.out.println("\n \n ");
                            break;
                        case 2:
                            System.out.println("Informe o ID do livro que deseja atualizar: ");
                            livroDAO.selectLivro();
                            try {
                                x = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Insira as informações do livro que deseja atualizar: ");
                            System.out.println("Digite o título da obra: ");
                            tituloObra = scanner.nextLine();
                            System.out.println("Digite o ID do autor: ");
                            autorDAO.selectAutor();
                            try {
                                idAutor = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Digite o ID do gênero literário: ");
                            generoLiterarioDAO.selectGeneroLiterario();

                            try {
                                idGenero = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Digite o ID da lista de interesse: ");
                            membroDAO.selectMembro();
                            try {
                                idListaInteresse = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            livroDAO.updateLivro(x,new Livro(tituloObra,idAutor,idGenero,idListaInteresse));
                            break;
                        case 3:
                            System.out.println("Digite o ID do livro que deseja excluir: ");
                            try {
                                id = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            livroDAO.deleteLivro(id);
                            System.out.println("Livro excluido com sucesso!");
                            System.out.println("\n \n ");
                            break;
                        case 4:
                            System.out.println("Vizualizando todos os livros: ");
                            livroDAO.selectLivro();
                            System.out.println("\n \n ");
                            break;
                        case 5:
                            System.out.println("Insira o ID do livro que deseja visualizar: ");
                            id = Integer.parseInt(scanner.nextLine());;
                            livroDAO.selectUnicoLivro(id);
                            break;
                    }
                    break;
                case 7:
                    System.out.println("Qual operação deseja realizar?");
                    System.out.println(" (1) - inserir novo membro\n (2) - atualizar registro\n (3) - deletar registro\n (4) - visualizar todos membros\n" +
                            " (5) - buscar membro");
                    try {
                        op = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida! Digite um número inteiro.");
                        continue; // pula para a proxima iteracao
                    }
                    switch(op){
                        case 1:
                            System.out.println("Digite o nome do membro: ");
                            String nomeMembro = scanner.nextLine();
                            System.out.println("Digite o endereço do membro: ");
                            String enderecoMembro = scanner.nextLine();
                            System.out.println("Digite o telefone do membro: ");
                            String telefoneMembro = scanner.nextLine();
                            System.out.println("Digite o e-mail do membro: ");
                            String emailMembro = scanner.nextLine();
                            System.out.println("Digite o status do membro (emprestado/devolvido/sem emprestimos): ");
                            String status = scanner.nextLine();

                            Membro membro = new Membro(nomeMembro,enderecoMembro,telefoneMembro,emailMembro,status);
                            membroDAO.insertMembro(membro);
                            System.out.println("\n \n ");
                            break;
                        case 2:
                            System.out.println("Informe o ID do membro que deseja atualizar: ");
                            try {
                                x = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Insira as informações do membro que deseja atualizar: ");
                            System.out.println("Digite o nome do membro: ");
                            nomeMembro = scanner.nextLine();
                            System.out.println("Digite o endereço do membro: ");
                            enderecoMembro = scanner.nextLine();
                            System.out.println("Digite o telefone do membro: ");
                            telefoneMembro = scanner.nextLine();
                            System.out.println("Digite o e-mail do membro: ");
                            emailMembro = scanner.nextLine();
                            System.out.println("Digite o status do membro (ativo/inativo): ");
                            status = scanner.nextLine();
                            membroDAO.updateMembro(x,new Membro(nomeMembro,enderecoMembro,telefoneMembro,emailMembro,status));
                            break;
                        case 3:
                            System.out.println("Digite o ID do membro que deseja excluir: ");
                            try {
                                id = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            membroDAO.deleteMembro(id);
                            System.out.println("Membro excluido com sucesso!");
                            System.out.println("\n \n ");
                            break;
                        case 4:
                            System.out.println("Vizualizando todos os membros: ");
                            membroDAO.selectMembro();
                            System.out.println("\n \n ");
                            break;
                        case 5:
                            System.out.println("Insira o ID do membro que deseja visualizar: ");
                            id = Integer.parseInt(scanner.nextLine());
                            membroDAO.selectUnicoMembro(id);
                            break;

                    }
                    break;
                case 8:
                    System.out.println("Qual operação deseja realizar?");
                    System.out.println(" (1) - inserir nova resenha\n (2) - visualizar todas resenhas\n");
                    try {
                        op = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida! Digite um número inteiro.");
                        continue; // pula para a proxima iteracao
                    }
                    switch(op){
                        case 1:
                            System.out.println("ATENCAO!!!\n Apos publicada, sua resenha não pode mais ser excluida ou atualizada!");
                            System.out.println("Digite a nota da resenha: ");
                            try {
                                nota = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Digite o comentário da resenha: ");
                            String comentario = scanner.nextLine();
                            System.out.println("Digite o ID do membro autor da resenha: ");
                            membroDAO.selectMembro();
                            try {
                                idMembro = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }
                            System.out.println("Digite o ID do livro resenhado: ");
                            livroDAO.selectLivro();
                            try {
                                idLivro = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite um número inteiro.");
                                continue; // pula para a proxima iteracao
                            }

                            Resenha resenha = new Resenha(nota, comentario, idMembro, idLivro);
                            resenhaDAO.insertResenha(resenha);
                            System.out.println("\n \n ");
                            break;
                        case 2:
                            System.out.println("Vizualizando todas as resenhas: ");
                            resenhaDAO.selectResenhas();
                            System.out.println("\n \n ");
                            break;

                    }
                    break;
                case 9:
                    System.out.println("Relacione livros e editoras: ");
                    System.out.println("Digite o ID do livro: ");
                    livroDAO.selectLivro();
                    try {
                        idLivro = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida! Digite um número inteiro.");
                        continue; // pula para a proxima iteracao
                    }
                    System.out.println("Digite o ID da editora: ");
                    editoraDAO.selectEditora();
                    try {
                        idEditora = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida! Digite um número inteiro.");
                        continue; // pula para a proxima iteracao
                    }
                    Livro_has_editora livEd = new Livro_has_editora(idLivro, idEditora);
                    livroHasEditoraDAO.insertLivroEditora(livEd);
                    System.out.println("\n \n ");
                    break;
                case 10:
                    System.out.println("Visualizando notas dos membros para as obras disponiveis: ");
                    livroDAO.joinMembroResenhaLivro();
                    System.out.println("\n \n ");
                    break;
                case 11:
                    System.out.println("Visualizando os titulos emprestados e suas datas de devolucao: ");
                    livroDAO.joinMembroEmprestimoLivro();
                    System.out.println("\n \n ");
                    break;
                case 12:
                    System.out.println("Visualizando a relacao de autores e obras publicadas por cada editora: ");
                    livroDAO.joinAutorObraEditora();
                    System.out.println("\n \n ");
                    break;
                default:
                    System.out.println("Digite um valor válido");
                    break;

            }

        } while(menuPrincipal);


        scanner.close();

    }
}