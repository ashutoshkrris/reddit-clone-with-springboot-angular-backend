package in.ashutoshkrris.reddit.mapper;

import in.ashutoshkrris.reddit.dto.SubRedditDto;
import in.ashutoshkrris.reddit.model.Post;
import in.ashutoshkrris.reddit.model.SubReddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubRedditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    @Mapping(target = "id", source = "subRedditId")
    SubRedditDto mapSubRedditToDto(SubReddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "subRedditId", source = "id")
    SubReddit mapDtoToSubReddit(SubRedditDto subredditDto);

}