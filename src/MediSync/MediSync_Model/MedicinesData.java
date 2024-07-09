package MediSync.MediSync_Model;

import java.time.LocalDate;

public class MedicinesData {
    private String medicineName;
    private String medicineForm;
    private String medicineDescription;
    private String medicineExpirationDate;
    private double medicinePrice;
    private int medicineQuantity;

    public MedicinesData() {
        this.medicineName = "";
        this.medicineForm = "";
        this.medicineDescription = "";
        this.medicineExpirationDate = LocalDate.now().toString(); // Using current date as default
        this.medicinePrice = 0.0;
        this.medicineQuantity = 0;
    }

    public MedicinesData(String medicineName, String medicineForm,
            String medicineDescription,double medicinePrice,
            String medicineExpirationDate, int medicineQuantity) {
        this.medicineName = medicineName;
        this.medicineForm = medicineForm;
        this.medicineDescription = medicineDescription;
        this.medicineExpirationDate = medicineExpirationDate;
        this.medicinePrice = medicinePrice;
        this.medicineQuantity = medicineQuantity;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineForm() {
        return medicineForm;
    }

    public void setMedicineForm(String medicineForm) {
        this.medicineForm = medicineForm;
    }

    public String getMedicineDescription() {
        return medicineDescription;
    }

    public void setMedicineDescription(String medicineDescription) {
        this.medicineDescription = medicineDescription;
    }

    public String getMedicineExpirationDate() {
        return medicineExpirationDate;
    }

    public void setMedicineExpirationDate(String medicineExpirationDate) {
        this.medicineExpirationDate = medicineExpirationDate;
    }

    public double getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(int medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    public int getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(int medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    

}
