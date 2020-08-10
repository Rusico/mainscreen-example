package mainscreen.domain.section.impl;

import mainscreen.domain.provider.ProviderConsumer;
import mainscreen.domain.provider.impl.ProfileProvider;
import mainscreen.domain.section.Result;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SecurityAccount extends AbstractSection<Map<String,Object>> {
    public SecurityAccount(Integer order) {
        super(
                order,
                ProviderConsumer.builder()
                        .providerClass(ProfileProvider.class)
                        .inputData(ProfileProvider.InputData.builder()
                                .regionKids(Collections.singleton("PERSON"))
                                .build())
                        .build());
    }

    @Override
    public Result<Map<String,Object>> result() {
        ProfileProvider.OutputData profileData = getOutputData(ProfileProvider.class);

        // Формируем ответ с данными от поставщика
        Map<String, Object> data = new HashMap<>(profileData.getData());

        return () -> data;
    }
}
