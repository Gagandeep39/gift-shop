# Steps to Run

- [Steps to Run](#steps-to-run)
  - [Single Stage project with SCM Plugin (Tested)](#single-stage-project-with-scm-plugin-tested)
    - [Set Environment](#set-environment)
    - [Create Tokens/ Secrets](#create-tokens-secrets)
    - [Setup Settings - SonarQube Server](#setup-settings---sonarqube-server)
    - [Setup Settings - SonarQube Installation](#setup-settings---sonarqube-installation)
    - [Setup Settings - Maven](#setup-settings---maven)
    - [Create Project](#create-project)
  - [Single Stage project with least amount of Plugins (Basically without File SCM) (Tested)](#single-stage-project-with-least-amount-of-plugins-basically-without-file-scm-tested)
    - [Set Environment](#set-environment-1)
    - [Create Tokens/ Secrets](#create-tokens-secrets-1)
    - [Setup Settings - SonarQube Server](#setup-settings---sonarqube-server-1)
    - [Setup Settings - SonarQube Installation](#setup-settings---sonarqube-installation-1)
    - [Create Project](#create-project-1)
  - [Single Stage project with SCM Git](#single-stage-project-with-scm-git)
    - [Set Environment](#set-environment-2)
    - [Create Tokens/ Secrets](#create-tokens-secrets-2)
    - [Setup Settings - SonarQube Server](#setup-settings---sonarqube-server-2)
    - [Setup Settings - SonarQube Installation](#setup-settings---sonarqube-installation-2)
    - [Setup Settings - Maven](#setup-settings---maven-1)
    - [Setup Settings - Maven](#setup-settings---maven-2)
    - [Create Project](#create-project-2)

## Single Stage project with SCM Plugin (Tested)

### Set Environment

1. Run Jenkins war file using `java -jar jenkins.war`
   - Command Prompt with Custom Location `"C:\Program Files\Java\jdk1.8.0_212\bin\java" -jar jenkins.war`
2. Install Sonarqube as application or Docker container
3. Set username `admin`, password `123456`
4. Install the following jenkins Plugins `Manage Jenkins -> Manage Plugins -> Available`
   1. File System SCM Plugin (Allows Checking out from file system)
   2. Filesystem Trigger Plug-in (Allows monitoring filesystem changes and triggering build)
   3. SonarQube Scanner for Jenkins (Allows SonarQube Scanner integration)
   4. JaCoCo plugin (Create Code Coverage Report)
5. Make sure your pom.xml has following snippet (**MANDATORY**)

   ```xml
    <plugin>
    	<groupId>org.jacoco</groupId>
    	<artifactId>jacoco-maven-plugin</artifactId>
    	<version>0.8.6</version>
    	<executions>
    		<execution>
    			<goals>
    				<goal>prepare-agent</goal>
    			</goals>
    		</execution>
    		<execution>
    			<id>report</id>
    			<phase>prepare-package</phase>
    			<goals>
    				<goal>report</goal>
    			</goals>
    		</execution>
    	</executions>
    </plugin>

    <plugin>
    	<groupId>org.sonarsource.scanner.maven</groupId>
    	<artifactId>sonar-maven-plugin</artifactId>
    	<version>3.7.0.1746</version>
    </plugin>
   ```

### Create Tokens/ Secrets

1. `Manage Jenkins` -> `Manage Credentials`
2. In `Stores scoped to Jenkins` table, `Domains` column, Click on `global`
3. Add credentials from side bar
4. Select Secet text or credentails from dropdown and complete other fom fields
5. Save

### Setup Settings - SonarQube Server

1. `Manage Jenkins` -> `Configure System`
2. Scroll down to SonarQube Servers
   1. Specify Installation Naame (Any name)
   2. Enter SonarQube URL
   3. Specify Authentication Token (Refer [create token](#create-tokens-secrets))
   4. Save

### Setup Settings - SonarQube Installation

1. `Manage Jenkins` -> `Global tool Configuration`
2. Scroll down to SonarQube Scanner
3. Click on SoarQube scanner Intallations
   1. Give any name eg. `First-Install`
   2. Check Install Automatically
   3. Make sure Top most (Latest version) is select in the dropdown
   4. Save

### Setup Settings - Maven

1. `Manage Jenkins` -> `Global tool Configuration`
2. Scroll down to Maven
3. Click on Maven Intallations
   1. Give any name eg. `First-Install`
   2. Check Install Automatically
   3. Make sure Top most (Latest version) is select in the dropdown
   4. Save (Alternatively give path to already installed maven on PC)

### Create Project

1. Select `New Item`
2. Enter name of Project
3. Select `Free style Project` -> `OK`
4. In `Source Code Management`, select `File System` (Requires File System SCM Plugin)
5. Give file system path to your microservice directly
6. Check `Clear Workspace` to prevent unforseen issues
7. Add `Build Step` -> `Invoke Top Level Maven Target`
8. Select maven installtion (Refer [step](#setup-settings---maven))
9. On goals text fiel type `package`
10. Add `Build Step` -> `Execute Sonar Scanner` (If Sonar Plugin/ Setting Config is not proper, there will be red colored errors)
11. In Analysis properties, enter the following

    ```env
    sonar.projectKey=dummy-project
    sonar.language=java
    sonar.sources=src/main/java
    sonar.sourceEncoding=UTF-8
    sonar.java.binaries=.
    ```

12. Click on Post Build actions
13. Select Record Jacoco converage report
14. Click on Post build step
15. Added JUnit Coverage report
16. Enter the test file path to `target/surefire-reports/**/*.xml`
17. Save
18. Build

## Single Stage project with least amount of Plugins (Basically without File SCM) (Tested)

### Set Environment

1. Run Jenkins war file using `java -jar jenkins.war`
   - Command Prompt with Custom Location `"C:\Program Files\Java\jdk1.8.0_212\bin\java" -jar jenkins.war`
2. Install Sonarqube as application or Docker container
3. Set username `admin`, password `123456`
4. Install the following jenkins Plugins `Manage Jenkins -> Manage Plugins -> Available`
   1. JaCoCo plugin (Create Code Coverage Report)
   2. SonarQube Scanner for Jenkins (Allows SonarQube Scanner integration)
5. Make sure your pom.xml has following snippet (**MANDATORY**)

   ```xml
    <plugin>
    	<groupId>org.jacoco</groupId>
    	<artifactId>jacoco-maven-plugin</artifactId>
    	<version>0.8.6</version>
    	<executions>
    		<execution>
    			<goals>
    				<goal>prepare-agent</goal>
    			</goals>
    		</execution>
    		<execution>
    			<id>report</id>
    			<phase>prepare-package</phase>
    			<goals>
    				<goal>report</goal>
    			</goals>
    		</execution>
    	</executions>
    </plugin>

    <plugin>
    	<groupId>org.sonarsource.scanner.maven</groupId>
    	<artifactId>sonar-maven-plugin</artifactId>
    	<version>3.7.0.1746</version>
    </plugin>
   ```

### Create Tokens/ Secrets

1. `Manage Jenkins` -> `Manage Credentials`
2. In `Stores scoped to Jenkins` table, `Domains` column, Click on `global`
3. Add credentials from side bar
4. Select Secet text or credentails from dropdown and complete other fom fields
5. Save

### Setup Settings - SonarQube Server

1. `Manage Jenkins` -> `Configure System`
2. Scroll down to SonarQube Servers
   1. Specify Installation Naame (Any name)
   2. Enter SonarQube URL
   3. Specify Authentication Token (Refer [create token](#create-tokens-secrets))
   4. Save

### Setup Settings - SonarQube Installation

1. `Manage Jenkins` -> `Global tool Configuration`
2. Scroll down to SonarQube Scanner
3. Click on SoarQube scanner Intallations
   1. Give any name eg. `First-Install`
   2. Check Install Automatically
   3. Make sure Top most (Latest version) is select in the dropdown
   4. Save

### Create Project

1. Select `New Item`
2. Enter name of Project
3. Select `Free style Project` -> `OK`
4. In `Source Code Management`, select `None`
5. Add `Build Step` -> `Execute batch Command`
6. Enter the following in command field

   ```sh
   xcopy /E D:\Workspaces\vscode_workspace\gift-shop\auth-service /i auth-service /Y
   cd auth-service
   ./mvnw package
   ```

7. Add `Build Step` -> `Execute Sonar Scanner` (If Sonar Plugin/ Setting Config is not proper, there will be red colored errors)
8. In Analysis properties, enter the following

   ```env
   sonar.projectKey=dummy-project
   sonar.language=java
   sonar.java.binaries=.
   sonar.sources=src/main/java
   sonar.sourceEncoding=UTF-8
   sonar.projectBaseDir=auth-service
   ```

9. Click on Post Build actions
10. Select Record Jacoco converage report
11. Click on Post build step
12. Added JUnit Coverage report
13. Enter the test file path to `target/surefire-reports/**/*.xml`
14. Save
15. Build

## Single Stage project with SCM Git

### Set Environment

1. Run Jenkins war file using `java -jar jenkins.war`
   - Command Prompt with Custom Location `"C:\Program Files\Java\jdk1.8.0_212\bin\java" -jar jenkins.war`
2. Install Sonarqube as application or Docker container
3. Set username `admin`, password `123456`
4. Install the following jenkins Plugins `Manage Jenkins -> Manage Plugins -> Available`
   1. SonarQube Scanner for Jenkins (Allows SonarQube Scanner integration)
   2. JaCoCo plugin (Create Code Coverage Report)
5. Make sure your pom.xml has following snippet (**MANDATORY**)

   ```xml
    <plugin>
    	<groupId>org.jacoco</groupId>
    	<artifactId>jacoco-maven-plugin</artifactId>
    	<version>0.8.6</version>
    	<executions>
    		<execution>
    			<goals>
    				<goal>prepare-agent</goal>
    			</goals>
    		</execution>
    		<execution>
    			<id>report</id>
    			<phase>prepare-package</phase>
    			<goals>
    				<goal>report</goal>
    			</goals>
    		</execution>
    	</executions>
    </plugin>

    <plugin>
    	<groupId>org.sonarsource.scanner.maven</groupId>
    	<artifactId>sonar-maven-plugin</artifactId>
    	<version>3.7.0.1746</version>
    </plugin>
   ```

### Create Tokens/ Secrets

1. `Manage Jenkins` -> `Manage Credentials`
2. In `Stores scoped to Jenkins` table, `Domains` column, Click on `global`
3. Add credentials from side bar
4. Select Secet text or credentails from dropdown and complete other fom fields
5. Save

### Setup Settings - SonarQube Server

1. `Manage Jenkins` -> `Configure System`
2. Scroll down to SonarQube Servers
   1. Specify Installation Naame (Any name)
   2. Enter SonarQube URL
   3. Specify Authentication Token (Refer [create token](#create-tokens-secrets))
   4. Save

### Setup Settings - SonarQube Installation

1. `Manage Jenkins` -> `Global tool Configuration`
2. Scroll down to SonarQube Scanner
3. Click on SoarQube scanner Intallations
   1. Give any name eg. `First-Install`
   2. Check Install Automatically
   3. Make sure Top most (Latest version) is select in the dropdown
   4. Save

### Setup Settings - Maven

1. `Manage Jenkins` -> `Global tool Configuration`
2. Scroll down to Maven
3. Click on Maven Intallations
   1. Give any name eg. `First-Install`
   2. Check Install Automatically
   3. Make sure Top most (Latest version) is select in the dropdown
   4. Save (Alternatively give path to already installed maven on PC)

### Setup Settings - Maven

1. `Manage Jenkins` -> `Global tool Configuration`
2. Scroll down to Maven
3. Click on Maven Intallations
   1. Give any name eg. `First-Install`
   2. Check Install Automatically
   3. Make sure Top most (Latest version) is select in the dropdown
   4. Save (Alternatively give path to already installed maven on PC)

### Create Project

1. Select `New Item`
2. Enter name of Project
3. Select `Free style Project` -> `OK`
4. In `Source Code Management`, select `Git` (Requires File System SCM Plugin)
5. Give Local/Remote git path
6. Select Brach name/pattern
7. Add `Build Step` -> `Execute windows batch command`
8. Go inside the subdirectory of microservice with `cd` command
9. Add `Build Step` -> `Invoke Top Level Maven Target`
10. Select maven installtion (Refer [step](#setup-settings---maven))
11. On goals text fiel type `-f auth-service/pom.xml package`
12. Add `Build Step` -> `Execute Sonar Scanner` (If Sonar Plugin/ Setting Config is not proper, there will be red colored errors)
13. In Analysis properties, enter the following

    ```env
    sonar.projectKey=dummy-project
    sonar.language=java
    sonar.sources=src/main/java
    sonar.sourceEncoding=UTF-8
    sonar.java.binaries=.
    ```

14. Click on Post Build actions
15. Select Record Jacoco converage report
16. Click on Post build step
17. Added JUnit Coverage report
18. Enter the test file path to `target/surefire-reports/**/*.xml`
19. Save
20. Build
