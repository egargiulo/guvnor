package org.guvnor.rest.backend.cmd;

import org.guvnor.rest.backend.JobRequestHelper;
import org.guvnor.rest.client.JobRequest;
import org.guvnor.rest.client.JobResult;
import org.guvnor.rest.client.JobStatus;
import org.guvnor.rest.client.RemoveRepositoryRequest;
import org.kie.internal.executor.api.CommandContext;

public class RemoveRepositoryCmd extends AbstractJobCommand {

    @Override
    public JobResult internalExecute(CommandContext ctx, JobRequest request) throws Exception {
        JobRequestHelper helper = getHelper(ctx);
        RemoveRepositoryRequest jobRequest = (RemoveRepositoryRequest) request;

        JobResult result = null;
        try { 
            result = helper.removeRepository( jobRequest.getJobId(), jobRequest.getRepositoryName() );
        } finally { 
            JobStatus status = result != null ? result.getStatus() : JobStatus.SERVER_ERROR;
            logger.info( "-----removeRepository--- , repository name: {} [{}]", 
                    jobRequest.getRepositoryName(), status);
        }
        return result;
    }
}
