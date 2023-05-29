package Stare.Stare.web.dto.posts;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequestDto {

    private String location;

    public PostCreateRequestDto(String location){
        this.location = location;
    }
}
