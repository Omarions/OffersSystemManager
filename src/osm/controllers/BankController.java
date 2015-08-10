/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osm.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import osm.connection.Connector;
import osm.models.Bank;

/**
 *
 * @author Omar
 */
public class BankController {
    private Bank bank;
    private int genBankID= -1;
    private ResultSet rs;
    private PreparedStatement stmt;
    
    public Bank getBankByID(int bankID){
        //TODO
        //return company that has sepcified ID.
        try {
            String query="SELECT * FROM bank WHERE bank_id=?";
            stmt=Connector.open().prepareStatement(query);
            stmt.setInt(1, bankID);
            rs=stmt.executeQuery();
            while(rs.next()){
                bank.setBankId(bankID);
                bank.setName(rs.getString("name"));
                bank.setAccountName(rs.getString("account_name"));
                bank.setAccountNumber(rs.getString("account_number"));
                bank.setSwiftCode(rs.getString("swift_code"));
                bank.setCurrency(rs.getString("Currency"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Connector.close();
        }
        return bank;
   } 
   
   public int addBank(Bank newBank){
       //TODO
       //add a new company to db and return true if added successfuly and false if not.
       
       String query="INSERT INTO bank (bank_id,name,account_name,account_number,swift_code,currency) VALUES(0,?,?,?,?,?)";
        try {
            stmt=Connector.open().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, newBank.getName());
            stmt.setString(2, newBank.getAccountName());
            stmt.setString(3, newBank.getAccountNumber());
            stmt.setString(4, newBank.getSwiftCode());
            stmt.setString(5, newBank.getCurrency());
            stmt.executeUpdate();
            rs=stmt.getGeneratedKeys();
            if(rs.next()){
                genBankID=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Connector.close();
        }
       
       return genBankID;
   }
   
   public Bank editOffer(Bank oldBank, Bank newBank){
       //TODO
       //edit an old offer with the new one and return the new offer.
       return null;
   }
   
   public boolean RemoveCompany(int bankID){
       //TODO
       //remove an sepecified offer with RefNo. return true if successful.
       return false;
   }

}
