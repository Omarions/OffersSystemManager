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
    private int genBankID = -1;
    private ResultSet rs;
    private PreparedStatement stmt;

    /*return company that has sepcified ID.
    *@parameter int bankId is the key of search to get bank data.
    *@return Bank bank is bank we search for by its ID.
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

    /*add a new company to db and return true if added successfuly and false if not.
    *@parameter Bank newBank is the new bank data to be added
    *@return int genBankID is the generated bank id for new record bank.
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

    /*edit an old offer with the new one and return the new offer.
    *@parameter int bankId is the id of bank to be modified
    *@return int rowsAffected is the affected rows by this operation.
    */
    public int editOffer(int bankId, Bank newBank) {
       
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

    /*remove an sepecified offer with RefNo. return true if successful. 
    * @parameter int bankId is the id of bank to be removed
    * @return int rowsAffected is the affected rows by this operation.
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
