# FabricModdingConventions todo

No active feature work is queued. New work must satisfy one of the concrete triggers below.

## Completed 2.3.0 ownership rollout

- [x] Published and tagged `v2.3.0`, including the runtime artifact and all six component plugin marker/implementation pairs on Maven Central.
- [x] Moved canonical project identity, dependency repositories, Java conventions, Loom source layout, GameTest defaults, and runtime-helper wiring into the owning component plugins.
- [x] Added reusable build and client-GameTest workflows, reduced the template/Fortnite/Twitch callers to project-specific configuration, and removed their duplicated workflow orchestration.
- [x] Verified the conventions suite, Central-only resolution, all generated template side variants, and build/client/production GameTest jobs for all three consumers.

## Trigger-gated work

These are not active tasks:

- Add Architectury or other multi-loader conventions only when a real multi-loader mod can define and verify the contract.
- Extract a generalized recorder session/process abstraction only when a second recorder backend or production-client recording creates a real second execution path.
- Add more Loom production-run options only when an active consumer needs one.
- Keep access-widener paths and split-environment source sets in Loom's native DSL unless consumers develop shared behavior or validation beyond their current one-line declarations.

## Guardrails, not tasks

- Prefer a module-filtered local sibling Maven repository during active multi-repository development, then Maven Central for released artifacts.
- Do not add GitHub Packages or GitHub release/Ivy repositories as dependency fallbacks.
- Keep repository-specific dependencies in their consumer projects.
- Keep the owned Baritone fork required-local until upstream publishes a usable Fabric artifact or redistribution is explicitly approved.
