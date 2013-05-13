package com.worthsoln.service;

import com.worthsoln.patientview.model.JoinRequest;

import java.util.List;

public interface JoinRequestManager {

    void save(JoinRequest joinRequest);

    List<JoinRequest> getUsersJoinRequests();

}
