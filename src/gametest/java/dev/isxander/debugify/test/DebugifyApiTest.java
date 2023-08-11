package dev.isxander.debugify.test;

import dev.isxander.debugify.api.DebugifyApi;

import java.util.Map;
import java.util.Set;

public class DebugifyApiTest implements DebugifyApi {
    @Override
    public String[] getDisabledFixes() {
        return new String[]{ "MC-577" };
    }

    @Override
    public Map<String, Set<String>> getProvidedDisabledFixes() {
        return Map.of("yet_another_config_lib_v3", Set.of("MC-577"));
    }
}
