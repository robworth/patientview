package com.solidstategroup.radar.web.dataproviders;

import com.solidstategroup.radar.model.filter.ProfessionalUserFilter;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import com.solidstategroup.radar.service.UserManager;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Iterator;
import java.util.List;

public class ProfessionalUserDataProvider implements IDataProvider<ProfessionalUser> {

    private UserManager userManager;
    private ProfessionalUserFilter userFilter;

    public ProfessionalUserDataProvider(UserManager userManager) {
        this.userManager = userManager;
        userFilter = new ProfessionalUserFilter();
    }

    public Iterator<? extends ProfessionalUser> iterator(int i, int i1) {
        int pageNumber = (i / i1) + 1;
        return getResults(pageNumber, i1).iterator();
    }

    public int size() {
        return getResults(-1, -1).size();
    }

    public IModel<ProfessionalUser> model(ProfessionalUser professionalUser) {
        return new Model<ProfessionalUser>(professionalUser);
    }

    public void detach() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private List<ProfessionalUser> getResults(int page, int resultsPerPage) {
        return userManager.getProfessionalUsers(userFilter, page, resultsPerPage);
    }

    public ProfessionalUserFilter getUserFilter() {
        return userFilter;
    }

    public void setAscending(boolean ascending) {
        userFilter.setReverse(ascending);
    }

}
