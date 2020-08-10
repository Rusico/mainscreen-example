package mainscreen.domain.provider;

import java.util.Set;

@ProviderBean
public interface Provider<InputData, OutputData> {
    /**
     * Провайдер загружает данные из источнника и сохраняет у потребителей
     * @param consumers список потребителей ресурсов провайдера
     */
    void loadData(Set<ProviderConsumer<InputData, OutputData>> consumers);
}
