package io.github.brainage04.fabricmoddingconventions.gradle.recorder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientGameTestRecorderPluginTest {
    @Test
    void rootProjectUsesUnqualifiedRunTaskName() {
        assertEquals("runClientGameTest", ClientGameTestRecorderPlugin.clientGameTestTaskPath(":"));
    }

    @Test
    void subprojectUsesQualifiedRunTaskPath() {
        assertEquals(":fabric:runClientGameTest", ClientGameTestRecorderPlugin.clientGameTestTaskPath(":fabric"));
    }
}
