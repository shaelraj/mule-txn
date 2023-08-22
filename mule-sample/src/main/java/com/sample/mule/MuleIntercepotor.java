package com.sample.mule;

import java.util.List;

import org.mule.runtime.api.artifact.Registry;
import org.mule.runtime.api.event.Event;
import org.mule.runtime.api.message.Message;
import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.core.api.construct.Flow;
import org.mule.runtime.core.api.event.CoreEvent;
import org.mule.runtime.core.api.event.EventContextFactory;
import org.mule.runtime.core.api.processor.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;


public class MuleIntercepotor  {
	
	private static final Logger log = LoggerFactory.getLogger(MuleIntercepotor.class);

	@Autowired
	private Registry registry;
	
	@Autowired 
    private PlatformTransactionManager transactionManager;

	public Flow getFlow(String flowName) {
		return (Flow) registry.lookupByName(flowName).orElse(null);
	}
	
	 public Event createEvent(Object payload, Flow flow) {
         Message msg = Message.builder().value(payload).build();
         CoreEvent event = CoreEvent.builder(EventContextFactory.create(flow,
               org.mule.runtime.dsl.api.component.config.DefaultComponentLocation.fromSingleComponent("add-location")))
                      .message(msg).build();
         return event;
  }

  public Event createEvent(String correlationId, Object payload, Flow flow) {
         Message msg = Message.builder().value(payload).build();
         CoreEvent event = CoreEvent
                      .builder(EventContextFactory.create(flow,
                                  org.mule.runtime.dsl.api.component.config.DefaultComponentLocation
                                               .fromSingleComponent("add-location")))
                      .addVariable("coorelationId", correlationId).message(msg).build();
         return event;
  }
  
  public void executeMuleFlow(Object dtoObject, String flowName) {
	  log.info("executeMuleFlow {}", Thread.currentThread().getName());
//	  TransactionSynchronizationManager.initSynchronization();
      TypedValue<Object> returnValue = null;
//      Log.info("Executing mule-4 flow - " + flowName);
      try {
            Flow flow = getFlow(flowName);
            log.info("flow {}", flow.getProcessingStrategy());
            List<Processor> processors = flow.getProcessors();
//            processors.forEach(p -> p );
            CoreEvent prevEvent = null;
            for(int i=1; i< processors.size(); i++) {
            	log.info(processors.get(i).toString());
            }
           
           // CoreEvent event = (CoreEvent) createEvent(dtoObject, flow);
          //  CoreEvent result = flow.process(event);
           // returnValue = result.getMessage().getPayload();
//            Log.info("Returned from flow - " + returnValue.getValue().toString());
      } catch (Exception exp) {
//            Log.error("Failed to invoke flow - " + exp);
      }
}

}
