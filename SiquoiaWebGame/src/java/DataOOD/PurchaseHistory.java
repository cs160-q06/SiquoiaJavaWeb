package DataOOD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mr.nam
 */
public class PurchaseHistory {

   

    
    private int id;
    private String dateTime;
    private int earnedPoint;
    private int usedPoint;
    private int userID;

    public PurchaseHistory(int id, String dateTime, int earnedPoint, int usedPoint,int userID ) {
        this.id = id;
        this.dateTime = dateTime;
        this.earnedPoint = earnedPoint;
        this.usedPoint = usedPoint;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getEarnedPoint() {
        return earnedPoint;
    }

    public int getUsedPoint() {
        return usedPoint;
    }

    public int getUserID() {
        return userID;
    }
    /**
     * 
     * @param conn
     * @param i
     * @return 
     */
    public static List<PurchaseHistory> doQueryByID(Connection conn, int i) throws SQLException {
        String query = "SELECT * from POINT_HISTORY where USER_ID = "+i;
        return doQuery(conn, query);
    }/**
     * 
     * @param conn
     * @return
     * @throws SQLException 
     */
    public static List<PurchaseHistory> doQueryGetAll(Connection conn) throws SQLException {
        String query = "SELECT * from POINT_HISTORY;";
        return doQuery(conn, query);
    }
    /**
     * 
     * @param conn
     * @param query
     * @return 
     */
     private static List<PurchaseHistory> doQuery(Connection conn, String query) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<PurchaseHistory> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("ID");
            int userID = rs.getInt("USER_ID");
            String dateTime = rs.getString("DATETIME");
            int earnP = rs.getInt("POINTEARNED");
            int useP=rs.getInt("POINTUSED");;
            PurchaseHistory i = new PurchaseHistory(id, dateTime, earnP, useP, userID);
            list.add(i);
        }
        return list;
    }

    @Override
    public String toString() {
        return "[id: "+id+"|dateTime: "+dateTime
                +"|earn: "+earnedPoint+"|use: "+usedPoint+"|userID: "+userID+"]";
    }
     
}
