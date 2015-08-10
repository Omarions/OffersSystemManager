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
import java.util.ArrayList;
import java.util.List;
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
    private int genBankID = -1;
    private ResultSet rs;
    private PreparedStatement stmt;
    private final List<Bank> listBank=new ArrayList<>();

    
    /**
     * To search by bank ID.
     * @param bankId is the key of search to get bank data. 
     * @return Bank bank is bank we search for by its ID.
    */
    public Bank getBankByID(int bankId) {
        
        try {
            String query = "SELECT * FROM bank WHERE bank_id=?";
            stmt = Connector.open().prepareStatement(query);
            stmt.setInt(1, bankId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                bank.setBankId(bankId);
                bank.setName(rs.getString("name"));
                bank.setAccountName(rs.getString("account_name"));
                bank.setAccountNumber(rs.getString("account_number"));
                bank.setSwiftCode(rs.getString("swift_code"));
                bank.setCurrency(rs.getString("Currency"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null) {
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

    /*
    *Returns all bank data in table as list
    *@return listBank is the list that has Objects of Bank retrived from db
    */
    public List<Bank> getAllBanks(){
        String query="SELECT * FROM bank";
        try {
            stmt=Connector.open().prepareStatement(query);
            rs=stmt.executeQuery();
            while(rs.next()){
                Bank recordBank=new Bank();
                recordBank.setBankId(rs.getInt("bank_id"));
                recordBank.setName(rs.getString("name"));
                recordBank.setAccountName(rs.getString("account_name"));
                recordBank.setAccountNumber(rs.getString("account_number"));
                recordBank.setSwiftCode(rs.getString("swift_code"));
                recordBank.setCurrency(rs.getString("currency"));
                listBank.add(recordBank);
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
        return listBank;
    }
    
    /**
     * Add a new company to db and return true if added successfully and false if not. 
     * @param newBank is the new bank data to be added
     * @return genBankID is the generated bank id for new record bank.
    */
    public int addBank(Bank newBank) {

        String query = "INSERT INTO bank (bank_id,name,account_name,account_number,swift_code,currency) VALUES(0,?,?,?,?,?)";
        try {
            stmt = Connector.open().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, newBank.getName());
            stmt.setString(2, newBank.getAccountName());
            stmt.setString(3, newBank.getAccountNumber());
            stmt.setString(4, newBank.getSwiftCode());
            stmt.setString(5, newBank.getCurrency());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                genBankID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null) {
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

    /**
     * Edit an old offer with the new one and return the new offer.
     * @param bankId is the id of bank to be modified
     * @param newBank is the affected rows by this operation.
     * @return rowsAffected the affected rows count.
     */
    public int editBank(int bankId, Bank newBank) {
       
        int rowsAffected = -1;
        String query = "UPDATE TABLE bank SET name=?, account_name=?, account_number=?, swift_code=?, currency=? WHERE bank_id=?";
        try {
            stmt = Connector.open().prepareStatement(query);
            stmt.setString(1, newBank.getName());
            stmt.setString(2, newBank.getAccountName());
            stmt.setString(3, newBank.getAccountNumber());
            stmt.setString(4, newBank.getSwiftCode());
            stmt.setString(5, newBank.getCurrency());
            stmt.setInt(6, bankId);
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Connector.close();
        }

        return rowsAffected;
    }

    /**
     * Remove an specified offer with RefNo. return true if successful. 
     * @param bankId the ID of bank we want to remove.
     * @return rowsAffected is the affected rows count
     */
    public int RemoveBank(int bankId) {
       
        int rowsAffected = -1;
        String query = "DELETE FROM bank WHERE bank_id=?";
        try {
            stmt = Connector.open().prepareStatement(query);
            stmt.setInt(1, bankId);
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Connector.close();
        }
        return rowsAffected;
    }

}
