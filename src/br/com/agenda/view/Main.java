package br.com.agenda.view;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {


        Contato contato = new Contato();
        contato.setNome("Hugo Vaz Dos Santos");
        contato.setIdade(26);
        contato.setDataCadastro(new Date());
        contato.setId(1);

        //Salvando Contato no banco de dados
        ContatoDAO contatoSql = new ContatoDAO();
        //contatoSql.save(contato);

        //System.out.println("Resgitro cadastrado com sucesso");

        contatoSql.update(contato);
        contatoSql.deleteByID(1);


        for (Contato c : contatoSql.getContatos()) {

            System.out.println("ID: " + c.getId() + " Nome: " + c.getNome() + " Idade: " + c.getIdade() + " Data de cadastro: " +  c.getDataCadastro() );

        }


    }
}
