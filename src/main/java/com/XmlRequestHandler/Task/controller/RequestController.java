package com.XmlRequestHandler.Task.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.XmlRequestHandler.Task.Service.RequestService;
import com.XmlRequestHandler.Task.Service.ResponseService;
import com.XmlRequestHandler.Task.model.Request;
import com.XmlRequestHandler.Task.model.Response;
import com.XmlRequestHandler.Task.model.StatisticsDto;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RequestController {

  final RequestService requestService;
  final ResponseService responseService;

  @Autowired
  public RequestController(RequestService requestService, ResponseService responseService) {
    this.responseService = responseService;
    this.requestService = requestService;
  }

  @PostMapping("/request")
  public ResponseEntity<Response> createRequest(@RequestBody Request request) {
    try {
      //Saving Request to Database
      requestService.saveOrUpdate(new Request(request.getId(),request.getFunction(), request.getParam1(), request.getParam2(),new Date()));
      //Saving Response and returning it
      Response _response = responseService.saveOrUpdate(new Response(request.getId(),request.getFunction(),"OK", new Date()));
      return new ResponseEntity<>(_response, HttpStatus.CREATED);
    } catch (Exception e) {
      //Returning Failed Status in case of missing parameters
      Response _response = responseService.saveOrUpdate(new Response(request.getId(),request.getFunction(),"Failed",new Date()));
      return new ResponseEntity<>(_response, HttpStatus.EXPECTATION_FAILED);
    }
  }

  //Getting Number of issues and Requests according to the required time frame (day,hour,min)
  @GetMapping("/Admin")
  public ResponseEntity<StatisticsDto> getRequestsByTimeFrame(@RequestParam(required = false) String timeFrame) {
    try {
      List<Request> requestList = new ArrayList<Request>();
      List<Response> issuesList = new ArrayList<Response>();

      if (timeFrame == null) {
        //If there is not specific time frame this will return All requests and issues
        requestService.list().forEach(requestList::add);
        responseService.list().forEach(issuesList::add);
      } else
      {
        requestService.findByTimeFrame(timeFrame).forEach(requestList::add);
        responseService.findIssues(timeFrame).forEach(issuesList::add);
      }

      //Returning NO_CONTENT status if nothing returned from the Get request
      if (requestList.isEmpty()&& issuesList.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      //StatisticsDto model used to display the number of issues and succeeded requests
      StatisticsDto statisticsDto = new StatisticsDto(requestList.size(),issuesList.size());
      return new ResponseEntity<>(statisticsDto, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
