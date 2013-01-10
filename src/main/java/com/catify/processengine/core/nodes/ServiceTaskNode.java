package com.catify.processengine.core.nodes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

import com.catify.processengine.core.data.dataobjects.DataObjectService;
import com.catify.processengine.core.data.model.NodeInstaceStates;
import com.catify.processengine.core.messages.ActivationMessage;
import com.catify.processengine.core.messages.DeactivationMessage;
import com.catify.processengine.core.messages.TriggerMessage;
import com.catify.processengine.core.nodes.eventdefinition.MessageEventDefinition_InOut;
import com.catify.processengine.core.processdefinition.jaxb.TMessageIntegration;
import com.catify.processengine.core.services.ActorReferenceService;
import com.catify.processengine.core.services.NodeInstanceMediatorService;

/**
 * The ServiceTaskNode is the supervisor for the {@link ServiceTaskInstance}, which implements the actual bpmn service task. 
 * The ServiceTaskNode instantiates the synchronous ServiceTaskInstances. 
 */
@Configurable
public class ServiceTaskNode extends Task {

	/** The node factory. */
	@Autowired
	private NodeFactory nodeFactory;
	
	/** The in out message integration. */
	private TMessageIntegration messageIntegrationInOut;
	
	/**
	 * Instantiates a new service task.
	 */
	public ServiceTaskNode() {

	}

	/**
	 * Instantiates a new service task node.
	 *
	 * @param uniqueProcessId the process id
	 * @param uniqueFlowNodeId the unique flow node id
	 * @param outgoingNodes the outgoing nodes
	 * @param messageIntegrationInOut the message integration in out
	 * @param dataObjectHandling the data object handling
	 */
	public ServiceTaskNode(String uniqueProcessId, String uniqueFlowNodeId,
			List<ActorRef> outgoingNodes,
			TMessageIntegration messageIntegrationInOut, DataObjectService dataObjectHandling) {
		this.setUniqueProcessId(uniqueProcessId);
		this.setUniqueFlowNodeId(uniqueFlowNodeId);
		this.setOutgoingNodes(outgoingNodes);
		this.setNodeInstanceMediatorService(new NodeInstanceMediatorService(
				uniqueProcessId, uniqueFlowNodeId));
		this.setMessageIntegrationInOut(messageIntegrationInOut);
		
		// all service task instances share this service object, it might therefore need some kind of synchronization
		this.setDataObjectHandling(dataObjectHandling);
	}

	@Override
	protected void activate(ActivationMessage message) {
		
		ActorRef serviceTaskInstance = this.getContext().actorOf(new Props(
				new UntypedActorFactory() {
					private static final long serialVersionUID = 1L;

					// create an instance of a (synchronous) service worker
					public UntypedActor create() {
							return nodeFactory.createServiceTaskWorkerNode(getUniqueProcessId(), 
									getUniqueFlowNodeId(), getOutgoingNodes(), 
									new MessageEventDefinition_InOut(getUniqueProcessId(), 
											getUniqueFlowNodeId(),
											messageIntegrationInOut), 
									getDataObjectHandling());
					}
				}), ActorReferenceService.getAkkaComplientString(message.getProcessInstanceId()));
		LOG.debug(String.format("Service task craeted %s --> resulting akka object: %s", this.getClass(),
				serviceTaskInstance.toString()));
		
		this.sendMessageToNodeActor(message, serviceTaskInstance);
	}

	@Override
	protected void deactivate(DeactivationMessage message) {		
		// if a service task instance is still active and waiting, stop it (invokes akka stop hooks) 
		this.getContext().stop(this.getContext().actorFor(message.getProcessInstanceId()));
		
		this.getNodeInstanceMediatorService().setNodeInstanceEndTime(message.getProcessInstanceId(), new Date());
		
		this.getNodeInstanceMediatorService().setState(
				message.getProcessInstanceId(),
				NodeInstaceStates.DEACTIVATED_STATE);
		
		this.getNodeInstanceMediatorService().persistChanges();
	}

	@Override
	protected void trigger(TriggerMessage message) {
		LOG.warn(String.format("Reaction to %s not implemented in %s. Please check your process.", message.getClass().getSimpleName(), this.getSelf()));
	}

	/**
	 * Gets the in out message integration .
	 *
	 * @return the message integration in out
	 */
	public TMessageIntegration getMessageIntegrationInOut() {
		return messageIntegrationInOut;
	}

	/**
	 * Sets the in out message integration.
	 *
	 * @param messageIntegrationInOut the new message integration in out
	 */
	public void setMessageIntegrationInOut(TMessageIntegration messageIntegrationInOut) {
		this.messageIntegrationInOut = messageIntegrationInOut;
	}
}