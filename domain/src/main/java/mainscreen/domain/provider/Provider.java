package mainscreen.domain.provider;

import mainscreen.domain.Data;

import java.util.Set;

/**
 * Поставщик ресурсов
 */
@ProviderBean
public interface Provider {
    /**
     * Провайдер загружает данные из источнника и сохраняет у потребителей
     * @param consumers список потребителей ресурсов провайдера
     */
    void loadData(Set<ProviderConsumer> consumers);
}
