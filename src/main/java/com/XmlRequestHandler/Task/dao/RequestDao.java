package com.XmlRequestHandler.Task.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.XmlRequestHandler.Task.model.Request;

@Repository
public class RequestDao extends HibernateDaoSupport {

    @Autowired
    public RequestDao(SessionFactory sessionFactory){
      this.setSessionFactory(sessionFactory);
    }

    @Transactional
    public Request saveOrUpdate(Request request) {
      Session session = getSessionFactory().getCurrentSession();
      session.saveOrUpdate(request);
      return request;
    }
  public List<Request> list() {
    DetachedCriteria cr = DetachedCriteria.forClass(Request.class);
    List<Request> requestList = (List<Request>) getHibernateTemplate().findByCriteria(cr);
    return requestList;
  }

  public List<Request> findByTimeFrame(Date timeFrame) {
    //Query to select from Requests That its Creation Date is greater than the given timeFrame
    String hql = "from Request where createdAt > " + "'"+timeFrame+"'";
    Query query = getSessionFactory().getCurrentSession().createQuery(hql);
   return query.list();
  }
}
