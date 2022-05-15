package com.XmlRequestHandler.Task.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.XmlRequestHandler.Task.model.Request;
import com.XmlRequestHandler.Task.model.Response;

import com.XmlRequestHandler.Task.dao.RequestDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class RequestService{
  final Logger logger = LoggerFactory.getLogger(RequestService.class);

  public final RequestDao requestDao;
  public final TaskServices taskServices;

  @Autowired
  public RequestService(RequestDao requestDao, TaskServices taskServices) {
    this.requestDao = requestDao;
    this.taskServices = taskServices;
  }
  public List<Request> list(){
      return  requestDao.list();
  }
  public Request saveOrUpdate(Request request){
    //Choosing which method to use according  to request <Function> value
    switch (request.getFunction())
    {
    case "func1":
      taskServices.func1(request.getParam1(),request.getParam2());
      break;
    case "func2":
      taskServices.func2(request.getParam1(),request.getParam2());
      break;
    case "func3":
      taskServices.func3(request.getParam1(),request.getParam2());
      break;
    default:
      logger.error("Not a predefined function");
    }
    return requestDao.saveOrUpdate(request);
  }

  public List<Request> findByTimeFrame(String timeFrame) {
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

    return requestDao.findByTimeFrame(date);
  }
}
