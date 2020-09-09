package mainscreen.domain.section.impl;

import mainscreen.domain.Data;
import mainscreen.domain.provider.Provider;
import mainscreen.domain.provider.ProviderConsumer;
import mainscreen.domain.provider.impl.EribProvider;
import mainscreen.domain.provider.impl.ProfileProvider;
import mainscreen.domain.section.Section;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DepoAccounts extends AbstractSection<Map<String,Object>> {

    @Override
    public Set<ProviderConsumer> createConsumers() {
        return Stream.of(ProviderConsumer.builder()
                        .providerClass(ProfileProvider.class)
                        .inputData(Data.builder()
                                .data(ProfileProvider.InputData.builder()
                                        .regionKids(Collections.singleton("PERSON"))
                                        .build())
                                .build())
                        .build(),
                ProviderConsumer.builder()
                        .providerClass(EribProvider.class)
                        .inputData(Data.builder()
                                .data(EribProvider.InputData.builder()
                                        .build())
                                .build())
                        .build())
                .collect(Collectors.toSet());
    }

    @Override
    protected Map<String, Object> result(Map<Class<? extends Provider>, Data<?>> outputData) {
        // Формируем результат с данными от поставщика
        Map<String,Object> data = new HashMap<>();

        if (outputData.containsKey(ProfileProvider.class) && outputData.containsKey(EribProvider.class)) {
            ProfileProvider.OutputData profileData = (ProfileProvider.OutputData) outputData.get(ProfileProvider.class).getData();
            EribProvider.OutputData eribData = (EribProvider.OutputData) outputData.get(EribProvider.class).getData();

            // Здесь вызываем всякие мапперы и вертим костыли

            data.putAll(profileData.getData());
            data.putAll(eribData.getData());
        }

        return data;
    }
}
