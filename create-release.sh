getVersion() {
  version=$(grep mc_major gradle.properties)
  mc_major=$( echo -n $version | tail -c 5)
  echo $mc_major
  version=${mc_major//[$'\t\r\n']}
  mod_version="-$(head -n 1 changelog.md)"
  full_version=$version$mod_version
}

getVersion
github-release upload --user NovaMachina --repo ExNihiloSequentia --tag $full_version --name $full_version