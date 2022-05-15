package com.XmlRequestHandler.Task.Service;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TaskServices {

  final Logger logger = LoggerFactory.getLogger(TaskServices.class);

  //instead of repeating the logging part in each function
  public void logging(String param1, String param2)
  {
    if(param1 == null || param2 == null)
    {
      logger.error("There is a missing parameter");
    }
    else
      logger.info("Param1 set to {}. param2 is {}.", param1, param2);
  }

  public void func1(String param1, String param2){
    System.out.print("In func1\n");
    logging(param1,param2);
  }

  public void func2(String param1, String param2){
    System.out.print("In func2\n");
    logging(param1,param2);
  }

  public void func3(String param1, String param2){
    System.out.print("In func3\n");
    logging(param1,param2);
  }

}
