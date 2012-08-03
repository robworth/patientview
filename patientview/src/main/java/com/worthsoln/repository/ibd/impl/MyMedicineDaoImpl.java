package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.medication.MyMedicine;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.MyMedicineDao;
import org.springframework.stereotype.Repository;

@Repository(value = "myMedicineDao")
public class MyMedicineDaoImpl extends AbstractHibernateDAO<MyMedicine> implements MyMedicineDao {

}
