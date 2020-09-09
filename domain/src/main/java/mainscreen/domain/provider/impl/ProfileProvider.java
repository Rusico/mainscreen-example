package mainscreen.domain.provider.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mainscreen.domain.Data;
import mainscreen.domain.provider.Provider;
import mainscreen.domain.provider.ProviderConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
public class ProfileProvider implements Provider {

    @Override
    public void loadData(Set<ProviderConsumer> consumers) {
        Set<String> regionKids = consumers.stream()
                .flatMap(source -> ((InputData)source.getInputData().getData()).getRegionKids().stream())
                .collect(Collectors.toSet());

        // Здесь получаем даннын из профиля
        Map<String,Object> data = new HashMap<>(); // Данные из профиля
        OutputData outputData = new OutputData(data);
        consumers.forEach(source -> source.setOutputData(Data.<OutputData>builder().data(outputData).build()));
    }

    /**
     * Выходные данные (ресурсы)
     */
    @Builder @AllArgsConstructor @Getter
    public static class OutputData {
        private final Map<String,Object> data;
    }

    /**
     * Входные данные для поставщика ресурсов
     */
    @Builder @AllArgsConstructor @Getter
    public static class InputData {
        private final Set<String> regionKids;
    }
}
