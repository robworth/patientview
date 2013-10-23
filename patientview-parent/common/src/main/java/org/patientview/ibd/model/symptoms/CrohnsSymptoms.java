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

package org.patientview.ibd.model.symptoms;

import org.patientview.ibd.model.enums.crohns.AbdominalPain;
import org.patientview.ibd.model.enums.crohns.Complication;
import org.patientview.ibd.model.enums.crohns.MassInTummy;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ibd_crohns_symptoms")
public class CrohnsSymptoms extends BaseSymptoms {

    @Column(nullable = false)
    private int openBowels;

    @Transient
    private AbdominalPain abdominalPain;

    @Transient
    private MassInTummy massInTummy;

    @Transient
    private Complication complication;

    @Override
    public Integer calculateScore() {
        int score = 0;

        score += openBowels;

        if (abdominalPain != null) {
            score += abdominalPain.getScore();
        }

        if (massInTummy != null) {
            score += massInTummy.getScore();
        }

        if (complication != null) {
            score += complication.getScore();
        }

        if (getFeeling() != null) {
            score += getFeeling().getScore();
        }

        return score;
    }

    public int getOpenBowels() {
        return openBowels;
    }

    public void setOpenBowels(int openBowels) {
        this.openBowels = openBowels;
    }

    public AbdominalPain getAbdominalPain() {
        return abdominalPain;
    }

    public void setAbdominalPain(AbdominalPain abdominalPain) {
        this.abdominalPain = abdominalPain;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "abdominal_pain_id", nullable = false)
    public int getAbdominalPainId() {
        if (abdominalPain != null) {
            return abdominalPain.getId();
        }

        return -1;
    }

    public void setAbdominalPainId(int id) {
        this.abdominalPain = AbdominalPain.getAbdominalPain(id);
    }

    public MassInTummy getMassInTummy() {
        return massInTummy;
    }

    public void setMassInTummy(MassInTummy massInTummy) {
        this.massInTummy = massInTummy;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "mass_in_tummy_id", nullable = false)
    public int getMassInTummyId() {
        if (massInTummy != null) {
            return massInTummy.getId();
        }

        return -1;
    }

    public void setMassInTummyId(int id) {
        this.massInTummy = MassInTummy.getMassInTummy(id);
    }

    public Complication getComplication() {
        return complication;
    }

    public void setComplication(Complication complication) {
        this.complication = complication;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "complication_id", nullable = false)
    public int getComplicationId() {
        if (complication != null) {
            return complication.getId();
        }

        return -1;
    }

    public void setComplicationId(int id) {
        this.complication = Complication.getComplication(id);
    }
}
