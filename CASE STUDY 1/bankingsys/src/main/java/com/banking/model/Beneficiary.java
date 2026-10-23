package com.banking.model;

public class Beneficiary {
    private long beneficiaryId;
    private long customerId;
    private String beneficiaryName;
    private String accountNumber;
    private String bankDetails;

    public Beneficiary() {}

    public Beneficiary(long beneficiaryId, long customerId, String beneficiaryName,
                       String accountNumber, String bankDetails) {
        this.beneficiaryId = beneficiaryId;
        this.customerId = customerId;
        this.beneficiaryName = beneficiaryName;
        this.accountNumber = accountNumber;
        this.bankDetails = bankDetails;
    }

    public Beneficiary(long customerId, String beneficiaryName, String accountNumber, String bankDetails) {
        this(0, customerId, beneficiaryName, accountNumber, bankDetails);
    }

    public long getBeneficiaryId() { return beneficiaryId; }
    public void setBeneficiaryId(long beneficiaryId) { this.beneficiaryId = beneficiaryId; }
    public long getCustomerId() { return customerId; }
    public void setCustomerId(long customerId) { this.customerId = customerId; }
    public String getBeneficiaryName() { return beneficiaryName; }
    public void setBeneficiaryName(String beneficiaryName) { this.beneficiaryName = beneficiaryName; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getBankDetails() { return bankDetails; }
    public void setBankDetails(String bankDetails) { this.bankDetails = bankDetails; }

    @Override
    public String toString() {
        return "Beneficiary{id=" + beneficiaryId + ", customerId=" + customerId + ", name='"
                + beneficiaryName + "', accountNumber='" + accountNumber + "', bank='" + bankDetails + "'}";
    }
}
