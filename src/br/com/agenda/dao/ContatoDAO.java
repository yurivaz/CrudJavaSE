package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    //Toda Regra de negocio vai acontecer dentro dessa classe

    public void save(Contato contato) {

        String sql = "INSERT INTO contatos (nome, idade, dataCadastro)  VALUES (?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMysql();

            //Criamos uma PReparedSteatement, para executar  uma query
            pstm = conn.prepareStatement(sql);

            //adicionar os valores que são esperados pela query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            //Executar a Query
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //fechar as conexões

            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public List<Contato> getContatos() {
        String sql = "select * from contatos";
        List<Contato> contatos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do Banco
        ResultSet rset = null;
        try {
            conn = ConnectionFactory.createConnectionToMysql();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            //Enquanto tiver dados para trazer contato
            while (rset.next()) {
                Contato contato = new Contato();

                //Recuperar Id
                contato.setId(rset.getInt("id"));
                contato.setNome(rset.getString("nome"));
                contato.setDataCadastro(rset.getDate("dataCadastro"));
                contatos.add(contato);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //fechar as conexões

            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return contatos;

    }

    public void update(Contato contato) {
        String sql = "Update contatos set nome = ?, idade = ?, datacadastro =?" + "where id =?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMysql();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            pstm.setInt(4, contato.getId());

            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //fechar as conexões

            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void deleteByID(int id) {
        String sql = "DELETE FROM contatos where id=?";
        Connection conn = null;
        PreparedStatement pstm = null;

        Contato contato = new Contato();

        try {
            conn = ConnectionFactory.createConnectionToMysql();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);

            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //fechar as conexões

            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
