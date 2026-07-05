# Current Temperature

A small Java application that shows the current temperature for your location
in a terminal user interface (TUI). It auto-detects your location based on your
network connection and fetches the live weather in Fahrenheit.

[![Java CI](https://github.com/Project516/CurrentTemperature/actions/workflows/gradle.yml/badge.svg)](https://github.com/Project516/CurrentTemperature/actions/workflows/gradle.yml)
[![Build and upload release assets](https://github.com/Project516/CurrentTemperature/actions/workflows/release.yml/badge.svg)](https://github.com/Project516/CurrentTemperature/actions/workflows/release.yml)

## How it works

When you launch the app it:

1. Geolocates you using [ip-api.com](http://ip-api.com/) to determine your
   latitude, longitude, and city.
2. Queries the current temperature from the
   [Open-Meteo API](https://open-meteo.com/) (in Fahrenheit).
3. Displays a Lanterna terminal window showing
   `It is currently <temp>°F in <city>.` with a button to exit.

No API keys or configuration are required.

## Requirements

- **Java 25** or newer. A recent build of [Eclipse Temurin](https://adoptium.net/temurin)
  or a JDK 25 from [SDKMAN](https://sdkman.io) is recommended.
- Make sure the `java` executable is on your system `PATH`.

## Quick start (release build)

1. Download the latest `app-all.jar` from the
   [Releases page](https://github.com/Project516/CurrentTemperature/releases).
2. Run the jar:

   ```sh
   java -jar app-all.jar
   ```

## Build from source

1. Clone the repository:

   ```sh
   git clone https://github.com/Project516/CurrentTemperature.git
   cd CurrentTemperature
   ```

2. Build with the bundled Gradle wrapper (no local Gradle install needed):

   ```sh
   ./gradlew build
   ```

3. Run the shadowed jar produced under `app/build/libs/`:

   ```sh
   java -jar app/build/libs/app-all.jar
   ```

A convenience `test.sh` script is also provided that builds and immediately runs
the jar:

```sh
./test.sh
```

## Project structure

```
settings.gradle            # Gradle settings (root project: CurrentTemperature)
gradle/libs.versions.toml  # Version catalog for test/UI dependencies
app/
  build.gradle             # Application, Shadow, Spotless plugin configuration
  src/main/java/dev/project516/CurrentTemperature/
    Main.java               # Entry point
    Location.java           # Network geolocation via ip-api.com
    Temperature.java        # Current temperature fetch via Open-Meteo
    TUI.java                # Lanterna terminal UI window
```

## Continuous integration

- `.github/workflows/gradle.yml` &mdash; builds the project and submits the
  dependency graph on every push and pull request to `master`.
- `.github/workflows/release.yml` &mdash; builds the `app-all.jar` and uploads
  it as an asset whenever a GitHub release is created.
- `.github/workflows/javadoc.yml` &mdash; generates Javadoc and deploys it to
  GitHub Pages.
- `.github/dependabot.yml` &mdash; weekly checks for Gradle and GitHub Actions
  dependency updates.

## Documentation

The online Javadoc published from CI is available at
<https://project516.dev/CurrentTemperature>.

## License

This project is licensed under the [MIT License](LICENSE).
