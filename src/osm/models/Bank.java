/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osm.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Omar
 */
@Entity
@Table(name = "bank")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bank_1.findAll", query = "SELECT b FROM Bank_1 b"),
    @NamedQuery(name = "Bank_1.findByBankId", query = "SELECT b FROM Bank_1 b WHERE b.bankId = :bankId"),
    @NamedQuery(name = "Bank_1.findByName", query = "SELECT b FROM Bank_1 b WHERE b.name = :name"),
    @NamedQuery(name = "Bank_1.findByAccountName", query = "SELECT b FROM Bank_1 b WHERE b.accountName = :accountName"),
    @NamedQuery(name = "Bank_1.findByAccountNumber", query = "SELECT b FROM Bank_1 b WHERE b.accountNumber = :accountNumber"),
    @NamedQuery(name = "Bank_1.findBySwiftCode", query = "SELECT b FROM Bank_1 b WHERE b.swiftCode = :swiftCode"),
    @NamedQuery(name = "Bank_1.findByCurrency", query = "SELECT b FROM Bank_1 b WHERE b.currency = :currency")})
public class Bank implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "bank_id")
    private Integer bankId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "account_name")
    private String accountName;
    @Basic(optional = false)
    @Column(name = "account_number")
    private String accountNumber;
    @Basic(optional = false)
    @Column(name = "swift_code")
    private String swiftCode;
    @Basic(optional = false)
    @Column(name = "currency")
    private String currency;

    public Bank() {
    }

    public Bank(Integer bankId) {
        this.bankId = bankId;
    }

    public Bank(Integer bankId, String name, String accountName, String accountNumber, String swiftCode, String currency) {
        this.bankId = bankId;
        this.name = name;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.swiftCode = swiftCode;
        this.currency = currency;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bankId != null ? bankId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bank)) {
            return false;
        }
        Bank other = (Bank) object;
        if ((this.bankId == null && other.bankId != null) || (this.bankId != null && !this.bankId.equals(other.bankId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "osm.models.Bank_1[ bankId=" + bankId + " ]";
    }
    
}
