package mainscreen.domain.section.impl;

import mainscreen.domain.provider.ProviderConsumer;
import mainscreen.domain.provider.impl.EribProvider;
import mainscreen.domain.provider.impl.ProfileProvider;
import mainscreen.domain.section.Result;
import mainscreen.domain.section.Section;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DepoAccounts extends AbstractSection<Map<String,Object>> {
    public DepoAccounts(Integer order) {
        super(
                order,
                ProviderConsumer.builder()
                        .providerClass(ProfileProvider.class)
                        .inputData(ProfileProvider.InputData.builder()
                                .regionKids(Collections.singleton("PERSON"))
                                .build())
                        .build(),
                ProviderConsumer.builder()
                        .providerClass(EribProvider.class)
                        .inputData(EribProvider.InputData.builder()
                                .build())
                        .build()
        );
    }

    @Override
    public Result<Map<String, Object>> result() {
        ProfileProvider.OutputData profileData = getOutputData(ProfileProvider.class);
        EribProvider.OutputData eribData = getOutputData(EribProvider.class);

        // Формируем результат с данными от поставщика
        Map<String,Object> data = new HashMap<>();
        data.putAll(profileData.getData());
        data.putAll(eribData.getData());

        return () -> data;
    }
}
