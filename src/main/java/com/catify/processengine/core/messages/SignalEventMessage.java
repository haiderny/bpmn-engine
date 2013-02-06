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

import akka.event.EventBus;
import scala.concurrent.duration.DurationConversions.Classifier;

/**
 * {@link Classifier} for the akka {@link EventBus}.
 * 
 * @author claus straube
 *
 */
public class SignalEventMessage {

	private String signalRef;
	
	public SignalEventMessage(String signalRef) {
		this.signalRef = signalRef;
	}

	public String getSignalRef() {
		return signalRef;
	}

	public void setSignalRef(String signalRef) {
		this.signalRef = signalRef;
	}
	
}