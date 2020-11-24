getVersion() {
  version=$(grep mc_major gradle.properties)
  mc_major=$( echo -n $version | tail -c 4)
  echo $mc_major
  mod_version="-$(head -n 1 changelog.md)"
  echo $mod_version
  full_version=$version$mod_version
  echo $full_version
}

getVersion
github-release upload --user NovaMachina --repo ExNihiloSequentia --tag $full_version --name $full_version