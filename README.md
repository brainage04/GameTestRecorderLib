# GameTestRecorderLib

Reusable Fabric client GameTest recording helpers for Minecraft mods.

The library provides:

- a client GameTest recording handshake between the Gradle recorder task and the running test client;
- a lightweight recording HUD for scenario step/log output;
- defensive helpers for launching and joining an in-process dedicated server from Fabric client GameTests;
- a shared Gradle convention for deterministic client GameTest run setup;
- a reusable `recordClientGameTest` Gradle task using `GTR_*` environment variables.

The recorder task requires `ffmpeg`, `ffprobe`, Xvfb/`xdpyinfo`, and PipeWire tools (`pw-cli`, `wpctl`) on the recording host.

## Dependency

```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation "io.github.brainage04:gametestrecorderlib:<version>"
}
```

For local development with a sibling checkout, publish the library to its local build repository first:

```shell
./gradlew --no-daemon publishMavenJavaPublicationToLocalRepository
```

Consumers in this workspace are configured to prefer `../GameTestRecorderLib/build/local-repo` when present and otherwise resolve the Maven Central artifact.

## Recording task

New consumers should apply `gradle/client-gametest-recorder.gradle`, then run the shared task directly:

```shell
GTR_RECORDING_PROFILE=smoke ./gradlew --no-daemon recordClientGameTest
```

The Java-side helpers live under `io.github.brainage04.gametestrecorderlib`.
