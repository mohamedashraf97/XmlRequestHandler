package com.XmlRequestHandler.Task.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.XmlRequestHandler.Task.model.Response;
import com.XmlRequestHandler.Task.dao.ResponseDao;

@Service
@Transactional
public class ResponseService{

  public final ResponseDao responseDao;

  @Autowired
  public ResponseService(ResponseDao responseDao) {
    this.responseDao = responseDao;
  }

  public List<Response> list(){
    return  responseDao.list();
  }

  public Response saveOrUpdate(Response response){
    return responseDao.saveOrUpdate(response);
  }

  public List<Response> findIssues(String timeFrame) {
    Date date = null;
    Timestamp ts;
    //Choosing which TimeFrame to use and preparing the date variable that will be used later in the Dao as a condition to the Query
    switch (timeFrame){
    case "min":
      ts=new Timestamp(System.currentTimeMillis()- 1*60*1000);
      date=ts;
      break;
    case "hour":
      ts=new Timestamp(System.currentTimeMillis()- 60*60*1000);
      date=ts;
      break;
    case "day":
      ts=new Timestamp(System.currentTimeMillis()- 24*60*60*1000);
      date=ts;
      break;
    default:
      break;
    }

    return responseDao.findIssues(date);
  }
}
