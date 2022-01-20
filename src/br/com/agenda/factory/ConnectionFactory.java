package br.com.agenda.factory;


import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
    //Nome do usuário SQL
    private static  final String USERNAME = "root";

    //Senha do banco
    private static  final String PASSWORD = "root";

    //Caminho banco de Dados
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

    //Conexão com o banco de Dados

   public  static Connection createConnectionToMysql() throws Exception{
       //Faz com que a classe seja carregada pela JVM
       Class.forName("com.mysql.cj.jdbc.Driver");

       //Cria a Conexão com banco de dados
       Connection connection = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);

       return connection;
   }

    public static void main(String[] args) throws Exception {
        //Recuperar a conexão com o banco de dados
        Connection con = createConnectionToMysql();

        //Testar se a conexão é nula

        if (con!= null){
            System.out.println("Conexão pode ser criada");
            con.close();
        }else {
            System.out.println("Conxeão já foi Criada");
            con.close();
        }

    }



}
