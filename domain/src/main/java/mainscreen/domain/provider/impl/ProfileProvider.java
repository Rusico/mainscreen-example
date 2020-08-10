package mainscreen.domain.provider.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mainscreen.domain.provider.ProviderConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
public class ProfileProvider extends AbstractProvider<ProfileProvider.InputData, ProfileProvider.OutputData> {

    @Override
    protected void write(Set<ProviderConsumer<InputData, OutputData>> consumers) {
        Set<String> regionKids = consumers.stream()
                .flatMap(source -> source.getInputData().getRegionKids().stream())
                .collect(Collectors.toSet());

        // Здесь получаем даннын из профиля
        Map<String,Object> data = new HashMap<>(); // Данные из профиля
        OutputData outputData = new OutputData(data);
        consumers.forEach(source -> source.setOutputData(outputData));
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
