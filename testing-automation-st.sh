#!/bin/bash

nameProject='experian-srv-concentre'
version=$(xmllint --xpath '/*[local-name()="project"]/*[local-name()="version"]/text()' pom.xml |sed -e 's/-SNAPSHOT//g')

echo ">>>>> Isso aí!!! Vamos clonar e buildar o projeto de testes Brow \o/"
echo ">>>>> Jutsu Clone das Sombras o+ "
git clone ssh://git@bitbucketglobal.experian.local/tbq/${nameProject}-test.git
cd ${nameProject}-test
git checkout develop
git pull origin develop

echo ">>>>> Buildando em 3...2...1 "
/opt/infratransac/jenkins/tools/hudson.tasks.Maven_MavenInstallation/maven-3.2.5/bin/mvn clean compile

#typeProject = (selenium | api)
typeProject='selenium'

echo ">>>>> Agora é hora de executar os testes. Que a força esteja com você!!"
/opt/infratransac/jenkins/tools/hudson.tasks.Maven_MavenInstallation/maven-3.2.5/bin/mvn test -Dtest=Runner.java

echo ">>>>> Acabaram os testes ThunderCats, mas precisamos ter a Visão Além do Alcance com os relatórios gerados!!"

echo ">>>>> Gerando report da versao ${version} "

reportDir="cucumber-html-reports/Run/cucumber-html-reports/"
jsonDir="cucumber-html-reports/Run"
resultsDir="/opt/infratransac/qs-reports/cucumber/${typeProject}/${nameProject}-test"
if ! [[ -d "${resultsDir}" ]]; then
    mkdir -p "${resultsDir}"
fi
reportDate=$(date "+%d-%b-%H:%M:%S")
finalDir="${resultsDir}/${version}-${reportDate}"
finalPage="http://spobrjenkins:9094/qs-reports/cucumber/${typeProject}/${nameProject}-test/${version}-${reportDate}/index.html"


cp $reportDir/overview-features.html $reportDir/index.html
cp $jsonDir/Run.json $reportDir/
cp -r $reportDir/. $finalDir

echo ">>>>> Guerreiros... Relatório disponibilizado: ${finalPage}"
echo ">>>>> Processo finalizado com Sucesso \o/. Espero que tenham atingido o objetivo oO"