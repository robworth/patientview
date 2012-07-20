package com.worthsoln.repository;

import com.worthsoln.patientview.model.EdtaCode;

import java.util.List;

/**
 *
 */
public interface EdtaCodeDao {

    void save(EdtaCode edtaCode);

    void delete(String edtaCode);

    List<EdtaCode> get(String linkType);
}
