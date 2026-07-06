package io.github.brainage04.gametestrecorderlib;

import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientGameTestRecorderConventionTest {
    @TempDir
    Path projectDir;

    @Test
    void conventionRegistersRecorderAndPreparationTasks() throws IOException {
        writeFixtureBuild();

        var result = GradleRunner.create()
                .withProjectDir(projectDir.toFile())
                .withArguments("tasks", "--all")
                .build();

        assertEquals(TaskOutcome.SUCCESS, result.task(":tasks").getOutcome());
        assertTrue(result.getOutput().contains("recordClientGameTest - Runs the Fabric client GameTest task"));
        assertTrue(result.getOutput().contains("prepareClientGameTestRun"));
    }

    private void writeFixtureBuild() throws IOException {
        Files.writeString(projectDir.resolve("settings.gradle"), "rootProject.name = 'recorder-convention-fixture'\n");
        var conventionPath = Path.of("gradle/client-gametest-recorder.gradle").toAbsolutePath().normalize().toString().replace("\\", "\\\\");
        Files.writeString(projectDir.resolve("build.gradle"), """
                plugins {
                    id 'base'
                }

                apply from: '%s'
                """.formatted(conventionPath));
    }
}
