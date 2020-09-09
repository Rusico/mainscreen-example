package mainscreen.domain.section.impl;

import mainscreen.domain.Data;
import mainscreen.domain.provider.Provider;
import mainscreen.domain.provider.ProviderConsumer;
import mainscreen.domain.section.Section;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractSection<T> implements Section<T> {

    @Override
    public Data<T> result(Set<ProviderConsumer> consumers) {
        return Data.<T>builder()
                .data(result(consumers.stream()
                        .collect(Collectors.toMap(ProviderConsumer::getProviderClass, ProviderConsumer::getOutputData))))
                .build();
    }

    protected abstract T result(Map<Class<? extends Provider>, Data<?>> outputData);
}
