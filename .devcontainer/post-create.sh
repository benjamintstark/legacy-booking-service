#!/usr/bin/env bash
set -e

# Claude Code
npm install -g @anthropic-ai/claude-code

# Warm the Maven cache on Java 8 so the first take doesn't open on downloads
mvn -q -DskipTests dependency:go-offline || true

# Baseline tag every take resets to
git rev-parse baseline >/dev/null 2>&1 || git tag baseline

echo "Ready. java -version:"
java -version
