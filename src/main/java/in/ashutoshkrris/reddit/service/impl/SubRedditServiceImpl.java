package in.ashutoshkrris.reddit.service.impl;

import in.ashutoshkrris.reddit.dto.SubRedditDto;
import in.ashutoshkrris.reddit.exceptions.RedditException;
import in.ashutoshkrris.reddit.mapper.SubRedditMapper;
import in.ashutoshkrris.reddit.model.SubReddit;
import in.ashutoshkrris.reddit.repository.SubRedditRepository;
import in.ashutoshkrris.reddit.service.SubRedditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SubRedditServiceImpl implements SubRedditService {

    private final SubRedditRepository subRedditRepository;
    private final SubRedditMapper subRedditMapper;

    @Override
    @Transactional
    public SubRedditDto save(SubRedditDto subRedditDto) {
        SubReddit subReddit = subRedditRepository.save(subRedditMapper.mapDtoToSubReddit(subRedditDto));
        subRedditDto.setId(subReddit.getSubRedditId());
        return subRedditDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubRedditDto> getAll() {
        return subRedditRepository.findAll()
                .stream()
                .map(subRedditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubRedditDto getById(Long subRedditId) {
        SubReddit subReddit = subRedditRepository.findBySubRedditId(subRedditId).orElseThrow(() -> new RedditException("SubReddit not found with id: " + subRedditId));
        return subRedditMapper.mapSubredditToDto(subReddit);
    }
}
