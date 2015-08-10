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
import osm.models.ItemDetails;

/**
 *
 * @author Omar
 */
public class ItemDetailsController {
    private ItemDetails itemDetails;
    private int genItemDetailsID = -1;
    private ResultSet rs;
    private PreparedStatement stmt;
    private List<ItemDetails> listItemDetails;

    /**
     * Search for a client by its ID.
     * @param itemDetailsId is the ID of item details to search with.
     * @return the item details with that ID.
     */
    public ItemDetails getItemDetailsByID(int itemDetailsId) {
        
        try {
            String query = "SELECT * FROM item_details WHERE item_details_id=?";
            stmt = Connector.open().prepareStatement(query);
            stmt.setInt(1, itemDetailsId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                itemDetails.setItemDetailsId(itemDetailsId);
                itemDetails.setDescription(rs.getString("description"));
                itemDetails.setPrice(rs.getDouble("price"));
                itemDetails.setItemId(rs.getInt("item_id"));
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
        return itemDetails;
    }

    /**
     * To return list of items details in db.
     * @return List of all items details data from DB.
     */ 
    public List<ItemDetails> getAllItemDetailses(){
        listItemDetails=new ArrayList<>();
        String query="SELECT * FROM item_details";
        try {
            stmt=Connector.open().prepareStatement(query);
            rs=stmt.executeQuery();
            while(rs.next()){
                ItemDetails iDetails=new ItemDetails();
                iDetails.setItemDetailsId(rs.getInt("item_details_id"));
                iDetails.setDescription(rs.getString("description"));
                iDetails.setPrice(rs.getDouble("price"));
                iDetails.setItemId(rs.getInt("item_id"));
                listItemDetails.add(iDetails);
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
        return listItemDetails;
    }
    
    /**
     * Return all ItemDetails of sepcified item
     * @param itemId the item id that we search with.
     * @return All items details of that item.
     */
    public List<ItemDetails> getAllItemDetailsByItemID(int itemId){
        listItemDetails=new ArrayList<>();
         try {
            String query = "SELECT * FROM item_details WHERE item_id=?";
            stmt = Connector.open().prepareStatement(query);
            stmt.setInt(1, itemId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ItemDetails iDetails=new ItemDetails();
                iDetails.setItemDetailsId(rs.getInt("item_details_id"));
                iDetails.setDescription(rs.getString("description"));
                iDetails.setPrice(rs.getDouble("price"));
                iDetails.setItemId(itemId);
                
                listItemDetails.add(iDetails);
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
        return listItemDetails;
    }
    
    /**
     * add a new client to db 
     * @param idetails is the new item details data to be added
     * @return genBankID is the generated item details id for new record.
    */
    public int addItemDetails(ItemDetails idetails) {

        String query = "INSERT INTO item_details (item_details_id,description,price,item_id) VALUES(0,?,?,?)";
        try {
            stmt = Connector.open().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, idetails.getDescription());
            stmt.setDouble(2, idetails.getPrice());
            stmt.setInt(3, idetails.getItemId());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                genItemDetailsID = rs.getInt(1);
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

        return genItemDetailsID;
    }
    
    /**
     * Edit an old offer with the new one and return the new offer.
     * @param iDetails is the id of client to be modified
     * @param newIDetails is the new data of item details.
     * @return rowsAffected is the affected rows count.
     */ 
    public int editItemDetails(int iDetails, ItemDetails newIDetails) {
       
        int rowsAffected = -1;
        String query = "UPDATE TABLE item_details SET description=?, price=?, item_id=? WHERE item_details_id=?";
        try {
            stmt = Connector.open().prepareStatement(query);
            stmt.setString(1, newIDetails.getDescription());
            stmt.setDouble(2, newIDetails.getPrice());
            stmt.setInt(3, newIDetails.getItemId());
            
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

    /**
     * Remove an sepcified Client with client ID. 
     * @param iDetailsId is the id of item details to be removed
     * @return rowsAffected is the affected rows count.
     */ 
    public int RemoveItemDetails(int iDetailsId) {
       
        int rowsAffected = -1;
        String query = "DELETE FROM item_details WHERE item_details_id=?";
        try {
            stmt = Connector.open().prepareStatement(query);
            stmt.setInt(1, iDetailsId);
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
