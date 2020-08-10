package mainscreen.api.v1.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor @Builder @Getter
public class SectionDto<T> implements BaseOutputDto {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
