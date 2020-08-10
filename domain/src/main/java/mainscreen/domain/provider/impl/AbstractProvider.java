package mainscreen.domain.provider.impl;

import mainscreen.domain.provider.Provider;
import mainscreen.domain.provider.ProviderConsumer;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractProvider<InputData, OutputData> implements Provider<InputData, OutputData> {

    private Set<ProviderConsumer<InputData, OutputData>>
            filterConsumers(Set<ProviderConsumer<InputData, OutputData>> consumers) {

        return consumers.stream()
                .filter(consumer -> consumer.isProviderConsumer(this))
                .collect(Collectors.toSet());
    }

    @Override
    public void loadData(Set<ProviderConsumer<InputData, OutputData>> consumers) {
        write(filterConsumers(consumers));
    }

    protected abstract void write(Set<ProviderConsumer<InputData, OutputData>> sources);
}
