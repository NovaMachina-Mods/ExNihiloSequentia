#!/bin/bash

getVersion() {
  readarray -d = -t mc_release<<< "$(grep minecraft_release gradle.properties)"
  readarray -d = -t major<<< "$(grep version_major gradle.properties)"
  readarray -d = -t api_major<<< "$(grep version_api_major gradle.properties)"
  readarray -d = -t api_minor<<< "$(grep version_api_minor gradle.properties)"
  readarray -d = -t patch<<< "$(grep version_patch gradle.properties)"
  full_version=$(echo ${mc_release[1]})-$(echo ${major[1]}).$(echo ${api_major[1]}).$(echo ${api_minor[1]}).$(echo ${patch[1]})
}

getRepo() {
  readarray -d = -t git_repo<<< "$(grep github_repo gradle.properties)"
  repo=$(echo ${git_repo[1]})
}

getVersion
getRepo
github-release release --user NovaMachina-Mods --repo "$repo" --tag "$full_version" --name "$full_version"
echo "Created Release $full_version"
github-release upload --user NovaMachina-Mods --repo "$repo" --tag "$full_version" --name "$full_version.jar" --file build/libs/*.jar
echo "Uploaded $full_version.jar to release $full_version"