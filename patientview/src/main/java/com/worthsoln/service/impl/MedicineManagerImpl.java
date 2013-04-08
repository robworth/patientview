package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Medicine;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.repository.MedicineDao;
import com.worthsoln.service.MedicineManager;
import com.worthsoln.service.UserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Service(value = "medicineManager")
public class MedicineManagerImpl implements MedicineManager {

    @Inject
    private UserManager userManager;

    @Inject
    private MedicineDao medicineDao;

    @Override
    public Medicine get(Long id) {
        return medicineDao.get(id);
    }

    @Override
    public List<Medicine> getUserMedicines(User user) {

        UserMapping userMapping = userManager.getUserMappingPatientEntered(user);

        List<Medicine> medicines = null;
        if (userMapping != null) {
            medicines = medicineDao.getMedicines(userMapping.getNhsno());
        }

        return medicines;
    }

    @Override
    public void save(Medicine medicine) {
        medicineDao.save(medicine);
    }

    @Override
    public List<Medicine> getAll() {
        return medicineDao.getAll();
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        medicineDao.delete(nhsno, unitcode);
    }
}
