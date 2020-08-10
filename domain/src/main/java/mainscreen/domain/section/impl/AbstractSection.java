package mainscreen.domain.section.impl;

import lombok.Getter;
import mainscreen.domain.provider.ProviderConsumer;
import mainscreen.domain.section.Section;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Getter
public abstract class AbstractSection<ResultData> implements Section<ResultData>, Comparator<AbstractSection> {
    private final Set<ProviderConsumer<?,?>> consumers;
    protected final Integer order;

    protected AbstractSection(Integer order, ProviderConsumer<?, ?>... consumers) {
        this.order = order;
        this.consumers = new HashSet<>(Arrays.asList(consumers));
    }

    @Override
    public int compare(AbstractSection o1, AbstractSection o2) {
        return o1.order.compareTo(o2.order);
    }
}
