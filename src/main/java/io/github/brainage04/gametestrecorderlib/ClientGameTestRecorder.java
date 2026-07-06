package io.github.brainage04.gametestrecorderlib;

import net.fabricmc.fabric.api.client.gametest.v1.context.ClientGameTestContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public final class ClientGameTestRecorder {
    private static final int READY_TIMEOUT_TICKS = 200;

    private ClientGameTestRecorder() {
    }

    public static void startRecording(ClientGameTestContext context) {
        Objects.requireNonNull(context, "context");
        context.runOnClient(_ -> ClientGameTestRecordingHud.clear());
        signalReadyToRecord(context);
    }

    public static void signalReadyToRecord(ClientGameTestContext context) {
        Objects.requireNonNull(context, "context");
        signalReadyToRecord(context, GameTestRecorderEnvironment.START_SIGNAL_ENV, GameTestRecorderEnvironment.READY_SIGNAL_ENV);
    }

    public static void signalReadyToRecord(ClientGameTestContext context, String startSignalEnv, String readySignalEnv) {
        Objects.requireNonNull(context, "context");
        Objects.requireNonNull(startSignalEnv, "startSignalEnv");
        Objects.requireNonNull(readySignalEnv, "readySignalEnv");

        String startSignal = System.getenv(startSignalEnv);
        if (startSignal == null || startSignal.isBlank()) {
            return;
        }

        writeSignal(startSignal);

        String readySignal = System.getenv(readySignalEnv);
        if (readySignal == null || readySignal.isBlank()) {
            return;
        }

        context.waitFor(_ -> Files.exists(Path.of(readySignal)), READY_TIMEOUT_TICKS);
    }

    public static void showStep(ClientGameTestContext context, String id, String title, String subtitle) {
        Objects.requireNonNull(context, "context");
        String message = "[CLIENT_GAMETEST_RECORDER] " + clean(id) + " | " + clean(title)
                + (clean(subtitle).isBlank() ? "" : " | " + clean(subtitle));
        System.out.println(message);
        context.runOnClient(_ -> ClientGameTestRecordingHud.showStep(id, title, subtitle));
    }

    public static void log(ClientGameTestContext context, String message) {
        Objects.requireNonNull(context, "context");
        context.runOnClient(_ -> ClientGameTestRecordingHud.log(message));
    }

    private static void writeSignal(String signalPath) {
        Path path = Path.of(signalPath);
        try {
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.writeString(path, Long.toString(System.currentTimeMillis()));
        } catch (IOException exception) {
            throw new AssertionError("Expected to write client GameTest recording signal: " + path, exception);
        }
    }

    private static String clean(String value) {
        return value == null ? "" : value.strip().replace('\n', ' ');
    }
}
