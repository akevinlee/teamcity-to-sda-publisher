/*
 * Copyright 2000-2010 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.serena;


import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.*;
import org.jetbrains.annotations.*;

public class SerenaDeployBuildRunner implements AgentBuildRunner, AgentBuildRunnerInfo
{
    @NotNull
    public BuildProcess createBuildProcess(@NotNull final AgentRunningBuild runningBuild, @NotNull final BuildRunnerContext context) throws RunBuildException
    {
        return new SerenaDeployBuildProcess(runningBuild, context);
    }

    @NotNull
    public AgentBuildRunnerInfo getRunnerInfo()
    {
        return this;
    }

    @NotNull
    public String getType()
    {
        return PluginConstants.RunType;
    }

    public boolean canRun(@NotNull final BuildAgentConfiguration agentConfiguration)
    {
        return true;
    }
}
