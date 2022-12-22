/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactorDeConexao {
    private static final String DRIVER ="com.mysql.jdbc.Driver";
    private static final String URL ="jdbc:mysql://localhost:3306/desportoprojecto?allowPublicKeyRetrieval=true&sslMode=DISABLED&useTimezone=true&serverTimezone=Africa/Luanda";
    private static final String USER ="root";
    private static final String PASS ="shinozuke";
    
    public static Connection getConnection (String DRIVER)throws SQLException,ClassNotFoundException{
        try{
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL, USER, PASS);
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro na conexão: " +ex.getMessage());
        }
    }
    public static void closeConnection(Connection con){
        try{
            if (con!=null) {
                con.close();
            }
        }catch (SQLException ex) {
            Logger.getLogger(FactorDeConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}