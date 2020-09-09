package mainscreen.domain.provider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mainscreen.domain.Data;
import mainscreen.domain.section.Section;

import java.util.Optional;

/**
 * Потребитель данных провайдера, по совместительству являеься
 * поставщиком данных для обращения в ФП провайдера
 */
@AllArgsConstructor
@Builder
@Getter
public class ProviderConsumer {

    private final Class<? extends Provider> providerClass;
    private final Data<?> inputData;
    @Setter
    private Data<?> outputData;
}
