/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class TabelScore extends DB{
    // Constructor
    public TabelScore() throws Exception, SQLException {
        super();
    }
    
    // Mendapatkan keseluruhan data tabel
    public void getScore() {
        try {
            String query = "SELECT * from tscore";
            createQuery(query);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // Menambahkan data 
    public void insertData(String username, int adapt, int fall) {
        // Check for update
        boolean update = false;
        // Untuk insert
        try{
            String q = "SELECT * FROM tscore";
            this.createQuery(q);
            while (this.getResult().next()) {
                if(username.equals(this.getResult().getString(2))) {
//                    System.out.println(this.getResult().getString(2) + " | " + username);
                    update = true;
                }
            }
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        // kondisi jika username yang dituliskan tidak ada di tabel
        if(!update) {
            try {
                String query = "INSERT INTO tscore VALUES(NULL, '" + username + "', " + Integer.toString(adapt) + ", "
                        + Integer.toString(fall) + ")";
                createUpdate(query);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        // kondisi jika username tidak ditemukan
        else {
            try {
                String query = "UPDATE tscore SET adapt = " + adapt + ", fall = " + fall + " WHERE username = '"
                        + username + "'";
                createUpdate(query);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    // Set up datatable function
    public DefaultTableModel setTable() {

        DefaultTableModel dataTabel = null;
        try {
            Object[] column = { "Username", "fall", "adapt" };
            dataTabel = new DefaultTableModel(null, column);

            String query = "SELECT * from tscore order by adapt DESC";
            this.createQuery(query);
            while (this.getResult().next()) {
                Object[] row = new Object[3];
                row[0] = this.getResult().getString(2);
                row[1] = this.getResult().getString(4);
                row[2] = this.getResult().getString(3);
                dataTabel.addRow(row);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return dataTabel;
    }
}
