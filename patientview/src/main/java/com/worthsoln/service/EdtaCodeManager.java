package com.worthsoln.service;

import com.worthsoln.patientview.model.EdtaCode;

import java.util.List;

/**
 *
 */
public interface EdtaCodeManager {

    EdtaCode getEdtaCode(String edtaCode);

    void save(EdtaCode edtaCode);

    void delete(String edtaCode);

    List<EdtaCode> get(String linkType);
}
