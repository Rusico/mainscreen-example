package mainscreen.domain.provider.impl;

import mainscreen.domain.provider.ProviderConsumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EribProvider extends AbstractProvider<EribProvider.InputData, EribProvider.OutputData> {
    @Override
    protected void write(Set<ProviderConsumer<InputData, OutputData>> consumers) {
        // Здесь получаем даннын из профиля
        Map<String,Object> data = new HashMap<>(); // Данные из профиля
        OutputData outputData = new OutputData(data);
        consumers.forEach(consumer -> consumer.setOutputData(outputData));
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
    }
}
