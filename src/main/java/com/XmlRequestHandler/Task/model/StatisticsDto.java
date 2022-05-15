package com.XmlRequestHandler.Task.model;

public class StatisticsDto {

  private int NumberOfRequests;
  private int NumberOFIssues;

  public int getNumberOfRequests() {
    return NumberOfRequests;
  }

  public void setNumberOfRequests(int numberOfRequests) {
    NumberOfRequests = numberOfRequests;
  }

  public int getNumberOFIssues() {
    return NumberOFIssues;
  }

  public void setNumberOFIssues(int numberOFIssues) {
    NumberOFIssues = numberOFIssues;
  }

  public StatisticsDto(int numberOfRequests, int numberOFIssues) {
    NumberOfRequests = numberOfRequests;
    NumberOFIssues = numberOFIssues;
  }
}
