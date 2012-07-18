package com.worthsoln.forum;

import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.model.User;
import com.worthsoln.utils.LegacySpringUtils;
import net.jforum.util.legacy.clickstream.ClickstreamFilter;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Check for screen name of user and redirect if one isn't set
 */
public class RpvForumFilter extends ClickstreamFilter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String screenName = null;
        try {
            // Check whether screen name is set
            String username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Expression.like("username", username));
            User user = (User) criteria.uniqueResult();
            screenName = user.getScreenname();
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            try {
                HibernateUtil.closeSession();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }

        if (screenName != null && screenName.trim().length() > 0) {
            super.doFilter(request, response, chain);
        } else {
            request.getRequestDispatcher("/setscreenname.do").forward(request, response);
        }
    }
}
