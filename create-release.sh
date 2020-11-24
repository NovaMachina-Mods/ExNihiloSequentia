getVersion() {
  version_line=$(grep mc_major gradle.properties)
  mc_major=$( echo -n $version_line | tail -c 4)
  mod_version="-$(head -n 1 changelog.md)"
  full_version=$mc_major$mod_version
}

getDescription() {
  description=$(cat changelog.md)
}

getVersion
getDescription
github-release release --user NovaMachina --repo ExNihiloSequentia --tag "$full_version" --name "$full_version" --description "$description"
echo "Created Release $full_version"
github-release upload --user NovaMachina --repo ExNihiloSequentia --tag "$full_version" --name "$full_version.jar" --file build/libs/*.jar
echo "Uploaded $full_version.jar to release $full_version"