package in.ashutoshkrris.reddit.mapper;

import in.ashutoshkrris.reddit.dto.SubRedditDto;
import in.ashutoshkrris.reddit.model.SubReddit;
import org.springframework.stereotype.Component;

@Component
public class SubRedditMapper {

    public SubRedditDto mapSubredditToDto(SubReddit subReddit) {
        return SubRedditDto.builder()
                .id(subReddit.getSubRedditId())
                .name(subReddit.getName())
                .description(subReddit.getDescription())
                .numberOfPosts(subReddit.getPosts().size())
                .build();
    }

    public SubReddit mapDtoToSubReddit(SubRedditDto subRedditDto) {
        return SubReddit.builder()
                .subRedditId(subRedditDto.getId())
                .name(subRedditDto.getName())
                .description(subRedditDto.getDescription())
                .build();
    }

}