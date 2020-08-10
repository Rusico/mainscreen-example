package mainscreen.domain.section;

import mainscreen.domain.provider.Provider;
import mainscreen.domain.provider.ProviderConsumer;

import java.util.Set;

@SectionBean
public interface Section<ResultData> {
    Set<ProviderConsumer<?,?>> getConsumers();
    Result<ResultData> result();

    default <T> T getOutputData(Class<? extends Provider<?, ?>> providerType) {
        return (T) getConsumers().stream()
                .filter(c -> c.getProviderClass() == providerType)
                .map(ProviderConsumer::getOutputData);
    }
}
