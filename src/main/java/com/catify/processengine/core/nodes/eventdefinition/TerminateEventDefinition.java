package com.catify.processengine.core.nodes.eventdefinition;

import akka.actor.ActorRef;

import com.catify.processengine.core.messages.ActivationMessage;
import com.catify.processengine.core.messages.DeactivationMessage;
import com.catify.processengine.core.messages.TriggerMessage;
import com.catify.processengine.core.services.ProcessInstanceMediatorService;

public class TerminateEventDefinition implements EventDefinition {


	private ProcessInstanceMediatorService processInstanceMediatorService;

	private String uniqueProcessId;
	private String uniqueFlowNodeId;
	private ActorRef actorRef;
	
	/**
	 * Instantiates a new terminate event definition.
	 *
	 * @param uniqueProcessId the unique process id
	 * @param uniqueFlowNodeId the unique flow node id
	 * @param actorRef the actor ref
	 */
	public TerminateEventDefinition(
			String uniqueProcessId, String uniqueFlowNodeId, ActorRef actorRef) {
		super();
		this.uniqueProcessId = uniqueProcessId;
		this.uniqueFlowNodeId = uniqueFlowNodeId;
		this.actorRef = actorRef;
		
//		this.processInstanceMediatorService = new ProcessInstanceMediatorService();
	}

	/* (non-Javadoc)
	 * @see com.catify.processengine.core.nodes.eventdefinition.EventDefinition#acitivate(com.catify.processengine.core.messages.ActivationMessage)
	 */
	@Override
	public void acitivate(ActivationMessage message) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.catify.processengine.core.nodes.eventdefinition.EventDefinition#deactivate(com.catify.processengine.core.messages.DeactivationMessage)
	 */
	@Override
	public void deactivate(DeactivationMessage message) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.catify.processengine.core.nodes.eventdefinition.EventDefinition#trigger(com.catify.processengine.core.messages.TriggerMessage)
	 */
	@Override
	public void trigger(TriggerMessage message) {
		// TODO Auto-generated method stub
		
	}


	public ProcessInstanceMediatorService getProcessInstanceMediatorService() {
		return processInstanceMediatorService;
	}

	public void setProcessInstanceMediatorService(
			ProcessInstanceMediatorService processInstanceMediatorService) {
		this.processInstanceMediatorService = processInstanceMediatorService;
	}

	public String getUniqueProcessId() {
		return uniqueProcessId;
	}

	public void setUniqueProcessId(String uniqueProcessId) {
		this.uniqueProcessId = uniqueProcessId;
	}

	public String getUniqueFlowNodeId() {
		return uniqueFlowNodeId;
	}

	public void setUniqueFlowNodeId(String uniqueFlowNodeId) {
		this.uniqueFlowNodeId = uniqueFlowNodeId;
	}

	public ActorRef getActorRef() {
		return actorRef;
	}

	public void setActorRef(ActorRef actorRef) {
		this.actorRef = actorRef;
	}
}