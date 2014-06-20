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

import java.util.concurrent.*;
import jetbrains.buildServer.*;
import jetbrains.buildServer.agent.*;
import org.jetbrains.annotations.*;

public abstract class FutureBasedBuildProcess implements BuildProcess, Callable<BuildFinishedStatus>
{
    private Future<BuildFinishedStatus> myFuture;

    public void start() throws RunBuildException
    {
        try
        {
            myFuture = Executors.newSingleThreadExecutor().submit(this);
        } catch (final RejectedExecutionException e)
        {
            throw new RunBuildException(e);
        }
    }

    public boolean isInterrupted()
    {
        return myFuture.isCancelled() && isFinished();
    }

    public boolean isFinished()
    {
        return myFuture.isDone();
    }

    public void interrupt()
    {
        myFuture.cancel(true);
    }

    @NotNull
    public BuildFinishedStatus waitFor() throws RunBuildException
    {
        try
        {
            final BuildFinishedStatus status = myFuture.get();
            return status;
        } catch (final InterruptedException e)
        {
            throw new RunBuildException(e);
        } catch (final ExecutionException e)
        {
            throw new RunBuildException(e);
        } catch (final CancellationException e)
        {
            return BuildFinishedStatus.INTERRUPTED;
        }
    }
}
