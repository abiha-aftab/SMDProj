# Builds and tests the app in a clean Linux environment.
# The Android SDK build tools are x86-64 only: on Apple silicon add --platform linux/amd64.
FROM eclipse-temurin:21-jdk

ARG CMDLINE_TOOLS=13114758
ENV ANDROID_HOME=/opt/android-sdk
ENV PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin

RUN apt-get update \
 && apt-get install -y --no-install-recommends curl unzip \
 && rm -rf /var/lib/apt/lists/*

RUN curl -fsSL -o /tmp/tools.zip \
      https://dl.google.com/android/repository/commandlinetools-linux-${CMDLINE_TOOLS}_latest.zip \
 && mkdir -p $ANDROID_HOME/cmdline-tools \
 && unzip -q /tmp/tools.zip -d $ANDROID_HOME/cmdline-tools \
 && mv $ANDROID_HOME/cmdline-tools/cmdline-tools $ANDROID_HOME/cmdline-tools/latest \
 && rm /tmp/tools.zip \
 && yes | sdkmanager --licenses > /dev/null \
 && sdkmanager "platforms;android-36" "build-tools;36.0.0" > /dev/null

WORKDIR /app
COPY . .
# The real Firebase config is excluded by .dockerignore; build against the placeholder.
RUN cp app/google-services.json.example app/google-services.json \
 && ./gradlew --no-daemon testDebugUnitTest assembleDebug

CMD ["sh", "-c", "mkdir -p /out && cp app/build/outputs/apk/debug/app-debug.apk /out/"]
