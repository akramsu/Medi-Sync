package MediSync.MediSync_Model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.NoTypePermission;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicinesDataManager {
    private List<MedicinesData> medicinesList;
    private XStream xstream;
    private static final String FILE_PATH = "src/MediSync/MediSync_Model/medicines.xml";

    public MedicinesDataManager() {
        medicinesList = new ArrayList<>();
        xstream = new XStream(new StaxDriver());
        configureXStreamSecurity();
        loadMedicinesData();
    }

    private void configureXStreamSecurity() {
        xstream.addPermission(NoTypePermission.NONE);
        xstream.allowTypesByWildcard(new String[] {
            "MediSync.**",
            "java.util.List",
            "java.util.ArrayList"
        });
        xstream.alias("medicine", MedicinesData.class);
    }

    public void addMedicine(MedicinesData medicine) {
        boolean medicineExists = false;
        for (int i = 0; i < medicinesList.size(); i++) {
            if (medicinesList.get(i).getMedicineName().equals(medicine.getMedicineName())) {
                medicinesList.set(i, medicine);
                medicineExists = true;
                break;
            }
        }
        if (!medicineExists) {
            medicinesList.add(medicine);
        }
        saveMedicinesData();
    }

    public void updateMedicine(MedicinesData medicine) {
        addMedicine(medicine);
    }

    public void removeMedicine(String medicineName) {
        medicinesList.removeIf(medicine -> medicine.getMedicineName().equals(medicineName));
        saveMedicinesData();
    }

    public List<MedicinesData> getAllMedicines() {
        return medicinesList;
    }

    private void saveMedicinesData() {
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH);
             OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8")) {
            xstream.toXML(medicinesList, writer);
            System.out.println("Medicines data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving medicines data: " + e.getMessage());
        }
    }

    private void loadMedicinesData() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 InputStreamReader reader = new InputStreamReader(fis, "UTF-8")) {
                medicinesList = (List<MedicinesData>) xstream.fromXML(reader);
                System.out.println("Medicines data loaded successfully.");
            } catch (IOException e) {
                System.err.println("Error loading medicines data: " + e.getMessage());
            }
        } else {
            System.err.println("No existing medicines data file found. A new one will be created upon saving data.");
        }
    }
}
