package org.algorithms.test.copilot.patterns.structural;

public class QuasiSingletonDemo {
    public static void main(String[] args) {
        // First retrieval (fetching from DB)
        LegalContract contract = ContractManager.getContract();
        contract.displayDetails();

        // Modify and reuse contract without re-fetching
        ContractManager.updateClient("Ovidiu T.");
        contract.displayDetails();

        // Modify again for another client
        ContractManager.updateClient("Robert Lewandowski");
        contract.displayDetails();
    }
}

// Contract Manager (Quasi-Singleton)
class ContractManager {
    private static LegalContract contractInstance;

    // Simulated "fetch from DB" only once
    public static LegalContract getContract() {
        if (contractInstance == null) {
            contractInstance = new LegalContract("CNTR-001", "Default Client", "Standard Terms & Conditions");
            System.out.println("Fetching contract from database...");
        }
        return contractInstance;
    }

    // Allows controlled updates
    public static void updateClient(String clientName) {
        contractInstance.setClientName(clientName);
        System.out.println("Updated contract for client: " + clientName);
    }
}

// Prototype Interface
interface ContractModel {
    void displayDetails();
}

// Concrete Class (Simulating DB Model)
class LegalContract implements ContractModel {
    private final String contractID;
    private String clientName;
    private final String contractTerms;

    public LegalContract(String contractID, String clientName, String contractTerms) {
        this.contractID = contractID;
        this.clientName = clientName;
        this.contractTerms = contractTerms;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void displayDetails() {
        System.out.println("Contract ID: " + contractID);
        System.out.println("Client Name: " + clientName);
        System.out.println("Terms: " + contractTerms);
        System.out.println("--------------------------------");
    }
}
