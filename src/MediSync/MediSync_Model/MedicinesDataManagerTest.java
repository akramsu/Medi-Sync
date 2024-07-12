// package MediSync.MediSync_Model;

// public class MedicinesDataManagerTest {
//     public static void main(String[] args) {
//         MedicinesDataManager manager = new MedicinesDataManager();

//         // Create new medicines
//         MedicinesData med1 = new MedicinesData("Paracetamol", "Tablets", "Pain relief", 2.99, "12-31-2025", 100);
//         MedicinesData med2 = new MedicinesData("Ibuprofen", "Capsules", "Anti-inflammatory", 5.49, "11-30-2024", 50);

//         // Add medicines
//         manager.addMedicine(med1);
//         manager.addMedicine(med2);

//         // Display all medicines
//         System.out.println("All Medicines:");
//         for (MedicinesData medicine : manager.getAllMedicines()) {
//             System.out.println(medicine.getMedicineName() + " - " + medicine.getMedicineForm() + " - " + medicine.getMedicineDescription() + " - $" + medicine.getMedicinePrice() + " - " + medicine.getMedicineExpirationDate() + " - Quantity: " + medicine.getMedicineQuantity());
//         }

//         // Update medicine
//         MedicinesData updatedMed1 = new MedicinesData("Paracetamol", "Tablets", "Pain relief", 3.49, "12-31-2025", 200);
//         manager.updateMedicine(updatedMed1);

//         // Display all medicines after update
//         System.out.println("\nUpdated Medicines:");
//         for (MedicinesData medicine : manager.getAllMedicines()) {
//             System.out.println(medicine.getMedicineName() + " - " + medicine.getMedicineForm() + " - " + medicine.getMedicineDescription() + " - $" + medicine.getMedicinePrice() + " - " + medicine.getMedicineExpirationDate() + " - Quantity: " + medicine.getMedicineQuantity());
//         }

//         // Remove a medicine
//         manager.removeMedicine("Ibuprofen");

//         // Display all medicines after deletion
//         System.out.println("\nMedicines After Deletion:");
//         for (MedicinesData medicine : manager.getAllMedicines()) {
//             System.out.println(medicine.getMedicineName() + " - " + medicine.getMedicineForm() + " - " + medicine.getMedicineDescription() + " - $" + medicine.getMedicinePrice() + " - " + medicine.getMedicineExpirationDate() + " - Quantity: " + medicine.getMedicineQuantity());
//         }
//     }
// }
