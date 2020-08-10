package mainscreen.domain.section;

import mainscreen.domain.provider.Provider;
import mainscreen.domain.provider.ProviderConsumer;

import java.util.Set;

/**
 * Секция содержит ассоциированных с поставщиками потребителей
 * @param <ResultData> тип возвращаемых данных из секции после обработки данных от поставщиков
 */
@SectionBean
public interface Section<ResultData> {
    Set<ProviderConsumer<?,?>> getConsumers();
    Result<ResultData> result();

    /**
     * Метод возвращает данные, полученные от поставщика
     * @param providerType класс поставщика
     * @param <T> тип возвращаемых данных
     * @return данные от поставщика
     */
    default <T> T getOutputData(Class<? extends Provider<?, ?>> providerType) {
        return (T) getConsumers().stream()
                .filter(c -> c.getProviderClass() == providerType)
                .map(ProviderConsumer::getOutputData);
    }
}
