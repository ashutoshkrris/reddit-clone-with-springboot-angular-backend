package in.ashutoshkrris.reddit.controller;

import in.ashutoshkrris.reddit.dto.request.VoteRequestDto;
import in.ashutoshkrris.reddit.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteRequestDto voteRequest) {
        voteService.vote(voteRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
