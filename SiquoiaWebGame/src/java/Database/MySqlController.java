/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;

/**
 *
 * @author mr.nam
 */
public class MySqlController {
    MySqlConnection conn = new MySqlConnection();

    public MySqlController() {
    }
    
    public Connection connect() throws Exception {
        return conn.connect();
    }

   
    
}
