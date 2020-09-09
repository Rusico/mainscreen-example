package mainscreen.domain.section;

import mainscreen.domain.Data;
import mainscreen.domain.provider.ProviderConsumer;

import java.util.Set;

/**
 * Секция содержит ассоциированных с поставщиками потребителей
 */
@SectionBean
public interface Section<T> {
    Set<ProviderConsumer> createConsumers();
    Data<T> result(Set<ProviderConsumer> consumers);
}
