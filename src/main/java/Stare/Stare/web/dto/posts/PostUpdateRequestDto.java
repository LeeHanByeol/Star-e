package Stare.Stare.web.dto.posts;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String location;

    public PostUpdateRequestDto(String location){
        this.location = location;
    }
}
