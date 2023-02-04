package esm.dto.request;

import java.util.ArrayList;
import java.util.List;

public class FindByTagRequestDto {
    List<String> tagNames=new ArrayList<>();

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }
}
