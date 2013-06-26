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

package org.patientview.service.impl;

import org.patientview.patientview.model.Letter;
import org.patientview.repository.LetterDao;
import org.patientview.service.LetterManager;
import org.patientview.service.SecurityUserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service(value = "letterManager")
public class LetterManagerImpl implements LetterManager {

    @Inject
    private LetterDao letterDao;

    @Inject
    private SecurityUserManager securityUserManager;

    @Override
    public Letter get(Long id) {
        return letterDao.get(id);
    }

    @Override
    public void save(Letter letter) {
        letterDao.save(letter);
    }

    @Override
    public List<Letter> get(String username) {
        return letterDao.get(username, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<Letter> getAll() {
        return letterDao.getAll();
    }

    @Override
    public void delete(String nhsno, String unitcode, Date date) {
        letterDao.delete(nhsno, unitcode, date);
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        letterDao.delete(nhsno, unitcode);
    }

    @Override
    public void delete(Long id) {
        letterDao.delete(id);
    }
}
