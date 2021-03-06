/**
 * *******************************************************
 * Copyright (C) 2013 catify <info@catify.com>
 * *******************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.catify.processengine.core.messages;

import java.util.Date;

import com.catify.processengine.core.nodes.EndEventNode;
import com.catify.processengine.core.services.ProcessInstanceCleansingService;

/**
 * The ArchiveMessage is sent from the {@link EndEventNode} node to the
 * {@link ProcessInstanceCleansingService}. It signalizes the
 * ProcessInstanceCleansingService to save a process instance to the archive.
 * 
 * @author christopher köster
 * 
 */
public class ArchiveMessage extends Message {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The unique process id. */
	private String uniqueProcessId;
	
	/** The process instance end time. */
	private Date processInstanceEndTime;
	
	/**
	 * Instantiates a new archive message.
	 *
	 * @param uniqueProcessId the unique process id
	 * @param processInstanceId the process instance id
	 * @param processInstanceEndTime the process instance end time
	 */
	public ArchiveMessage(String uniqueProcessId, String processInstanceId, Date processInstanceEndTime) {
		this.uniqueProcessId = uniqueProcessId;
		this.processInstanceId = processInstanceId;
		this.processInstanceEndTime = processInstanceEndTime;
	}

	/**
	 * Gets the unique process id.
	 * 
	 * @return the unique process id
	 */
	public String getUniqueProcessId() {
		return uniqueProcessId;
	}

	/**
	 * Sets the unique process id.
	 * 
	 * @param uniqueProcessId
	 *            the new unique process id
	 */
	public void setUniqueProcessId(String uniqueProcessId) {
		this.uniqueProcessId = uniqueProcessId;
	}

	public Date getEndTime() {
		return processInstanceEndTime;
	}

	public void setEndTime(Date endTime) {
		this.processInstanceEndTime = endTime;
	}
}
