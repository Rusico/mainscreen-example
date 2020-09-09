package mainscreen.domain.provider.impl;

import mainscreen.domain.Data;
import mainscreen.domain.provider.Provider;
import mainscreen.domain.provider.ProviderConsumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EribProvider implements Provider {

    @Override
    public void loadData(Set<ProviderConsumer> consumers) {
        // Здесь получаем даннын из профиля
        Map<String,Object> data = new HashMap<>(); // Данные из профиля
        OutputData outputData = new OutputData(data);
        consumers.forEach(consumer -> consumer.setOutputData(Data.builder().data(outputData).build()));
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
