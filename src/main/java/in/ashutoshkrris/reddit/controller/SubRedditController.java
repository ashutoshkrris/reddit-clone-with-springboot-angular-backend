package in.ashutoshkrris.reddit.controller;

import in.ashutoshkrris.reddit.dto.SubRedditDto;
import in.ashutoshkrris.reddit.service.impl.SubRedditServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
public class SubRedditController {

    private final SubRedditServiceImpl subRedditService;

    @PostMapping
    public ResponseEntity<SubRedditDto> createSubReddit(@RequestBody SubRedditDto subRedditDto) {
        SubRedditDto savedSubReddit = subRedditService.save(subRedditDto);
        return new ResponseEntity<>(savedSubReddit, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubRedditDto>> getAllSubReddits() {
        List<SubRedditDto> subReddits = subRedditService.getAll();
        return new ResponseEntity<>(subReddits, HttpStatus.OK);
    }

    @GetMapping("/{subRedditId}")
    public ResponseEntity<SubRedditDto> getSubRedditById(@PathVariable Long subRedditId) {
        SubRedditDto subReddit = subRedditService.getById(subRedditId);
        return new ResponseEntity<>(subReddit, HttpStatus.OK);
    }

}
