/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.radar.service.alport.impl;

import org.patientview.model.Patient;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.radar.dao.alport.MedicineDao;
import org.patientview.radar.model.alport.Medicine;
import org.patientview.radar.service.alport.MedicineManager;

import java.util.List;

public class MedicineManagerImpl implements MedicineManager {

    private MedicineDao medicineDao;

    public void save(Medicine medicine) {
        medicineDao.save(medicine);
    }

    public void delete(Medicine medicine) {
        medicineDao.delete(medicine);
    }

    public Medicine get(Long id) {
        return medicineDao.get(id);
    }

    public List<Medicine> getMedicines(Patient patient) {
        if (patient.getNhsno() != null) {
            return medicineDao.getMedicinesByNhsNo(patient.getNhsno());
        }

        return null;
    }

    public List<Medicine> getMedicines(Patient patient, DiseaseGroup diseaseGroup) {
        if (patient.getNhsno() != null) {
            return medicineDao.getMedicinesByNhsNoAndDiseaseGroup(patient.getNhsno(), diseaseGroup);
        }

        return null;
    }

    public void setMedicineDao(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }
}
