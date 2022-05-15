package com.XmlRequestHandler.Task.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.XmlRequestHandler.Task.model.Request;
import com.XmlRequestHandler.Task.model.Response;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ResponseDao extends HibernateDaoSupport {

   @Autowired
   public ResponseDao(SessionFactory sessionFactory){
      this.setSessionFactory(sessionFactory);
    }

  public List<Response> list() {
    DetachedCriteria cr = DetachedCriteria.forClass(Request.class);
    List<Response> responseList = (List<Response>) getHibernateTemplate().findByCriteria(cr);
    return responseList;
  }

  @Transactional
  public Response saveOrUpdate(Response response) {
    Session session = getSessionFactory().getCurrentSession();
    session.saveOrUpdate(response);
    return response;
  }

  public List<Response> findIssues(Date timeFrame) {
    //Query to select from FAILED Response That its Creation Date is greater than the given timeFrame
    String hql = "from Response where createdAt > " + "'"+timeFrame+"'" + "AND status = 'Failed'";
    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
    return query.list();
  }
}
