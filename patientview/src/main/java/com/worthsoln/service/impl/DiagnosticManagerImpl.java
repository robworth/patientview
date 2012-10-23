package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Diagnostic;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.enums.DiagnosticType;
import com.worthsoln.repository.DiagnosticDao;
import com.worthsoln.service.DiagnosticManager;
import com.worthsoln.service.UserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Service(value = "diagnosticManager")
public class DiagnosticManagerImpl implements DiagnosticManager {

    @Inject
    private DiagnosticDao diagnosticDao;

    @Inject
    private UserManager userManager;

    @Override
    public Diagnostic get(String nhsno) {
        return diagnosticDao.get(nhsno);
    }

    @Override
    public void save(Diagnostic diagnostic) {
        diagnosticDao.save(diagnostic);
    }

    @Override
    public List<Diagnostic> getForUser(User user, DiagnosticType diagnosticType) {
        // get all nhs nos this user is known as for this tenancy to get there diagnostic results
        if (user != null) {
            List<UserMapping> mappings = userManager.getUserMappings(user.getUsername());
            Set<String> nhsNos = new HashSet<String>();
            for (UserMapping userMapping : mappings) {
                nhsNos.add(userMapping.getNhsno());
            }

            return diagnosticDao.get(nhsNos, diagnosticType);
        }

        return null;
    }
}
