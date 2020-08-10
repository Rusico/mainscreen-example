package mainscreen.domain.provider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

/**
 * Потребитель данных провайдера, по совместительству являеься
 * поставщиком данных для обращения в ФП провайдера
 * @param <InputData>
 * @param <OutputData>
 */
@AllArgsConstructor
@Builder
@Getter
public class ProviderConsumer<InputData, OutputData> {

    private final Class<? extends Provider<?,?>> providerClass;
    private final InputData inputData;
    @Setter
    private OutputData outputData;

    public boolean isProviderConsumer(Provider<?,?> provider) {
        return Optional.ofNullable(provider)
                .map(pr -> pr.getClass() == providerClass)
                .orElse(false);

    }
}
