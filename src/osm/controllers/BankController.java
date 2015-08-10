/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osm.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public Bank getBankByID(int bankID){
        //TODO
        //return company that has sepcified ID.
        try {
            String query="SELECT * FROM bank WHERE bank_id=?";
            PreparedStatement stmt=Connector.open().prepareStatement(query);
            stmt.setInt(1, bankID);
            ResultSet set=stmt.executeQuery();
            while(set.next()){
                bank.setBankId(bankID);
                bank.setName(set.getString("name"));
                bank.setAccountName(set.getString("account_name"));
                bank.setAccountNumber(set.getString("account_number"));
                bank.setSwiftCode(set.getString("swift_code"));
                bank.setCurrency(set.getString("Currency"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bank;
   } 
   
   public boolean addBank(Bank newBank){
       //TODO
       //add a new company to db and return true if added successfuly and false if not.
       return false;
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
