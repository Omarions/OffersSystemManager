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
import osm.models.Client;

/**
 *
 * @author Omar
 */
public class ClientController {
    private Client client;
    private int genClientID = -1;
    private ResultSet rs;
    private PreparedStatement stmt;
    private final List<Client> listClient=new ArrayList<>();

    /**
     * Search for a client by its ID.
     * @param clientId is the ID of client to search with.
     * @return Client that has clientId.
     */
    
    public Client getClientByID(int clientId) {
        
        try {
            String query = "SELECT * FROM client WHERE client_id=?";
            stmt = Connector.open().prepareStatement(query);
            stmt.setInt(1, clientId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                client.setClientId(clientId);
                client.setName(rs.getString("name"));
                client.setAddress(rs.getString("address"));
                client.setTel(rs.getString("tel"));
                client.setFax(rs.getString("fax"));
                client.setEmail(rs.getString("email"));
                client.setWebsite(rs.getString("website"));
                client.setContactName(rs.getString("contact_name"));
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
        return client;
    }

    /**
     * To return list of clients in db.
     * @return List of all clients data from DB.
     */ 
    public List<Client> getAllClients(){
        String query="SELECT * FROM bank";
        try {
            stmt=Connector.open().prepareStatement(query);
            rs=stmt.executeQuery();
            while(rs.next()){
                Client recordClient=new Client();
                recordClient.setClientId(rs.getInt("client_id"));
                recordClient.setName(rs.getString("name"));
                recordClient.setAddress(rs.getString("address"));
                recordClient.setTel(rs.getString("tel"));
                recordClient.setFax(rs.getString("fax"));
                recordClient.setEmail(rs.getString("email"));
                recordClient.setWebsite(rs.getString("website"));
                recordClient.setContactName(rs.getString("contact_name"));
                listClient.add(recordClient);
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
        return listClient;
    }
    
    /**
     * add a new client to db 
     * @param newClient is the new client data to be added
     * @return genBankID is the generated client id for new record client.
    */
    public int addClient(Client newClient) {

        String query = "INSERT INTO client (client_id,name,address,tel,fax,email,website,contact_name) VALUES(0,?,?,?,?,?,?,?)";
        try {
            stmt = Connector.open().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, newClient.getName());
            stmt.setString(2, newClient.getAddress());
            stmt.setString(3, newClient.getTel());
            stmt.setString(4, newClient.getFax());
            stmt.setString(5, newClient.getEmail());
            stmt.setString(6, newClient.getWebsite());
            stmt.setString(7, newClient.getContactName());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                genClientID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Connector.close();
        }

        return genClientID;
    }
    
    /**
     * Edit an old offer with the new one and return the new offer.
     * @param clientId is the id of client to be modified
     * @param newClient is the new data of client.
     * @return rowsAffected is the affected rows by this operation.
     */ 
    public int editClient(int clientId, Client newClient) {
       
        int rowsAffected = -1;
        String query = "UPDATE TABLE bank SET name=?, address=?, tel=?, fax=?, email=?, website=?, contact_name=? WHERE bank_id=?";
        try {
            stmt = Connector.open().prepareStatement(query);
            stmt.setString(1, newClient.getName());
            stmt.setString(2, newClient.getAddress());
            stmt.setString(3, newClient.getTel());
            stmt.setString(4, newClient.getFax());
            stmt.setString(5, newClient.getEmail());
            stmt.setString(6, newClient.getWebsite());
            stmt.setString(7, newClient.getContactName());
            stmt.setInt(8, newClient.getClientId());
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Connector.close();
        }

        return rowsAffected;
    }

    /*
    * @parameter int bankId 
    * @return int 
    */

    /**
     * Remove an sepcified Client with client ID. 
     * @param clientId is the id of client to be removed
     * @return rowsAffected is the affected rows count by this operation.
     */
    
    public int RemoveClient(int clientId) {
       
        int rowsAffected = -1;
        String query = "DELETE FROM client WHERE client_id=?";
        try {
            stmt = Connector.open().prepareStatement(query);
            stmt.setInt(1, clientId);
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Connector.close();
        }
        return rowsAffected;
    }
 
}
