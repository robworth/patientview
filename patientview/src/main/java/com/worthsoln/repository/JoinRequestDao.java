package com.worthsoln.repository;

import com.worthsoln.patientview.model.JoinRequest;

import java.util.List;

public interface JoinRequestDao {

    void save(JoinRequest joinRequest);

    List<JoinRequest> getAll();

    List<JoinRequest> getJoinRequestsForUnitCodes(List<String> unitcodes);
}
