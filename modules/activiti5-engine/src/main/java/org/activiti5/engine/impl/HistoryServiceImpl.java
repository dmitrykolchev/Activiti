/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti5.engine.impl;

import java.util.List;

import org.activiti5.engine.HistoryService;
import org.activiti5.engine.history.HistoricActivityInstanceQuery;
import org.activiti5.engine.history.HistoricDetailQuery;
import org.activiti5.engine.history.HistoricIdentityLink;
import org.activiti5.engine.history.HistoricProcessInstanceQuery;
import org.activiti5.engine.history.HistoricTaskInstanceQuery;
import org.activiti5.engine.history.HistoricVariableInstanceQuery;
import org.activiti5.engine.history.NativeHistoricActivityInstanceQuery;
import org.activiti5.engine.history.NativeHistoricDetailQuery;
import org.activiti5.engine.history.NativeHistoricProcessInstanceQuery;
import org.activiti5.engine.history.NativeHistoricTaskInstanceQuery;
import org.activiti5.engine.history.NativeHistoricVariableInstanceQuery;
import org.activiti5.engine.history.ProcessInstanceHistoryLogQuery;
import org.activiti5.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti5.engine.impl.cmd.DeleteHistoricProcessInstanceCmd;
import org.activiti5.engine.impl.cmd.DeleteHistoricTaskInstanceCmd;
import org.activiti5.engine.impl.cmd.GetHistoricIdentityLinksForTaskCmd;

/**
 * @author Tom Baeyens
 * @author Bernd Ruecker (camunda)
 * @author Christian Stettler
 */
public class HistoryServiceImpl extends ServiceImpl implements HistoryService {
	
	public HistoryServiceImpl() {
		
	}
	
	public HistoryServiceImpl(ProcessEngineConfigurationImpl processEngineConfiguration) {
		super(processEngineConfiguration);
	}

  public HistoricProcessInstanceQuery createHistoricProcessInstanceQuery() {
    return new HistoricProcessInstanceQueryImpl(commandExecutor);
  }

  public HistoricActivityInstanceQuery createHistoricActivityInstanceQuery() {
    return new HistoricActivityInstanceQueryImpl(commandExecutor);
  }

  public HistoricTaskInstanceQuery createHistoricTaskInstanceQuery() {
    return new HistoricTaskInstanceQueryImpl(commandExecutor, processEngineConfiguration.getDatabaseType());
  }

  public HistoricDetailQuery createHistoricDetailQuery() {
    return new HistoricDetailQueryImpl(commandExecutor);
  }

  @Override
  public NativeHistoricDetailQuery createNativeHistoricDetailQuery() {
    return new NativeHistoricDetailQueryImpl(commandExecutor);
  }

  public HistoricVariableInstanceQuery createHistoricVariableInstanceQuery() {
    return new HistoricVariableInstanceQueryImpl(commandExecutor);
  }

  @Override
  public NativeHistoricVariableInstanceQuery createNativeHistoricVariableInstanceQuery() {
    return new NativeHistoricVariableInstanceQueryImpl(commandExecutor);
  }

  public void deleteHistoricTaskInstance(String taskId) {
    commandExecutor.execute(new DeleteHistoricTaskInstanceCmd(taskId));
  }

  public void deleteHistoricProcessInstance(String processInstanceId) {
    commandExecutor.execute(new DeleteHistoricProcessInstanceCmd(processInstanceId));
  }

  public NativeHistoricProcessInstanceQuery createNativeHistoricProcessInstanceQuery() {
    return new NativeHistoricProcessInstanceQueryImpl(commandExecutor);
  }

  public NativeHistoricTaskInstanceQuery createNativeHistoricTaskInstanceQuery() {
    return new NativeHistoricTaskInstanceQueryImpl(commandExecutor);
  }

  public NativeHistoricActivityInstanceQuery createNativeHistoricActivityInstanceQuery() {
    return new NativeHistoricActivityInstanceQueryImpl(commandExecutor);
  }
  
  @Override
  public List<HistoricIdentityLink> getHistoricIdentityLinksForProcessInstance(String processInstanceId) {
    return commandExecutor.execute(new GetHistoricIdentityLinksForTaskCmd(null, processInstanceId));
  }
  
  @Override
  public List<HistoricIdentityLink> getHistoricIdentityLinksForTask(String taskId) {
    return commandExecutor.execute(new GetHistoricIdentityLinksForTaskCmd(taskId, null));
  }
  
  @Override
  public ProcessInstanceHistoryLogQuery createProcessInstanceHistoryLogQuery(String processInstanceId) {
  	return new ProcessInstanceHistoryLogQueryImpl(commandExecutor, processInstanceId);
  }
  
}
