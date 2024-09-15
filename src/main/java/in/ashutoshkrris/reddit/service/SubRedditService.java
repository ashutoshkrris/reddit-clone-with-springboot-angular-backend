package in.ashutoshkrris.reddit.service;

import in.ashutoshkrris.reddit.dto.SubRedditDto;

import java.util.List;

public interface SubRedditService {

    SubRedditDto save(SubRedditDto subRedditDto);

    List<SubRedditDto> getAll();

    SubRedditDto getById(Long subRedditId);
}
