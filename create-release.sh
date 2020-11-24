getVersion() {
  version_line=$(grep mc_major gradle.properties)
  mc_major=$( echo -n $version_line | tail -c 4)
  echo $mc_major
  mod_version="-$(head -n 1 changelog.md)"
  echo $mod_version
  full_version=mc_major$mod_version
  echo $full_version
}

getDescription() {
  description=$(cat changelog.md)
}

getVersion
getDescription
github-release release --user NovaMachina --repo ExNihiloSequentia --tag "$full_version" --name "$full_version" --description "$description"
github-release upload --user NovaMachina --repo ExNihiloSequentia --tag "$full_version" --name "$full_version.jar" --file build/libs/*.jar