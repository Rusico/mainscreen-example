package mainscreen.service;

import mainscreen.domain.Data;
import mainscreen.domain.provider.Provider;
import mainscreen.domain.provider.ProviderConsumer;
import mainscreen.domain.section.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Агрегатор-сутенёр) - сводит секции и необходимых для них поставщиков
 */
@Component
public class Aggregator {
    private final Set<Provider> providers;
    private final Map<Class<? extends Provider>, Provider> providersMap;

    @Autowired
    public Aggregator(Set<Provider> providers, Set<Section<?>> sections) {
        this.providers = providers;
        providersMap = providersMap();
    }

    private Map<Class<? extends Provider>, Provider> providersMap() {
        return providers.stream()
                .collect(Collectors.toMap(p -> (Class<? extends Provider>)p.getClass(), p -> p));
    }

    public Set<Data<?>> aggregate(Section<?>... sections) {

        // Формируем мапы для удобства и скорости
        Map<Class<? extends Provider>, Set<ProviderConsumer>> providerConsumers = new ConcurrentHashMap<>();
        Map<Class<? extends Section>, Set<ProviderConsumer>> sectionConsumers = new ConcurrentHashMap<>();

        Stream.of(sections)
                .forEach(section ->
                        sectionConsumers.put(section.getClass(), section.createConsumers().stream()
                                .peek(consumer -> {
                                    if (!providerConsumers.containsKey(consumer.getProviderClass())) {
                                        providerConsumers.put(consumer.getProviderClass(), new HashSet<>());
                                    }
                                    providerConsumers.get(consumer.getProviderClass()).add(consumer);
                                }).collect(Collectors.toSet())));

        // Загрузить данные от поставщиков потребителям
        providers.forEach(provider -> provider.loadData(providerConsumers.get(provider.getClass())));

        // Формируем результат по секциям
        Set<Data<?>> result = new CopyOnWriteArraySet<>();

        CompletableFuture.allOf(Stream.of(sections)
                .map(section -> CompletableFuture
                        .runAsync(() -> result.add(section.result(sectionConsumers.get(section.getClass())))))
                .toArray(CompletableFuture[]::new))
                .join();

        return result;
    }
}
