package com.tlms.bpm.application;

import org.activiti.rest.common.application.ActivitiRestApplication;
import org.activiti.rest.common.filter.JsonpFilter;
import org.activiti.rest.diagram.application.DiagramServicesInit;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * 整合diagram viewer
 * @author tom
 *
 */
public class TraceRestApplication extends ActivitiRestApplication {
  
  public TraceRestApplication() {
    super();
  }
  /**
   * Creates a root Restlet that will receive all incoming calls.
   */
  @Override
  public synchronized Restlet createInboundRoot() {
    Router router = new Router(getContext());
    DiagramServicesInit.attachResources(router);
    JsonpFilter jsonpFilter = new JsonpFilter(getContext());
    jsonpFilter.setNext(router);
    return jsonpFilter;
  }

}
