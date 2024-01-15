#!/usr/bin/env bash

#export REMOTE_DEBUGGER=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005

java ${REMOTE_DEBUGGER} -jar "$(find -E target -regex '.*/ecohub-.*-SNAPSHOT\.jar$')"
