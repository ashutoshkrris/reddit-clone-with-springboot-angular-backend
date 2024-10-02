package in.ashutoshkrris.reddit.service;

import in.ashutoshkrris.reddit.dto.request.VoteRequestDto;

public interface VoteService {
    void vote(VoteRequestDto voteRequest);
}
