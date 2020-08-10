package mainscreen.service;

import mainscreen.domain.provider.Provider;
import mainscreen.domain.provider.ProviderConsumer;
import mainscreen.domain.section.Result;
import mainscreen.domain.section.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Aggregator {
    private final Set<Provider> providers;

    @Autowired
    public Aggregator(Set<Provider> providers) {
        this.providers = providers;
    }

    private Set<ProviderConsumer<?,?>> getConsumers(Section<?>... sections) {
        return Stream.of(sections)
                .flatMap(section -> section.getConsumers().stream())
                .collect(Collectors.toSet());
    }

    public Set<Result<?>> aggregate(Section<?>... sections) {

        // Получить всех потребителей
        Set<ProviderConsumer<?,?>> consumers = getConsumers(sections);

        // Загрузить данные от поставщиков потребителям
        providers.forEach(provider -> provider.loadData(consumers));

        // Формируем результат по каждой секции
        return Stream.of(sections).parallel()
                .sorted()
                .map(Section::result)
                .collect(Collectors.toSet());
    }
}
