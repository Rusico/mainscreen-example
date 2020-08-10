package mainscreen.domain.provider;

import java.util.Set;

/**
 * Поставщик ресурсов
 * @param <InputData> - данные для чтения из ФП
 * @param <OutputData> - данные из ФП
 */
@ProviderBean
public interface Provider<InputData, OutputData> {
    /**
     * Провайдер загружает данные из источнника и сохраняет у потребителей
     * @param consumers список потребителей ресурсов провайдера
     */
    void loadData(Set<ProviderConsumer<InputData, OutputData>> consumers);
}
